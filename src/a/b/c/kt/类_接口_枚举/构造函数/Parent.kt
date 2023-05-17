package a.b.c.kt.类_接口_枚举.构造函数

// 类名后面直接带括号表示主构造函数
// 主构造函数参数可以用var或val修复，给外部提供get和set方法（次构造函数参数不能用var、val修饰符）
open class Parent(var name: String) {
    // 次构造函数
    constructor(name: String, age: Int) : this(name) {
        println("次构造函数: $age")
    }

    fun say() {
        println("hello $name")
    }
}

fun main() {
    val p = Parent("zhangSan")
    p.say()

    val p2 = Parent("lisi", 23)
    p2.say()
}