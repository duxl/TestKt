package a.b.c.kt.基础部分

// kotlin中的lambda就是闭包
// 能接收函数或返回函数的函数叫高级函数
fun main() {
    say().invoke("张三")
}

// say方法返回一个匿名函数
fun say(): (name: String) -> Unit {
    // 匿名函数能修改并引用定义在自己的作用域之外的变量,
    val pre = "hello"
    return { println("$pre $it") }
}