官方题解：
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int best = 10000000;

        // 枚举 a
        for (int i = 0; i < n; ++i) {
            // 保证和上一次枚举的元素不相等
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            // 使用双指针枚举 b 和 c
            int j = i + 1, k = n - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                // 如果和为 target 直接返回答案
                if (sum == target) {
                    return target;
                }
                // 根据差值的绝对值来更新答案
                if (Math.abs(sum - target) < Math.abs(best - target)) {
                    best = sum;
                }
                if (sum > target) {
                    // 如果和大于 target，移动 c 对应的指针
                    int k0 = k - 1;
                    // 移动到下一个不相等的元素
                    while (j < k0 && nums[k0] == nums[k]) {
                        --k0;
                    }
                    k = k0;
                } else {
                    // 如果和小于 target，移动 b 对应的指针
                    int j0 = j + 1;
                    // 移动到下一个不相等的元素
                    while (j0 < k && nums[j0] == nums[j]) {
                        ++j0;
                    }
                    j = j0;
                }
            }
        }
        return best;
    }
}




优化-官方题解：
/*
做了两处优化：

一、设 s = nums[i] + nums[i+1] + nums[i+2]。如果 s > target，由于数组已经排序，后面无论怎么选，选出的三个数的和不会比 s 还小，所以不会找到比 s 更优的答案了。所以只要 s > target，就可以直接 break 外层循环了。在 break 前判断 s 是否离 target 更近，如果更近，那么更新 best 为 s。

二、设 s = nums[i] + nums[n-2] + nums[n-1]。如果 s < target，由于数组已经排序，nums[i] 加上后面任意两个数都不超过 s，所以下面的双指针就不需要跑了，无法找到比 s 更优的答案。但是后面还有更大的 nums[i]，可能找到一个离 target 更近的三数之和，所以还需要继续枚举，continue 外层循环。在 continue 前判断 s 是否离 target 更近，如果更近，那么更新 best 为 s。

*/
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);

        int n = nums.length;
        int ans = Integer.MAX_VALUE / 2; // 除 2 防止减去一个负数溢出
        for (int i = 0; i < n - 2; i++) {
            int x = nums[i];
            // 优化
            if (i > 0 && x == nums[i - 1]) {
                continue; 
            }

            // 优化
            int s = x + nums[i + 1] + nums[i + 2];
            if (s > target) { // 后面无论怎么选，选出的三数之和不会比 s 还小
                if (s - target < Math.abs(ans - target)) {
                    ans = s;
                }
                break;
            }

            // 优化
            s = x + nums[n - 2] + nums[n - 1];
            if (s < target) { // x 加上后面任意两个数都不超过 s，所以下面的双指针就不需要跑了
                if (target - s < Math.abs(ans - target)) {
                    ans = s;
                }
                continue;
            }

            // 双指针
            int j = i + 1;
            int k = n - 1;
            while (j < k) {
                s = x + nums[j] + nums[k];
                if (s == target) {
                    return target;
                }
                if (Math.abs(s - target) < Math.abs(ans - target)) { // s 离 target 更近
                    ans = s;
                }
                if (s > target) {
                    k--;
                } else { // s < target
                    j++;
                }
            }
        }
        return ans;
    }
}
