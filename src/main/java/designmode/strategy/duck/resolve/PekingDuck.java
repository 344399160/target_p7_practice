package designmode.strategy.duck.resolve;

public class PekingDuck extends Duck {

    public PekingDuck() {
        flyBehavior = new NoFlyBehavior();
    }

    @Override
    public void display() {
        System.out.println(" 北京鸭 ");
    }

    public void fly() {
        flyBehavior.fly();
    }
}
