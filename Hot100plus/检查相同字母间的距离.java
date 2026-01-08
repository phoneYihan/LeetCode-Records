


public class Solution {
    /**
     * 检查字符串是否为匀整字符串
     * @param s 仅由小写英文字母组成的字符串，每个字母恰好出现两次
     * @param distance 长度为26的整数数组，表示每个字母两次出现之间的距离
     * @return 如果是匀整字符串返回true，否则返回false
     */
    public boolean checkDistances(String s, int[] distance) {
        // 使用Map记录每个字符第一次出现的位置
        Map<Character, Integer> firstOccurrence = new HashMap<>();
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (firstOccurrence.containsKey(c)) {
                // 如果已经出现过一次，计算两次出现之间的距离
                int firstIndex = firstOccurrence.get(c);
                int actualDistance = i - firstIndex - 1;
                
                // 检查实际距离是否与distance数组中的期望距离一致
                if (actualDistance != distance[c - 'a']) {
                    return false;
                }
            } else {
                // 记录第一次出现的位置
                firstOccurrence.put(c, i);
            }
        }
        
        return true;
    }
}