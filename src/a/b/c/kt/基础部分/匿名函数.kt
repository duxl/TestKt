package a.b.c.kt.基础部分

fun main() {
    // 普通函数
    var count = "hello".count()
    println("count=$count")

    // 使用匿名函数（匿名函数也就是lambda表达式）
    // 统计字符串中’l’字符的个数
    count = "hello".count { it =='l' }
    println("count=$count")
}