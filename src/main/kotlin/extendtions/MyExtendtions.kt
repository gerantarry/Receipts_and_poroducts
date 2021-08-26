package extendtions

import tables.Products

/**
 * Метод переводит первый символ в верхний регистр
 * Пример поМиДор -> Помидор
 */
fun String.upFirstChar(): String =
    this.lowercase().
        replaceFirstChar { it.uppercase() }

fun MutableList<Products>.searchProductByName(productName:String): Boolean{
    var result = false
    this.forEach {
        if (it.name == productName)
        result = true
     }
    return result
}

/**
 * метод-расширение ищет запись по имени продукта
 * при нахождении записывает индекс и удаляет запись по индексу
 */
fun MutableList<Products>.removeProductByName(productName: String) {
    var index = 1000
    this.forEach {
        if (it.name == productName)
            index = this.indexOf(it)
    }
    if (index != 1000) this.removeAt(index)
}


