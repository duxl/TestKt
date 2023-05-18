package a.b.c.kt.基础部分

/**
 * 操作符归类：
 * https://blog.csdn.net/qq_32677531/article/details/127336188
 *
 * 1、一元操作符
 * +a   a.unaryPlus()
 * -a   a.unaryMinus()
 * !a   a.not()
 *
 * 2、递增递减
 * a++，++a  a.inc()
 * a--,--a  a.dec()
 *
 * 3、二元操作符
 * a+b  a.plus(b)
 * a-b  a.minus(b)
 * a*b  a.times(b)
 * a/b  a.div(b)
 * a%b  a.rem(b)
 *
 * 4、符合赋值操作符
 * a+=b a.plusAssign(b)
 * a-=b a.minusAssign(b)
 * a*=b a.timesAssign(b)
 * a/=b a.divAssign(b)
 * a%=b a.remAssign(b)
 *
 * 5、".."操作符
 * a..b a.rangeTo(b)
 *
 * 6、in操作符
 * a in b   b.contains(a)
 * a !in b  !b.contains(a)
 *
 * 7、索引访问操作符
 * a[i]     a.get(i)
 * a[i,j]   a.get(i,j)
 * a[i]=b   a.set(i, b)
 * a[i,j]=b a.set(i,j,b)
 *
 * 6、==操作符
 * a==b     a?.equals(b)?:(b===null)
 * a!=b     !(a?.equals(b)?:(b===null))
 *
 * 7、比较操作符（>、<、>=、<=）
 * a>b  a.compareTo(b)>0
 * a<b  a.compareTo(b)<0
 * a>=b a.compareTo(b)>=0
 * a<=b a.compareTo(b)<=0
 *
 * 8、调用操作符
 * a()      a.invoke()
 * a(i)     a.invoke(i)
 * a(i,j)   a.invoke(i,j)
 */
data class Point(var x: Int, var y: Int) {
    // + 把一个对象添加到另一个对象里
    operator fun plus(other: Point) = Point(other.x + x, other.y + y)

    // += 把一个对象添加到另一个对象里
    // 让后将结果赋值给第一个对象
    operator fun plusAssign(other: Point) {
        x += other.x
        y += other.y
    }

    // plusAssign必须有且有一个参数(参数类型任意)
    operator fun plusAssign(other: Pair<Int, Int>) {
        x += other.first
        y += other.second
    }

    // ==操作符重载
    override operator fun equals(other: Any?): Boolean {
        if (other is Point) {
            return x == other.x && y == other.y
        }
        return false
    }

    // 大于操作符重载
    operator fun compareTo(other: Point): Int {
        val sum = x + y
        val otherSum = other.x + other.y
        return when {
            sum - otherSum > 0 -> 1
            sum - otherSum < 0 -> -1
            else -> 0
        }
    }

    //..操作符
    operator fun rangeTo(other: Point): IntRange {
        // 本示例是将x的和与y的和做IntRange返回
        val sumX = x + other.x
        val sumY = y + other.y
        return if (sumX < sumY) sumX.rangeTo(sumY) else sumY.rangeTo(sumX)
    }
}

fun main() {
    val p1 = Point(10, 20)
    val p2 = Point(3, 5)

    val plusResult = p1 + p2
    println(plusResult)

    plusResult += Point(1, 2) // plusAssign
    println(plusResult)

    plusResult += Pair(6, 3) // plusAssign
    println(plusResult)

    println(p1 == Point(10, 20)) // equals

    println(p1 > p2) // 调用compareTo

    println(Point(1, 2)..Point(3, 0))
}