package designmode.strategy.duck.traditional;

public class PekinigDuck extends Duck {
    @Override
    public void display() {
        System.out.println("~~北京鸭~~");
    }

    //因为北京鸭不能飞翔因此重写fly

    @Override
    public void fly() {
        System.out.println("北京鸭不能飞");
    }
}
