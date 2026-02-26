//官方题解





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
