package app

import org.junit.Assert.assertTrue
import org.junit.Test
import tornadofx.View
import tornadofx.borderpane
import app.MyController.Creator as MyControllerCreator

class MyControllerTest{

    //необходим для создания controller
    companion object: View(){
        val controller: MyController by inject()
        override val root = borderpane {

        }
    }


    @Test
    fun addProductToListTest(){
        val productName = "Сыр"
        val coast = 500
        val kiloCalories = 877
        controller.addProductToList(productName, coast, kiloCalories)
        assertTrue(MyControllerCreator.productList.any {
                    it.name==productName
                    && it.coast==coast
                    && it.kiloCalories==kiloCalories })

    }

}