package io.arkvaer.algorithm.basic.day29;

import io.arkvaer.algorithm.utils.AlgUtil;

/**
 * 蓄水池算法
 * 解决的问题
 * 假设有一个源源吐出不同球的机器只有装下10个球的袋子，
 * 每一个吐出的球，要么放入袋子，要么永远扔掉
 * 如何做到机器吐出每一个球之后，所有吐出的球都等概率被放进袋子里
 */
public class ReservoirSampling {
    public static class RandomBox {
        // 袋子: 最终可以留下的球的数组, 记录球的编号
        private int[] bag;
        // 袋子的容量
        private int capacity;
        private int size;

        public RandomBox(int capacity) {
            this.bag = new int[capacity];
            this.capacity = capacity;
            this.size = 0;
        }

        private int rand(int n) {
            return (int) (Math.random() * n) + 1;
        }

        private void add(int num) {
            size++;
            if (size <= capacity) {
                bag[size - 1] = num;
            } else {
                if (rand(size) <= capacity) {
                    bag[rand(capacity) - 1] = num;
                }
            }
        }
        private int[] choices() {
            return AlgUtil.copyArr(bag);
        }

    }


    public static void main(String[] args) {
        int all = 100;
        int choose = 10;
        int testTimes = 50000;
        int[] counts = new int[all + 1];
        for (int i = 0; i < testTimes; i++) {
//            RandomBox1 box = new RandomBox1(choose);
            RandomBox box = new RandomBox(choose);
            for (int num = 1; num <= all; num++) {
                box.add(num);
            }
            int[] ans = box.choices();
            for (int an : ans) {
                counts[an]++;
            }
        }

        for (int i = 0; i < counts.length; i++) {
            System.out.println(i + " times : " + counts[i]);
        }
    }


}
