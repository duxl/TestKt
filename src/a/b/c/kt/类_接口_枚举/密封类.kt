package a.b.c.kt.类_接口_枚举

/**
 * 密封类也是类，比普通类多了一些特性而已：
 * 密封类是为继承设计的，是一个抽象类,更多在于限制继承
 * 密封类的子类是确定的，除了已经定义好的子类外，不能再有其他子类
 * 子类可以不在密封类的内部，但必须在同一个package下面
 */

// 示例1：驾驶证状态，无证，正在学习，有证(有驾驶证编号)
sealed class DriverLicenseState {
    object NONE : DriverLicenseState()
    object STUDY : DriverLicenseState()
    class Hold(val no: String) : DriverLicenseState()
}

fun test1(state: DriverLicenseState) {
    when (state) {
        DriverLicenseState.NONE -> println("没有驾驶证")
        DriverLicenseState.STUDY -> println("正在学习驾驶证")
        is DriverLicenseState.Hold -> println("有驾驶证，编号：${state.no}")
    }
}

// 示例1：颜色
sealed class Color(val hex: String) {
    object RED : Color("#FF0000")
    object GREEN : Color("#00FF00")
    object BLUE : Color("#0000FF")
}

// 子类可以不在密封类的内部，但必须在同一个package下面
object YELLOW : Color("#FFF000")


// 示例3：密封接口，不希望外部实现方法，提供有限的实现方法
sealed interface People {
    fun sayHello()

    object Man : People {
        override fun sayHello() {
            println("hello: man")
        }
    }

    object Woman : People {
        override fun sayHello() {
            println("hello: woman")
        }
    }
}

// 示例4：密封类本身也可以继承其它类
sealed class MyException(msg: String) : Exception(msg) {
    object NullException : MyException("空指针异常")
    object NumberFormatException : MyException("数字格式化异常")
}

fun main() {
    // 示例1
    test1(DriverLicenseState.NONE)
    test1(DriverLicenseState.Hold("123456"))

    // 示例2
    println("RED的十六进制${Color.RED.hex}")

    // 示例3
    People.Man.sayHello()

    // 示例4
    MyException.NullException.printStackTrace()
}