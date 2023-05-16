package a.b.c.kt.基础部分

fun main() {
    // .. 左右闭区间
    if (3 in 0..10) {
        println("3 in 0 <=..<=10")
    }

    // until 左闭右开区间
    if(3 in 0 until 10) {
        println("3 in 0<= until <10")
    }

    // 打印0、1、2，不打印3
    for (i in 0 until 3) {
        println(i)
    }
}