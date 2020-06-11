package designmode.strategy.duck.resolve;

public class NoFlyBehavior implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println(" 不会飞");
    }
}
