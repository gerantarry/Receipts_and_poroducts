package tables.model

import tables.Products
import tornadofx.ItemViewModel

class ProductsModel(product:Products): ItemViewModel<Products>(product) {
    /*val name = bind {product.nameProperty()}
    val coast = bind {product.coastProperty()}
    val kiloCalories = bind {product.kiloCaloriesProperty()}*/
    val name = bind(product::nameProperty) //TODO ругается на null значение
    val coast = bind(product::coastProperty)
    val kiloCalories = bind(product::kiloCaloriesProperty)
}