package a.b.c.kt.类_接口_枚举


/**
 * 伴生对象好比java的静态方法或变量
 */
class CompanionObj {
    companion object {
        const val pageSize = 20
        fun say() {
            println("hello")
        }
    }
}

fun main() {
    println(CompanionObj.pageSize)
    CompanionObj.say()
}