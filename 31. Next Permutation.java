/*
    Task: Given an array of integers, find the next lexicographical permutation.

     Key observations:
        - While scanning from the end to the beginning, if a number is less than the one before it,
          we don't swap them, because that would result in a lexicographically smaller permutation —
          meaning it would have already appeared earlier in the permutation order.

        - If the entire array is non-increasing (i.e., descending), there is no lexicographically greater
        permutation. So, the next one is simply the lexicographically smallest (i.e., the array reversed).

    Solution:
     - Find the longest non-increasing suffix.
     - Identify the element just before the suffix — this is the pivot.
     - Swap the pivot with the smallest element in the suffix that is greater than the pivot.
     - Finally, sort the suffix to get the next lexicographical permutation.


        Buscamos el sufijo en orden no creciente más grande, ya que esos elementos no
        pueden ser cambiados, porque sabemos que:
            (Del final al inicio)
            - Si un número es más chico que el anterior, no conviene intercambiarlos
              porque ese número sería lexicográficamente menor y por lo tanto,
              sería una permutación que hubiera aparecido ya antes.

        Cuando encontramos el punto en el que se rompe el orden no creciente, es decir,
        encontramos que un número es más grande que el anterior,
        entonces sabemos que el anterior es el último fijo y que el orden del resto de los
        elementos es el último posible, porque es el lexicográficamente más grande alcazable.
        Entonces, sabemos que tenemos que alterar al último fijo y cambiarlo por el siguiente
        número más grande posible, y volver a empezar el resto de los caracteres
        ordenados con el mínimo lexicográfico posible.
*/
class Solution {
    public void nextPermutation(int[] nums) {
        int p = nums.length - 1;
        while(p > 0 && nums[p-1] >= nums[p])
            p--;

        if(p != 0)
        {
            int lastFixed = nums[p-1];
            int posNextGreater = p;
            int nextGreater = nums[p];
            for(int i = p; i < nums.length; i++)
            {
                if(lastFixed < nums[i] && nums[i] < nextGreater)
                {
                    nextGreater = nums[i];
                    posNextGreater = i;
                }
            }
            int tmp = nums[p-1];
            nums[p-1] = nums[posNextGreater];
            nums[posNextGreater] = tmp;
        }
        Arrays.sort(nums, p, nums.length);
    }
}

/*
    n = 3
    [2,3,1] -> [3,2,1]

    [1, 2, 3] -> [1, 3, 2]
    [1, 1, 5] -> [1, 5, 1]

    [3, 2, 6, 1, 4] -> [3, 2, 6, 4, 1]

    n = 6
    [4, 2, 3, 9, 8, 7]
    [4, 2, 3, 9, 7, 8]
    [4, 2, 3, 7, 9, 8]
    [4, 2, 7, 3, 9, 8]
    [4, 2, 7, 3, 8, 9]
    [4, 2, 7, 3, 8, 9]

    [2, 3, 1]
    [3, 2, 1]
    [1, 2, 3]

    [1, 2, 3, 5, 7, 8, 6, 4]
    [1, 2, 3, 5, 8, 4, 6, 7]

*/
