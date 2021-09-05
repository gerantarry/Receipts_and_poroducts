package app

import extendtions.getFromListByName
import extendtions.upFirstChar
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import tornadofx.View
import tornadofx.borderpane
import kotlin.test.assertNotNull

class MyControllerTest{

    //необходим для создания controller
    companion object: View(){
        val controller: MyController by inject()
        override val root = borderpane {
        }
    }

    @After
    fun clearList(){
        MyController.productList.clear()
        MyController.receiptsList.clear()
    }


    @Test()//проверка добавления продукта в список продуктов
    fun simpleAddingProductTest(){
        controller.addProductToList(productName_1, coast, kiloCalories)
        assertTrue(MyController.productList.any {
                    it.name==productName_1.upFirstChar()
                    && it.coast==coast
                    && it.kiloCalories==kiloCalories })
    }

    @Test//проверка поиска дубликатов в общем списке продуктов
    fun findingDublicateInProductListTest(){
        controller.addProductToList(productName_1, coast, kiloCalories)
        controller.addProductToList(productName_2,coast,kiloCalories)
        assertTrue("",MyController.productList.size == 1)
    }

    @Test // проверка удаления продукта из общего продуктлиста
    fun removeFromProductListTest(){
        controller.addProductToList(productName_1, coast, kiloCalories)
        controller.removeProductFromList(productName_2)
        assertEquals("Размер не 0! Ошибка",0,MyController.productList.size)
    }

    @Test//проверка перехвата ошибки при указании отсутствующего продукта
    fun removeFromProductList_notFoundTest(){
        controller.addProductToList(productName_1, coast, kiloCalories)
        controller.removeProductFromList("IsR")
        assertEquals("Размер не 1! Ошибка",1,MyController.productList.size)
    }

    @Test // проверка загрузки продуктов в общий продукт лист
    fun loadProductFromJson(){
        controller.loadProductListFromJson()
        assertNotNull(MyController.productList)
        assertTrue(MyController.productList.size == 3)
    }

    /*@Test
    fun saveProductToJson(){
        controller.addProductToList(productName_2,coast, kiloCalories)
        controller.saveProductListAsJson()

    }*/
    @Test // проверка создания рецепта
    fun createReceiptTest(){
        controller.addReceiptToReceiptList(receiptName_1)
        assertTrue(MyController.receiptsList.size == 1
                && MyController.receiptsList.any { it.name == receiptName_1.upFirstChar() })
    }

    @Test // проверка добавления продукта в рецепт
    //Шаги : загружает список продуктов, создает рецепт, добавляет продукт в рецепт
    fun addProductToReceipt(){
        loadProductFromJson()
        createReceiptTest()
        val neededProduct = MyController.productList.find { it.name == "Помидоры" }
        controller.addProductToReceipt(receiptName_1.upFirstChar(), neededProduct!!,2)
        assertEquals("Ошибка записи продукта в рецепт",
            2,
            MyController.receiptsList[0].receiptProductList[neededProduct])//сравниваем наличие продукта в рецепте и его кол-во
    }

    @Test // проверка удаления рецепта из списка рецептов
    fun removeReceiptFromReceiptListTest(){
        createReceiptTest()
        controller.removeReceiptFromList(receiptName_1)
        assertTrue(MyController.receiptsList.size == 0)
    }

    @Test // проверка удаления продукта в рецепте
    fun removeProductFromReceiptTest(){
        addProductToReceipt()
        val receipt = MyController.receiptsList[0]
        receipt.remove("Помидоры")
        assertEquals(0,
        receipt.receiptProductList
            .count{it.key == MyController.productList.getFromListByName("помидор") })
    }

    @Test
    fun saveReceiptListTest(){
        addProductToReceipt()
        controller.saveReceiptListAsJson()
        assertTrue(true)
    }

    @Test // проверка загрузки продуктов в общий продукт лист
    fun loadReceiptsFromJson(){
        controller.loadReceiptListFromJson()
        assertNotNull(MyController.receiptsList)
        assertTrue(MyController.receiptsList.size == 1)
    }
}