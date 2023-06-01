package a.b.c.kt.基础部分

// 导入的扩展函数使用as从命名
import a.b.c.kt.协程.printThread as printThreadName

typealias Num = Int
typealias Block<T> = (T) -> Unit

fun main() {
    val age: Num = 10
    println(age)
    println(age is Num)
    println(age is Int)

    val block: Block<String> = {
        println("hello: $it")
    }
    block.invoke("kt")
    println(block is Block<String>)

    // 使用重命名的函数
    printThreadName("别名demo")
}