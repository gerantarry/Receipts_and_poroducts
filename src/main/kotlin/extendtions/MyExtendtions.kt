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
    var result:Boolean = false
    this.forEach {
        if (it.name == productName)
        result = true
     }
    return result
}


