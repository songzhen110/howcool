package com.songzhen.howcool.study.algorithm;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    /**
     * 求解两数只和
     * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
     *
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
     *
     * 你可以按任意顺序返回答案。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：nums = [2,7,11,15], target = 9
     * 输出：[0,1]
     * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/two-sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args args
     */
    public static void main(String[] args) {



        int[] nums = new int[]{2, 7, 11, 15};
        int target = 9;
        int[] ints = twoSum(nums, target);
        if (null != ints) {
            System.out.println(ints[0] + "," + ints[1]);
        }
        int[] ints2 = twoSum2(nums, target);
        if (null != ints2) {
            System.out.println(ints2[0] + "," + ints2[1]);
        }

    }

    /**
     * 穷举（暴力）
     */
    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (target - nums[i] == nums[j]) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    /**
     * hashMap
     */
    public static int[] twoSum2(int[] nums, int target) {

        Map<Integer,Integer> map = new HashMap<>(16);
        for (int i = 0; i < nums.length; i++) {
            int flag = target - nums[i];
            if(map.containsKey(flag)){
                return new int[]{map.get(flag),i};
            }
            map.put(nums[i],i);
        }
        return null;
    }

}