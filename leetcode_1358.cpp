/*
1358. Number of Substrings Containing All Three Characters
Solved
Medium
Topics
premium lock icon
Companies
Hint
Given a string s consisting only of characters a, b and c.

Return the number of substrings containing at least one occurrence of all these characters a, b and c.

 

Example 1:

Input: s = "abcabc"
Output: 10
Explanation: The substrings containing at least one occurrence of the characters a, b and c are "abc", "abca", "abcab", "abcabc", "bca", "bcab", "bcabc", "cab", "cabc" and "abc" (again). 
Example 2:

Input: s = "aaacb"
Output: 3
Explanation: The substrings containing at least one occurrence of the characters a, b and c are "aaacb", "aacb" and "acb". 
Example 3:

Input: s = "abc"
Output: 1
 

Constraints:

3 <= s.length <= 5 x 10^4
s only consists of a, b or c characters.
*/

#include <bits/stdc++.h>
using namespace std;


class Solution {
public:
    int substrings(string s)
    {
        vector<int>count(3,0);
        int total = 0;
        int start = 0, end = 0;
        int n = s.length();
        int substrs = 0;

        while(end < n)
        {
            if(count[s[end] - 'a'] == 0)
            total++;

            count[s[end] - 'a']++;

            while(total == 3)
            {
                count[s[start] - 'a']--;

                if(count[s[start] - 'a'] == 0)
                total--;

                start++;
            }

            substrs += (end-start+1);
            end++;
        }

        return substrs;
    }

    int numberOfSubstrings(string s) {
        int n = s.length();

        long long totalSubstring = (long long)n*(n+1) / 2;

        return totalSubstring - substrings(s);
    }
};