package tables.model

import tables.Products
import tornadofx.ItemViewModel

class ProductsModel(): ItemViewModel<Products>() {
    val name = bind {item?.nameProperty()}
    val coast = bind {item?.coastProperty}
    val kiloCalories = bind {item?.kiloCaloriesProperty}
    /*val name = bind(Products::nameProperty)
    val coast = bind(Products::coastProperty)
    val kiloCalories = bind(Products::kiloCaloriesProperty)*/
}