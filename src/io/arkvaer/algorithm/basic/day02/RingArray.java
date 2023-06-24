package io.arkvaer.algorithm.basic.day02;

import io.arkvaer.algorithm.utils.AlgException;

/**
 * 使用数组实现队列
 *
 * @author waver
 * @date 2023/6/23 下午11:29
 */
public class RingArray {

    public static class MyQueue {
        private int[] data;
        private int head;
        private int tail;

        private int size;
        private int limit;

        public MyQueue(int limit) {
            this.limit = limit;
            data = new int[limit];
            head = 0;
            tail = 0;
            size = 0;
        }

        public void push(int val) {
            if (size < limit) {
                data[tail] = val;
                tail = nextIndex(tail);
                size++;
            } else {
                throw new AlgException("队列超出限制");
            }
        }


        public Integer poll() {
            int result;
            if (size != 0) {
                result = data[head];
                head = nextIndex(head);
                return result;
            } else {
                throw new AlgException("队列为空!");
            }
        }


        /**
         * 跳转到下一个节点, 如果超过数组长度, 则回到0
         * @param i 当前index
         * @return 下一个index
         */
        private int nextIndex(int i) {
            return i < limit - 1 ? i + 1 : 0;
        }

    }

}
