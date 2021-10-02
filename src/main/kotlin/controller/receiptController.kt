package controller


import com.beust.klaxon.JsonReader
import com.beust.klaxon.Klaxon
import com.google.gson.GsonBuilder
import constants.RECEIPT_LIST_FOR_LOAD_PATH
import constants.RECEIPT_LIST_FOR_SAVE_PATH
import extendtions.removeReceiptByName
import extendtions.upFirstChar
import tables.Products
import tables.Receipt
import tornadofx.Controller
import tornadofx.asObservable
import java.io.File
import java.io.StringReader

class receiptController: Controller() {
    companion object Starter{
        val receiptsList = mutableListOf<Receipt>().asObservable()
    }

    /**
     * @param name - название рецепта
     * @param list - список в котором осуществляется поиск
     * @return результат поиска true или false
     * Метод проверяет наличие дубликата перед добавлением и удалением
     */
    private fun findInList(name: String): Boolean {
        println("Finding $name in the list!")
        return receiptsList.searchReceipt(name)
    }

    //создает рецепт и помещает в список рецептов
    fun addReceiptToReceiptList(receiptName:String){
        val formatedReceiptName = receiptName.upFirstChar()
        val nextReceipt = Receipt(formatedReceiptName)
        if (findInList(receiptName))
            println("Список уже содержит $formatedReceiptName!")
        else {
            receiptsList.add(nextReceipt)
            println("Adding $formatedReceiptName to list!\n new list: $receiptsList")
        }
        println("Receipt \"$formatedReceiptName\" was create and added to receiptList")
    }

    //удаляет рецепет из списка
    fun removeReceiptFromList(removeName: String){
        val formatName = removeName.upFirstChar()
        if (findInList(formatName))
        {
            receiptsList.removeReceiptByName()
            println("Removing $formatName from list!\n new list: $receiptsList")
        }
        else println("List don't contain $formatName")
    }

    //ВОЗМОЖНО ПЕРЕНУСУ В RECEIPT
    //добавляет продукт из общего списка продуктов в список продуктов рецепта
    fun addProductToReceipt(receiptName: String, product: Products, number:Int){
        val receipt = receiptsList.asSequence() //фильтрации по имени
            .filter { it.name == receiptName }
            .toMutableList()[0] // возвращает единственное найденной значение
        receipt.add(product,number)
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