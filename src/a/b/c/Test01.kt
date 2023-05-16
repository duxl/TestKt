package a.b.c

const val PI = 3.14

fun main(args: Array<String>) {
    var items = listOf("1a", "2b", "1c", "4d")
    items.myLet {
        println(size)
    }


}

inline fun <T> T.myLet(block: T.() -> Unit) {
    block(this)
}