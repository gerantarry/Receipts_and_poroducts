package extendtions

import model.Receipt
import tables.Products

fun MutableList<Products>.asString(): String{
    return this.joinToString("\n","\n","\n",
        transform = {Products -> Products.toString()})
}

fun  MutableList<Products>.searchByName(name:String): Boolean{
    return this.any { it.name == name }
}

fun  MutableList<Receipt>.searchReceipt(name:String): Boolean{
    return this.any { it.name == name }
}

//возвращает ненулл значение из списка найденное по имени
fun  MutableList<Products>.getFromListByName(name: String): Products {
    val formatName = name.upFirstChar()
    return find { it.name == formatName }!!
}

/**
 * метод-расширение ищет запись по имени продукта
 * при нахождении записывает индекс и удаляет запись по индексу
 */
fun MutableList<Products>.removeByName(productName: String) {
    var index = 1000
    this.forEach {
        if (it.name == productName)
            index = this.indexOf(it)
    }
    if (index != 1000) this.removeAt(index)
}
//TODO доделать рефакторинг расширейний
fun MutableList<Receipt>.removeReceiptByName(productName: String) {
    var index = 1000
    this.forEach {
        if (it.name == productName)
            index = this.indexOf(it)
    }
    if (index != 1000) this.removeAt(index)
}