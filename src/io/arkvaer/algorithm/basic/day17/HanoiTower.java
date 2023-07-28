package io.arkvaer.algorithm.basic.day17;

import java.util.HashSet;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 汉诺塔是一个发源于印度的益智游戏，也叫河内塔。相传它源于印度神话中的大梵天创造的三个金刚柱，一根柱子上叠着上下从小到大64个黄金圆盘。大梵天命令婆罗门将这些圆盘按从小到大的顺序移动到另一根柱子上，其中大圆盘不能放在小圆盘上面。当这64个圆盘移动完的时候，世界就将毁灭。
 * <p>
 * 有三根柱子，分别是 A B C ，A 柱子上有 n 个环盘子，小盘子在大盘子上面有序排列，
 * 通过移动最终将 A 柱子上的盘子移动到 C 柱子上，且移动时每次只能移动一个盘子
 * 求A柱子上又n个盘子时， 最少需要多少次移动可以全部移到C柱子上
 */
public class HanoiTower {


    // region Method 1
    public int move1(int n) {
        if (n < 2) {
            return n;
        }
        AtomicInteger count = new AtomicInteger();
        leftToRight(n, count);
        return count.get();
    }

    public void leftToRight(int n, AtomicInteger count) {
        if (n == 1) {
            count.incrementAndGet();
            System.out.println("move 1 from left to right");
            return;
        }
        leftToMid(n - 1, count);
        count.incrementAndGet();
        System.out.println("move " + n + " from left to right");
        midToRight(n - 1, count);
    }

    public void leftToMid(int n, AtomicInteger count) {
        if (n == 1) {
            count.incrementAndGet();
            System.out.println("move 1 from left to right");
            return;
        }
        leftToRight(n - 1, count);
        count.incrementAndGet();
        System.out.println("move " + n + " from left to mid");
        rightToMid(n - 1, count);
    }

    public void midToRight(int n, AtomicInteger count) {
        if (n == 1) {
            count.incrementAndGet();
            System.out.println("move 1 from mid to right");
            return;
        }
        midToLeft(n - 1, count);
        count.incrementAndGet();
        System.out.println("move " + n + " from mid to right");
        leftToRight(n - 1, count);
    }

    public void midToLeft(int n, AtomicInteger count) {
        if (n == 1) {
            count.incrementAndGet();
            System.out.println("move 1 from mid to left");
            return;
        }
        midToRight(n - 1, count);
        count.incrementAndGet();
        System.out.println("move " + n + " from mid to right");
        rightToLeft(n - 1, count);
    }

    public void rightToMid(int n, AtomicInteger count) {
        if (n == 1) {
            count.incrementAndGet();
            System.out.println("move 1 from right to mid");
            return;
        }
        rightToLeft(n - 1, count);
        count.incrementAndGet();
        System.out.println("move " + n + " from right to left");
        leftToMid(n - 1, count);
    }

    public void rightToLeft(int n, AtomicInteger count) {
        if (n == 1) {
            count.incrementAndGet();
            System.out.println("move 1 from right to left");
            return;
        }
        rightToMid(n - 1, count);
        count.incrementAndGet();
        System.out.println("move " + n + " from right to mid");
        midToLeft(n - 1, count);
    }

    // endregion

    // region Method 2
    public int move2(int n) {
        if (n < 2) {
            return n;
        }
        AtomicInteger count = new AtomicInteger();
        moveFromSourceToTarget(n, "left", "right", "mid", count);
        return count.get();
    }

    public void moveFromSourceToTarget(int n, String source, String target, String other, AtomicInteger count) {
        if (n == 1) {
            count.incrementAndGet();
            System.out.println("move 1 from " + source + " to " + target);
            return;
        }
        moveFromSourceToTarget(n - 1, source, other, target, count);
        count.incrementAndGet();
        System.out.println("move " + n + " from " + source + " to " + target);
        moveFromSourceToTarget(n - 1, other, target, source, count);
    }
    // endregion



    public static class Record {
        public int level;
        public String from;
        public String to;
        public String other;

        public Record(int l, String f, String t, String o) {
            level = l;
            from = f;
            to = t;
            other = o;
        }
    }
    // 之前的迭代版本，很多同学表示看不懂
    // 所以我换了一个更容易理解的版本
    // 看注释吧！好懂！
    // 你把汉诺塔问题想象成二叉树
    // 比如当前还剩i层，其实打印这个过程就是：
    // 1) 去打印第一部分 -> 左子树
    // 2) 打印当前的动作 -> 当前节点
    // 3) 去打印第二部分 -> 右子树
    // 那么你只需要记录每一个任务 : 有没有加入过左子树的任务
    // 就可以完成迭代对递归的替代了
    public static void hanoi3(int N) {
        if (N < 1) {
            return;
        }
        // 每一个记录进栈
        Stack<Record> stack = new Stack<>();
        // 记录每一个记录有没有加入过左子树的任务
        HashSet<Record> finishLeft = new HashSet<>();
        // 初始的任务，认为是种子
        stack.add(new Record(N, "left", "right", "mid"));
        while (!stack.isEmpty()) {
            // 弹出当前任务
            Record cur = stack.pop();
            if (cur.level == 1) {
                // 如果层数只剩1了
                // 直接打印
                System.out.println("Move 1 from " + cur.from + " to " + cur.to);
            } else {
                // 如果不只1层
                if (!finishLeft.contains(cur)) {
                    // 如果当前任务没有加入过左子树的任务
                    // 现在就要加入了！
                    // 把当前的任务重新压回去，因为还不到打印的时候
                    // 再加入左子树任务！
                    finishLeft.add(cur);
                    stack.push(cur);
                    stack.push(new Record(cur.level - 1, cur.from, cur.other, cur.to));
                } else {
                    // 如果当前任务加入过左子树的任务
                    // 说明此时已经是第二次弹出了！
                    // 说明左子树的所有打印任务都完成了
                    // 当前可以打印了！
                    // 然后加入右子树的任务
                    // 当前的任务可以永远的丢弃了！
                    // 因为完成了左子树、打印了自己、加入了右子树
                    // 再也不用回到这个任务了
                    System.out.println("Move " + cur.level + " from " + cur.from + " to " + cur.to);
                    stack.push(new Record(cur.level - 1, cur.other, cur.to, cur.from));
                }
            }
        }
    }
    public static void main(String[] args) {
        HanoiTower hanoiTower = new HanoiTower();
        int count = hanoiTower.move1(3);
        System.out.println(count);
        int count1 = hanoiTower.move2(3);
        System.out.println(count1);
    }

}
