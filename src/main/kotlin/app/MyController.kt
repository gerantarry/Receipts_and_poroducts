package app

import com.beust.klaxon.JsonReader
import com.beust.klaxon.Klaxon
import com.google.gson.GsonBuilder
import constants.PRODUCT_LIST_FOR_LOAD_PATH
import constants.PRODUCT_LIST_FOR_SAVE_PATH
import constants.RECEIPT_LIST_FOR_LOAD_PATH
import constants.RECEIPT_LIST_FOR_SAVE_PATH
import extendtions.asString
import extendtions.removeByName
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
    }

    fun removeProductFromList(productName: String){
        removeFromList(productName, productList)
    }

    private fun <E:Receiptables> addToList(name:String, list:MutableList<E>, nextReceiptables: E){
        if (findInList(name, list))
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
     * Метод проверяет наличие дубликата перед добавлением и удалением
     */
    //TODO searchProductByName должен рабоать с наследникмаи Receiptables
    private fun <E:Receiptables> findInList(name: String, list:MutableList<E>): Boolean {
        println("Finding $name in the list!")
        return list.searchByName(name)
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
    fun saveProductListAsJsonKlaxon(){
        val klaxon = Klaxon()
        val jsonString = klaxon.toJsonString(productList)
        File(PRODUCT_LIST_FOR_SAVE_PATH).writeText(jsonString)
        println("ProductList was saved")
        /*val gson = GsonBuilder()
        val gsonPretty = GsonBuilder().setPrettyPrinting().create()
        val jsonString = gsonPretty.toJson(productList)*/
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
        println("Загруженный список:" + productList.asString())
    }

    /**
     * @param removeName - название продукта
     * Метод удаляет из переданного списка элемент
     *///TODO попробовать пользоваться filter или map
   private fun <E:Receiptables> removeFromList(removeName: String, list: MutableList<E>) {
        val formatName = removeName.upFirstChar()
        if (findInList(formatName, list))
        {
            list.removeByName(formatName)
        println("Removing $formatName from list!\n new list: $list")
        }
        else println("List don't contain $formatName")
    }

    //создает рецепт и помещает в список рецептов
    fun addReceiptToReceiptList(receiptName:String){
        val formatedReceiptName = receiptName.upFirstChar()
        val nextReceipt = Receipt(formatedReceiptName)
        addToList(formatedReceiptName, receiptsList, nextReceipt)
        println("Receipt \"$formatedReceiptName\" was create and added to receiptList")
    }

    fun removeReceiptFromList(removeName: String){
        removeFromList(removeName, receiptsList)
    }
    //ВОЗМОЖНО ПЕРЕНУСУ В RECEIPT
    //добавляет продукт из общего списка продуктов в список продуктов рецепта
    fun addProductToReceipt(receiptName: String, product: Products, number:Int){
        val receipt = receiptsList.asSequence() //фильтрации по имени
            .filter { it.name == receiptName }
            .toMutableList()[0] // возвращает единственное найденной значение
        receipt.receiptProductList.put(product,number)
        println("Product was added to receipt \"${receipt.name}\"" +
                "${receipt.receiptProductList}")
    }

    fun saveReceiptListAsJson(){
        val gsonPretty = GsonBuilder().setPrettyPrinting().create()
        val jsonProductList: String = gsonPretty.toJson(receiptsList)
        File(RECEIPT_LIST_FOR_SAVE_PATH).writeText(jsonProductList)
        println("ReceiptList was saved")
    }

    fun loadReceiptListFromJson(){
        receiptsList.clear()
        val receiptListAsString = File(RECEIPT_LIST_FOR_LOAD_PATH).readText()
        val klaxon = Klaxon()
        JsonReader(StringReader(receiptListAsString)).use{ reader ->
            reader.beginArray {
                while (reader.hasNext()) {
                    val receipt = klaxon.parse<Receipt>(reader)
                    receiptsList.add(receipt!!)
                }
            }
        }
        println("Загруженный список: $receiptsList")
    }
    }
