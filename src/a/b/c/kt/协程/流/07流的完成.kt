package a.b.c.kt.协程.流

import a.b.c.kt.协程.printThread
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
    //testFlowFinish01()
    testFlowFinish02()
}

// 方式一：try-finally
private suspend fun testFlowFinish01() {
    try {
        (0..5).asFlow()
                .collect {
                    printThread("collect: $it")
                }
    } finally {
        printThread("---- OVER ----")
    }
}

// 方式二：onCompletion函数
private suspend fun testFlowFinish02() {
    (0..5).asFlow()
            .onCompletion {
                printThread("---- OVER ----")
            }
            .collect {
                printThread("collect: $it")
            }
}