package io.arkvaer.algorithm.primary.day4;

import io.arkvaer.algorithm.utils.AlgUtil;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 使用单链表实现队列和栈
 */
public class LinkedListToQueueAndStack {
    public static class Node<T> {
        public T value;
        public Node<T> next;

        public Node(T value) {
            this.value = value;
        }
    }

    public static class MyQueue<T> {
        public Node<T> head;
        public Node<T> tail;

        public int size;

        public MyQueue() {
            this.head = null;
            this.tail = null;
            this.size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }

        public void offer(T value) {
            Node<T> node = new Node<>(value);
            if (tail == null) {
                tail = node;
                head = node;
            } else {
                tail.next = node;
                tail = node;
            }
            size++;
        }

        public T poll() {
            T result = null;
            if (head != null) {
                result = head.value;
                head = head.next;
            }
            if (head == null) {
                tail = null;
            }
            size--;
            return result;
        }

        public T peek() {
            T result = null;
            if (head != null) {
                result = head.value;
            }
            return result;
        }
    }

    public static class MyStack<T> {
        public Node<T> top;
        public int size;

        public MyStack() {
            this.top = null;
            this.size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }

        public void push(T value) {
            Node<T> node = new Node<>(value);
            if (top != null) {
                node.next = top;
            }
            top = node;
            size++;
        }

        public T pop() {
            T value = null;
            if (top != null) {
                value = top.value;
                top = top.next;
                size--;
            }
            return value;
        }

        public T peek() {
            T value = null;
            if (top != null) {
                value = top.value;
            }
            return value;
        }
    }

    public static void testQueue() {
        MyQueue<Integer> myQueue = new MyQueue<>();
        Queue<Integer> test = new LinkedList<>();
        int testTime = 5000000;
        int maxValue = 200000000;
        System.out.println("测试开始！");
        for (int i = 0; i < testTime; i++) {
            if (myQueue.isEmpty() != test.isEmpty()) {
                System.out.println("Oops!");
            }
            if (myQueue.size() != test.size()) {
                System.out.println("Oops!");
            }
            double decide = Math.random();
            if (decide < 0.33) {
                int num = AlgUtil.random.nextInt(maxValue);
                //int num = (int) (Math.random() * maxValue);
                myQueue.offer(num);
                test.offer(num);
            } else if (decide < 0.66) {
                if (!myQueue.isEmpty()) {
                    int num1 = myQueue.poll();
                    int num2 = test.poll();
                    if (num1 != num2) {
                        System.out.println("Oops!");
                    }
                }
            } else {
                if (!myQueue.isEmpty()) {
                    int num1 = myQueue.peek();
                    int num2 = test.peek();
                    if (num1 != num2) {
                        System.out.println("Oops!");
                    }
                }
            }
        }
        if (myQueue.size() != test.size()) {
            System.out.println("Oops!");
        }
        while (!myQueue.isEmpty()) {
            int num1 = myQueue.poll();
            int num2 = test.poll();
            if (num1 != num2) {
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束！");
    }

    public static void testStack() {
        MyStack<Integer> myStack = new MyStack<>();
        Stack<Integer> test = new Stack<>();
        int testTime = 5000000;
        int maxValue = 200000000;
        System.out.println("测试开始！");
        for (int i = 0; i < testTime; i++) {
            if (myStack.isEmpty() != test.isEmpty()) {
                System.out.println("Oops!");
            }
            if (myStack.size() != test.size()) {
                System.out.println("Oops!");
            }
            double decide = Math.random();
            if (decide < 0.33) {
                int num = AlgUtil.random.nextInt(maxValue);
                myStack.push(num);
                test.push(num);
            } else if (decide < 0.66) {
                if (!myStack.isEmpty()) {
                    int num1 = myStack.pop();
                    int num2 = test.pop();
                    if (num1 != num2) {
                        System.out.println("Oops!");
                    }
                }
            } else {
                if (!myStack.isEmpty()) {
                    int num1 = myStack.peek();
                    int num2 = test.peek();
                    if (num1 != num2) {
                        System.out.println("Oops!");
                    }
                }
            }
        }
        if (myStack.size() != test.size()) {
            System.out.println("Oops!");
        }
        while (!myStack.isEmpty()) {
            int num1 = myStack.pop();
            int num2 = test.pop();
            if (num1 != num2) {
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束！");
    }

    public static void main(String[] args) {
        //testQueue();
        testStack();
    }

}
