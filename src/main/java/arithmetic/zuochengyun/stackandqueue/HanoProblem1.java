package arithmetic.zuochengyun.stackandqueue;

import java.util.Stack;

/**
 * 用栈来求解汉诺塔问题
 * 【题目】
 * 汉诺塔问题比较经典，这里修改一下游戏规则：现在限制不能从最左侧的塔直接移动到最
 * 右侧，也不能从最右侧直接移动到最左侧，而是必须经过中间。求当塔有 N 层的时候，打印最
 * 优移动过程和最优移动总步数。
 * 例如，当塔数为两层时，最上层的塔记为 1，最下层的塔记为 2，则打印：
 * Move 1 from left to mid
 * Move 1 from mid to right
 * Move 2 from left to mid
 * Move 1 from right to mid
 * Move 1 from mid to left
 * Move 2 from mid to right
 * Move 1 from left to mid
 * Move 1 from mid to right
 * It will move 8 steps.程序员代码面试指南： IT 名企算法与数据结构题目最优解（第 2 版）
 * 14
 * 注意：关于汉诺塔游戏的更多讨论，将在本书递归与动态规划的章节中继续。
 * 【要求】
 * 用以下两种方法解决。
 *  方法一：递归的方法；
 *  方法二：非递归的方法，用栈来模拟汉诺塔的三个塔。
 * 【难度】
 * 校 ★★★☆
 *
 * 方法二：非递归的方法——用栈来模拟整个过程。
 * 修改后的汉诺塔问题不能让任何塔从“左”直接移动到“右”，也不能从“右”直接移动到
 * “左”，而是要经过中间过程。 也就是说， 实际动作只有 4 个： “左”到“中”、“中”到“左”、
 * “中”到“右”、“右”到“中”。
 * 现在我们把左、中、右三个地点抽象成栈，依次记为 LS、 MS 和 RS。最初所有的塔都在 LS
 * 上。那么如上 4 个动作就可以看作是：某一个栈（from） 把栈顶元素弹出，然后压入到另一个
 * 栈里（to），作为这一个栈（to） 的栈顶。
 * 例如， 如果是 7 层塔，在最初时所有的塔都在 LS 上， LS 从栈顶到栈底就依次是 1~7，如果
 * 现在发生了“左”到“中”的动作，这个动作对应的操作是 LS 栈将栈顶元素 1 弹出，然后 1 压
 * 入到 MS 栈中，成为 MS 的栈顶。其他操作同理。
 * 一个动作能发生的先决条件是不违反小压大的原则。
 * from 栈弹出的元素 num 如果想压入到 to 栈中，那么 num 的值必须小于当前 to 栈的栈顶。
 * 还有一个原则不是很明显，但也是非常重要的，叫相邻不可逆原则， 解释如下：
 * 1． 我们把 4 个动作依次定义为： L->M、 M->L、 M->R 和 R->M。
 * 2． 很明显， L->M 和 M->L 过程互为逆过程， M->R 和 R->M 互为逆过程。
 * 3． 在修改后的汉诺塔游戏中，如果想走出最少步数，那么任何两个相邻的动作都不是互为
 * 逆过程的。举个例子：如果上一步的动作是 L->M，那么这一步绝不可能是 M->L，直观地解释
 * 为： 你在上一步把一个栈顶数从“左”移动到“中”，这一步为什么又要移回去呢？这必然不是
 * 取得最小步数的走法。同理， M->R 动作和 R->M 动作也不可能相邻发生。
 * 有了小压大和相邻不可逆原则后， 可以推导出两个十分有用的结论——非递归的方法核心
 * 结论：
 * 1． 游戏的第一个动作一定是 L->M，这是显而易见的。
 * 2． 在走出最少步数过程中的任何时刻， 4 个动作中只有一个动作不违反小压大和相邻不可
 * 逆原则，另外三个动作一定都会违反。
 * 对于结论 2，现在进行简单的证明。
 * 因为游戏的第一个动作已经确定是 L->M， 则以后的每一步都会有前一步的动作。
 * 假设前一步的动作是 L->M：
 * 1． 根据小压大原则， L->M 的动作不会重复发生。
 * 2． 根据相邻不可逆原则， M->L 的动作也不该发生。
 * 3． 根据小压大原则， M->R 和 R->M 只会有一个达标。
 * 假设前一步的动作是 M->L：
 * 1． 根据小压大原则， M->L 的动作不会重复发生。
 * 2． 根据相邻不可逆原则， L->M 的动作也不该发生。第 1 章 栈和队列
 * 17
 * 3． 根据小压大原则， M->R 和 R->M 只会有一个达标。
 * 假设前一步的动作是 M->R：
 * 1． 根据小压大原则， M->R 的动作不会重复发生。
 * 2． 根据相邻不可逆原则， R->M 的动作也不该发生。
 * 3． 根据小压大原则， L->M 和 M->L 只会有一个达标。
 * 假设前一步的动作是 R->M：
 * 1． 根据小压大原则， R->M 的动作不会重复发生。
 * 2． 根据相邻不可逆原则， M->R 的动作也不该发生。
 * 3． 根据小压大原则， L->M 和 M->L 只会有一个达标。
 * 综上所述，每一步只会有一个动作达标。那么只要每走一步都根据这两个原则考查所有的
 * 动作就可以，哪个动作达标就走哪个动作，反正每次都只有一个动作满足要求， 按顺序走下来
 * 即可。
 * 非递归的具体过程请参看如下代码中的 hanoiProblem1 方法。
 */
public class HanoProblem1 {

    public static void main(String[] args) {
        HanoProblem1 hano = new HanoProblem1();
        int step = hano.hanoiProblem2(5, "左", "中", "右");
        System.out.println("步数为：" + step);
    }

    public enum Action {
        No, LToM, MToL, MToR, RToM
    }
    public int hanoiProblem2(int num, String left, String mid, String right) {
        Stack<Integer> lS = new Stack<>();
        Stack<Integer> mS = new Stack<>();
        Stack<Integer> rS = new Stack<>();
        lS.push(Integer.MAX_VALUE);
        mS.push(Integer.MAX_VALUE);
        rS.push(Integer.MAX_VALUE);
        for (int i = num; i > 0; i--) {
            lS.push(i);
        }
        Action[] record = { Action.No };
        int step = 0;
        while (rS.size() != num + 1) {
            step += fStackTotStack(record, Action.MToL, Action.LToM, lS, mS,
                    left, mid);
            step += fStackTotStack(record, Action.LToM, Action.MToL, mS, lS,
                    mid, left);
            step += fStackTotStack(record, Action.RToM, Action.MToR, mS, rS,
                    mid, right);
            step += fStackTotStack(record, Action.MToR, Action.RToM, rS, mS,
                    right, mid);
        }
        return step;
    }
    public static int fStackTotStack(Action[] record, Action preNoAct,Action nowAct, Stack<Integer> fStack, Stack<Integer> tStack,
                                     String from, String to) {
        if (record[0] != preNoAct && fStack.peek() < tStack.peek()) {
            tStack.push(fStack.pop());
            System.out.println("将 " + tStack.peek() + "从 " + from + " --> " + to);
                    record[0] = nowAct;
            return 1;
        }
        return 0;
    }
}
