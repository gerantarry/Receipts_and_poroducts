package app

import tables.Products
import tornadofx.Controller

class MyController: Controller() {
    val products: Products = Products()

    /**
     * Демонстрационный метод
     */
    fun writeToDb(inputValue: String) {
        println("Writing $inputValue to database!")
        }

    fun addToList(inputValue: String){
        val uppercaseInputValue = inputValue.uppercase()

        if (findInList(uppercaseInputValue))
            println("Список уже содержит $uppercaseInputValue!")
        else{
            products.productList.add(uppercaseInputValue)
            println("Adding $inputValue to list!")
            println("new list contains: ${products.productList}")
        }
    }

    private fun findInList(inputValue: String): Boolean{
        println("Finding $inputValue in the list!")
        return inputValue in products.productList
    }

    /*fun removeFromList(inputValue: String) {
        println("Removing $inputValue from list!")

    }*/
    }
