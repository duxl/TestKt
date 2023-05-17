package a.b.c.kt.基础部分

class TestByLazy {
    val num by lazy {
        println("初始化num")
        10
    }

    init {
        println("执行init")
        println()
    }
}

fun main() {
    // 实例化对象的时候不会初始化num
    val test = TestByLazy()
    Thread.sleep(3000)
    // 访问num时才初始化
    println("访问num")
    test.num
}