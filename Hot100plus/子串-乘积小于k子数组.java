//官方题解（滑动窗口）

class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int n = nums.length, ret = 0;
        int prod = 1, i = 0;
        for (int j = 0; j < n; j++) {
            prod *= nums[j];
            while (i <= j && prod >= k) {
                prod /= nums[i];
                i++;
            }
            ret += j - i + 1;
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