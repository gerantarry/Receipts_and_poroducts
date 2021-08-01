package app

import javafx.beans.property.SimpleStringProperty
import tornadofx.*


class MyApp : App(MyView::class)

class MyView : View() {
    override val root = borderpane {
        top<TopView>()
    }
}

class TopView : View() {
    val controller: MyController by inject()
    val input = SimpleStringProperty()

    override val root = form {
        fieldset {
            field("Input") {
                textfield(input)
            }

            button("Commit") {
                action {
                    controller.writeToDb(input.value)
                    input.value = ""
                }
            }
            button("Добавить продукт") {
                action {
                    try {
                        controller.addToList(input.value)
                    } catch (e: NullPointerException) {
                        println("Ошибка, null не допустим при вводе!")
                    }
                        input.value = null
                }
            }
            button("Open window") {
                action {
                    openInternalWindow<MyFragment>()
                }
            }
        }
    }
}

