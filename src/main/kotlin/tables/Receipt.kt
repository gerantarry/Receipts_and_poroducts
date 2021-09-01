package tables

/**
 * Класс содержащий словарь из продуктов и их количества
 */
data class Receipt(override var name:String):Receiptables {
    val productList: HashMap<Products,Int> = HashMap()

}