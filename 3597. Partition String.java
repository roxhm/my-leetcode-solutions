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
    public List<String> partitionString(String s) {
        Set<String> auxSet = new HashSet<>();
        List<String> uniqueSegments = new ArrayList<>();

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < s.length(); i++)
        {
            sb.append(s.charAt(i));
            String aux = sb.toString();
            if(!auxSet.contains(aux))
            {
                auxSet.add(aux);
                uniqueSegments.add(aux);
                sb.setLength(0);
            }
        }

        return uniqueSegments;
    }
}

/*
    s = "abbccccd"
               "d"
     ["a", "b", "bc", "c", "cc", "d"]
*/
