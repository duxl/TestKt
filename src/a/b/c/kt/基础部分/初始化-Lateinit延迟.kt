package a.b.c.kt.基础部分

/***
 * lateinit
 * 1、只能修饰非空类型的变量，并且只能在类体中使用，不能在函数体或者代码块中使用
 * 2、这个变量不能是基本类型
 * 3、必须在使用前进行初始化，否则会抛出异常
 */
class TestLateInit {
    lateinit var num: String

    fun showNum() {
        init()
        println("num is $num")
    }

    private fun init() {
        if (!::num.isInitialized) {
            println("初始化num")
            num = "10"
        }
    }
}

fun main() {
    TestLateInit().showNum()
}