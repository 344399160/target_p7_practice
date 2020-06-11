package designmode.singleton;

/**
 * 单例模式介绍：
 * 1. 单例模式保证了系统内存中该类只存在一个对象，节省了系统资源，对于一些需要频繁创建销毁的对象，
 * 使用单例模式可以提高系统性能
 * 2. 当想实例化一个单例类的时候，必须要记住使用相应的获取对象的方法，而不是使用new
 * 3. 单例模式使用的场景：需要频繁的进行创建和销毁的对象、创建对象时耗时过多或耗费资源过多（重量级对象），
 * 但又经常用到的对象、工具类对象、频繁访问数据库或文件的对象（比如数据源、session工厂等）
 * java.lang.Runtime 就有用到
 *
 * 饿汉式(静态常量)
 *
 * 优点：写法简单，在类加载的时候就完成实例化，避免了线程同步的问题
 * 缺点：在类加载的时候就完成实例化，没有达到lazy loading的效果。如果从始至终
 * 从未使用过这个实例，则会造成内存浪费
 *
 * 这种方式基于classloader机制避免了多线程的同步问题，不过，instance在类装载时
 * 就实例化，在单例模式中大多数都是调用getInstance方法，但是导致类加载的原因有
 * 很多种，因此不能确定有其他的方式（或者其他的静态方法）导致类装载，这时候初始化
 * instance就没有达到lazy loading的效果
 *
 * 结论：可用，可能造成内存浪费
 */
public class SingletonTest01 {
    public static void main(String[] args) {
        Singleton singleton = Singleton.getInstance();
        Singleton singleton2 = Singleton.getInstance();
        System.out.println(singleton == singleton2);
    }

}

class Singleton{

    private final static Singleton instance = new Singleton();

    /**
     * 构造器私有化，外部能new
     */
    private Singleton() {}

    public static Singleton getInstance() {
        return instance;
    }
}