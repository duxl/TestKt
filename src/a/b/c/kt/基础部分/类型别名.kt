package a.b.c.kt.基础部分

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
}