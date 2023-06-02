package a.b.c.kt.与java互调.JvmOverloads;

public class OverloadsJava {

    public static void main(String[] args) {
        OverloadsKotlinKt.test1("zs", 12, true);
        // 可以调用重载方法，如果kotlin中没有使用@JvmOverloads，那么所有参数都不能省略
        OverloadsKotlinKt.test1();
    }
}
