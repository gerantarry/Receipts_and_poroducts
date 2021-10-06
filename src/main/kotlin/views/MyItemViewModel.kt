package views

import tornadofx.View
import tornadofx.hbox

class MyItemViewModel: View() {
    override val root = hbox{
        add<ProductsList>()
        add<ProductsEditor>()
    }
}