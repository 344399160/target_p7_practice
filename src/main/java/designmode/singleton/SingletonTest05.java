package designmode.singleton;

/**
 * 双重检查
 *
 * 1.该概念是多线程开发中常使用到的，如代码中所示，我们进行了两次
 * if(singleton==null)检查，这样就可以保证线程安全了。
 * 2. 这样，实例化代码只用执行一次，后面再次访问时，判断if(singleton==null)，
 * 直接return实例化对象，也避免了反复进行方法同步
 * 3. 线程安全；延迟加载；效率较高
 * 结论：在实际开发中，推荐使用这种单例设计模式
 */
public class SingletonTest05 {
    public static void main(String[] args) {
        SingletonTest5 instance = SingletonTest5.getInstance();
        SingletonTest5 instance1 = SingletonTest5.getInstance();
        System.out.println(instance.equals(instance1));
    }
}
class SingletonTest5 {
    private static volatile SingletonTest5 instance;

    private SingletonTest5() {}

    /**
     * 提供一个静态的共有方法，加入双重检查代码，解决线程安全问题，同时解决懒加载问题
     */
    public static SingletonTest5 getInstance() {
        if (null == instance) {
            synchronized (SingletonTest5.class) {
                if (null == instance) {
                    instance = new SingletonTest5();
                }
            }
        }
        return instance;
    }
}
