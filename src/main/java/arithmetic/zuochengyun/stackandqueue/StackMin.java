package arithmetic.zuochengyun.stackandqueue;

import java.util.Stack;

/**
 * 实现一个特殊的栈， 在实现栈的基本功能的基础上， 再实现返回栈中最小元素的操作。
 *
 * 在设计时， 我们使用两个栈，一个栈用来保存当前栈中的元素， 其功能和一个正常的栈没
 * 有区别，这个栈记为 stackData；另一个栈用于保存每一步的最小值，这个栈记为 stackMin。具体的实现方式有两种
 *
 *第二种设计方案
 * （ 1） 压入数据规则
 * 假设当前数据为 newNum，先将其压入 stackData。然后判断 stackMin 是否为空。
 * 如果为空，则 newNum 也压入 stackMin； 如果不为空， 则比较 newNum 和 stackMin 的栈顶
 * 元素中哪一个更小。
 * 如果 newNum 更小或两者相等，则 newNum 也压入 stackMin； 如果 stackMin 中栈顶元素
 * 小，则把 stackMin 的栈顶元素重复压入 stackMin，即在栈顶元素上再压入一个栈顶元素。
 *
 * （ 2） 弹出数据规则
 * 在 stackData 中弹出数据，弹出的数据记为 value； 弹出 stackMin 中的栈顶， 返回 value。
 * 很明显可以看出，压入与弹出规则是对应的。
 * （ 3） 查询当前栈中的最小值操作
 * 由上文的压入数据规则和弹出数据规则可知， stackMin 始终记录着 stackData 中的最小值，
 * 所以 stackMin 的栈顶元素始终是当前 stackData 中的最小值。
 *
 * 示例见 1-1.png
 */
public class StackMin {

    private Stack<Integer> dataStack;
    private Stack<Integer> minStack;

    public StackMin() {
        dataStack = new Stack<>();
        minStack = new Stack<>();
    }


    /**
     * 入栈
     */
    public void push(int num) {
        dataStack.push(num);
        if (minStack.isEmpty()) {
            minStack.push(num);
        } else {
            if (getMin() >= num) {
                minStack.push(num);
            }
        }
    }

    /**
     * 出栈
     */
    public Integer pop() {
        if (dataStack.isEmpty()) {
            throw new RuntimeException("Stack is Empty");
        }
        if (dataStack.peek().equals(getMin())) {
            minStack.pop();
        }
        return dataStack.pop();
    }

    /**
     * 获取栈最小值
     */
    public Integer getMin() {
        if (minStack.isEmpty()) {
            throw new RuntimeException("Stack is Empty");
        }
        return minStack.peek();
    }


    public static void main(String[] args) {
        StackMin stackMin = new StackMin();
        stackMin.push(3);
        stackMin.push(2);
        stackMin.push(1);
        stackMin.push(2);
        stackMin.push(1);
        stackMin.push(5);
        stackMin.push(6);


        for (int i = 0; i < 7; i++) {
            System.out.println("min -- " + stackMin.getMin());
            System.out.println("pop -- " + stackMin.pop());
        }
    }
}
