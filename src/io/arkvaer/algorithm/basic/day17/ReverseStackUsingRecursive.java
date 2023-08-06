package io.arkvaer.algorithm.basic.day17;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author waver
 * @date 2023/8/6 21:55
 */
public class ReverseStackUsingRecursive {
    public static void reverse(Deque<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        int i = process(stack);
        reverse(stack);
        stack.push(i);
    }

    public static int process(Deque<Integer> stack) {
        Integer result = stack.pop();
        if (stack.isEmpty()) {
            return result;
        } else {
            int last = process(stack);
            stack.push(result);
            return last;
        }
    }

    public static void main(String[] args) {
        Deque<Integer> test = new LinkedList<>();
        test.push(1);
        test.push(2);
        test.push(3);
        test.push(4);
        test.push(5);
        reverse(test);
        while (!test.isEmpty()) {
            System.out.println(test.pop());
        }
    }
}
