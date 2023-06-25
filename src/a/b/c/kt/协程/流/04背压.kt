package a.b.c.kt.协程.流

import a.b.c.kt.协程.printThread
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

// 背压产生的原因：生产者的效率高于消费者，导致整个效率降低（消费者脱慢了生产者）
// 解决背压的方式：1使用buffer把生产者产出缓冲起来、2将生产者切换到其它线程中异步执行

fun main() = runBlocking<Unit> {
    testBackPressure01()
    testBackPressure02()
    testBackPressure03()
}

// 每个100毫秒emit一个元素
fun generateFlow() = flow {
    repeat(3) {
        delay(100)
        printThread("emit $it")
        emit(it)
    }
}

// 此示例会产生背压：每隔100毫秒生产一个元素，每个300毫秒消费一个元素
// 导致一个元素的处理需要400毫秒，三个元素共计需要1200毫秒左右
suspend fun testBackPressure01() {
    val time = measureTimeMillis {
        generateFlow()
                .collect {
                    delay(300)
                    printThread("collect $it")
                }
    }
    printThread("背压问题导致总耗时：$time")
}

// 使用buffer解决背压方式
suspend fun testBackPressure02() {
    val time = measureTimeMillis {
        generateFlow()
                // 生产过快，这里设置缓冲区大小为100来缓存生产的元素
                .buffer(100)
                .collect {
                    delay(300)
                    printThread("collect $it")
                }
    }
    printThread("使用buffer解决背压总耗时：$time")
}

// 使用切换线程解决背压
suspend fun testBackPressure03() {
    val time = measureTimeMillis {
        generateFlow()
                // 将生产者切换的其它线程
                .flowOn(Dispatchers.Default)
                .collect {
                    delay(300)
                    printThread("collect $it")
                }
    }
    printThread("使用异步解决背压总耗时：$time")
}