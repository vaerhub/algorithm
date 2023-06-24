package io.arkvaer.algorithm.basic.day02;

import io.arkvaer.algorithm.utils.AlgException;

import java.util.Stack;

/**
 * 通过栈实现队列
 *
 * @author waver
 * @date 2023/6/24 上午10:43
 */
public class TwoStackImplementQueue {

    public static class MyQueue {

        private Stack<Integer> addStack = new Stack<>();
        private Stack<Integer> pollStack = new Stack<>();

        public MyQueue() {
        }

        public void transDataFromAddToPoll() {
            if (pollStack.isEmpty() && !addStack.isEmpty()) {
                while (!addStack.isEmpty()) {
                    pollStack.push(addStack.pop());
                }
            }
        }

        public void add(Integer value) {
            addStack.push(value);
            transDataFromAddToPoll();
        }


        public Integer poll() {
            if (addStack.isEmpty() && pollStack.isEmpty()) {
                throw new AlgException("当前队列为空!");
            }
            transDataFromAddToPoll();
            return pollStack.pop();
        }

        public Integer peek() {
            if (addStack.isEmpty() && pollStack.isEmpty()) {
                throw new AlgException("当前队列为空!");
            }
            transDataFromAddToPoll();
            return pollStack.peek();
        }
    }

    public static void main(String[] args) {
        MyQueue test = new MyQueue();
        test.add(1);
        test.add(2);
        test.add(3);
//        System.out.println(test.peek());
        System.out.println(test.poll());
//        System.out.println(test.peek());
        System.out.println(test.poll());
//        System.out.println(test.peek());
        System.out.println(test.poll());
    }

}
