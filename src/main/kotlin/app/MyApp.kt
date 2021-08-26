package app

import javafx.beans.property.SimpleIntegerProperty
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

    init {
        controller.loadProductListFromJson()
    }

    private val input_name = SimpleStringProperty()
    private val input_coast = SimpleIntegerProperty()

    override val root = form {
        fieldset {
            field("Название продукта") {
                textfield(input_name)
            }
            field("Стоимость") {
                textfield(input_coast)
            }
            button("Добавить продукт") {
                action {
                    try {
                        controller.addProductToList(input_name.value, input_coast.value)
                    } catch (e: NullPointerException) {
                        println("Ошибка, null не допустим при вводе!")
                    }
                    input_name.value = null
                    input_coast.value = null
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
                        controller.removeProductFromList(input_name.value)
                    } catch (e: NullPointerException) {
                        println("Ошибка, null не допустим при вводе!")
                    }
                    input_name.value = null
                }
            }
        }
    }
}