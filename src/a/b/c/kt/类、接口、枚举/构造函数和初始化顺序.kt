package a.b.c.kt.`类、接口、枚举`

// 执行顺序：①主构造函数②初始化块或属性③次构造函数
// show Kotlin bytecode 反编译成java代码后会发现，
// 主构造函数圆括号里面的属性最先初始化，然后属性bobby
// 和init代码块都在主构造函数中执行，次构造函数最后执行
class User(var name: String = "张三") {

    init {
        // 初始化块和属性bobby的执行顺序由编写代码的顺序决定
        println("初始化块 $name")
    }

    var hobby = "旅游"

    constructor(name: String = "李四", age: Int = 29) : this(name) {
        println("次构造函数里面的代码最后执行")
    }
}