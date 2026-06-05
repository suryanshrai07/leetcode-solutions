/*
451. Sort Characters By Frequency
Solved
Medium
Topics
premium lock icon
Companies
Given a string s, sort it in decreasing order based on the frequency of the characters. The frequency of a character is the number of times it appears in the string.

Return the sorted string. If there are multiple answers, return any of them.

 

Example 1:

Input: s = "tree"
Output: "eert"
Explanation: 'e' appears twice while 'r' and 't' both appear once.
So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
Example 2:

Input: s = "cccaaa"
Output: "aaaccc"
Explanation: Both 'c' and 'a' appear three times, so both "cccaaa" and "aaaccc" are valid answers.
Note that "cacaca" is incorrect, as the same characters must be together.
Example 3:

Input: s = "Aabb"
Output: "bbAa"
Explanation: "bbaA" is also a valid answer, but "Aabb" is incorrect.
Note that 'A' and 'a' are treated as two different characters.
 

Constraints:

1 <= s.length <= 5 * 105
s consists of uppercase and lowercase English letters and digits.

*/

class Pair
{
    int first;
    int second;

    Pair(int first,int second)
    {
        this.first = first;
        this.second = second;
    }
}

class Solution {
    public String frequencySort(String s) {
        int[] alpha = new int[256];

        for(char ch : s.toCharArray())
        {
            alpha[ch]++;
        }

        PriorityQueue<Pair>pq = new PriorityQueue<>((a,b) -> Integer.compare(b.second,a.second));

        for(int i=0;i<256;i++)
        {
            if(alpha[i] == 0)
            continue;

            pq.offer(new Pair(i,alpha[i]));
        }

        StringBuilder ans = new StringBuilder();

        while(!pq.isEmpty())
        {
            char ch = (char)pq.peek().first;
            int freq = pq.peek().second;

            pq.poll();

            for(int i=0;i<freq;i++)
            ans.append(ch);
        }

        return ans.toString();

    }
}