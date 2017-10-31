/**
 * Created by slyang on 17/6/8.
 */


class A {
    // 隐含的标签 @A
    inner class B { // 隐含的标签 @B

        var tettt = this

        fun Int.foo() { // 隐含的标签 @foo
            val a = this@A // 指向 A 的 this
            val b = this@B // 指向 B 的 this
            val c = this // 指向 foo() 函数的接受者, 一个 Int 值
            val c1 = this@foo // 指向 foo() 函数的接受者, 一个 Int 值
            val funLit = lambda@ fun String.() {
                val d = this // 指向 funLit 的接受者
            }
            val funLit2 = { s: String ->
                // 指向 foo() 函数的接受者, 因为包含当前代码的 Lambda 表达式没有接受者
                val d1 = this
            }
        }
    }
}