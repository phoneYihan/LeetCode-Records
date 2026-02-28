/*
必须先移除旧字符（left指针），再检查是否匹配；否则，cntS2[26]中永远包含(窗口数+1)个字母的信息，永远匹配不上。
*/
class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }        

        int[] cntS1 = new int[26];
        for (char c:s1.toCharArray()) {
            cntS1[c - 'a']++;
        }
        int[] cntS2 = new int[26];

        //填充初始窗口，窗口长度为s1.length()
        for (int i = 0; i < s1.length(); i++) {
            cntS2[s2.charAt(i) - 'a']++;
        }
        
        // 检查初始窗口
        if (Arrays.equals(cntS1, cntS2)) {
            return true;
        }        

        //右指针直接从右窗口开始遍历；
        //right指向本次循环中新加入的那个字符(新的右窗口)；
        //left指向本次循环中需要删除的左窗口之外的那个字符（同时这个字符也是上次循环中的左窗口）
        for (int right = s1.length();right < s2.length();right++) {
            cntS2[s2.charAt(right) - 'a']++;
            int left = right - s1.length();
            cntS2[s2.charAt(left) - 'a']--;
         
            if (Arrays.equals(cntS1, cntS2)) {
                return true;
            }
            //cntS2[s2.charAt(left) - 'a']--;
        }
        return false;
    }
}