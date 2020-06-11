package designmode.singleton;

/**
 * 饿汉式 （静态代码块）
 *
 * 优点： 这种方式和第一种类似，只不过将类实例化的过程放在了静态代码块中，
 * 也是在类装载的时候就执行静态代码块中的代码，初始化类的实例
 *
 * 缺点：和1一致
 *
 * 这种单例模式可用，但是可能造成内存浪费
 */
public class SingletonTest02 {
    public static void main(String[] args) {
        Singleton2 singleton = Singleton2.getInstance();
        Singleton2 singleton2 = Singleton2.getInstance();
        System.out.println(singleton == singleton2);
    }
}

class Singleton2 {

    private final static Singleton2 instance;

    public Singleton2() {}

    static { // 在静态代码块中，创建单例对象
        instance = new Singleton2();
    }

    public static Singleton2 getInstance() {
        return instance;
    }

}