package a.b.c.kt.与java互调.JvmStatic;

public class StaticJava {

    public static void main(String[] args) {
        test1();
        test2();
    }

    /**
     * companion object 伴生中使用@JvmStatic
     */
    private static void test1() {
        // 像静态字段和方法一样的方式访问
        System.out.println(StaticKotlin.name);
        StaticKotlin.sayHello();

        // 普通半生对象的访问
        System.out.println(StaticKotlin.Companion.getAge());
        StaticKotlin.Companion.say();
    }

    /**
     * object class 单例中使用@JvmStatic
     */
    private static void test2() {
        // 像静态方法一个样调用object的单例
        ObjectKotlin.test1();

        // 普通没有加@JvmStatic的object单例方法
        ObjectKotlin.INSTANCE.test2();
    }
}
