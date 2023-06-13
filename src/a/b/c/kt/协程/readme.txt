协程的取消
1、取消作用域会取消它的子协程
2、被取消的子协程并不会影响其余兄弟协程
3、协程通过抛出一个特殊的异常CancellationException来处理取消操作
4、所有的kotlinx.coroutines中的挂起函数（withContext、delay等）都是可以取消的

coroutineScope与supervisorScope
1、coroutineScope：一个协程失败了，所有的其它兄弟协程也会取消
2、supervisorScope：一个协程失败了，不影响其它兄弟协程

协程的启动模式
1、DEFAULT：协程创建后，立即开始调度，在调度前如果被取消，将直接进入取消状态
2、ATOMIC：协程创建后，立即开始调度，协程执行到第一个挂起点之前不响应取消
3、LAZY：只有被需要时，包括主动调用协程的start、join或者await等函数才会开始调度，如果调度前被取消，那么改协程将直接进入异常结束状态
4、UNDISPATCHED：协程创建后理解在当前函数调用栈中被执行，直到第一个挂起点
