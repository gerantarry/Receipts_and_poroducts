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
    private val controller: MyController by inject()
    private val input = SimpleStringProperty()

    override val root = form {
        fieldset {
            field("Input") {
                textfield(input)
            }
            button("Добавить продукт") {
                action {
                    try {
                        controller.addProductToList(input.value)
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
            button("Сохранить список продуктов") {
                action {
                    controller.saveProductListAsJson()
                }
            }
            button("Загрузить список продуктов") {
                action {
                    controller.loadProductListFromJson()
                }
            }
            button("Удалить продукт") {
                action {
                    try {
                        controller.removeProductFromList(input.value)
                    } catch (e: NullPointerException) {
                        println("Ошибка, null не допустим при вводе!")
                    }
                    input.value = null
                }
            }
        }
    }
}

