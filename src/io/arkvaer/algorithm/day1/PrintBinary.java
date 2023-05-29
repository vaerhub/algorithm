package io.arkvaer.algorithm.day1;

/**
 * 打印整数的二进制形式
 *
 * @author waver
 * @date 2023/5/29 下午7:38
 */
public class PrintBinary {
    public static void print(int num) {
        for (int i = 31; i >= 0; i--) {
            System.out.print((num & (1 << i)) == 0 ? "0" : "1");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        print(65);
        print(Integer.MAX_VALUE);
        print(Integer.MIN_VALUE);
    }
}
