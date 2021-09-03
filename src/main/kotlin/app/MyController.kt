package app

import com.beust.klaxon.JsonReader
import com.beust.klaxon.Klaxon
import com.google.gson.GsonBuilder
import constants.PRODUCT_LIST_FOR_LOAD_PATH
import constants.PRODUCT_LIST_FOR_SAVE_PATH
import extendtions.removeProductByName
import extendtions.searchByName
import extendtions.upFirstChar
import tables.Products
import tables.Receipt
import tables.Receiptables
import tornadofx.Controller
import tornadofx.asObservable
import java.io.File
import java.io.StringReader

class MyController : Controller() {
  //  val receipt: MutableList<Products>
companion object Creator{
      val productList = mutableListOf<Products>().asObservable()
      val receiptsList = mutableListOf<Receipt>().asObservable()
}

    /**
     * @param productName название продукта
     * Метод записывает продукта в products.productList
     */
    fun addProductToList(productName: String, coast:Int, kiloCalories:Int) {
        val formatProductName = productName.upFirstChar()
        val nextProduct = Products(formatProductName, coast, kiloCalories)
        addToList(formatProductName, productList, nextProduct)

        /*if (findProductInList(formatProductName, productList))
            println("Список уже содержит $formatProductName!")
        else {
            productList.add(nextProduct)
            println("Adding $productName to list!\n new list: $productList")
        }*/
    }

    private fun <E:Receiptables> addToList(name:String, list:MutableList<E>, nextReceiptables: E){
        if (findProductInList(name, list))
            println("Список уже содержит $name!")
        else {
            list.add(nextReceiptables)
            println("Adding $name to list!\n new list: $list")
        }

    }

    /**
     * @param name - название продукта
     * @param list - список в котором осуществляется поиск
     * @return результат поиска true или false
     * Метод проверяет наличие дубликата в product.productList
     */
    //TODO searchProductByName должен рабоать с наследникмаи Receiptables
    private fun <E:Receiptables> findProductInList(name: String, list:MutableList<E>): Boolean {
        println("Finding $name in the list!")
        return list.searchByName(name)
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
     * Функция парсит json используя библиотеку Klaxon
     */
    fun loadProductListFromJson() {
        productList.clear()
        val productListAsString = File(PRODUCT_LIST_FOR_LOAD_PATH).readText()
        val klaxon = Klaxon()
        JsonReader(StringReader(productListAsString)).use{ reader ->
            reader.beginArray {
                while (reader.hasNext()) {
                    val product = klaxon.parse<Products>(reader)
                    productList.add(product!!)
                }
            }
        }
        println("Загруженный список: $productList")
    }

    /**
     * @param productName - название продукта
     * Метод удаляет продукт из списка
     *///TODO попробовать пользоваться filter или map
    fun removeProductFromList(productName: String) {
        val formatProductName = productName.upFirstChar()
        if (findProductInList(formatProductName, productList))
        {
            productList.removeProductByName(formatProductName)
        println("Removing $formatProductName from list!\n new list: $productList")
        }
        else println("Список не содержит $formatProductName")
    }

    fun createReceipt(receiptName:String){
        val formatedReceiptName = receiptName.upFirstChar()
        val nextReceipt = Receipt(formatedReceiptName)
        addToList(formatedReceiptName, receiptsList, nextReceipt)
    }

    fun addProductToReceipt(receipt: Receipt, product: Products, number:Int){
        receipt.productList.put(product,number)



    }
    }
