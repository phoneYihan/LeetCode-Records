//官方题解
//核心思路：如果不是字母异位词，则添加。

class Solution {
    public List<String> removeAnagrams(String[] words) {
        List<String> res = new ArrayList<>();
        res.add(words[0]);   // 结果数组
        int n = words.length;
        for (int i = 1; i < n; i++) {
            if (!compare(words[i], words[i - 1])) {
                res.add(words[i]);
            }
        }
        return res;
    }
    
    // 判断两个单词是否为字母异位词
    private boolean compare(String word1, String word2) {
        int[] freq = new int[26];
        for (char ch : word1.toCharArray()) {
            freq[ch - 'a']++;
        }
        for (char ch : word2.toCharArray()) {
            freq[ch - 'a']--;
        }
        for (int x : freq) {
            if (x != 0) {
                return false;
            }
        }
        return true;
    }
}





//我的解法
////核心思路：如果是字母异位词，则删除。
class Solution {
    public List<String> removeAnagrams(String[] words) {
        // 使用 List 便于动态操作
        List<String> lastWords = new ArrayList<>(Arrays.asList(words));
        
        for (int i = 1; i < lastWords.size(); ) {
            if (isAnagram(lastWords.get(i - 1), lastWords.get(i))) {
                lastWords.remove(i);
            } else {
                i++;
            }
        }
        return lastWords;
    }

    private boolean isAnagram(String s, String t) {
        char[] charArray1 = s.toCharArray();
        char[] charArray2 = t.toCharArray();
        Arrays.sort(charArray1);
        Arrays.sort(charArray2);
        boolean result = Arrays.equals(charArray1, charArray2);
        return result;
    }
}