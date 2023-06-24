package io.arkvaer.algorithm.basic.day02;

import io.arkvaer.algorithm.utils.AlgException;

import java.util.Stack;

/**
 * 实现一个特殊栈, 提供getMin()方法用与获取当前栈中的最小值, 且使pop posh getMin 方法的时间复杂度都为O(1)
 *
 * @author waver
 * @date 2023/6/23 下午11:46
 */
public class GetMinStack {

    public static class MyStack {
        private Stack<Integer> dataStack = new Stack<>();
        private Stack<Integer> minStack = new Stack<>();

        public void push(Integer val) {
            dataStack.push(val);
            if (dataStack.isEmpty()) {
                minStack.push(val);
            } else {
                int min = getMin();
                if (min > val) {
                    minStack.push(val);
                } else {
                    minStack.push(min);
                }
            }
        }


        public int pop() {
            if (!dataStack.isEmpty()) {
                minStack.pop();
                return dataStack.pop();
            } else {
                throw new AlgException("当前栈为空!");
            }
        }

        public Integer getMin() {
            return minStack.peek();
        }

    }


}
