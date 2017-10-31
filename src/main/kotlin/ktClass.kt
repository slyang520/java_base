import java.io.File
import java.util.*

/**
 * Created by slyang on 17/6/5.
 */

fun main(args: Array<String>) {

    var obj: ClassTest = ClassTest("first name")
    println(obj.customerKey)

    val obj2: ClassTest2 = ClassTest2("first", "last", 18)
    println(obj2.firstName + "    " + obj2.lastName + "      " + obj2.age)

    var obj3: Person222 = Person222()
    println(obj3.lastName)
    obj3.no = 9      // set
    obj3.heiht = 52355f
    obj3.doFly()

    println(obj3.toString())

    println(obj3.no)

    val bob = Client("Bob", 973293)
    println(bob)
    println(bob.copy(name = "2"))

    println(CaseInsensitiveFileComparator.compare(
            File("/User"), File("/user")))
    val files = listOf(File("/Z"), File("/a"))
    println(files.sortedWith(CaseInsensitiveFileComparator))

    val persons = listOf(PersonT("Bob"), PersonT("Alice"))
    println(persons.sortedWith(PersonT.NameComparator))

    println(PersonT.TAG)
    PersonT.FUN_STATIC()

}

//  类
class Invoice {
}

//  构造函数  初始化属性
class ClassTest constructor(firstName: String) {

    val customerKey = firstName.toUpperCase()

    init {
        println("init value == ${firstName}")
    }

}

//  声明属性和初始化主构造函数
class ClassTest2(val firstName: String, val lastName: String, var age: Int) {
}

//  get , set
open class Person222 {

    // set 不可见
    var lastName: String = "zhang"
        get() = field.toUpperCase()
        private set

    //TODO  field 字段是啥呢 ？
    // 后端变量(Backing Fields)   ?
    var no: Int = 100
        get() = field
        set(value) {
            if (value < 10) {
                field = value
            } else {
                field = -1
            }
        }

    var heiht: Float = 145.4f
        set

    override fun toString(): String {
        return super.toString()
    }

    fun doFly() {
        println("亲生的")
    }

}


//  内部类
class Outer {
    inner class Inner {
        fun getOuterReference(): Outer = this@Outer
    }
}

//  密封的类  todo
//sealed class Expr {
//    class Num(val value: Int) : Expr()
//    class Sum(val left: Expr, val right: Expr) : Expr()
//}

data class Client(val name: String, val postalCode: Int)


// 类似java 匿名内部类
object CaseInsensitiveFileComparator : Comparator<File> {
    override fun compare(file1: File, file2: File): Int {
        return file1.path.compareTo(file2.path,
                ignoreCase = true)
    }
}

data class PersonT(val name: String) {

    object NameComparator : Comparator<PersonT> {
        override fun compare(p1: PersonT, p2: PersonT): Int =
                p1.name.compareTo(p2.name)
    }

    companion object {
        val TAG: String = "static filed"
        fun FUN_STATIC() {
            println("this is static fun")
        }
    }

}
