package engine

import engine.primitives.microDom

fun testScene(): Scene {
    val weirdBorders = Borders(
            width = BoxSizes(top = 2, right = 3, bottom = 3, left = 2),
            color = BoxColors(top = 0xAAAAAA.color, left = 0xAAAAAA.color, bottom = 0x444444.color, right = 0x444444.color)
    )

    val myStyle = style { width = 100; height = 100; border = weirdBorders }

    return microDom {
        row {
            style { padding = 50.all; margin = 50.all }
            box { style = myStyle }
            box {
                style = myStyle
                box {
                    style {
                        width = 50; height = 50
                        margin = 10.top and 30.left
                        backgroundColor = 0x00FF00.color
                        borders(2, 0x008000.color)
                    }
                }
            }
            column {
                style { padding = 30.all }
                -"Hello world!"
                text {
                    -"Other text"
                    style { color = 0x0000FF.color; margin = 30.left }
                }
                text("Yet another") { style { color = 0xFF0000.color } }
            }
        }
    }
}
