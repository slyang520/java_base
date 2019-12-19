/**
 * Created by slyang on 17/6/10.
 */


//  方法扩展 2个方式
//  字段扩展

open class View {
    open fun click() = println("View clicked")
}

class Button : View() {
    override fun click() = println("Button clicked")
}

fun View.showOff() = println("I'm a view!")
fun Button.showOff() = println("I'm a button!")


val String.lastChar: Char
    get() = get(length - 1)

var StringBuilder.lastChar: Char
    get() = get(length - 1)
    set(value: Char) {
        this.setCharAt(length - 1, value)
    }

fun main(args: Array<String>) {


    var view: View = Button()

    //Button clicked
    //I'm a view!
    view.click()
    view.showOff()

    // 强转
    val button: Button = view as Button

    //Button clicked
    //I'm a button!
    button.click()
    button.showOff()

    /*  字段扩展  */
    println("Kotlin".lastChar)

    val sb = StringBuilder("Kotlin?")
    println(sb.lastChar)

    sb.lastChar = '!'
    println(sb)

}