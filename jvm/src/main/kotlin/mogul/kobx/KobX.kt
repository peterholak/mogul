package mogul.kobx

import kotlin.reflect.KMutableProperty
import kotlin.reflect.KProperty

typealias Reaction = () -> Unit
typealias ReactionList = MutableList<Reaction>
typealias PropertyList = MutableList<KMutableProperty<*>>

class KobX {
    var trackingReaction: Reaction? = null
    val properties = mutableMapOf<KMutableProperty<*>, ReactionList>()
    val reactions = mutableMapOf<Reaction, PropertyList>()

    fun markRead(property: KMutableProperty<*>) {
        val reaction = trackingReaction ?: return
        properties
            .getOrPut(property) { mutableListOf() }
            .takeUnless { it.contains(reaction) }
            ?.add(reaction)
        reactions
            .getOrPut(reaction) { mutableListOf() }
            .takeUnless { it.contains(property) }
            ?.add(property)
    }

    fun autorun(code: Reaction): Reaction {
        trackingReaction = code
        code()
        trackingReaction = null
        return code
    }

    fun reaction(trackCode: Reaction, reactionCode: Reaction): Reaction {
        trackingReaction = reactionCode
        trackCode()
        trackingReaction = null
        return reactionCode
    }

    fun dispose(reaction: Reaction) {
        val tracking = reactions[reaction]
        reactions.remove(reaction)
        tracking?.forEach {
            properties[it]?.remove(reaction)
        }
    }

    fun runReactions(property: KProperty<*>) {
        properties[property]?.forEach { it.invoke() }
    }

    fun <T> observable(value: T) = Observable(this, value)
}
