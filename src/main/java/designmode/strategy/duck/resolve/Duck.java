package designmode.strategy.duck.resolve;

public abstract class Duck {

    protected FlyBehavior flyBehavior;
    //其他属性<->策略接口

    public abstract void display();

}
