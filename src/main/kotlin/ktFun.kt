import java.util.*

/**
 * Created by slyang on 17/6/5.
 */


fun main(args: Array<String>) {

    doSwim() //打印： "do swim"
    doSwim("just do it") // 打印："just do it"

    register("li")  // register("li", 1001, 0)
    register(name = "wang", no = 1003)
    register(sex = 2, name = "wang")


    val list1 = asList(1, 2, 3)
    val list2 = asList(1)
    println("list1  =  " + list1)
    println("list2  =  " + list2)

    val person: Person = Person("SLY", 20)
    // 使用中缀标记法调用扩展函数
    // Log: addr: AA-BB, name: Jone
    person printName ("AA-BB")
    // 上面的语句等价于
    person.printName("AA-BB")

}

fun doSwim(sports: String = "do swim") {
    println(sports)
}

fun register(name: String, no: Int = 1001, sex: Int = 0) {
    println("name: $name, no:$no, sex: $sex")
}

//  vararg 可变参数
fun <T> asList(vararg ts: T): List<T> {
    val result = ArrayList<T>()
    for (t in ts) // ts 是一个 Array
        result.add(t)
    return result
}


//todo    使用infix ?
class Person(var name: String,
             var age: Int) {
    // 使用infix 关键字标记，该函数可被中缀标记法法调用
    infix fun printName(addr: String) {
        println("addr: $addr, name: $name")
    }
}


fun <T> MutableList<T>.swap(x: Int, y: Int) {
    val tmp = this[x] // 'this' corresponds to the list
    this[x] = this[y]
    this[y] = tmp
}