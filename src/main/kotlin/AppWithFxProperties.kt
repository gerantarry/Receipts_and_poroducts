
import javafx.application.Application
import tornadofx.App
import views.MyItemViewModel

class AppWithFxProperties: App(MyItemViewModel::class) {

}

fun main(args: Array<String>){
    Application.launch(AppWithFxProperties::class.java, *args)
}