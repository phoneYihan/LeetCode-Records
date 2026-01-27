//官方题解：同下。

//我的题解：
//使用HashSet存储所有数字；
//for循环：遍历每个数字，只有当它是连续序列的起点时才开始计数
//内层while循环：从起点开始，逐个检查下一个连续数字是否存在，直到序列中断；记录并更新遇到的最大连续序列长度。

//关键在于只对连续序列的起点执行内层while循环，而不是对每个元素都执行完整的while循环。
//每个数字要么在if分支中被跳过，要么在while循环中被处理一次；所有while循环的总执行次数不超过n次，总操作数 ≤ n + n = 2n = O(n)
class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }        
        // 将所有数字放入HashSet，便于O(1)查找
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }       
        int maxLength = 0;        
        // 遍历每个数字
        for (int num : numSet) {
            // 只有当num-1不存在时，num才是一个连续序列的起点
            if (!numSet.contains(num - 1)) {
                int currentNum = num;
                int currentLength = 1;                
                // 从起点开始，找到这个连续序列的终点
                while (numSet.contains(currentNum + 1)) {
                    currentNum++;
                    currentLength++;
                }                
                // 更新最大长度
                maxLength = Math.max(maxLength, currentLength);
            }
        }
        return maxLength;
    }
}