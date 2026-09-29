// Kotlin 顶层函数，指定生产java代码所在的类名为KtUtils
@file:JvmName(name = "KtUtils")

package a.b.c.kt.与java互调.Top

fun sayHello(name: String) {
    println("hello $name")
}
