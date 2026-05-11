/*
Count Occurences of Anagrams
Difficulty: MediumAccuracy: 48.09%Submissions: 107K+Points: 4
Given a word pat and a text txt. Return the count of the occurrences of anagrams of the word in the text.

Example 1:

Input: txt = "forxxorfxdofr", pat = "for"
Output: 3
Explanation: for, orf and ofr appears in the txt, hence answer is 3.
Example 2:

Input: txt = "aabaabaa", pat = "aaba"
Output: 4
Explanation: aaba is present 4 times in txt.
Constraints:
1 <= |pat| <= |txt| <= 105
Both strings contain lowercase English letters.
*/

// User function Template for Java

class Solution {

    int search(String pat, String txt) {
        // code here
        int ans = 0;
        int []alpha = new int[26];
        
        int total = 0;
        for(char ch : pat.toCharArray())
        {
            alpha[ch-'a']++;
            total++;
        }
        
        int start = 0, end = 0;
        int n = txt.length();
        
        while(end < n)
        {   
            char ch = txt.charAt(end);
            alpha[ch-'a']--;
            
            if(alpha[ch-'a'] >= 0)
            total--;
            
            while(alpha[ch-'a'] < 0)
            {
                alpha[txt.charAt(start)-'a']++;
                
                if(alpha[txt.charAt(start)-'a'] > 0)
                total++;
                
                start++;
            }
            
            if(total == 0)
            ans++;
            
            end++;
        }
        
        return ans;
    }
}