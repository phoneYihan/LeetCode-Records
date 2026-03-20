//官方题解（滑动窗口）

/*
固定子数组 [i,j] 的右端点 j；
对于子数组 [i,j]，存在一个满足以下条件的l1：当左端点 i≥l1时，所有子数组的元素乘积都小于 k，当左端点 i<l1时，所有子数组的元素乘积都大于等于 k；
那么对于右端点为 j+1 的所有子数组，它的左端点 i 就不需要从 0 开始枚举，而是从 i=l1处开始枚举。

我们枚举子数组的右端点 j，并且左端点从 i=0 开始，用 prod 记录子数组 [i,j] 的元素乘积。每枚举一个右端点 j，如果当前子数组元素乘积 prod 大于等于 k，
那么我们【右移左端点 i 直到满足当前子数组元素乘积小于 k 或者 i>j】，那么元素乘积小于 k 的子数组数目为 j−i+1。返回所有数目之和。
*/

class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int n = nums.length, ret = 0;
        int prod = 1, i = 0;
        for (int j = 0; j < n; j++) {
            prod = prod * nums[j];
            while (i <= j && prod >= k) {
                prod = prod / nums[i];
                i++;
            }
            ret = ret + j - i + 1;
        }
        return ret;
    }
}





//我的错误解法（无法处理有 0 的情况）
class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int n = nums.length;
        int[] PAI = new int[n + 1];
	    //构造前缀积数组PAI[]；默认PAI[0]=0，从PAI[1]开始赋值。
        for (int i = 0; i < n; i++) {
            PAI[i + 1] = PAI[i] * nums[i];
        }       

        //哈希表的键是前缀积数组PAI[]的数值，值是key对应的前缀积出现的次数；（出现一次就自增1）
        Map<Integer, Integer> cnt = new HashMap<>(n + 1, 1); // 预分配空间
        int ans = 0; 
        for (int sj : PAI) {
            //ans = ans + cnt.getOrDefault(sj / k, 0);		//查找之前出现过多少次这个起始位置的前缀积；
		    //计算 (sj / k) 这个值代表我们需要找的另一个前缀和。如果找到了，它表明存在一个子数组的积为 k。
            for (Integer key : cnt.keySet()) {
                if (sj / k < key) {
                    ans++;
                }
            }

            cnt.merge(sj, 1, Integer::sum); 		// cnt[sj]++
		    //同时也要更新 cnt 来记录前缀和的出现次数，供后续迭代使用。
        }
        return ans;
    }
}