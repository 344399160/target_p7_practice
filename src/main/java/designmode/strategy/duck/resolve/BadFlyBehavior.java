package designmode.strategy.duck.resolve;

public class BadFlyBehavior implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println(" 飞翔技术一般");
    }
}
