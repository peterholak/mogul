package engine


fun testScene(): Scene {
    val myStyle = style { width = 100; height = 100 }
    return microDom {
        row {
            style {
                padding = BoxSizes(50)
                margin = BoxSizes(50)
            }
            box { style = myStyle }
            box {
                style = myStyle
                box {
                    style {
                        width = 50; height = 50
                        margin = BoxSizes(top = 10, left = 30)
                        backgroundColor = 0x00FF00.color
                        borderColor = Color.white
                    }
                }
            }
            -"Hello world!"
            text {
                -"Other text"
                style { color = 0x00FFFF.color }
            }
            text("Yet another") { style { color = 0x00FF00.color } }
        }
    }
}
