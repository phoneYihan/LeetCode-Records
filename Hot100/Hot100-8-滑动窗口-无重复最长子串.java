//官方题解：




//我的错误解法：
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        char[] ss = s.toCharArray();
        int n = ss.length - 1;
        StringBuilder ansString = new StringBuilder();

        for (int left = 0;left < n;left++) {
            if (ansString.length() == ss.length - left) {
                break;
            }
            //为动态字符数组ansStringIn添加元素
            Set<Character> ansSet = new HashSet<>();
            StringBuilder ansStringIn = new StringBuilder();
            int leftIn = left;
            while (!ansSet.contains(ss[leftIn])) {
                ansSet.add(ss[leftIn]);
                ansStringIn.append(ss[leftIn]);
                leftIn++;
            }
            if (ansStringIn.length() > ansString.length()) {
                //替换
                ansString = ansStringIn;
            }
        }

        int nn = ansString.length();
        return nn;
    }
}