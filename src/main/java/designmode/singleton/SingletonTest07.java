package designmode.singleton;

/**
 * 避免多线程同步问题，而且还能防止反序列化重新创建新的对象
 * 该方式是Effective java作者Josh Blosh提倡的方式
 */
public class SingletonTest07 {
    public static void main(String[] args) {
        Singleton7 instance = Singleton7.INSTANCE;
        Singleton7 instance1 = Singleton7.INSTANCE;
        System.out.println(instance.equals(instance1));
        instance.sayOK();
    }
}

enum Singleton7{
    INSTANCE;

    public void sayOK() {
        System.out.println("OK");
    }
}
