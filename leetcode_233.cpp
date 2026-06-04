#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    int dp[11][2][11];
    int solve(string &s,int idx,bool tight,int cnt)
    {
        if(idx == s.size())
        return cnt;

        if(dp[idx][tight][cnt] != -1)
        return dp[idx][tight][cnt];

        int lb = 0;
        int ub = (tight == true) ? s[idx]-'0' : 9;

        int res = 0;

        for(int dig = lb; dig <= ub; dig++)
        {
            res += solve(s,idx+1,(tight && dig == ub),cnt+(dig == 1));
        }

        return dp[idx][tight][cnt] =  res;
    }

    int countDigitOne(int n) {
        string s = to_string(n);

        memset(dp,-1,sizeof(dp));
        return solve(s,0,true,0);
    }
};