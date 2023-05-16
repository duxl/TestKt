package a.b.c.kt.基础部分

fun main() {

    // 变量的类型是一个匿名函数
    val block: () -> String
    block = {
        val holiday = "New year."
        // 匿名函数返回函数体最后一行的结果
        "Happy $holiday"
    }

    println(block())
    println(block.invoke())

    // 类型推断（不用给block2指定类型，类型由匿名函数推断得出）
    val block2 = { name: String -> println("hello $name") }
    block2.invoke("zhangSan")
}