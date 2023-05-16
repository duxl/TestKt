package a.b.c.kt.基础部分

import a.b.c.kt.printSplitLine

fun main() {
    testCreate()
    testFun()
}

fun testFun() {
    printSplitLine("使用Map的方法")

    var map = mapOf("Java" to 86, "Kotlin" to 92, "Go" to 78)
    var all = map.all {
        it.value > 90 && it.key.contains("j")
    }
    println("all() 方法： map中所有item都成立才会返回真：$all")

    var any = map.any {
        it.key.contains("Kot")
    }
    println("any() 方法： map中任意一个item成立都会返回真：$any")

    println("Kotlin in map = ${"Kotlin" in map}")
    println("Kot in map = ${"Kot" in map}")

    var result = map.map {
        it.key + it.value
    }
    println("map()变换：变换后的集合$result")


    var maxItem = map.maxBy {
        it.value
    }
    if (maxItem != null) {
        println("最大的Item项：key=${maxItem.key}, value=${maxItem.value}")
    }
    // minBy同理

    var map1 = mapOf<String, Int>("Java" to 86, "Kotlin" to 92, "Go" to 80)
    var map2 = mapOf<String, Int>("C" to 85, "Kotlin" to 92, "Go" to 79);
    println("map并集,相同的key用最新的value：${map1 + map2}")
    println("map相减,减数是字符串或字符串集合：${map1 - "Java"}")

    printSplitLine("遍历map")

    println("方式1")
    for (entry in map) {
        println("key=${entry.key}, value=${entry.value}")
    }

    println("方式2")
    for ((k, v) in map) {
        println("key=$k, value=$v")
    }

    println("方式3")
    for(k in map.keys) {
        println("key=$k, value=${map.get(k)}")
    }

    println("方式4")
    map.forEach {println("key=${it.key}, value=${it.value}")}

}

fun testCreate() {
    printSplitLine("声明和创建Map集合")

    var mapOf = mapOf(1 to "a", 3 to "c", 2 to "b", 9 to "z", 8 to "y", 7 to "x")
    println("mapOf(不可变) = ${mapOf.javaClass.name} = $mapOf") // 内部使用的 linkedMap

    var mutableMapOf = mutableMapOf(1 to "a", 3 to "c", 2 to "b", 9 to "z", 8 to "y", 7 to "x")
    println("mutableMapOf(可变) = ${mutableMapOf.javaClass.name} = $mutableMapOf") // 内部使用的 linkedMap

    var linkedMapOf = linkedMapOf(1 to "a", 3 to "c", 2 to "b", 9 to "z", 8 to "y", 7 to "x")
    println("linkedMapOf(可变) = ${linkedMapOf.javaClass.name} = $linkedMapOf") // 保持原有排序

    var hashMapOf = hashMapOf(1 to "a", 3 to "c", 2 to "b", 9 to "z", 8 to "y", 7 to "x")
    println("hashMapOf(可变) = ${hashMapOf.javaClass.name} = $hashMapOf") // 顺序无法保证

    var sortedMapOf = sortedMapOf(1 to "a", 3 to "c", 2 to "b", 9 to "z", 8 to "y", 7 to "x")
    println("sortedMapOf(可变) = ${sortedMapOf.javaClass.name} = $sortedMapOf") // 会根据key排序

    println("可变map提供了如下方法: clear()、remove()、put()、putAll()")
}