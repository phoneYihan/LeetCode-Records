//我们使用数组 firstIndex 记录每个字母从左到右第一次出现的位置，当该字母第二次出现时，
//减去第一次出现的位置即可得到两个相同字母之间的字母数量。
//初始化 firstIndex 中的元素全为 0
class Solution {
    public boolean checkDistances(String s, int[] distance) {
        int[] firstIndex = new int[26];
        for (int i = 0; i < s.length(); i++) {
            int idx = s.charAt(i) - 'a';
            if (firstIndex[idx] != 0 && i - firstIndex[idx] != distance[idx]) {
                return false;
            }
            firstIndex[idx] = i + 1;	//这样做是为了区分"未出现"（值为0）和"出现在位置0"（值为1）的情况，
							//当firstIndex[idx]等于0时，可能是字母还没出现过，也可能是字母出现在位置0。
        }
        return true;
    }
}

作者：力扣官方题解
链接：https://leetcode.cn/problems/check-distances-between-same-letters/solutions/2213877/jian-cha-xiang-tong-zi-mu-jian-de-ju-chi-gxqg/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。


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
                
                // 检查实际距离是否与distance数组中的期望距离一致；
		//distance[c - 'a']是获取字符c对应的距离值，本质上是利用了字符的ASCII码值进行计算；
		//这种技巧在处理字母相关的问题时非常常见，被称为"字符映射"或"字母哈希"，
		//可以将字母快速转换为数组索引，避免使用复杂的条件判断语句。
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