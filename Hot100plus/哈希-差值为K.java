//在遍历时我们可以使用一个哈希表来维护不同数值的频率，并统计符合条件的数对总数。
//getOrDefault:返回参数在哈希表中的出现次数，若没有则返回默认值0.

class Solution {
    public int countKDifference(int[] nums, int k) {
        int res = 0, n = nums.length;
        Map<Integer, Integer> cnt = new HashMap<Integer, Integer>();
        for (int i = 0; i < n; ++i) {
            res += cnt.getOrDefault(nums[i] - k, 0) + cnt.getOrDefault(nums[i] + k, 0);
            cnt.put(nums[i], cnt.getOrDefault(nums[i], 0) + 1);
            //把当前值 nums[i] 的出现次数在哈希表中 +1
            //哈希表的键是数组的数值，哈希表的值是该数的出现次数。
        }
        return res;
    }
}

/*
class Solution {
    public int countKDifference(int[] nums, int k) {
        int myCount = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++){
                if (Math.abs(nums[i] - nums[j]) == k) {
                    myCount++;
                }
            }
        }
        return myCount;
    }
}
*/