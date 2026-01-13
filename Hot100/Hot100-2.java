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
//字母异位词的两个字符串中的相同字母出现的次数一定是相同的，故可以将每个字母出现的次数使用【字符串】表示，作为哈希表的键。
//由于字符串只包含小写字母，因此对于每个字符串，可以使用长度为 26 的数组记录每个字母出现的次数。
//关于StringBuffer的使用：
/*
使用StringBuffer（或StringBuilder）是为了提高字符串拼接的效率。

（多线程用StringBuffer，线程安全但有同步开销）
（在单线程环境中，StringBuilder通常是更好的选择，不是线程安全的，但性能更好）

使用String直接拼接的问题：
Java中的String是不可变对象（immutable），
每次使用+=操作都会创建一个新的String对象，
原来的String对象变成垃圾，需要被GC回收。
时间复杂度为O(n²)，其中n是拼接的字符串数量。

使用StringBuffer的优势：
内部维护一个可变的字符数组（内部缓冲区），
append操作直接在数组上进行，无需创建新对象，
最后一次性转换为String对象。
时间复杂度为O(n)。
*/
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (String str : strs) {
            int[] counts = new int[26];
            int length = str.length();
            for (int i = 0; i < length; i++) {
		//计算当前字符相对于'a'的偏移量作为索引，为对应位置的计数器加一；
                counts[str.charAt(i) - 'a']++;
            }
            // 将每个出现次数大于 0 的字母和出现次数按顺序拼接成字符串，作为哈希表的键
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < 26; i++) {
                if (counts[i] != 0) {
                    sb.append((char) ('a' + i));	//将当前字母添加到特征键字符串中；
                    sb.append(counts[i]);		//将当前字母的出现次数添加到特征键字符串中。
                }
            }
            String key = sb.toString();	//将StringBuffer转换回字符串，作为特征键；
            List<String> list = map.getOrDefault(key, new ArrayList<String>());	//从map中获取对应特征键的列表，如果不存在则创建一个新的空列表
            list.add(str);		//将当前字符串添加到对应的列表中；
            map.put(key, list);		//将特征键和对应的列表存回map中。
        }
        return new ArrayList<List<String>>(map.values());
    }
}




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