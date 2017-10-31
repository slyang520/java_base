/**
 * Created by slyang on 17/6/9.
 */
fun main(args: Array<String>) {

    var b: String? = "abc"
    b = null // ok

    // null 检测
    if (b != null && b.length > 0)
        println("String of length ${b.length}")
    else
        println("Empty string")

    // b = "abd"

    //   ?.    操作  
    // 为null 打印空 不为null正常打印
    //  bob?.department?.head?.name  有一个为null 就为null 其他正常打印
    println("String of length=${b?.length}")

    //   ?:     操作符
    // 如果 ?: 左侧的表达式值不是null, 就会返回表达式的的值,否则, 返回右侧表达式的值.
    val l = b?.length ?: -1
    println("String of length=${l}")

    //   !!    操作符
    //    为null 会抛异常
    try {
        val l2 = b!!.length
    } catch (e: Exception) {
        e.printStackTrace()
    }

}