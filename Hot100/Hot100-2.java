//官方题解：排序法。
//由于互为字母异位词的两个字符串包含的字母相同，因此对两个字符串分别进行排序之后
//得到的字符串一定是相同的，故可以将排序之后的字符串作为哈希表的键。
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (String str : strs) {
            //将当前字符串 str 转换为字符数组 array，方便后续排序。
            char[] array = str.toCharArray();
            //直接使用Arrays.sort()对任意数组进行排序;
            Arrays.sort(array);
            //将排序后的字符数组重新转换为字符串 key，作为哈希表的键。
            String key = new String(array);
            //从哈希表 map 中获取与键 key 对应的字符串列表。如果不存在，则返回一个新的空列表。
            List<String> list = map.getOrDefault(key, new ArrayList<String>());
            list.add(str);
            map.put(key, list);
        }
        //将哈希表 map 中的所有值（即所有字母异位词组）转换为一个ArrayList列表并返回。
        return new ArrayList<List<String>>(map.values());
    }
}


//官方题解：计数法。





//我的题解
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 1) {
            List<List<String>> finalOut = new ArrayList<>();
            List<String> innerList = new ArrayList<>();
            innerList.add(strs[0]);
            finalOut.add(innerList);
            return finalOut;
        }
        List<List<String>> finalSTRSarrList = new ArrayList<>();
        Set<String> nextSet = new HashSet<String>();
        for (int i = 0; i < strs.length; i++) {
            //避免重复匹配：
            if (nextSet.contains(strs[i])) {
                continue;
            }
            //创建第i个字符串数组：
            ArrayList<String> firstSTRSarrList = new ArrayList<>();
            firstSTRSarrList.add(strs[i]);

            String firstSTRSString = strs[i];
            Map<Character, Integer> firstSTRSMap = new HashMap<Character, Integer>();
            // 统计字符出现次数
            for (int m = 0; m < firstSTRSString.length(); m++) {
                char currentChar = firstSTRSString.charAt(m);
                firstSTRSMap.put(currentChar, firstSTRSMap.getOrDefault(currentChar, 0) + 1);
                //哈希表的值用来记录字符出现次数。要么是1（未出现），要么是>=2（已出现过）。
            }

            for (int j = i + 1; j < strs.length; j++) {
                //匹配操作，若匹配成功则添加元素；若失败则什么也不做；
                if (strs[i].length() != strs[j].length()){
                    continue;
                }
                // 统计当前strs[j]字符串的字符频率
                Map<Character, Integer> currentMap = new HashMap<Character, Integer>();
                String nextSTRSString = strs[j];
                for (int k = 0; k < nextSTRSString.length(); k++) {
                    char c = nextSTRSString.charAt(k);
                    currentMap.put(c, currentMap.getOrDefault(c, 0) + 1);
                }
                // 比较两个哈希表各自代表的字符频率是否完全相同
                if (firstSTRSMap.equals(currentMap)) {
                    //把字符串strs[j]添加到字符串数组firstSTRSarrList里
                    firstSTRSarrList.add(strs[j]);
                    nextSet.add(strs[j]);
                }                   
            }
            //把所有的这些长短不一的字符串数组拼凑成 以字符串数组为元素的数组。
            finalSTRSarrList.add(firstSTRSarrList);
        }
        return finalSTRSarrList;
    }
}