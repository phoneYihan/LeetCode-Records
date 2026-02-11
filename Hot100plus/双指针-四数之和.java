官方题解：




我的正确题解：
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums); // 先排序
        List<List<Integer>> res = new ArrayList<>();
        
        for (int i = 0; i < nums.length; i++) {
            // 跳过重复元素
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            //第二重for循环
            for (int j = i + 1; j < nums.length; j++) {
                // 跳过重复元素
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                
                // 双指针，目标是找到 nums[l] + nums[r] = -nums[i]
                int l = j + 1, r = nums.length - 1;
                long innerTarget = (long)target - nums[i] - nums[j];
                
                while (l < r) {
                    long sum = (long)nums[l] + nums[r];
                    if (sum == innerTarget) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[l], nums[r]));
                        l++;
                        r--;
                        // 跳过重复元素
                        while (l < r && nums[l] == nums[l - 1]) l++;
                        while (l < r && nums[r] == nums[r + 1]) r--;
                    } else if (sum < innerTarget) {
                        l++;
                    } else {
                        r--;
                    }
                }
            }
        }
        
        return res;
    }
}