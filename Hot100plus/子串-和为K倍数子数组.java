//大神题解：

/*

*/

class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        int n = nums.length;
        int[] sum = new int[n + 1];
        for (int i = 1; i <= n; i++) sum[i] = sum[i - 1] + nums[i - 1];
        Set<Integer> set = new HashSet<>();
        for (int i = 2; i <= n; i++) {
            set.add(sum[i - 2] % k);
            if (set.contains(sum[i] % k)) return true;
        }
        return false;
    }
}

作者：宫水三叶
链接：https://leetcode.cn/problems/continuous-subarray-sum/solutions/808246/gong-shui-san-xie-tuo-zhan-wei-qiu-fang-1juse/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。