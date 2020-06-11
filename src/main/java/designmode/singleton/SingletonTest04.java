package designmode.singleton;

/**
 * 懒汉式 2
 *
 * 1. 解决了线程不安全问题
 * 2. 效率太低了，每个线程在想活获得类的实例时候， 执行getInstance()方法都要进行
 * 同步。而其实这个方法只执行一次实例化代码就够了，后面的想获得该类实例直接return就行了
 * 方法进行同步效率太低
 * 结论：在实际开发中，不推荐使用这种方式
 */
public class SingletonTest04 {

    public static void main(String[] args) {
        Singleton4 instance = Singleton4.getInstance();
        Singleton4 instance1 = Singleton4.getInstance();
        System.out.println(instance.equals(instance1));
    }
}

class Singleton4 {
    private static Singleton4 instance;

    private Singleton4() {}

    /**
     * 提供一个静态的公有方法，加入同步处理的代码，解决线程安全问题
     * 即懒汉模式
     */
    public synchronized static Singleton4 getInstance() {
        if (null == instance) {
            instance = new Singleton4();
        }
        return instance;
    }
}