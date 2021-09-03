package app

import extendtions.upFirstChar
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import tornadofx.View
import tornadofx.borderpane
import kotlin.test.assertNotNull
import app.MyController.Creator as MyControllerCreator

class MyControllerTest{

    //необходим для создания controller
    companion object: View(){
        val controller: MyController by inject()
        override val root = borderpane {

        }
    }

    @After
    fun clearList(){
        MyControllerCreator.productList.clear()
    }


    @Test()
    fun simpleAddingProductTest(){
        controller.addProductToList(productName_1, coast, kiloCalories)
        assertTrue(MyControllerCreator.productList.any {
                    it.name==productName_1.upFirstChar()
                    && it.coast==coast
                    && it.kiloCalories==kiloCalories })
    }

    @Test
    fun findingDublicateInProductListTest(){
        controller.addProductToList(productName_1, coast, kiloCalories)
        controller.addProductToList(productName_2,coast,kiloCalories)
        assertTrue("",MyControllerCreator.productList.size == 1)
    }

    @Test
    fun removeFromProductListTest(){
        controller.addProductToList(productName_1, coast, kiloCalories)
        controller.removeProductFromList(productName_2)
        assertEquals("Размер не 0! Ошибка",0,MyControllerCreator.productList.size)
    }

    @Test
    fun removeFromProductList_notFoundTest(){
        controller.addProductToList(productName_1, coast, kiloCalories)
        controller.removeProductFromList("IsR")
        assertEquals("Размер не 0! Ошибка",1,MyControllerCreator.productList.size)
    }

    @Test
    fun loadProductFromJson(){
        controller.loadProductListFromJson()
        assertNotNull(MyControllerCreator.productList)
        assertTrue(MyControllerCreator.productList.size == 3)
    }

    /*@Test
    fun saveProductToJson(){
        controller.addProductToList(productName_2,coast, kiloCalories)
        controller.saveProductListAsJson()

    }*/



}