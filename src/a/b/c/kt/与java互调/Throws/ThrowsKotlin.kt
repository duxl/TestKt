package a.b.c.kt.与java互调.Throws

import java.io.IOException
import kotlin.jvm.Throws

/**
 * 默认情况抛出的异常，java段调用不需要捕获
 */
fun testException1() {
    throw IOException("testException1异常了")
}

/**
 * 使用Throws后，java调用必须捕获这个异常，否则编译不过
 */
@Throws(IOException::class)
fun testException2() {
    throw IOException("testException2异常了")
}

fun main() {
    // kt里面调用带异常的方法，不需要捕获就可以变过通过
    testException1()
    testException2()
}