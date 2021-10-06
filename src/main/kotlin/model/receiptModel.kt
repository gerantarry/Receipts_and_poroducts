package model

/**
 * Класс содержащий словарь из продуктов и их количества
 */
data class Receipt(var name:String){
    val receiptProductList: HashMap<Products,Int> = HashMap()

    /*fun remove(productName: String){
        val formatName:String = productName.upFirstChar()
        val removingProducts = ProductsController.productList.find { it.name == formatName  }
        println("Removing $formatName from ${this.name}")
        try {
            receiptProductList.remove(removingProducts)
        }catch (e:Exception){
            println("Removing error!")
        }
    }*/

    fun add(product: Products, number:Int){
        receiptProductList[product] = number
    }

}