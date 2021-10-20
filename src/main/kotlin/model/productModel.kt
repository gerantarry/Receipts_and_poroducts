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

    val coastProperty = SimpleIntegerProperty(this, "coast", coast)
    var coast: Int by coastProperty

    val kiloCaloriesProperty = SimpleIntegerProperty(this, "kiloCalories", kiloCalories)
    var kiloCalories: Int by kiloCaloriesProperty

    /*var coast by property(coast)
    fun coastProperty() = getProperty(Products::coast)

    var kiloCalories by property(kiloCalories)
    fun kiloCaloriesProperty() = getProperty(Products::kiloCalories)

    var name by property(name)
    fun nameProperty() = getProperty(Products::name)*/

    class ProductsModel: ItemViewModel<Products>() {
        val name = bind(autocommit = true) { item?.nameProperty }
        val coast = bind(autocommit = true) { item?.coastProperty }
        val kiloCalories = bind(autocommit = true) { item?.kiloCaloriesProperty }
    }

    override fun toString(): String {
        val stringBuilder = StringBuilder()
            .append("Название: $name, ")
            .append("Цена: $coast, ")
            .append("Калории: $kiloCalories")
        return stringBuilder.toString()
    }
}