package a.b.c.kt.类_接口_枚举.构造函数

// 这种继承方式不能调用父类的次构造函数
class Child : Parent("小孩") {

    // 像这种继承直接在主构造函数中调用了父类的主构造函数的情况
    // 无法再在次构造函数中调用父类的次构造函数
    //constructor(name: String, age: Int) : super(name, age)
}


// 这种继承的方式，可以调用父类的次构造函数
class Girl : Parent {
    constructor(name: String) : super(name)
    constructor(name: String, age: Int) : super(name, age)
}

fun main() {
    Child().say()

    Girl("女孩").say()
    Girl("女孩2", 13).say()
}