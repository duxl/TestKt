package a.b.c.kt.类_接口_枚举

/**
 * 类、接口默认访问符都是publish，private需要手动设置
 * 类默认是final的，需要继承需要手动设置open
 */
class Benz : ICar {
    override fun run() {
        println("奔驰run")
    }
}

fun main() {
    Benz().run()
}