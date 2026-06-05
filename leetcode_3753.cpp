/*
3753. Total Waviness of Numbers in Range II
Solved
Hard
Topics
premium lock icon
Companies
Hint
You are given two integers num1 and num2 representing an inclusive range [num1, num2].

The waviness of a number is defined as the total count of its peaks and valleys:

A digit is a peak if it is strictly greater than both of its immediate neighbors.
A digit is a valley if it is strictly less than both of its immediate neighbors.
The first and last digits of a number cannot be peaks or valleys.
Any number with fewer than 3 digits has a waviness of 0.
Return the total sum of waviness for all numbers in the range [num1, num2].
 

Example 1:

Input: num1 = 120, num2 = 130

Output: 3

Explanation:

In the range [120, 130]:

120: middle digit 2 is a peak, waviness = 1.
121: middle digit 2 is a peak, waviness = 1.
130: middle digit 3 is a peak, waviness = 1.
All other numbers in the range have a waviness of 0.
Thus, total waviness is 1 + 1 + 1 = 3.

Example 2:

Input: num1 = 198, num2 = 202

Output: 3

Explanation:

In the range [198, 202]:

198: middle digit 9 is a peak, waviness = 1.
201: middle digit 0 is a valley, waviness = 1.
202: middle digit 0 is a valley, waviness = 1.
All other numbers in the range have a waviness of 0.
Thus, total waviness is 1 + 1 + 1 = 3.

Example 3:

Input: num1 = 4848, num2 = 4848

Output: 2

Explanation:

Number 4848: the second digit 8 is a peak, and the third digit 4 is a valley, giving a waviness of 2.

 

Constraints:

1 <= num1 <= num2 <= 1015​​​​​​​

*/

#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    long long dp[17][2][11][11][2][17];
    long long solve(string& s, int idx, bool tight, int lastDigit,
              int secondLastDigit,bool lz,int count) {
        
        if (idx == s.size())
            return count;

        if( dp[idx][tight][lastDigit][secondLastDigit][lz][count] != -1)
        return dp[idx][tight][lastDigit][secondLastDigit][lz][count];

        long long res = 0;

        long long lb = 0;
        long long ub = (tight == true) ? (s[idx] - '0') : 9;

        
        for (long long dig = lb; dig <= ub; dig++) {

            int add = 0;
            if(lastDigit != 10 && secondLastDigit != 10 && !lz)
            {
                if(lastDigit > dig && lastDigit > secondLastDigit)
                add++;

                if(lastDigit < dig && lastDigit < secondLastDigit)
                add++;
            }

            if(lz && dig == 0)
            {
                res += solve(s,idx+1,(tight && dig == ub),lastDigit,secondLastDigit,true,count+add);
            }
            else
            res += solve(s,idx+1,(tight && dig == ub),dig,lastDigit,false,count+add);         
            
        }

        return dp[idx][tight][lastDigit][secondLastDigit][lz][count] = res;
    }

    long long totalWaviness(long long num1, long long num2) {

        string s1 = to_string(num2);
        string s2 = to_string(num1-1);

        memset(dp,-1,sizeof(dp));

        long long ans1 = solve(s1,0,true,10,10,true,0);
        memset(dp,-1,sizeof(dp));
        long long ans2 = solve(s2,0,true,10,10,true,0);

        return ans1 - ans2;

    }
};