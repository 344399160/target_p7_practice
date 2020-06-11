package designmode.strategy.duck.resolve;

public class Client {
    public static void main(String[] args) {
        WildDuck wildDuck = new WildDuck();
        wildDuck.fly();
        wildDuck.setBehavior(new NoFlyBehavior());
        wildDuck.fly();
    }
}
