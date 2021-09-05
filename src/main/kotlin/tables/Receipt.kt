package tables

import app.MyController
import extendtions.upFirstChar

/**
 * Класс содержащий словарь из продуктов и их количества
 */
data class Receipt(override var name:String):Receiptables {
    val receiptProductList: HashMap<Products,Int> = HashMap()

    fun remove(productName: String){
        val formatName:String = productName.upFirstChar()
        val removingProducts = MyController.productList.find { it.name == formatName  }
        println("Removing $formatName from ${this.name}")
        try {
            receiptProductList.remove(removingProducts)
        }catch (e:Exception){
            println("Removing error!")
        }
    }

}