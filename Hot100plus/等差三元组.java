//官方题解，用HashSet，本质上是统计所有三元组的最小元素的数量。
class Solution {
    public int arithmeticTriplets(int[] nums, int diff) {
        Set<Integer> set = new HashSet<Integer>();
        for (int x : nums) {
            set.add(x);
        }
        int ans = 0;
        for (int x : nums) {
            if (set.contains(x + diff) && set.contains(x + 2 * diff)) {
                ans++;
            }
        }
        return ans;
    }
}

作者：力扣官方题解
链接：https://leetcode.cn/problems/number-of-arithmetic-triplets/solutions/2200026/suan-zhu-san-yuan-zu-de-shu-mu-by-leetco-ldq4/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。



//用两个HashMap：
//初试右表满、左表空，每次循环从右表移到左表。
class Solution {
    public int arithmeticTriplets(int[] nums, int diff) {
        int n = nums.length;
        Map<Integer, Integer> left = new HashMap<>(n);   // 左侧出现次数
        Map<Integer, Integer> right = new HashMap<>(n);  // 右侧出现次数

        // 先统计全局频率，作为初始 right
        for (int x : nums) {
            right.merge(x, 1, Integer::sum);
        }

        long ans = 0;
        for (int j = 0; j < n; j++) {
            int x = nums[j];
            // 把当前元素从 right 移出，表示 j 正在扫描
            right.merge(x, -1, Integer::sum);

            int needLeft  = x - diff;
            int needRight = x + diff;
            // 左侧已有数量
            int cntLeft  = left.getOrDefault(needLeft, 0);
            // 右侧剩余数量
            int cntRight = right.getOrDefault(needRight, 0);
            ans += (long) cntLeft * cntRight;

            // 把当前元素加入 left
            left.merge(x, 1, Integer::sum);
        }
        return (int) ans;      
    }
}


//只用一个for循环的优化。
//找三元组的最大元素。官方题解的变形。
//思路：一边把数字丢进 HashSet，一边检查是否存在 num-diff 和 num-2*diff，若都存在则计数 +1。
//时空都是O(n)
public class Solution {
    public int arithmeticTriplets(int[] nums, int diff) {
        Set<Integer> set = new HashSet<>();
        int res = 0;
        for (int num : nums) {
            set.add(num);
            if (set.contains(num - diff) && set.contains(num - 2 * diff)) {
                res++;
            }
        }
        return res;
    }