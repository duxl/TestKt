package a.b.c.kt.基础部分

@JvmInline
value class TeamId(val value: Int) {

    /**
     * 可以有init初始化逻辑
     */
    init {
        require(value > 0) {
            "value must be greater than or equal to 0"
        }
    }
}

@JvmInline
value class PlayerId(val value: Int)

fun show(teamId: TeamId, playerId: PlayerId) {
    println("球队id=${teamId.value}")
    println("球员id=${playerId.value}")
}

fun main(args: Array<String>) {
    // @JvmInline value class
    // 1、明确参数的含义
    // 2、强类型，避免都是Int传错值
    // 3、Kotlin/JVM 在可能的情况下直接使用 Int
    // 4、尽量不要用在范型和可空函数上
    show(TeamId(1), PlayerId(2))

}

