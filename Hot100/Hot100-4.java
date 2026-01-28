//官方题解：交换
class Solution {
    public void moveZeroes(int[] nums) {
        int n = nums.length, left = 0, right = 0;
        while (right < n) {
            if (nums[right] != 0) {
                swap(nums, left, right);
                left++;
            }
            right++;
        }
    }

    public void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}


//我的题解：覆盖+填充
class Solution {
    public void moveZeroes(int[] nums) {
        // 记录非零元素应该放置的位置（锚点指针）
        int nonZeroIndex = 0;        
        // 遍历数组，将非零元素依次移到前面，保持了非零元素的相对顺序不变。（循环指针）
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                // 将当前非零元素移到nonZeroIndex位置
                nums[nonZeroIndex] = nums[i];   //直接赋值覆盖。
                nonZeroIndex++;     //锚点向右移动一次。
            }
        }        
        // 将剩余位置填充为0
        while (nonZeroIndex < nums.length) {
            nums[nonZeroIndex] = 0;
            nonZeroIndex++;
        }
    }
}
