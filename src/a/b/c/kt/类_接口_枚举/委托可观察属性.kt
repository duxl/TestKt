package a.b.c.kt.类_接口_枚举

import kotlin.properties.Delegates

// Delegates.observable可观察属性变化的前后值
// Delegates.vetoable 可观察属性变化的前后值，并可决定新的值是否生效
class ObservableUser {

    var name: String by Delegates.observable("initValue") { prop, old, new ->
        // name的赋值会回掉这里
        println("$old -> $new")
    }

    // 年龄初始化0，后续修改只能在0到100的闭区间，否则年纪修改不会生效
    var age: Int by Delegates.vetoable(0) { prop, old, new ->
        println("$old -> $new")
        // lambda表达式最后一行返回:如果最新age在0···100闭区间返回true，否则返回false
        new in 0..100
    }
}

fun main() {
    val user = ObservableUser()
    println(user.name)
    user.name = "first"
    user.name = "second"

    println(user.age)
    user.age = 30
    user.age = 500
    println(user.age)
}