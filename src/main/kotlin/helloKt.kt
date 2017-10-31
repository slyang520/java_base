import java.io.File

/**
 * Created by slyang on 17/6/3.
 */

fun main(arg: Array<String>) {

    println("hello kotlin")
    println("sum=" + sum(3, 5))
    println("sum2=" + sum2(3, 5))
    println("sum3=" + sum3(3, 5))


    var test = File("/")
    // 函数当作参数
    test.listFiles().forEach(::testtttttttt)
    test.listFiles().forEach(::println)
    // 匿名函数
    test.listFiles().forEach(fun(test): Unit {
        println(test)
        println(test)
    })

    test.listFiles().forEach(fun(test): Unit = println(test))

}


fun testtttttttt(a: Any) {
    println(a)
}

//  function .
fun sum(a: Int, b: Int): Int {
    return a + b
}

//  方法体只有一条语句，并且自动推测返回类型
fun sum2(a: Int, b: Int) = a + b


public fun sum3(a: Int, b: Int) = a + b