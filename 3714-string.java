/*
You are given a string s consisting only of the characters 'a', 'b', and 'c'.

A substring of s is called balanced if all distinct characters in the substring appear the same number of times.

Return the length of the longest balanced substring of s.

 

Example 1:

Input: s = "abbac"

Output: 4

Explanation:

The longest balanced substring is "abba" because both distinct characters 'a' and 'b' each appear exactly 2 times.

Example 2:

Input: s = "aabcc"

Output: 3

Explanation:

The longest balanced substring is "abc" because all distinct characters 'a', 'b' and 'c' each appear exactly 1 time.

Example 3:

Input: s = "aba"

Output: 2

Explanation:

One of the longest balanced substrings is "ab" because both distinct characters 'a' and 'b' each appear exactly 1 time. Another longest balanced substring is "ba".

 

Constraints:

1 <= s.length <= 105
s contains only the characters 'a', 'b', and 'c'
*/

import java.util.*;
class Solution {
    public int helper(String s,char ch1,char ch2)
    {
        int count1 = 0;
        int count2 = 0;
        int n = s.length();
        int maxL = 0;

        HashMap<Integer,Integer>mp = new HashMap<>();
    
        for(int i=0;i<n;i++)
        {
            if(s.charAt(i) != ch1 && s.charAt(i) != ch2)
            {
                mp = new HashMap<>();
                count1 = 0;
                count2 = 0;
                continue;
            }
            if(s.charAt(i) == ch1) count1++;
            if(s.charAt(i) == ch2) count2++;

            if(count1 == count2)
            {
                maxL = Math.max(maxL,count1+count2);
            }

            int diff = count1-count2;

            if(mp.containsKey(diff)){
                maxL = Math.max(maxL,i-mp.get(diff));
            }
            else{
                mp.put(diff,i);
            }
        }

        return maxL;

    }

    public int longestBalanced(String s) {
        int n = s.length();
        int maxL = 0;
        //case 1;
        // aaaa

        int count = 1;

        for(int i=1;i<n;i++)
        {
            if(s.charAt(i) == s.charAt(i-1)){
                count++;
            }
            else{
                maxL = Math.max(maxL,count);
                count = 1;
            }
        }
        maxL = Math.max(maxL,count);

        // case 2
        maxL = Math.max(maxL,helper(s,'a','b'));
        maxL = Math.max(maxL,helper(s,'a','c'));
        maxL = Math.max(maxL,helper(s,'b','c'));

        // case 3
        int countA = 0;
        int countB = 0;
        int countC = 0;

        HashMap<String,Integer>mp = new HashMap<>();

        for(int i=0;i<n;i++){
            if(s.charAt(i) == 'a') countA++;
            if(s.charAt(i) == 'b') countB++;
            if(s.charAt(i) == 'c') countC++;

            if(countA == countB && countA == countC)
            maxL = Math.max(maxL,countA+countB+countC);

            int diffAB = countA - countB;
            int diffAC = countA - countC;
            
            String key= diffAB+"_"+diffAC;

            if(mp.containsKey(key)){
                maxL = Math.max(maxL,i-mp.get(key));
            }
            else{
                mp.put(key,i);
            }
        }

        return maxL;
    }
}