package io.arkvaer.algorithm.basic.day01;

/**
 * 查找数组中出现奇数次的数字
 *
 * @author waver
 * @date 2023/6/21 下午4:07
 */
public class EvenTimesOddTimeNum {

    public static void printOddTimesNum2(int[] arr) {
        int eor = 0;
        for (int j : arr) {
            eor ^= j;
        }
        int rightOne = eor ^ (~eor + 1);
        int eor2 = 0;
        for (int j : arr) {
            if ((rightOne & j) != 0) {
                eor2 ^= j;
            }
        }


        int first = eor ^ eor2;
        System.out.println(eor2);
        System.out.println(first);
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 1, 2, 3, 1, 2, 3, 4, 6, 4, 3};
        printOddTimesNum2(arr);

    }
}
