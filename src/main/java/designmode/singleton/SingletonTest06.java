package designmode.singleton;

/**
 * 静态内部类方式
 *
 * 1. 这种方式采用了类装载的机制来保证初始化实例时只有一个线程
 * 2. 静态内部类方式在Singleton类被加载时并不会立即实例化，而是在需要实例化时，
 * 调用getInstance方法，才会装载SingletonInstance类，从而完成Singleton的实例化
 * 3. 类的静态属性只会在第一次加载类的时候初始化，所以在这里，JVM帮我们保证了
 * 线程的安全性，在类进行初始化时，别的线程是无法进入的
 * 优点：避免了线程不安全，利用静态内部类的特点实现延迟加载，效率高
 */
public class SingletonTest06 {
    public static void main(String[] args) {
        Singleton6 instance = Singleton6.getInstance();
        Singleton6 instance1 = Singleton6.getInstance();
        System.out.println(instance.equals(instance1));
    }
}
class Singleton6{

    private Singleton6() {}

    /**
     * 写一个静态内部类，该类中有一个静态属性
     */
    private static class SingletonInstance {
        private static final Singleton6 INSTANCE = new Singleton6();
    }

    public static Singleton6 getInstance() {
        return SingletonInstance.INSTANCE;
    }
}
