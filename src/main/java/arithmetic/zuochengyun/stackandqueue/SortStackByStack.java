package arithmetic.zuochengyun.stackandqueue;

import java.util.Stack;

/**
 *用一个栈实现另一个栈的排序
 *
 * 【题目】
 * 一个栈中元素的类型为整型，现在想将该栈从顶到底按从大到小的顺序排序，只许申请一
 * 个栈。 除此之外， 可以申请新的变量，但不能申请额外的数据结构。如何完成排序？
 * 【难度】
 * 士 ★☆☆☆第 1 章 栈和队列
 *
 * 【解答】
 * 将要排序的栈记为 stack，申请的辅助栈记为 help。在 stack 上执行 pop 操作，弹出的元素
 * 记为 cur。
 *  如果 cur 小于或等于 help 的栈顶元素，则将 cur 直接压入 help；
 *  如果 cur 大于 help 的栈顶元素，则将 help 的元素逐一弹出，逐一压入 stack，直到 cur
 * 小于或等于 help 的栈顶元素，再将 cur 压入 help。
 * 一直执行以上操作，直到 stack 中的全部元素都压入到 help。最后将 help 中的所有元素逐一压入 stack，即完成排序
 *
 * 流程见图 1-SortStackByStack
 */
public class SortStackByStack {

    public static void sort(Stack<Integer> stack) {
        Stack<Integer> helpStack = new Stack<>();
        while (!stack.isEmpty()) {
            Integer cur = stack.pop();
            while (!helpStack.isEmpty() && helpStack.peek() < cur) {
                stack.push(helpStack.pop());
            }
            helpStack.push(cur);
        }
        while (!helpStack.isEmpty()) {
            stack.push(helpStack.pop());
        }
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(2);
        stack.push(5);
        stack.push(27);
        stack.push(1);
        stack.push(13);
        stack.push(0);
        sort(stack);
        for (int i = 0; i < 6; i++) {
            System.out.println(stack.pop());
        }
    }
}
