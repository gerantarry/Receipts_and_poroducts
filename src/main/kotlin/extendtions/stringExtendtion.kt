package extendtions

/**
 * Метод переводит первый символ в верхний регистр
 * Пример поМиДор -> Помидор
 */
fun String.upFirstChar(): String =
    this.lowercase().
    replaceFirstChar { it.uppercase() }
