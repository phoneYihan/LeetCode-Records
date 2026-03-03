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
