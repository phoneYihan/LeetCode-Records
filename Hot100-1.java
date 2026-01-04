// LeetCode Hot100刷题记录。同步至我的github。
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> hashtable = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; ++i){
            if (hashtable.containsKey(target - nums[i])){
                return new int[]{hashtable.get(target - nums[i]), i};
            }
            hashtable.put(nums[i], i);
            // 存储到哈希表里：数组元素的值 -> 数组元素的索引
        }
        return new int[]{};
    }
} 
/*
//for循环内部也可以替换成更容易理解但更长的代码：
{
            int currentValue = nums[i];  // 当前数组元素的值
            int complement = target - currentValue;  // 需要的补数
            
            if (hashtable.containsKey(complement)){
                // 找到了！返回：补数的索引 和 当前索引
                return new int[]{hashtable.get(complement), i};
            }
            // 存储：数组元素的值 -> 数组元素的索引
            hashtable.put(currentValue, i);
}
*/