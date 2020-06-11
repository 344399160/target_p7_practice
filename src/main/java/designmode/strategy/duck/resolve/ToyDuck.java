package designmode.strategy.duck.resolve;

public class ToyDuck extends Duck {
    public ToyDuck() {
        flyBehavior = new NoFlyBehavior();
    }

    @Override
    public void display() {
        System.out.println(" 玩具鸭 ");
    }
    public void fly() {
        flyBehavior.fly();
    }
}
