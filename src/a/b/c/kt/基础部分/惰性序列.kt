package a.b.c.kt.基础部分

import kotlin.random.Random


fun main(args: Array<String>) {

    test01(5)

    println("\n----------test02----------")
    test02()

}

/**
 * 随机产生N个随机数，要求随机数以三个0结尾
 */
fun test01(n: Int) {
    val r = Random(System.currentTimeMillis())

    /**
     * 惰性的意思是generateSequence一开始并不知道需要生成多少个元素
     * 一致到filter&take条件完成后才不会在参数元素，否则会不断调用
     * generateSequence的lambda代码
     */
    generateSequence { r.nextInt() }
            .filter { it.toString().endsWith("000") }
            .take(n)
            .forEach(::println)
}

/**
 * 初始种子值作为序列的起步值
 * 求：100~300序列
 */
fun test02() {
    // 函数返回null序列停止生成
    generateSequence(100) {
        if (it + 1 <= 300) it + 1 else null
    }.forEach(::println)
}