package a.b.c.kt.协程.流

import a.b.c.kt.协程.printThread
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun main() = runBlocking {
    //testFlowContext01()
    //testFlowContext02()
    //testFlowContext03()
    testFlowContext04()
}

// 流的收集collect函数总是在调用协程的上下文中发生，叫上下文保存
// 也就是构建流和收集流在同一个协程里面
suspend fun testFlowContext01() {
    withContext(Dispatchers.IO) {
        printThread("withContext")
        flowOf(1) // 构造在IO线程
                .collect {// 收集也在IO线程
                    printThread("collect $it")
                }
    }
}

// flow{}构建器必须遵循上下文保存，且不允许从其它上下文中发送emit，这个示例会报错
suspend fun testFlowContext02() {
    flow {// 调用flow在main线程，即构造流在main线程
        withContext(Dispatchers.IO) {
            emit(1) // 切换上下文中发送流，这里会报错。正确方式应该使用flowOn，参加testFlowContext03()
        }
    }.collect {
        printThread("collect $it")
    }
}

// 使用flowOn发送流的切换上下文
suspend fun testFlowContext03() {
    flow {// 调用flow在main线程，即构造流在main线程
        printThread("emit 1")
        emit(1) // 发送流在IO线程
    }.flowOn(Dispatchers.IO).collect {
        printThread("collect $it") // 收集流在main线程
    }
}

// 使用launchIn替换collect，我们可以在指定的协程中启动收集流（使用onEach收集）
suspend fun testFlowContext04() {
    flowOf(1, 2, 3)
            .onEach {
                printThread("onEach $it")
            }
            .launchIn(CoroutineScope(Dispatchers.IO)) // launchIn返回的是一个job对象
            .join()

}