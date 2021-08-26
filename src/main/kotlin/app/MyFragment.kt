package app

import javafx.collections.ObservableList
import tables.Products
import tornadofx.Fragment
import tornadofx.readonlyColumn
import tornadofx.tableview

class MyFragment: Fragment() {
    //Делегирую локальные переменные значениями объекта
    private val productList1: ObservableList<Products> by MyController.Companion::productList

    override val root = tableview(productList1){
        readonlyColumn("Продукт", Products::name)

    }
}