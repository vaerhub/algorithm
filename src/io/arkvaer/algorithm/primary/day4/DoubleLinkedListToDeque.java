package io.arkvaer.algorithm.primary.day4;

import java.util.Deque;
import java.util.LinkedList;

public class DoubleLinkedListToDeque {
    public static class DoubleNode<T> {
        public T value;
        public DoubleNode<T> prev;
        public DoubleNode<T> next;

        public DoubleNode(T value) {
            this.value = value;
            this.prev = null;
            this.next = null;
        }
    }

    public static class MyDeque<T> {
        public DoubleNode<T> head;
        public DoubleNode<T> tail;
        public int size;

        public MyDeque() {
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

        public void pushHead(T value) {
            DoubleNode<T> node = new DoubleNode<>(value);
            if (head == null) {
                head = node;
                tail = node;
            } else {
                head.prev = node;
                node.next = head;
                head = node;
            }
            size++;
        }

        public void pushTail(T value) {
            DoubleNode<T> node = new DoubleNode<>(value);
            if (tail == null) {
                tail = node;
                head = node;
            } else {
                tail.next = node;
                node.prev = tail;
                tail = node;
            }
            size++;
        }

        public T pollHead() {
            T result = null;
            if (head == null) {
                return result;
            }
            result = head.value;
            size --;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                head = head.next;
                head.prev = null;
            }
            return result;
        }

        public T pollTail(){
            T result = null;
            if (tail == null) {
                return result;
            }
            result = tail.value;
            size--;
            if (tail == head) {
                tail = null;
                head = null;
            } else {
                tail = tail.prev;
                tail.next = null;
            }
            return result;
        }



        public T peekHead(){
            T result = null;
            if (head != null) {
                result = head.value;
            }
            return result;
        }

        public T peekTail() {
            T result = null;
            if (tail != null) {
                result = tail.value;
            }
            return result;
        }
    }

    public static void testDeque() {
        MyDeque<Integer> myDeque = new MyDeque<>();
        Deque<Integer> test = new LinkedList<>();
        int testTime = 5000000;
        int maxValue = 200000000;
        System.out.println("测试开始！");
        for (int i = 0; i < testTime; i++) {
            if (myDeque.isEmpty() != test.isEmpty()) {
                System.out.println("Oops!");
            }
            if (myDeque.size() != test.size()) {
                System.out.println("Oops!");
            }
            double decide = Math.random();
            if (decide < 0.33) {
                int num = (int) (Math.random() * maxValue);
                if (Math.random() < 0.5) {
                    myDeque.pushHead(num);
                    test.addFirst(num);
                } else {
                    myDeque.pushTail(num);
                    test.addLast(num);
                }
            } else if (decide < 0.66) {
                if (!myDeque.isEmpty()) {
                    int num1 = 0;
                    int num2 = 0;
                    if (Math.random() < 0.5) {
                        num1 = myDeque.pollHead();
                        num2 = test.pollFirst();
                    } else {
                        num1 = myDeque.pollTail();
                        num2 = test.pollLast();
                    }
                    if (num1 != num2) {
                        System.out.println("Oops!");
                    }
                }
            } else {
                if (!myDeque.isEmpty()) {
                    int num1 = 0;
                    int num2 = 0;
                    if (Math.random() < 0.5) {
                        num1 = myDeque.peekHead();
                        num2 = test.peekFirst();
                    } else {
                        num1 = myDeque.peekTail();
                        num2 = test.peekLast();
                    }
                    if (num1 != num2) {
                        System.out.println("Oops!");
                    }
                }
            }
        }
        if (myDeque.size() != test.size()) {
            System.out.println("Oops!");
        }
        while (!myDeque.isEmpty()) {
            int num1 = myDeque.pollHead();
            int num2 = test.pollFirst();
            if (num1 != num2) {
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束！");
    }

    public static void main(String[] args) {
        testDeque();
    }
}
