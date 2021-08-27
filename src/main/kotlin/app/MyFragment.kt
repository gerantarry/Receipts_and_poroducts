package app

import javafx.collections.ObservableList
import tables.Products
import tornadofx.Fragment
import tornadofx.column
import tornadofx.makeEditable
import tornadofx.tableview

class MyFragment: Fragment() {
    //Делегирую локальные переменные значениями объекта
    private val productList1: ObservableList<Products> by MyController.Companion::productList

    /*override val root = tableview(productList1){
        readonlyColumn("Продукт", Products::name)
        readonlyColumn("Стоимость", Products::coast)
        readonlyColumn("ккалории", Products::kiloCalories)
    }*/
    //TODO прочитать как сохранять изменения с помощью методов в column
    override val root = tableview(productList1){
        column("Продукты",Products::propertyName).makeEditable().onEditStartProperty()
        column("Цена", Products::propertyCoast).makeEditable()
        column("кКалории", Products::propertyKiloCalories).makeEditable()
    }
}