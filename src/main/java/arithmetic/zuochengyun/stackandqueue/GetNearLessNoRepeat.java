package arithmetic.zuochengyun.stackandqueue;

import java.util.Stack;

/**
 *单调栈结构
 * 【题目】
 * 给定一个不含有重复值的数组 arr，找到每一个 i 位置左边和右边离 i 位置最近且值比 arr[i]
 * 小的位置。返回所有位置相应的信息。
 * 【举例】
 * arr = {3,4,1,5,6,2,7}
 * 返回如下二维数组作为结果：
 * {
 * {-1, 2},
 * { 0, 2},
 * {-1,-1},
 * { 2, 5},
 * { 3, 5},
 * { 2,-1},
 * { 5,-1}
 * } -1
 * 表示不存在。所以上面的结果表示在 arr 中， 0 位置左边和右边离 0 位置最近且值比 arr[0]
 * 小的位置是-1 和 2； 1 位置左边和右边离 1 位置最近且值比 arr[1]小的位置是 0 和 2； 2 位置左
 * 边和右边离 2 位置最近且值比 arr[2]小的位置是-1 和-1……
 * 进阶问题： 给定一个可能含有重复值的数组 arr，找到每一个 i 位置左边和右边离 i 位置最
 * 近且值比 arr[i]小的位置。返回所有位置相应的信息。
 * 【要求】
 * 如果 arr 长度为 N，实现原问题和进阶问题的解法，时间复杂度都达到 O(N)。
 * 【难度】
 * 尉 ★★☆☆
 *
 * 该题详解太长了。。。看书吧，图解 1-getNearLessNoRepeat
 */
public class GetNearLessNoRepeat {

    public static int[][] getNearLessNoRepeat(int[] arr) {
        int[][] res = new int[arr.length][2];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                int popIndex = stack.pop();
                int leftLessIndex = stack.isEmpty() ? -1 : stack.peek();
                res[popIndex][0] = leftLessIndex;
                res[popIndex][1] = i;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int popIndex = stack.pop();
            int leftLessIndex = stack.isEmpty() ? -1 : stack.peek();
            res[popIndex][0] = leftLessIndex;
            res[popIndex][1] = -1;
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] nearLessNoRepeat = getNearLessNoRepeat(new int[]{3, 4, 1, 5, 6, 2, 7});
        for (int i = 0; i < nearLessNoRepeat.length; i++) {
            System.out.println(nearLessNoRepeat[i][0] + "," + nearLessNoRepeat[i][1]);
        }
    }
}
