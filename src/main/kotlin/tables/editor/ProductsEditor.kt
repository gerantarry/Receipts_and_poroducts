package tables.editor

import app.MyController
import javafx.collections.ObservableList
import javafx.scene.layout.BorderPane
import tables.Products
import tables.model.ProductsModel
import tornadofx.*

class ProductsEditor: View("Products Editor") {
    private val productListLink: ObservableList<Products> by MyController.Creator::productList
    override val root = BorderPane()
    val model = ProductsModel(Products("заглушка"))

    init {
        with(root) {
            center {
                tableview(productListLink) {
                    column("Name", Products::nameProperty)
                    column("Coast", Products::coastProperty)
                    column("kiloCalories", Products::kiloCaloriesProperty)

                    // Update the person inside the view model on selection change
                    model.rebindOnChange(this) { selectedProduct ->
                        item = selectedProduct ?: Products("зашлушка")
                    }
                }
            }

            right {
                form {
                    fieldset("Edit person") {
                        field("Name") {
                            textfield(model.name)
                        }
                        field("Coast") {
                            textfield(model.coast)
                        }
                        field  ("kiloCalories"){
                            textfield(model.kiloCalories)
                        }
                        button("Save") {
                            enableWhen(model.dirty)
                            action {
                                save()
                            }
                        }
                        button("Reset").action {
                            model.rollback()
                        }
                    }
                }
            }
        }
    }
    private fun save() {
        // Flush changes from the text fields into the model
        model.commit()

        // The edited person is contained in the model
        val products = model.item

        // A real application would persist the person here
        println("Saving ${products.name} / ${products.coast} / ${products.kiloCalories}")
    }
}