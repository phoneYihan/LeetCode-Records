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