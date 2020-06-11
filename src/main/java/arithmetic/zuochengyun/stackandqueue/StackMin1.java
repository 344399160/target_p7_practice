package arithmetic.zuochengyun.stackandqueue;

import java.util.Stack;

/**
 * 实现一个特殊的栈， 在实现栈的基本功能的基础上， 再实现返回栈中最小元素的操作。
 *
 * 在设计时， 我们使用两个栈，一个栈用来保存当前栈中的元素， 其功能和一个正常的栈没
 * 有区别，这个栈记为 stackData；另一个栈用于保存每一步的最小值，这个栈记为 stackMin。具体的实现方式有两种
 *
 *第一种设计方案
 * （1） 压入数据规则
 * 假设当前数据为 newNum，先将其压入 stackData。然后判断 stackMin 是否为空：
 *  如果为空，则 newNum 也压入 stackMin。
 *  如果不为空，则比较 newNum 和 stackMin 的栈顶元素中哪一个更小：
 *  如果 newNum 更小或两者相等， 则 newNum 也压入 stackMin；程序员代码面试指南： IT 名企算法与数据结构题目最优解（第 2 版）
 * 2
 *  如果 stackMin 中栈顶元素小， 则 stackMin 不压入任何内容。。
 *
 * 示例见 1-2.png
 */
public class StackMin1 {

    private Stack<Integer> dataStack;
    private Stack<Integer> minStack;

    public StackMin1() {
        dataStack = new Stack<>();
        minStack = new Stack<>();
    }

    /**
     * 压栈
     */
    public void push(Integer newNum) {
        if (minStack.isEmpty()) {
            minStack.push(newNum);
        } else if (getMin() > newNum) {
            minStack.push(newNum);
        } else {
            Integer min = getMin();
            minStack.push(min);
        }
        dataStack.push(newNum);
    }

    /**
     * 出栈
     */
    public Integer pop() {
        if (dataStack.isEmpty()) {
            throw new RuntimeException("Stack is Empty");
        }
        return dataStack.pop();
    }

    /**
     * 获取栈最小值
     */
    public Integer getMin() {
        if (dataStack.isEmpty()) {
            throw new RuntimeException("Stack is Empty");
        }
        return minStack.peek();
    }

    public static void main(String[] args) {
        StackMin1 stackMin1 = new StackMin1();
        stackMin1.push(1);
        stackMin1.push(4);
        stackMin1.push(5);
        stackMin1.push(2);
        stackMin1.push(1);
        stackMin1.push(3);

        for (int i = 0; i < 6; i++) {
            System.out.println("min -- " + stackMin1.getMin());
            System.out.println("pop -- " + stackMin1.pop());
        }
    }
}
