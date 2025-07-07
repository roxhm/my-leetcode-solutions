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
            and look for its compliment.
                a = b

            To look for a number, we can use a set.
            However, as we can have the same number twice in the triplet,
            we not only care about whether the number exists,
            but also about whether there are enough occurrences to use it.
            That's why we use a frequency map (or hash map) instead of a simple set.

        Complexity: (n²log(n))
*/
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        HashMap<Integer, Integer> freq = new HashMap<>();
        List<List<Integer>> ans = new ArrayList<>();
        Set<List<Integer>> uniqueTriplets = new HashSet<>();

        for(int num : nums)
            freq.put(num, freq.getOrDefault(num, 0) + 1);

        List<Integer> keys = new ArrayList<>();

        freq.forEach((k, v) -> keys.add(k));

        int nkey = keys.size();

        if(freq.containsKey(0) && freq.get(0) >= 3)
            ans.add(Arrays.asList(0,0,0));

        for(int i = 0; i < nkey - 1; i++)
        {
            int target = keys.get(i);
            for(int j = i + 1; j < nkey; j++)
            {
                int a = keys.get(j);
                int b = -(target + a);
                if(freq.containsKey(b))
                {
                    if((b == a || b == target) && freq.get(b) < 2)
                        continue;
                    List<Integer> triplet = Arrays.asList(target, a, b);
                    Collections.sort(triplet);
                    uniqueTriplets.add(triplet);
                }
            }
        }

        ans.addAll(uniqueTriplets);
        return ans;
    }
}

/*
    target + a + b = 0
    target + a = -b
    b = -(target + a)
*/

