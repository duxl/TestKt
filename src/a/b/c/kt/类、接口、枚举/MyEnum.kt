package a.b.c.kt.`类、接口、枚举`

/**
 * kt中枚举也是class
 */
enum class MyEnum {
    Java,
    Kotlin
}

enum class PeopleEnum(name: String) {
    Man("男人"),
    Woman("女人");

    fun say() {
        println("我是$name")
    }
}

fun main() {
    println(MyEnum.Java)
    PeopleEnum.Man.say()
}