/*
    Task:
        Process a string to get unique substrings while iterating it.

    Idea:
        Simulate the given algorithm using a set and an answer array
        to keep the order.

        Time complexity: O(n)
        Space complexity: O(n)
*/
class Solution {
public:
    vector<string> partitionString(string s) {
        unordered_set<string> auxSet;
        vector<string> uniqueSegments;

        string auxString = "";
        for(char c : s)
        {
            auxString += c;
            if(auxSet.find(auxString) == auxSet.end())
            {
                auxSet.insert(auxString);
                uniqueSegments.push_back(auxString);
                auxString = "";
            }
        }

        return uniqueSegments;
    }
};


/*
s = "abbccccd"
            "d"
     ["a", "b", "bc", "c", "cc", "d"]
*/
