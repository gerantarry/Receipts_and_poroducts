package app

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import constants.PRODUCT_LIST_FOR_LOAD_PATH
import constants.PRODUCT_LIST_FOR_SAVE_PATH
import extendtions.searchProductByName
import extendtions.upFirstChar
import tables.Products
import tornadofx.Controller
import java.io.File

class MyController : Controller() {

private val productList = mutableListOf<Products>()

    /**
     * @param productName название продукта
     * Метод записывает продукта в products.productList
     */
    fun addProductToList(productName: String) {
        val formatProductName = productName.upFirstChar()
        val nextProduct = Products(formatProductName)

        if (findProductInList(formatProductName))
            println("Список уже содержит $formatProductName!")
        else {
            productList.add(nextProduct)
            println("Adding $productName to list!\n new list: $productList")
        }
    }

    /**
     * @param productName - название продукта
     * @return результат поиска true или false
     * Метод проверяет наличие дубликата в product.productList
     */
    private fun findProductInList(productName: String): Boolean {
        println("Finding $productName in the list!")
        return productList.searchProductByName(productName)
    //productList.contains(Products(productName))
    }

    /**
     * Метод сохраняет список productList в json файл
     */
    fun saveProductListAsJson() {
        val gsonPretty = GsonBuilder().setPrettyPrinting().create()
        val jsonProductList: String = gsonPretty.toJson(productList)
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
        val loadedProducts =
            gson.fromJson(productListAsString, ) //берётся лишь 1 объект из json, а должны ВСЕ
        productList.clear()
        productList.addAll(mutableListOf(loadedProducts))
        println("Загруженный список: $productList")

    }

    /**
     * @param productName - название продукта
     * Метод удаляет продукт из списка
     */
    fun removeProductFromList(productName: String) {
        val formatProductName = productName.upFirstChar()
        if (findProductInList(formatProductName))
        {
            productList.remove(Products(formatProductName))
        println("Removing $formatProductName from list!\n new list: $productList")
        }
        else println("Список не содержит $formatProductName")
    }
    }
