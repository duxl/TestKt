package a.b.c.kt.类_接口_枚举.委托

import kotlin.reflect.KProperty

class Delegate {
    // thisRef参数类型限制委托可作用与那个类型上
    // property参数可取得委托作用与类型的哪个属性
    operator fun getValue(thisRef: Example?, property: KProperty<*>): String {
        return "$thisRef, thank you for delegating '${property.name}' to me!"
    }

    operator fun setValue(thisRef: Example?, property: KProperty<*>, value: String) {
        println("$value has ben assigned to '${property.name}' in $thisRef.")
    }
}

class Example {
    var p: String by Delegate()
}

fun main() {
    /*var str: String by Delegate()
    str = "abc"
    println(str)*/

    var e = Example()
    println(e.p)
    e.p = "abc"
}