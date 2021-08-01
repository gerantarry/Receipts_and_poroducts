package app

import tornadofx.Fragment
import tornadofx.button
import tornadofx.label
import tornadofx.vbox

class MyFragment: Fragment() {
        override val root = vbox {
            label("Save product list")
                button("save now!") {  }
        }
    }
