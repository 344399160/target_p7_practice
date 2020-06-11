package designmode.strategy.duck.traditional;

public class ToyDuck extends Duck {

    @Override
    public void display() {
        System.out.println("~~玩具鸭~~");
    }

    //需要重写父类所有方法
    @Override
    public void quack() {
        System.out.println("玩具鸭不会嘎嘎叫~~");
    }

    @Override
    public void swim() {
        System.out.println("玩具鸭不会游泳~~");
    }

    @Override
    public void fly() {
        System.out.println("玩具鸭不会飞");
    }
}
