/*
You are given a binary string s. You are allowed to perform two types of operations on the string in any sequence:

Type-1: Remove the character at the start of the string s and append it to the end of the string.
Type-2: Pick any character in s and flip its value, i.e., if its value is '0' it becomes '1' and vice-versa.
Return the minimum number of type-2 operations you need to perform such that s becomes alternating.

The string is called alternating if no two adjacent characters are equal.

For example, the strings "010" and "1010" are alternating, while the string "0100" is not.
 

Example 1:

Input: s = "111000"
Output: 2
Explanation: Use the first operation two times to make s = "100011".
Then, use the second operation on the third and sixth elements to make s = "101010".
Example 2:

Input: s = "010"
Output: 0
Explanation: The string is already alternating.
Example 3:

Input: s = "1110"
Output: 1
Explanation: Use the second operation on the second element to make s = "1010".
 

Constraints:

1 <= s.length <= 105
s[i] is either '0' or '1'.

*/

import java.util.*;

class Solution {
    public int minFlips(String s) {
        int n = s.length();
        s = s + s;
        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();

        for (int i = 0; i < 2 * n; i++) {
            s1.append(i % 2 == 1 ? '0' : '1');
            s2.append(i % 2 == 1 ? '1' : '0');
        }

        int result = Integer.MAX_VALUE;
        int i = 0, j = 0;

        int flip1 = 0, flip2 = 0;

        while (j < 2 * n) {
            if (s.charAt(j) != s1.charAt(j)) {
                flip1++;
            }

            if (s.charAt(j) != s2.charAt(j)) {
                flip2++;
            }

            if (j - i + 1 > n) {
                if (s.charAt(i) != s1.charAt(i)) {
                    flip1--;
                }

                if (s.charAt(i) != s2.charAt(i)) {
                    flip2--;
                }

                i++;
            }

            if (j - i + 1 == n) {
                result = Math.min(result, Math.min(flip1, flip2));
            }

            j++;
        }

        return result;

    }
}