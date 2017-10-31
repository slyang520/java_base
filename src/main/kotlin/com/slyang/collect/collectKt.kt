package com.slyang.collect

/**
 * Created by slyang on 17/6/10.
 */


val set = hashSetOf(1, 7, 53)
val list = arrayListOf(1, 7, 53)
val map = hashMapOf(1 to "one", 7 to "seven", 53 to "fifty-three")

fun main(args: Array<String>) {
    println(set.javaClass)
    println(map.javaClass)
    println(list.javaClass)

    val strings = listOf("first", "second", "fourteenth")
    val numbers = setOf("1", "14", "2")

    val list = listOf(1, 2, 3, 4)
    println(list.map { it * it })

    val list_1 = listOf(1, 2, 3, 4, 5, 6, 7)
    println(list_1.filter { it > 4 })

    val maps = mapOf(0 to "zero", 1 to "one")
    println(maps.mapValues { it.value.toUpperCase() })

}