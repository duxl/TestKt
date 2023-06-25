package a.b.c.kt.协程.流

import a.b.c.kt.协程.printThread
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking

/**
 * 流的异常发生可能在构建地方（上游），也有可能在收集的地方（下游）
 * 所以异常处理要区分两处的方式
 */
fun main() = runBlocking<Unit> {
    //testFlowException01()
    //testFlowException02()
    testFlowException03()
}

// 下游异常：使用try-catch处理收集异常（在collect中处理）
private suspend fun testFlowException01() {
    flowOf(1, 2, 3)
            .collect {
                try {
                    check(it <= 2)
                    printThread("collect: $it")
                } catch (e: Exception) {
                    printThread("异常了：${e.message}")
                }
            }
}

// 下游异常：使用try-catch处理收集异常（try整个flow代码块）
private suspend fun testFlowException02() {
    try {
        flowOf(1, 2, 3)
                .collect {
                    check(it <= 2)
                    printThread("collect: $it")
                }
    } catch (e: Exception) {
        printThread("异常了：${e.message}")
    }
}

// 上游异常：使用try-catch处理构建异常（不建议这么做，打破了flow的设计原则）
private suspend fun testFlowException03() {
    try {
        flow {
            emit(1)
            emit(2)
            throw RuntimeException("my message")
            emit(3)
        }
                .collect {
                    printThread("collect: $it")
                }
    } catch (e: Exception) {
        printThread("异常了：${e.message}")
    }
}

private suspend fun testFlowException04() {
    flow {
        emit(1)
        emit(2)
        throw RuntimeException("my message")
        emit(3)
    }.catch {
        printThread("异常了：${it.message}")
        emit(0)
    }
            .collect {
                printThread("collect: $it")
            }
}
