package a.b.c.kt.与java互调.JvmStatic;

public class StaticJava {

    public static void main(String[] args) {

        // 像静态字段和方法一样的方式访问
        System.out.println(StaticKotlin.name);
        StaticKotlin.sayHello();

        // 普通半生对象的访问
        System.out.println(StaticKotlin.Companion.getAge());
        StaticKotlin.Companion.say();
    }
}
