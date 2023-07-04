package a.b.c.kt.协程.通道

import kotlinx.coroutines.*
import kotlinx.coroutines.selects.select

/**
 * 多路复用:select无参数返回的情况
 */
fun main() = runBlocking<Unit> {

    val job01 = GlobalScope.launch {
        println("job01 starting...")
        delay(200)
        println("job01 finish")
    }

    val job02 = GlobalScope.launch {
        println("job02 starting...")
        delay(100)
        println("job02 finish")
    }

    val job03 = GlobalScope.launch {
        select<Unit> {
            // job01和job02会先执行完毕，就会执行onJoin里面的代码
            job01.onJoin{ println("job01 onJoin（我回调了，其它不会被回调）")}
            job02.onJoin{ println("job02 onJoin（我回调了，其它不会被回调）")}
        }
    }

    joinAll(job01, job02, job03)
}