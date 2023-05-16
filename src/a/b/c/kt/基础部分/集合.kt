package a.b.c.kt.基础部分

fun main() {
    // 不可变集合
    val list = listOf("Java", "Kotlin", "ObjectC")
    println(list[0])
    println(list.getOrElse(5) { "unknown" })
    println(list.getOrNull(5))

    // 可变集合
    val mutList = mutableListOf("a", "b")
    mutList.add("c")
    println(mutList)

}