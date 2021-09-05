package app

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


    @Test()
    fun simpleAddingProductTest(){
        controller.addProductToList(productName_1, coast, kiloCalories)
        assertTrue(MyController.productList.any {
                    it.name==productName_1.upFirstChar()
                    && it.coast==coast
                    && it.kiloCalories==kiloCalories })
    }

    @Test
    fun findingDublicateInProductListTest(){
        controller.addProductToList(productName_1, coast, kiloCalories)
        controller.addProductToList(productName_2,coast,kiloCalories)
        assertTrue("",MyController.productList.size == 1)
    }

    @Test
    fun removeFromProductListTest(){
        controller.addProductToList(productName_1, coast, kiloCalories)
        controller.removeProductFromList(productName_2, MyController.productList)
        assertEquals("Размер не 0! Ошибка",0,MyController.productList.size)
    }

    @Test
    fun removeFromProductList_notFoundTest(){
        controller.addProductToList(productName_1, coast, kiloCalories)
        controller.removeProductFromList("IsR", MyController.productList)
        assertEquals("Размер не 1! Ошибка",1,MyController.productList.size)
    }

    @Test
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
    @Test
    fun createReceiptTest(){
        controller.createReceipt(receiptName_1)
        assertTrue(MyController.receiptsList.size == 1
                && MyController.receiptsList.any { it.name == receiptName_1.upFirstChar() })
    }

    @Test
    fun addProductToReceipt(){
        loadProductFromJson()
        createReceiptTest()
        val neededProduct = MyController.productList.find { it.name == "Помидоры" }
        controller.addProductToReceipt(receiptName_1.upFirstChar(), neededProduct!!,2)
        assertEquals("Ошибка записи продукта в рецепт",
            2,
            MyController.receiptsList[0].productList[neededProduct])//сравниваем наличие продукта в рецепте и его кол-во
    }



}