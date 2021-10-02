package model

import tornadofx.ItemViewModel
import tornadofx.getProperty
import tornadofx.property

/**
 * Класс содержащий список из продуктов
 */
class Products(name:String, coast:Int=0, kiloCalories:Int=0) {
    var name by property(name)
    fun nameProperty() = getProperty(Products::name)

    var coast by property(coast)
    fun coastProperty() = getProperty(Products::coast)

    var kiloCalories by property(kiloCalories)
    fun kiloCaloriesProperty() = getProperty(Products::kiloCalories)

    override fun toString(): String {
        val stringBuilder = StringBuilder()
            .append("Название: $name, ")
            .append("Цена: $coast, ")
            .append("Калории: $kiloCalories")
        return stringBuilder.toString()
    }

    class ProductsModel(product: tables.Products): ItemViewModel<tables.Products>(product) {
        /*val name = bind {product.nameProperty()}
        val coast = bind {product.coastProperty()}
        val kiloCalories = bind {product.kiloCaloriesProperty()}*/
        val name = bind(product::nameProperty) //TODO ругается на null значение
        val coast = bind(product::coastProperty)
        val kiloCalories = bind(product::kiloCaloriesProperty)
    }
}