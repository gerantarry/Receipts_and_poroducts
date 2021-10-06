package model

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.ItemViewModel
import tornadofx.getValue
import tornadofx.setValue

/**
 * Класс содержащий список из продуктов
 */
class Products(name:String ="заглушка", coast:Int=0, kiloCalories:Int=0) {

    val nameProperty = SimpleStringProperty(this, "name", name)
    var name: String by nameProperty

    /*var name by property(name)
    fun nameProperty() = getProperty(Products::name)*/

    val coastProperty = SimpleIntegerProperty(this, "coast", coast)
    var coast: Int by coastProperty

    /*var coast by property(coast)
    fun coastProperty() = getProperty(Products::coast)*/

    /*var kiloCalories by property(kiloCalories)
    fun kiloCaloriesProperty() = getProperty(Products::kiloCalories)*/

    val kiloCaloriesProperty = SimpleIntegerProperty(this, "kiloCalories", kiloCalories)
    var kiloCalories: Int by kiloCaloriesProperty

    class ProductsModel: ItemViewModel<Products>() {
        val name = bind { item?.nameProperty }
        val coast = bind { item?.coastProperty }
        val kiloCalories = bind { item?.kiloCaloriesProperty }
    }

    override fun toString(): String {
        val stringBuilder = StringBuilder()
            .append("Название: $name, ")
            .append("Цена: $coast, ")
            .append("Калории: $kiloCalories")
        return stringBuilder.toString()
    }
}