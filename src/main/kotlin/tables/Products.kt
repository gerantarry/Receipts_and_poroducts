package tables

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.getValue
import tornadofx.setValue

/**
 * Класс содержащий список из продуктов
 */
class Products( name:String? = null, coast:Int=0, kiloCalories:Int=0):Receiptables {
    val nameProperty = SimpleStringProperty(this, "name", name)
   override var name: String by nameProperty

    val coastProperty = SimpleIntegerProperty(this, "coast", coast)
    var coast by coastProperty

    val kiloCaloriesProperty = SimpleIntegerProperty(this, "kiloCalories", kiloCalories)
    var kiloCalories by kiloCaloriesProperty
}