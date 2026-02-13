//双指针解法：（是基于动态规划的改进）
/*

*/
class Solution {
    public int trap(int[] height) {
        int ans = 0;
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;
        while (left < right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            if (height[left] < height[right]) {
                ans += leftMax - height[left];
                left++;
            } else {
                ans += rightMax - height[right];
                right--;
            }
        }
        return ans;
    }
}


//动态规划解法：
/*
使用动态规划的方法，可以在 O(n) 的时间内预处理得到每个位置两边的最大高度。

总体思路：以最高的那一个或几个柱子为界，分成左右两边；然后
正向遍历数组 height 得到左边区域数组 leftMax 的每个元素值，反向遍历数组 height 得到右边区域数组 rightMax 的每个元素值。

leftMax[i] 表示下标 i 及其左边的位置中，height 的最大高度；rightMax[i] 表示下标 i 及其右边的位置中，height 的最大高度。

在得到数组 leftMax 和 rightMax 的每个元素值之后，下标 i 处能接的雨水量等于 min(leftMax[i],rightMax[i])−height[i]。
遍历每个下标位置即可得到能接的雨水总量。
*/
class Solution {
    public int trap(int[] height) {
        int n = height.length;
        if (n == 0) {
            return 0;
        }

        int[] leftMax = new int[n];
        leftMax[0] = height[0];
        for (int i = 1; i < n; ++i) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        int[] rightMax = new int[n];
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }

        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return ans;
    }
}


