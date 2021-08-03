package app

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import constants.PRODUCT_LIST_FOR_LOAD_PATH
import constants.PRODUCT_LIST_FOR_SAVE_PATH
import extendtions.upFirstChar
import tables.Products
import tornadofx.Controller
import java.io.File

class MyController : Controller() {
    private val products: Products = Products()

    /**
     * @param inputValue название продукта
     * Метод записывает продукта в products.productList
     */
    fun addProductToList(inputValue: String) {
        val formatInputValue = inputValue.upFirstChar()

        if (findProductInList(formatInputValue))
            println("Список уже содержит $formatInputValue!")
        else {
            products.productList.add(formatInputValue)
            println("Adding $inputValue to list!\n new list: ${products.productList}")
        }
    }

    /**
     * @param inputValue - название продукта
     * @return результат поиска true или false
     * Метод проверяет наличие дубликата в product.productList
     */
    private fun findProductInList(inputValue: String): Boolean {
        println("Finding $inputValue in the list!")
        return inputValue in products.productList
    }

    /**
     * Метод сохраняет список productList в json файл
     */
    fun saveProductListAsJson() {
        val gsonPretty = GsonBuilder().setPrettyPrinting().create()
        val jsonProductList: String = gsonPretty.toJson(products)
        File(PRODUCT_LIST_FOR_SAVE_PATH).writeText(jsonProductList)
        println("Список продуктов сохранён")
    }

    /**
     * Метод загружает данные из json в класс, тип которого передали
     * Products::class.java - передаёт тип класса
     */
    fun loadProductListFromJson() {
        val productListAsString = File(PRODUCT_LIST_FOR_LOAD_PATH).readText()
        val gson = Gson()
        val loadedProducts: Products =
            gson.fromJson(productListAsString, Products::class.java)
        products.productList.clear()
        products.productList.addAll(loadedProducts.productList)
        println("Загруженный список: " + products.productList)

    }

    /**
     * @param inputValue - название продукта
     * Метод удаляет продукт из списка
     */
    fun removeProductFromList(inputValue: String) {
        val formatInputValue = inputValue.upFirstChar()
        if (findProductInList(formatInputValue))
        {
            products.productList.remove(formatInputValue)
        println("Removing $formatInputValue from list!\n new list: ${products.productList}")
        }
        else println("Список не содержит $formatInputValue")
    }
    }
