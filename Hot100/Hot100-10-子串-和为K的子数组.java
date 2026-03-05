//只有当数组元素全部同号时，才能用滑动窗口！有正有负就不能用！

/*
Si定义为序列的前i项和；a[n]从0开始；
则有：
S1 = S0 + a0
S2 = S1 + a1
S3 = S2 + a2
......
Si = Si-1 + ai-1
（Si = Si-2 + ai-2 +ai-1）
......

移项：
ai = Si+1 - Si，或ai-1 = Si - Si-1

求和规律：
ai                       = Si+1 - Si
ai + ai-1            = Si+1 - Si-1
ai + ai-1 + ai-2 = Si+1 - Si-2
......

*/



//灵茶题解
/*
假设和为k的子数组是从nums[i]到nums[j]，那么：
由：
s[j+1] = nums[0]+...+nums[i]+...+nums[j]
s[i]      = nums[0]+nums[i-1]
得：
s[j+1] - s[i] = nums[i]+...+nums[j] = k
即：
s[j+1] - k = s[i]

所以当遍历到 s[j+1] 时，如果之前出现过 s[i]，就意味着存在一个和为k的子数组。

本质上是对暴力枚举算法的哈希表优化：
暴力枚举做法是，外层循环枚举 j，内层循环枚举 i，如果 s[j]−s[i]=k，那么答案加一。
前缀和解法保留了「外层循环枚举 j」这个过程，把内层循环用哈希表优化成了 O(1)。
时间复杂度是O(n)。

*/

class Solution {
    public int subarraySum(int[] nums, int k) {
        int n = nums.length;
        int[] s = new int[n + 1];
	//构造前缀和数组s[]；默认s[0]=0，从s[1]开始赋值。
        for (int i = 0; i < n; i++) {
            s[i + 1] = s[i] + nums[i];
        }
	
	//哈希表的键是前缀和数组s[]的数值，值是key对应的前缀和出现的次数；（出现一次就自增1）
        Map<Integer, Integer> cnt = new HashMap<>(n + 1, 1); // 预分配空间
        int ans = 0;
        for (int sj : s) {
            ans += cnt.getOrDefault(sj - k, 0);		//查找之前出现过多少次这个起始位置的前缀和；
		//计算 (sj - k) 这个值代表我们需要找的另一个前缀和。如果找到了，它表明存在一个子数组的和为 k。
            cnt.merge(sj, 1, Integer::sum); 		// cnt[sj]++
		//同时也要更新 cnt 来记录前缀和的出现次数，供后续迭代使用。
        }
        return ans;
    }
}






//官方题解：
public class Solution {
    public int subarraySum(int[] nums, int k) {
        int count = 0, pre = 0;
        HashMap < Integer, Integer > mp = new HashMap < > ();
        mp.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            pre += nums[i];
            if (mp.containsKey(pre - k)) {
                count += mp.get(pre - k);
            }
            mp.put(pre, mp.getOrDefault(pre, 0) + 1);
        }
        return count;
    }
}



//暴力枚举解法：
/*
关键是在内层循环时，向左遍历数组，并且不设置continue；
*/
public class Solution {
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        for (int start = 0; start < nums.length; ++start) {
            int sum = 0;
            for (int end = start; end >= 0; --end) {
                sum += nums[end];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }
}
