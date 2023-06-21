package a.b.c.kt.协程.流

import a.b.c.kt.协程.printThread
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull

/**
 * 流采用与协程同样的协作取消，收集collect函数是在协程中调用
 */
fun main() = runBlocking {
    //testFlowCancel01()
    testFlowCancel02()
}

// 使用withTimeoutOrNull超时取消流
suspend fun testFlowCancel01() {
    // 2.5秒后超时，每隔1秒emit一个元素，总共只能emit2个元素
    withTimeoutOrNull(2500) {
        flowOf(1, 2, 3)
                .onEach { delay(1000) }
                .collect {
                    printThread("collect $it")
                }
    }
}

// 使用协程作用域CoroutineScore的cancel方法取消
fun testFlowCancel02() = runBlocking {
    (1..3).asFlow()
            .onEach { delay(1000) }
            .cancellable() // 这里要明确调用流是可取消的，否则计算密集型的流不能被取消
            .collect {
                printThread("collect $it")
                if (it == 2) {
                    // 这里的取消是取消协程，等同于取消了flow的收集
                    cancel()
                }
            }
}

