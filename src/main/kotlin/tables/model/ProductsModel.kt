package tables.model

import tables.Products
import tornadofx.ItemViewModel

class ProductsModel(products: Products): ItemViewModel<Products>() {
    val name = bind(Products::nameProperty)
    val coast = bind(Products::coastProperty)
    val kiloCalories = bind(Products::kiloCaloriesProperty)
}