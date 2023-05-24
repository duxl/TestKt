package a.b.c.kt.类_接口_枚举.委托

interface Base {
    fun show(str: String)
}

class BaseImpl : Base {
    override fun show(str: String) {
        println("base impl show: $str")
    }
}

class Driver(b: Base) : Base by b {
    // 如果Driver覆盖了此方法，那么不会调用委托实现的方法
    /*override fun show(str: String) {
        println("driver $str")
    }*/
}

fun main() {
    Driver(BaseImpl()).show("123")
}