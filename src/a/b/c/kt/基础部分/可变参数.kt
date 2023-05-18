package a.b.c.kt.基础部分

fun printlnNums(vararg nums: Int) {
    nums.forEach { println(it) }
}

fun showNums(vararg nums: Int) {
    println("可变参数传给方法，参数前面加星号")
    printlnNums(*nums)
}

fun main() {
    printlnNums(1, 2, 3)
    showNums(7, 8, 9)
}