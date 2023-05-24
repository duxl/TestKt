package a.b.c.kt.类_接口_枚举.委托

import kotlin.reflect.KProperty

class Later<T>(private val block: () -> T) {
    private var value: T? = null
    operator fun getValue(any: Any?, prop: KProperty<*>): T {
        if (value == null) {
            value = block.invoke()
        }
        return value!!
    }
}

//fun <T> later(block: () -> T): Later<T> = Later(block)
fun <T> later(block: () -> T) = Later(block)

fun main() {
    // 这里使用委托类
    val age by Later {
        println("访问age打印")
        18
    }
    println("访问age之前打印")
    println(age)

    // 这里调用later方法间接调用Later委托类，看起来更像by lazy的方式
    val name by later {
        println("访问name打印")
        "abc"
    }
    println("访问name之前打印")
    println(name)
    println(name)

}