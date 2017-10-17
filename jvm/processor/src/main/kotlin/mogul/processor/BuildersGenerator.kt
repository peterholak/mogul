package mogul.processor

import com.sun.tools.javac.code.Symbol
import java.io.File
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.ElementKind
import javax.lang.model.element.TypeElement
import javax.tools.Diagnostic.Kind.ERROR

@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.CLASS)
annotation class GenerateBuilders(
        val observer: Boolean = false
)

@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes("mogul.processor.GenerateBuilders")
@SupportedOptions(BuildersGenerator.KAPT_KOTLIN_GENERATED_OPTION_NAME)
class BuildersGenerator : AbstractProcessor() {

    companion object {
        const val KAPT_KOTLIN_GENERATED_OPTION_NAME = "kapt.kotlin.generated"
    }

    override fun process(annotations: MutableSet<out TypeElement>, roundEnv: RoundEnvironment): Boolean {
        val annotatedElements = roundEnv.getElementsAnnotatedWith(GenerateBuilders::class.java)
        if (annotatedElements.isEmpty()) return false

        val kaptKotlinGeneratedDir = processingEnv.options[KAPT_KOTLIN_GENERATED_OPTION_NAME] ?: run {
            processingEnv.messager.printMessage(ERROR, "Can't find the target directory for generated Kotlin files.")
            return false
        }

        val buildersCode = annotatedElements.joinToString("\n\n") {
            generateBuilder(it as Symbol.ClassSymbol)
        }
        val imports = "import mogul.react.Element\nimport mogul.react.ElementType\nimport mogul.react.GuiBuilder"

        File(kaptKotlinGeneratedDir, "GeneratedBuilders.kt").apply {
            parentFile.mkdirs()
            writeText("@file:Suppress(\"UNUSED_ANONYMOUS_PARAMETER\")\n\npackage mogul.generated\n\n$imports\n\n$buildersCode")
        }

        return false
    }

    fun generateBuilder(cls: Symbol.ClassSymbol): String {
        val propsType = cls.superclass.typeArguments.first().asElement()
        val propFields = propsType.members().elements.filter {
            it.getKind() == ElementKind.FIELD
        }.reversed()

        val constructor = cls.members().elements.filter {
            it.isConstructor
        }.single() as Symbol.MethodSymbol
        val constructorParams = constructor.params

        val generateAnnotation = cls.getAnnotation(GenerateBuilders::class.java)

        val builderName = cls.simpleName.toString().decapitalize()
        val typeName = builderName + "Type"
        val constructorCall = constructorParams.joinToString(", ") { "container.get(\"${it.type.asElement().simpleName}\")" }
        val componentConstruction = "${cls.qualifiedName}($constructorCall)"
        val outerConstruction = if (generateAnnotation.observer) {
            "mogul.kobx.ObserverComponent(container.get(\"KobX\"), $componentConstruction)"
        }else{
            componentConstruction
        }
        val typeDeclarationCode = "val $typeName = ElementType(\"${cls.simpleName}\", { container -> $outerConstruction })"
        val propsCode = if (propsType.type.toString() == "kotlin.Unit") {
            "Unit"
        }else{
            "${propsType.qualifiedName}(${propFields.joinToString(", ") { it.name }})"
        }
        val builderArgs = if (propsType.type.toString() == "kotlin.Unit") {
            ""
        }else{
            propFields.joinToString(", ") { "${it.name}: ${fixKotlinType(it)}" }
        }
        val builderCode = "fun GuiBuilder.$builderName($builderArgs) {\n    children.add(Element($typeName, $propsCode))\n}"
        return "$typeDeclarationCode\n$builderCode"
    }

    fun fixKotlinType(symbol: Symbol): String {
        val isNullable = symbol.annotationMirrors.any {
            it.type.toString() == "org.jetbrains.annotations.Nullable"
        }
        val javaType = symbol.type.toString()

        // Later this could extract the actual defaults directly from the source code, but for now, this will suffice.
        val default = when {
            isNullable -> " = null"
            javaType.equals("mogul.microdom.Style") -> " = mogul.microdom.Style()"
            else -> ""
        }

        return when {
            javaType.equals("java.lang.String") -> "String"
            javaType.startsWith("kotlin.jvm.functions.Function") -> javaType.substringAfter("kotlin.jvm.functions.")
            else -> javaType
        } + (if (isNullable) "?" else "") + default
    }
}