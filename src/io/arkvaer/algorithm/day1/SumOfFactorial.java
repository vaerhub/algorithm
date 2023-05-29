package io.arkvaer.algorithm.day1;

/**
 * 计算阶乘之和
 *
 * @author waver
 * @date 2023/5/29 下午7:58
 */
public class SumOfFactorial {


    public static long fun1(int n) {
        long ans = 0;
        for (int i = 1; i <= n; i++) {
            ans += factorial(i);
        }
        return ans;
    }


    public static long factorial(int n) {
        int ans = 1;
        for (int i = 1; i <= n; i++) {
            ans *= i;
        }
        return ans;
    }


    public static long fun2(int n) {
        long sum = 0;
        long ans = 1;
        for (int i = 1; i <= n; i++) {
            ans *= i;
            sum += ans;
        }
        return sum;
    }


    public static void main(String[] args) {
        int n = 10;
        System.out.println(fun1(n));
        System.out.println(fun2(n));
    }

}
