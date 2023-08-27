package io.arkvaer.algorithm.leet;

import java.util.ArrayList;
import java.util.List;

/**
 * @author waver
 * @date 2023/8/26 22:08
 */
public class SummaryRanges {


    public static List<String> summaryRanges1(int[] nums) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            int left = i;
            while (left < nums.length && left + 1 < nums.length && nums[left + 1] - nums[left] == 1) {
                left++;
            }
            int rightIndex = Math.min(left, nums.length - 1);
            if (rightIndex == i) {
                stringBuilder.append(nums[i]);
            } else {
                stringBuilder.append(nums[i]).append("->").append(nums[rightIndex]);
            }
            result.add(stringBuilder.toString());
            i = left;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {0,1,2,4,5,7};


        List<String> strings1 = summaryRanges1(nums);
        System.out.println(strings1);
    }

}
