class Solution {
    public int findMaxK(int[] nums) {
        Set<Integer> zzs = new HashSet<Integer>();
        int MAXnum = 0;
        for (int i = 0; i < nums.length; i++) {
            int nowMax = 0 - nums[i];
            if(zzs.contains(nowMax) && Math.abs(nowMax) > MAXnum){
                MAXnum = Math.abs(nowMax);
            }
            zzs.add(nums[i]);
        }
        if (MAXnum == 0) {
            return -1;
        }
        return MAXnum;
    }
}


//官方题解如下：
class Solution {
    public int findMaxK(int[] nums) {
        int k = -1;
        Set<Integer> set = new HashSet<Integer>();
        for (int x : nums) {
            set.add(x);
        }
        for (int x : nums) {
            if (set.contains(-x)) {
                k = Math.max(k, x);
            }
        }
        return k;
    }
}