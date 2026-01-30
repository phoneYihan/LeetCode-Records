//题解：修改官方的交换解法；
//我们只需要遍历该序列至多两次。
class Solution {
    public int removeElement(int[] nums, int val) {
        int n = nums.length, left = 0, right = 0;
        while (right < n) {
            if (nums[right] != val) {
                swap(nums, left, right);
                left++;
            }
            right++;
        }
        return left;
    }

    public void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}


//优化解法：
//两个指针初始时分别位于数组的首尾，向中间移动遍历该序列。方法二避免了需要保留的元素的重复复制操作，只需要遍历该序列至多一次。
class Solution {
    public int removeElement(int[] nums, int val) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            if (nums[left] != val) {
                left++;
            } else if (nums[right] == val) {
                right--;
            } else {
                nums[left] = nums[right];
                right--;
            }
        }
        return left;
    }
}