package controller

import com.beust.klaxon.JsonReader
import com.beust.klaxon.Klaxon
import com.google.gson.GsonBuilder
import constants.PRODUCT_LIST_FOR_LOAD_PATH
import constants.PRODUCT_LIST_FOR_SAVE_PATH
import extendtions.asString
import extendtions.removeByName
import extendtions.searchProductByName
import extendtions.upFirstChar
import model.Products
import tornadofx.Controller
import tornadofx.asObservable
import java.io.File
import java.io.StringReader

class ProductsController : Controller() {
    companion object Starter {
    }

    val productList = mutableListOf<Products>(
        Products("сыр",4,6),
        Products("qwer",7,9)).asObservable()
    val selectedProduct = Products.ProductsModel()

    /**
     * @param productName название продукта
     * Метод записывает продукта в products.productList
     */
    fun addProductToList(productName: String, coast: Int, kiloCalories: Int) {
        val formatProductName = productName.upFirstChar()
        val nextProduct = Products(formatProductName, coast, kiloCalories)
        if (findInList(formatProductName, productList))
            println("Список уже содержит $formatProductName!")
        else {
            productList.add(nextProduct)
            println("Adding $formatProductName to list!\n new list: $productList")
        }
    }

    /**
     * @param name - название продукта
     * @param list - список в котором осуществляется поиск
     * @return результат поиска true или false
     * Метод проверяет наличие дубликата перед добавлением и удалением
     */
    fun findInList(name: String, list: MutableList<Products>): Boolean {
        println("Finding $name in the list!")
        return list.searchProductByName(name)
    }

    /**
     * @param removeName - название продукта
     * Метод удаляет из переданного списка элемент
     */
    //TODO попробовать пользоваться filter или map
    fun removeProductFromList(productName: String) {
        val formatName = productName.upFirstChar()
        if (findInList(formatName, productList))
        {
            productList.removeByName(formatName)
            println("Removing $formatName from list!\n new list: $productList")
        }
        else println("List don't contain $formatName")
    }

    /**
     * Метод сохраняет список productList в json файл
     */
    fun saveProductListAsJson() {
        val gsonPretty = GsonBuilder().setPrettyPrinting().create()
        val jsonProductList: String = gsonPretty.toJson(productList)
        File(PRODUCT_LIST_FOR_SAVE_PATH).writeText(jsonProductList)
        println("ProductList was saved")
    }

    fun saveProductListAsJsonKlaxon() {
        val klaxon = Klaxon()
        val jsonString = klaxon.toJsonString(productList)
        File(PRODUCT_LIST_FOR_SAVE_PATH).writeText(jsonString)
        println("ProductList was saved")
    }

    /**
     * Функция парсит json используя библиотеку Klaxon
     */
    fun loadProductListFromJson() {
        productList.clear()
        val productListAsString = File(PRODUCT_LIST_FOR_LOAD_PATH).readText()
        val klaxon = Klaxon()
        JsonReader(StringReader(productListAsString)).use { reader ->
            reader.beginArray {
                while (reader.hasNext()) {
                    val product = klaxon.parse<Products>(reader)
                    productList.add(product!!)
                }
            }
        }
        println("Загруженный список:" + productList.asString())
    }
}