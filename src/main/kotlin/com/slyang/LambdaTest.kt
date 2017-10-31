package com.slyang

/**
 * Created by slyang on 17/6/11.
 */

fun main(args: Array<String>) {
    val sum = { x: Int, y: Int ->
        println("Computing the sum of $x and $y...")
        x + y
    }
    println(sum(1, 2))

    //run { println(42) }


}


fun testLambda(funT: (x: Int, y: Int) -> Int) {
}