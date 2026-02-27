//官方题解（定长滑动窗口）
/*
维护一个长度相同的滑动窗口，并在滑动中维护窗口中【每种字母的数量】；
当窗口中每种字母的数量与字符串 p 中每种字母的数量相同时，则说明当前窗口为字符串 p 的异位词。

用两个int[26]来存储每种字母的数量。一个给p，另一个给s的子串t（每次循环时它会动态地改变）。

窗口的滑动由for循环保证；每一次滑动都要确保s的子串t的对应的int[26]数组的对应位置的数值正确进行增/减。
*/

class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        // 统计 p 的每种字母的出现次数
        int[] cntP = new int[26];
        for (char c : p.toCharArray()) {
            cntP[c - 'a']++; // 统计 p 的字母
        }

        List<Integer> ans = new ArrayList<>();
        int[] cntS = new int[26]; // 统计 s 的长为 p.length() 的子串 t 的每种字母的出现次数
        for (int right = 0; right < s.length(); right++) {
            cntS[s.charAt(right) - 'a']++; // 右端点字母进入窗口
            int left = right - p.length() + 1;
            if (left < 0) { // 窗口长度不足 p.length()
                continue;
            }
            if (Arrays.equals(cntS, cntP)) { // t 和 p 的每种字母的出现次数都相同
                ans.add(left); // t 左端点下标（起始索引）加入答案
            }
            cntS[s.charAt(left) - 'a']--; // 若出现次数不相同，窗口左端点字母离开窗口
        }
        return ans;
    }
}

作者：灵茶山艾府
链接：https://leetcode.cn/problems/find-all-anagrams-in-a-string/solutions/2969498/liang-chong-fang-fa-ding-chang-hua-chuan-14pd/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。





//我的错误题解（超时）
//主要瓶颈在于每次都对子字符串进行排序操作，时间复杂度为O(n * m log m)，当数据规模大时会导致超时！

class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        char[] ss = s.toCharArray();
        int pL = p.length();
        List<Integer> ans = new ArrayList<>();
        for (int i = 0;i <= ss.length - pL;i++) {
            String inStr = String.copyValueOf(ss, i, pL);
            if (isAnagram(inStr, p)) {
                ans.add(i);
            }
        }
        return ans;
    }

    //判断是否是字母异位词
    private boolean isAnagram(String s, String t) {
        char[] charArray1 = s.toCharArray();
        char[] charArray2 = t.toCharArray();
        Arrays.sort(charArray1);
        Arrays.sort(charArray2);
        boolean result = Arrays.equals(charArray1, charArray2);
        return result;
    }
}
