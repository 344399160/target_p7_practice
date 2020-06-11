package designmode.singleton;

/**
 * 懒汉式 1
 *
 * 1. 起到了懒加载的效果，但是只能在单线程下使用
 * 2. 如果在多线程下，一个线程进入了if(singleton==null)判断语句块，还未来得及
 * 往下执行，另一个线程也通过了这个判断语句，这时便会产生多个实例，所以在多线程
 * 环境下不可使用这种方式
 *
 * 结论：在实际开发中不要使用该方式
 */
public class SingletonTest03 {

    public static void main(String[] args) {
        Singleton3 instance = Singleton3.getInstance();
        Singleton3 instance1 = Singleton3.getInstance();
        System.out.println(instance.equals(instance1));
    }
}

class Singleton3 {

    private static Singleton3 instance;

    private Singleton3(){}


    /**
     * 提供一个静态的共有方法，当使用到该方法时才去创建instance
     */
    public static Singleton3 getInstance() {
        if (null == instance) {
            instance = new Singleton3();
        }
        return instance;
    }
}
