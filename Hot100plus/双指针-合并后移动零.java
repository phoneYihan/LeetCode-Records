class Solution {
    public int[] applyOperations(int[] nums) {
        //合并
        int addLeft = 0, addRight = 1;
        while (addRight < nums.length) {
            if(nums[addLeft] == nums[addRight]) {
                nums[addLeft] = nums[addLeft] + nums[addLeft];
                nums[addRight] = 0;
                addLeft += 2;
                addRight += 2;
            }
            else {
                addLeft++;
                addRight++;
            }
        }

        //移动零
        int n = nums.length, left = 0, right = 0;
        while (right < n) {
            if (nums[right] != 0) {
                swap(nums, left, right);
                left++;
            }
            right++;
        }

        return nums;
    }

    public void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}