package a.b.c.kt.协程

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * 普通工程中添加coroutines的依赖：
 * Project Structure-》Modules-》Add-》Library-》From Maven
 * 输入地址：org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.0-》点击OK
 *
 * https://www.kotlincn.net/docs/reference/coroutines/coroutines-guide.html
 */
fun main() {
    //  GlobalScope 启动协程的生命周期只受整个应用程序的生命周期限制
    GlobalScope.launch {
        printThread("协程中打印")
    }
    printThread("main中打印")
    // 这里需要延迟，不然主线程很有可能比协程提前结束
    Thread.sleep(1000)
}