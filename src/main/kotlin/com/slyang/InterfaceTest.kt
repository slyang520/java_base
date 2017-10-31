package com.slyang

/**
 * Created by slyang on 17/6/11.
 */
interface Clickable {
    fun click()
    fun showOff() = println("I'm clickable!")
}

interface Focusable {
    fun setFocus(b: Boolean) =
            println("I ${if (b) "got" else "lost"} focus.")

    fun showOff() = println("I'm focusable!")
}

class Button : Clickable, Focusable {
    override fun showOff() {
        super<Clickable>.showOff()
        super<Focusable>.showOff()
    }

    override fun click() = println("I was clicked")
}

open class RichButton : Clickable {
    override fun click() {
    }


}


fun main(args: Array<String>) {
    val btn = Button()

    btn.showOff()
    btn.setFocus(true)
    btn.click()

}
