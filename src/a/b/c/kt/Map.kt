package a.b.c.kt

fun main() {
    var mapOf = mapOf(1 to "a", 3 to "c", 2 to "b", 9 to "z", 8 to "y", 7 to "x")
    println("mapOf = ${mapOf.javaClass.name} = $mapOf") // 内部使用的 linkedMap

    var mutableMapOf = mutableMapOf(1 to "a", 3 to "c", 2 to "b", 9 to "z", 8 to "y", 7 to "x")
    println("mutableMapOf = ${mutableMapOf.javaClass.name} = $mutableMapOf") // 内部使用的 linkedMap

    var linkedMapOf = linkedMapOf(1 to "a", 3 to "c", 2 to "b", 9 to "z", 8 to "y", 7 to "x")
    println("linkedMapOf = ${linkedMapOf.javaClass.name} = $linkedMapOf") // 保持原有排序

    var hashMapOf = hashMapOf(1 to "a", 3 to "c", 2 to "b", 9 to "z", 8 to "y", 7 to "x")
    println("hashMapOf = ${hashMapOf.javaClass.name} = $hashMapOf") // 顺序无法保证

    var sortedMapOf = sortedMapOf(1 to "a", 3 to "c", 2 to "b", 9 to "z", 8 to "y", 7 to "x")
    println("sortedMapOf = ${sortedMapOf.javaClass.name} = $sortedMapOf") // 会根据key排序

    printSplitLine()


}