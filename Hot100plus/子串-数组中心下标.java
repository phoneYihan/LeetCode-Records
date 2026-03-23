//我的题解

class Solution {
    public int pivotIndex(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return 0;
        }

        int[] s = new int[n + 1];
        for (int i = 0;i < n;i++) {
            s[i + 1] = s[i] + nums[i];
        }

        for (int x = 1;x < n + 1;x++) {
            if(s[x - 1] == s[n] - s[x]) {
                return x - 1;
            }
        }
        return -1;
    }
}