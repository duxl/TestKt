package a.b.c.kt.类_接口_枚举

/**
 * 只有一个抽象方法的接口称为函数式接口或 SAM（单一抽象方法）接口。
 * 函数式接口可以有多个非抽象成员，但只能有一个抽象成员。
 *
 * ps：函数式接口通过lambda调用，代码更加简洁易读
 */
fun interface OnClickListener1 {
    fun onClick(text: String)
}

interface OnClickListener2 {
    fun onClick(text: String)
}

fun main() {
    val listener1 = OnClickListener1 {
        println("函数式接口被调用：$it")
    }
    listener1.onClick("通过 SAM 转换,配合Lambda使用代码更加简洁")

    val listener2 = object : OnClickListener2 {
        override fun onClick(text: String) {
            println("普通接口被调用：$text")
        }
    }
    listener2.onClick("只能创建一个类的实例")
}