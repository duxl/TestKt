/**
 * 使用该注解可以指定生成java文件的类名称
 * 不实用下面的注解，默认生成的java文件是NameKotlinKt，就是kt文件名后面加Kt关键字
 */
@file:JvmName("MyKotlin")

package a.b.c.kt.与java互调.JvmName

fun sayHello(name: String) {
    println("hello: $name")
}

