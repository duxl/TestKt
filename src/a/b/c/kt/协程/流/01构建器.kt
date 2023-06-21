package a.b.c.kt.协程.流

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
    testFlowBuild01()
    //testFlowBuild02()
    //testFlowBuild03()
}

// 使用flow-emit构造流
suspend fun testFlowBuild01() {
    flow<Int> {
        emit(1)
        delay(1000)
        emit(3)
        delay(1000)
        emit(5)
    }
            .collect {
                println("flow collect $it")
            }
}

// 使用flowOf构建
suspend fun testFlowBuild02() {
    //flowOf(1..3)
    flowOf(1, 2, 3)
            .collect {
                println("flowOf collect $it")
            }
}

// 使用asFlow构建
suspend fun testFlowBuild03() {
    (10..30 step 10).asFlow()
            .collect {
                println("asFlow collect $it")
            }
}