package app

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import constants.PRODUCT_LIST_FOR_LOAD_PATH
import constants.PRODUCT_LIST_FOR_SAVE_PATH
import tables.Products
import tornadofx.Controller
import java.io.File

class MyController : Controller() {
    private val products: Products = Products()

    /**
     * @param inputValue название продукта
     * Метод записывает продукта в products.productList
     */
    //TODO переделать запись из uppercase в (помидор -> Помидор)
    fun addToList(inputValue: String) {
        val uppercaseInputValue = inputValue.uppercase()

        if (findInList(uppercaseInputValue))
            println("Список уже содержит $uppercaseInputValue!")
        else {
            products.productList.add(uppercaseInputValue)
            println("Adding $inputValue to list!")
            println("new list contains: ${products.productList}")
        }
    }

    /**
     * @param inputValue - название продукта
     * @return результат поиска true или false
     * Метод проверяет наличие дубликата в product.productList
     */
    private fun findInList(inputValue: String): Boolean {
        println("Finding $inputValue in the list!")
        return inputValue in products.productList
    }

    /**
     * Метод сохраняет список productList в json файл
     */
    fun saveProductListAsJson() {
        val productList = products
        val gsonPretty = GsonBuilder().setPrettyPrinting().create()
        val jsonProductList: String = gsonPretty.toJson(productList)
        File(PRODUCT_LIST_FOR_SAVE_PATH).writeText(jsonProductList)
        println("Список продуктов сохранён")
    }

    /**
     *
     */
    fun loadProductListFromJson() {
        val productListAsString = File(PRODUCT_LIST_FOR_LOAD_PATH).readText()
        val gson = Gson()
        val loadedProducts: Products =
            gson.fromJson(productListAsString, Products::class.java) //Products::class.java - передал тип класса
        products.productList.clear()
        products.productList.addAll(loadedProducts.productList)
        println("Загруженный список: " + products.productList)

    }
    //TODO написать метод удаляющий продукт из списка
    /*fun removeFromList(inputValue: String) {
        println("Removing $inputValue from list!")

    }*/
    }
