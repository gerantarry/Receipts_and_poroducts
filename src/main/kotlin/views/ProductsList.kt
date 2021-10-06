package views

import controller.ProductsController
import model.Products
import tornadofx.View
import tornadofx.bindSelected
import tornadofx.column
import tornadofx.tableview


class ProductsList: View() {
    val controller: ProductsController by inject()

    override val root = tableview(controller.productList){
        column("Продукт", Products::name)
        column("Цена", Products::coast)
        column("кКал", Products::kiloCalories)
        bindSelected(controller.selectedProduct)
    }
}