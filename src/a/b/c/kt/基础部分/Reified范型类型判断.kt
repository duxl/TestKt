package a.b.c.kt.基础部分

/**
 * 使用reified关键字，方法必须使用inline修饰
 */
inline fun <reified T> sayType(arg: Any) {
    if (arg is T) {
        println("${arg}和范型T是同一个类型")
    } else {
        println("${arg}和范型T是类型不同")
    }
}

fun main() {
    sayType<Int>(1)
    sayType<Int>(2.0f)
}