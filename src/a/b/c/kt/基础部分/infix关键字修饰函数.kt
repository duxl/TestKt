package a.b.c.kt.基础部分

/**
 * 1、infix关键字修饰的函数必须是成员函数或扩展函数
 * 2、infix修饰的函数只能有一个参数
 */
infix fun String.myLink(str: String): String {
    return this.plus(str)
}

class TestLink(val str: String) {
    infix fun myLink(other: TestLink) {
        println("str=$str, other=${other.str}")
    }
}

fun main() {
    //上面myLink扩展函数常规调用
    println("hello".myLink(" kotlin"))
    // 使用infix方式调用
    println("hello" myLink " world")

    TestLink("hello") myLink TestLink("abc")

    // ps:Map相关的Pair类的to函数也是用类infix修饰

}