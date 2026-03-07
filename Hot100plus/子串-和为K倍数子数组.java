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



//官方题解：

/*
--同余定理：如果两个整数m、n满足n-m能被k整除，那么n和m对k同余；

即：若 ( pre(j) - pre (i) ) % k == 0 ，则 pre(j) % k == pre(i) % k

又有：pre (i) % k = (a0 + a1 + ... + ai) % k = (a0 % k + a1 % k + ... ai % k ) % k

--哈希表：Key ：pre(i) % k；Value： i

--遍历过程：
1、计算前缀和 pre( j ) % k
2、当pre(j) % k 在哈希表中已存在，则说明此时存在 i 满足 pre(j) % k == pre(i) % k ( i < j )；
在HashMap里，已知Key，可以取到Value 即i的值， 最后 判断 j - i >= 2 是否成立 即可。
3、当 pre(j) % k 不存在于哈希表，则将 (pre(j) % k, j ) 存入哈希表。因在计算 pre(i) = (pre(i-1) + nums[i]) % k 时，pre(i) 只与上一个状态有关，
故可以直接用变量pre 替代数组。 那么 (求前缀和 % k) 的公式就简化为 题解代码中的 remainder = (remainder + nums[i]) % k;

*/

class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        int m = nums.length;
        if (m < 2) {
            return false;
        }
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0, -1);
        int remainder = 0;
        for (int i = 0; i < m; i++) {
            remainder = (remainder + nums[i]) % k;
            if (map.containsKey(remainder)) {
                int prevIndex = map.get(remainder);
                if (i - prevIndex >= 2) {
                    return true;
                }
            } else {
                map.put(remainder, i);
            }
        }
        return false;
    }
}