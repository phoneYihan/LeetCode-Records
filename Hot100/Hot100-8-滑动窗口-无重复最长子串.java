//官方题解：
/*
如果我们依次递增地枚举子串的起始位置，那么子串的结束位置也是递增的（非严格递增）。
**原因：原因在于，假设我们选择字符串中的第 k 个字符作为起始位置，并且得到了不包含重复字符的最长子串的结束位置为 r k。
那么当我们选择第 k+1 个字符作为起始位置时，首先从 k+1 到 r k的字符显然是不重复的，并且由于少了原本的第 k 个字符，
我们可以尝试继续增大 r k，直到右侧出现了重复字符为止。

使用「滑动窗口」来解决这个问题：

--使用两个指针表示字符串中的某个子串（或窗口）的左右边界；
--在每一步的操作中，我们会将左指针向右移动一格，表示 我们开始枚举下一个字符作为起始位置，然后我们可以不断地向右移动右指针，
但需要保证这两个指针对应的子串中没有重复的字符。在移动结束后，这个子串就对应着 以左指针开始的，不包含重复字符的最长子串。
我们记录下这个子串的长度。
--在枚举结束后，我们找到的最长的子串的长度即为答案。

--还需要使用哈希集合来判断 是否有重复的字符。
**在左指针向右移动的时候，我们从哈希集合中移除一个字符，在右指针向右移动的时候，我们往哈希集合中添加一个字符。
*/
class Solution {
    public int lengthOfLongestSubstring(String s) {
        // 哈希集合，记录每个字符是否出现过
        Set<Character> occ = new HashSet<Character>();
        int n = s.length();
        // rk是右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                // i是左指针。左指针每向右移动一格，就移除一个字符。
                occ.remove(s.charAt(i - 1));
            }
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                // 不断地移动右指针
                occ.add(s.charAt(rk + 1));
                ++rk;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }
}






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