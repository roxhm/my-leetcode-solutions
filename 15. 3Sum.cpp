/*
    Task:
        Find three numbers at different positions that sum up to 0.
    Solution:
        The main idea is to try all combinations of three numbers to sum up to 0.

        Key observations:
            1. We can only pick one number from each position.
            2. We only have one special case where the number appears three times (i.e. 0+0+0 = 0)
            3. It's possible to have two of the three numbers the same (i.e a+b+b= 0).

        So, if we want to find three numbers in the array that sum up to 0,
            we can fix one of them as the first number,
            and look for the pairs of numbers that works as its complement.
            So, basically:
                target = a+b
            Then, for every pair, we can repeat the process: fix one of them,
            and look for its complement.
                a = b

            To look for a number, we can use a set.
            However, as we can have the same number twice in the triplet,
            we not only care about whether the number exists,
            but also about whether there are enough occurrences to use it.
            That's why we use a frequency map (or hash map) instead of a simple set.

        Complexity: (n²log(n))
*/
class Solution {
public:
    vector<vector<int>> threeSum(vector<int>& nums) {
        unordered_map<int, int> freq;
        vector<vector<int>> ans;
        set<vector<int>> uniqueTriplets;

        for(int num : nums)
            freq[num]++;

        vector<int> keys;
        for(auto& [k, _] : freq)
            keys.push_back(k);
        int nkey = keys.size();

        if(freq.find(0) != freq.end() && freq[0] >= 3)
            ans.push_back({0,0,0});

        for(int i = 0; i < nkey - 1; i++)
        {
            int target = keys[i];
            for(int j = i + 1; j < nkey; j++)
            {
                int a = keys[j];
                int b = -(target + a);
                if(freq.find(b) != freq.end())
                {
                    if((b == a || b == target) && freq[b] < 2)
                        continue;
                    vector<int> triplet = {target, a, b};
                    sort(triplet.begin(), triplet.end());
                    uniqueTriplets.insert(triplet);
                }
            }
        }

        for(vector<int> v : uniqueTriplets)
            ans.push_back(v);
        return ans;
    }
};
/*
    target + a + b = 0
    target + a = -b
    b = -(target + a)
*/
