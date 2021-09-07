package tables

import tornadofx.getProperty
import tornadofx.property

/**
 * Класс содержащий список из продуктов
 */
data class Products(override var name:String, var coast:Int, var kiloCalories:Int):Receiptables {

    var propertyName by property(name)
    fun nameProperty() = getProperty(Products::propertyName)

    var propertyCoast by property(coast)
    fun CoastProperty() = getProperty(Products::propertyCoast)

    var propertyKiloCalories by property(kiloCalories)
    fun kiloCaloriesProperty() = getProperty(Products::propertyKiloCalories)
}