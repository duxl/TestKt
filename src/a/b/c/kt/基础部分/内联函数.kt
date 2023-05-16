package a.b.c.kt.基础部分

/**
 * inline的作用
 * 当一个函数被inline标注后，在编译时期，
 * 把调用这个函数的地方用这个函数的方法体进行替换。
 * 简而言之，就是说直接把代码复制过去一份，
 * 不通过方法间压栈进栈的方式进行调用
 */
fun main() {
    val block = { name: String -> println("hello $name") }
    test("zangSan", block)
}

// 带有lambda函数参数的函数，建议使用inline，能显著提高性能
private inline fun test(name: String, block: (String) -> Unit) {
    println("这是内联函数的打印语句")
    block.invoke(name)
}