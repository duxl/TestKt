package a.b.c.kt.类_接口_枚举.委托

// 这也适用于 var 属性，如果把只读的 Map 换成 MutableMap 的话
class DelegateMap(map: Map<String, Any>) {
    // 相当于name = map.get("name")
    val name: String by map

    // 相当于sex = map.get("sex")
    val sex: String by map

    // 相当于age = map.get("age")
    val age: Int by map
}

fun main() {
    val dm = DelegateMap(mapOf(
            "age" to 19,
            "name" to "zhangSan",
            "sex" to "boy"
    ))

    println(dm.name)
    println(dm.sex)
    println(dm.age)
}