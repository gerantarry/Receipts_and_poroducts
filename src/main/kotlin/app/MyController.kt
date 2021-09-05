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
        println("ProductList was saved")
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
    fun <E:Receiptables> removeProductFromList(productName: String, list: MutableList<E>) {
        val formatProductName = productName.upFirstChar()
        if (findProductInList(formatProductName, list))
        {
            list.removeProductByName(formatProductName)
        println("Removing $formatProductName from list!\n new list: $list")
        }
        else println("List don't contain $formatProductName")
    }

    fun createReceipt(receiptName:String){
        val formatedReceiptName = receiptName.upFirstChar()
        val nextReceipt = Receipt(formatedReceiptName)
        addToList(formatedReceiptName, receiptsList, nextReceipt)
        println("Receipt \"$formatedReceiptName\" was create and added to receiptList")
    }

    fun addProductToReceipt(receiptName: String, product: Products, number:Int){
        val receipt = receiptsList.asSequence() //фильтрации по имени
            .filter { it.name == receiptName }
            .toMutableList()[0] // возвращает единственное найденной значение
        receipt.productList.put(product,number)
        println("Product was added to receipt \"${receipt.name}\"" +
                "${receipt.productList}")
    }
    }
