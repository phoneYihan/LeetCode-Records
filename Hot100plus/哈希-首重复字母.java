class Solution {
    public char repeatedCharacter(String s) {
        Set<Character> seen = new HashSet<Character>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (!seen.add(ch)) {
                return ch;
            }
        }
        return ' ';
    }
}

/*
class Solution {
    public char repeatedCharacter(String s) {
        Map<Character, Boolean> ftcTB = new HashMap<>();
        for (char crctr : s.toCharArray()) {
            if (ftcTB.containsKey(crctr)) {
                return crctr;
            }
            ftcTB.put(crctr, true);
        }
        return '0';
    }
}
*/