/*
    Task: Determine if string t is a permutation of s.

    Key observations:
        - Both string s and t consist only of lowercase english letters (26).
        - s and t can be of different sizes.

    Solution:
        - Get the frequency of the letters from the string s
          and find if we can build string t with that frequencies
          leaving the frequency bucket empty.

*/

class Solution {
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length())
            return false;

        int[] freq = new int[26];
        int n = s.length();
        for(int i = 0; i < n; i++)
            freq[s.charAt(i)-'a']++;

        for(int i = 0; i < n; i++) {
            if(freq[t.charAt(i)-'a'] > 0)
                freq[t.charAt(i)-'a']--;
            else
                return false;
        }

        for(int i : freq) {
            if(i > 0)
                return false;
        }

        return true;
    }
}
