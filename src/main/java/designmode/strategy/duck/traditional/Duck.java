package designmode.strategy.duck.traditional;

/**
 * 1.其他鸭子，都继承了Duck类，所以fly让所有子类都会飞了，这是不正确的
 * 2。上面说的1的问题，其实是继承带来的问题：对类的局部改动，尤其超类的局部改动，
 * 会影响其他部分。会有溢出效应
 * 3.为了改进1问题我们可以通过覆盖fly 方法来解决=》覆盖解决
 * 4.问题又来了，如果我们有一个玩具鸭子ToyDuck,这样就需要ToyDuck去覆盖Duck的
 * 所有实现的方法
 * 解决思路：策略模式
 */
public abstract class Duck {

    public Duck() {
    }

    public abstract void display();

    public void quack() {
        System.out.println("鸭子嘎嘎叫~~");
    }

    public void swim() {
        System.out.println("鸭子会游泳~~");
    }

    public void fly() {
        System.out.println("鸭子会飞");
    }
}
