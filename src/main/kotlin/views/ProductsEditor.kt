package views

import controller.ProductsController
import tornadofx.*

class ProductsEditor: View() {
    val controller: ProductsController by inject()
    override val root = form{
        fieldset("Информация о продукте") {
            field("Продукт") {
                textfield(controller.selectedProduct.name)
            }
            button("Сохранить"){
                setOnAction {
                    controller.selectedProduct.commit()
                }
            }
        }


    }

}