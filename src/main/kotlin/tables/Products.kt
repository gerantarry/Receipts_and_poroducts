package tables

import javafx.beans.property.SimpleIntegerProperty
import tornadofx.getProperty
import tornadofx.getValue
import tornadofx.property
import tornadofx.setValue

/**
 * Класс содержащий список из продуктов
 */
class Products(name:String, coast:Int=0, kiloCalories:Int=0):Receiptables {
    override var name by property(name)
    fun nameProperty() = getProperty(Products::name)

    val coastProperty = SimpleIntegerProperty(this, "coast", coast)
    var coast by coastProperty

    val kiloCaloriesProperty = SimpleIntegerProperty(this, "kiloCalories", kiloCalories)
    var kiloCalories by kiloCaloriesProperty

    override fun toString(): String {
        val stringBuilder = StringBuilder()
            .append("Название: $name, ")
            .append("Цена: $coast, ")
            .append("Калории: $kiloCalories")
        return stringBuilder.toString()
    }
}