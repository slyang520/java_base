/**
 * Created by slyang on 17/6/8.
 */

package com.slyang

//  in  操作符

fun isLetter(c: Char) = c in 'a'..'z' || c in 'A'..'Z'
fun isNotDigit(c: Char) = c !in '0'..'9'
fun recognize(c: Char) = when (c) {
    in '0'..'9' -> "It's a digit!"
    in 'a'..'z', in 'A'..'Z' -> "It's a letter!"
    else -> "I don't know…​"
}

fun main(args: Array<String>) {
    println(isLetter('q'))
    println(isNotDigit('x'))
    println(recognize('8'))
}
