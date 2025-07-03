/*
    Task:
        For every element in the array, find out the number of following
        elements (starting from the following one) that are in increasing
        order, until find the one that is greater than the current element.

    My first brute force approach. O(n³)
        - For every element, find either the position of the element
        (or the element itself) that is grater. -> O(n²)
        - For that range, find the number of numbers in ascending order
        starting from position i+1. -> O(n)

    Solution. O(n)
        For every position of the array, from right to left,
        check with an auxiliary stack that keeps
        the following elements in descending order,
        how many elements we can see from the
        current position. Do this while updating the stack.

    Algorithm:

    - Start an empty stack.
    - Process every element from the end to the begining.
        - Get the value of the current position.
        - Start a counter to check how many elements are less
          than the current position.
          While the stack is not empty and
          the top is less than the current element:
            - Pop the top from the stack.
            - Increment the counter by 1.
        - Set the ans for this position as the sum of
          the counter + if the stack is not empty.
        - Push the current element into the stack.
*/

class Solution {
public:
    vector<int> canSeePersonsCount(vector<int>& heights) {
        stack<int> sortedElements;
        int n = heights.size();
        vector<int> ans(n);

        for(int i = n-1; i >= 0; i--)
        {
            int shorterElements = 0;
            int currentValue = heights[i];
            while(!sortedElements.empty() && sortedElements.top() < currentValue)
            {
                shorterElements++;
                sortedElements.pop();
            }
            ans[i] = shorterElements;
            ans[i] += !sortedElements.empty();
            sortedElements.push(currentValue);
        }
        return ans;
    }
};

/*
  [10 6 8 5 11 9]
    |
  [3][1][2][1][1][0]
                                  elements taken out + stack is not empty
    stack: [9]                                 -> 0 + 0
    stack: [11]        {9}                     -> 1 + 0
    stack: [11, 5]           1                 -> 0 + 1
    stack: [11, 8]     {5}   1                 -> 1 + 1
    stack: [11, 8, 6]        1                 -> 0 + 1
    stack: [11]        {6,8} 1                 -> 2 + 1
*/
