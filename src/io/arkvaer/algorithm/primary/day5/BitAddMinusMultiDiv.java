package io.arkvaer.algorithm.primary.day5;

/**
 * 位运算实现加减乘除
 * 测试链接：<a href="https://leetcode.com/problems/divide-two-integers">divide-two-integers</a>
 *
 * @author waver
 * @date 2023/6/12 下午8:17
 */
public class BitAddMinusMultiDiv {

    /**
     * 位运算实现加法
     *
     * @param a 第一个值
     * @param b 第二个值
     * @return 两数相加结果
     */
    public static int add(int a, int b) {
        int sum = a;
        while (b != 0) {
            // 异或运算相当于求 a + b 的无进位和
            sum = a ^ b;
            // 与运算相当于求 a + b 的进位
            b = (a & b) << 1;
            a = sum;
        }
        return sum;
    }

    /**
     * 位运算计算相反数
     *
     * @param a a
     * @return a 的相反数
     */
    public static int oppositeNumber(int a) {
        return add(~a, 1);
    }

    /**
     * 位运算实现减法
     *
     * @param a 被减数
     * @param b 减数
     * @return 两数相减结果
     */
    public static int minus(int a, int b) {
        return add(a, oppositeNumber(b));
    }

    /**
     * 位运算实现乘法
     *
     * @param a a
     * @param b b
     * @return 两数相乘结果
     */
    public static int multi(int a, int b) {
        int result = 0;
        while (b != 0) {
            if ((b & 1) != 0) {
                result = add(result, a);
            }
            a <<= 1;
            // 如果使用 >> 当b为负数时, 右移会使用符号为补位, 导致死循环
            b >>>= 1;
        }
        return result;
    }

    public static boolean isNeg(int m) {
        return m < 0;
    }

    public static int div(int x, int y) {
        if (y == 0) {
            throw new ArithmeticException("/ by zero");
        }
        int a = isNeg(x) ? oppositeNumber(x) : x;
        int b = isNeg(y) ? oppositeNumber(y) : y;
        int result = 0;
        for (int i = 30; i >= 0; i--) {
            if ((a >> i) >= b) {
                result |= (1 << i);
                a = minus(a, b << i);
            }
        }
        return isNeg(x) ^ isNeg(y) ? oppositeNumber(result) : result;
    }

    public  static int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == Integer.MIN_VALUE) {
            return 1;
        } else if (divisor == Integer.MIN_VALUE) {
            return 0;
        } else if (dividend == Integer.MIN_VALUE) {
            if (divisor == oppositeNumber(1)) {
                return Integer.MAX_VALUE;
            } else {
                int div = div(add(dividend, 1), divisor);
                return add(div, div(minus(dividend, multi(div, divisor)), divisor));
            }
        } else {
            return div(dividend, divisor);
        }
    }


    public static void main(String[] args) {
        System.out.println(add(4, 0));
        System.out.println(minus(0, 1));
        System.out.println(multi(2, 1));
        System.out.println(Integer.MIN_VALUE);
        System.out.println(divide(-2147483648, -1));

    }
}
