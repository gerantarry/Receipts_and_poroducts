package extendtions

import tables.Products
import tables.Receiptables

/**
 * Метод переводит первый символ в верхний регистр
 * Пример поМиДор -> Помидор
 */
fun String.upFirstChar(): String =
    this.lowercase().
        replaceFirstChar { it.uppercase() }

fun MutableList<Products>.asString(): String{
   return this.joinToString("\n","\n","\n",
       transform = {Products -> Products.toString()})
}

fun <E:Receiptables> MutableList<E>.searchByName(name:String): Boolean{
    /*this.forEach {
        if (it.name == productName)
        result = true
     }*/
   return this.any { it.name == name }
}
//возвращает ненулл значение из списка найденное по имени
fun <E : Receiptables> MutableList<E>.getFromListByName(name: String): E {
    val formatName = name.upFirstChar()
    return find { it.name == formatName }!!
}

/**
 * метод-расширение ищет запись по имени продукта
 * при нахождении записывает индекс и удаляет запись по индексу
 */
fun<E:Receiptables> MutableList<E>.removeByName(productName: String) {
    var index = 1000
    this.forEach {
        if (it.name == productName)
            index = this.indexOf(it)
    }
    if (index != 1000) this.removeAt(index)
    /*this.asSequence()
        .filter { it.name == productName }
        .map { this.removeAt(0) }
        .toMutableList()*/
}


