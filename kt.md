var：定义变量
val：定义常量
fun：定义方法
Unit：默认方法返回值，类似于Java中的void，可以理解成返回没什么用的值
vararg：可变参数
$：字符串模板(取值)
位运算符：or(按位或)，and(按位与)，shl(有符号左移)，shr(有符号右移)，
ushr(无符号右移)，xor(按位异或)，inv(按位取反)
in：在某个范围中
downTo：递减，循环时可用，每次减1
step：步长，循环时可用，设置每次循环的增加或减少的量
when：Kotlin中增强版的switch，可以匹配值，范围，类型与参数
is：判断类型用，类似于Java中的instanceof()

#this

###在类的成员函数中,this指向这个类的当前对象实例
###在扩展函数中,或带接收者的函数字面值(function literal) 中, this 代表调用函数时, 在点号左侧传递的接收者参数；

###如果this没有限定符,那么它指向包含当前代码的最内层范围.如果想要指向其他范围内的this,需要使用标签限定符。



#import 默认倒入
####-- kotlin.
####-- kotlin.annotation.
####-- kotlin.collections.
####-- kotlin.comparisons. (since 1.1)
####-- kotlin.io.
####-- kotlin.ranges.
####-- kotlin.sequences.
####-- kotlin.text

##一些增强包会根据平台来决定是否默认导入:

###-- JVM:
####---- java.lang.
####---- kotlin.jvm.
###-- JS:
####---- kotlin.js.*
  
###http://blog.csdn.net/to_yan/article/details/51881818  
###继承 
由于kotlin中所有类和方法默认都是final的，
不能直接继承或重写
，需要继承的类或类中要重写的方法都应当在定义时添加open关键字  