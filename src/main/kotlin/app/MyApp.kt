package app

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import tables.editor.ProductsEditor
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

    private val inputName = SimpleStringProperty()
    private val inputCoast = SimpleIntegerProperty()
    private val inputKiloCalories = SimpleIntegerProperty()

    override val root = form {
        autosize()
        fieldset {
            field("Название продукта") {
                textfield(inputName)
            }
            field("Стоимость") {
                textfield(inputCoast)
            }
            field("кКал") {
                textfield(inputKiloCalories)
            }
            button("Добавить продукт") {
                action {
                    try {
                        controller.addProductToList(inputName.value, inputCoast.value, inputKiloCalories.value)
                    } catch (e: NullPointerException) {
                        println("Ошибка, null не допустим при вводе!")
                    }
                    inputName.value = null
                    inputCoast.value = null
                    inputKiloCalories.value = null
                }
            }
            button("Open window") {
                action {
                    openInternalWindow<ProductsEditor>()
                }
            }
            button("Сохранить список продуктов") {
                action {
                    controller.saveProductListAsJsonKlaxon()
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
                        controller.removeProductFromList(inputName.value)
                    } catch (e: NullPointerException) {
                        println("Ошибка, null не допустим при вводе!")
                    }
                    inputName.value = null
                }
            }
        }
    }
}