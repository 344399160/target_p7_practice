package designmode.strategy.duck.resolve;

public class WildDuck extends Duck {

    public WildDuck() {
        flyBehavior = new GoodFlyBehavior();
    }

    public void setBehavior(FlyBehavior behavior) {
        flyBehavior = behavior;
    }

    @Override
    public void display() {
        System.out.println(" 野鸭 ");
    }

    public void fly() {
        flyBehavior.fly();
    }
}
