package io.arkvaer.algorithm.day2;

import io.arkvaer.algorithm.utils.AlgUtil;

/**
 * 题目 通过一个只能产生1~5随机数的黑盒方法 产生1~7的等概率随机
 *
 * @author waver
 * @date 2023/5/29 下午9:36
 */
public class RandToRand {
    /**
     * 等概率产生1~5的随机数
     *
     * @return 1~5的随机数
     */
    public static int fun() {
        return AlgUtil.random.nextInt(1, 6);
    }

    /**
     * 等概率产生0或1
     *
     * @return 0 或 1
     */
    public static int getSameProbabilityOneOrZero() {
        int result;
        do {
            result = fun();
        } while (result == 3);
        return result < 3 ? 0 : 1;
    }

    /**
     * 等概率返回 0 到 6
     * 思路: 6至少需要3个二进制位, 则生成3次 0 / 1, 可生成 0 ~ 7 的随机数, 等于7时重做, 即可将7的概率平分到0~6上
     *
     * @return 0 到 6 的随机数
     */
    public static int getSameProbabilityZeroToSix() {
        int result;
        do {
            result = (getSameProbabilityOneOrZero() << 2) + (getSameProbabilityOneOrZero() << 1) + getSameProbabilityOneOrZero();
        } while (result == 7);
        return result;
    }

    /**
     * 等概率返回1~7
     *
     * @return 1-7的随机数
     */
    public static int getSameProbabilityOneToSeven() {
        return getSameProbabilityZeroToSix() + 1;
    }


    public static class RandomBox {
        private final int min;
        private final int max;

        public RandomBox(int min, int max) {
            this.min = min;
            this.max = max;
        }

        public int random() {
            return AlgUtil.random.nextInt(min, max + 1);
        }

        public int min() {
            return min;
        }

        public int max() {
            return max;
        }

    }

    // 利用条件RandomBox，如何等概率返回0和1
    public static int rand01(RandomBox randomBox) {
        int min = randomBox.min();
        int max = randomBox.max();
        // min ~ max
        int size = max - min + 1;
        // size是不是奇数，odd 奇数
        boolean odd = (size & 1) != 0;
        int mid = size / 2;
        int ans = 0;
        do {
            ans = randomBox.random() - min;
        } while (odd && ans == mid);
        return ans < mid ? 0 : 1;
    }

    /**
     * 等概率产生 c-d
     *
     * @return c-d 的随机数
     */
    public static int random(RandomBox randomBox, int from, int to) {
        if (from == to) {
            return from;
        }
        // 3 ~ 9
        // 0 ~ 6
        // 0 ~ range
        int range = to - from;
        int num = 1;
        // 求0～range需要几个2进制位
        while ((1 << num) - 1 < range) {
            num++;
        }

        // 我们一共需要num位
        // 最终的累加和，首先+0位上是1还是0，1位上是1还是0，2位上是1还是0...
        int ans = 0;
        do {
            ans = 0;
            for (int i = 0; i < num; i++) {
                // 因为此处只会又一位是1且每一个位置都不同, 所有使用 或运算和 累加运算结果一样
                // 001 | 010 | 100 == 001 + 010 + 100
                // ans += (rand01(randomBox) << i);
                ans |= (rand01(randomBox) << i);

            }
        } while (ans > range);
        return ans + from;
    }

    // 使用非等概率但概率固定的函数实现等概率返回 0 或 1
    /**
     * 你只能知道，x会以固定概率返回0和1，但是x的内容，你看不到！
     */
    public static int x() {
        return Math.random() < 0.84 ? 0 : 1;
    }

    // 等概率返回0和1
    public static int y() {
        int ans = 0;
        do {
            ans = x();
        } while (ans == x());
        return ans;
    }

    public static void main(String[] args) {
        int testTime = 20000000;
        int[] counts = new int[7];
        for (int i = 0; i < testTime; i++) {
            int fun = getSameProbabilityOneToSeven();
            counts[fun - 1]++;
        }
        for (int count : counts) {
            System.out.println(count);
        }
        System.out.println("=========");

        int testTimes = 10000000;

        int a = 2;
        int b = 4;
        int c = 100;
        int d = 200;

        RandomBox randomBox = new RandomBox(a, b);
        // [0,K) -> [0,8]

        counts = new int[d - c + 1];
        for (int i = 0; i < testTimes; i++) {
            int ans = random(randomBox, c, d); // [0,K-1]
            counts[ans - counts.length + 1]++;
        }
        for (int i = 0; i < d - c; i++) {
            System.out.println(i + (d - c) + "这个数，出现了 " + counts[i] + " 次");
        }

        System.out.println("=========");
    }
}
