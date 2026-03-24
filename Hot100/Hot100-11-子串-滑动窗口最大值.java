

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if (k == 1) {
            return nums;
        }

        if (k == n) {
            Arrays.sort(nums);
            int[] ans1 = new int[1];
            ans1[0] = nums[n - 1];
            return ans1;
        }

        for (int i = 0;i < n;i++) {
            //构造定长滑动窗口；
            //队列？
        }
    }
}