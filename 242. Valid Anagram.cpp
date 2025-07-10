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
public:
    bool isAnagram(string s, string t) {
        if(s.size() != t.size())
            return false;

        int freq[26];
        for(char c : s)
            freq[c -'a']++;

        for(char c : t)
        {
            if(freq[c-'a'] > 0)
                freq[c - 'a']--;
            else
                return false;
        }

        for(int i : freq)
        {
            if(i > 0)
                return false;
        }

        return true;
    }
};
