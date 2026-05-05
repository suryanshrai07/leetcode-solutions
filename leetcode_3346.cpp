/*
3346. Maximum Frequency of an Element After Performing Operations I
Solved
Medium
Topics
premium lock icon
Companies
Hint
You are given an integer array nums and two integers k and numOperations.

You must perform an operation numOperations times on nums, where in each operation you:

Select an index i that was not selected in any previous operations.
Add an integer in the range [-k, k] to nums[i].
Return the maximum possible frequency of any element in nums after performing the operations.

 

Example 1:

Input: nums = [1,4,5], k = 1, numOperations = 2

Output: 2

Explanation:

We can achieve a maximum frequency of two by:

Adding 0 to nums[1]. nums becomes [1, 4, 5].
Adding -1 to nums[2]. nums becomes [1, 4, 4].
Example 2:

Input: nums = [5,11,20,20], k = 5, numOperations = 1

Output: 2

Explanation:

We can achieve a maximum frequency of two by:

Adding 0 to nums[1].
 

Constraints:

1 <= nums.length <= 105
1 <= nums[i] <= 105
0 <= k <= 105
0 <= numOperations <= nums.length

*/

#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    int maxFrequency(vector<int>& nums, int k, int numOperations) {
        int maxElement = *max_element(begin(nums),end(nums))+k;
        int n = nums.size();

        vector<int>diff(maxElement+2,0);
        unordered_map<int,int>freq;

        for(int i=0;i<n;i++)
        {
            freq[nums[i]]++;
            int L = max(nums[i]-k,0);
            int R = min(nums[i]+k,maxElement);

            diff[L]++;
            diff[R+1]--;
        }

        int ans = 0;
        for(int target = 0;target<=maxElement;target++)
        {
            diff[target] += (target>0?diff[target-1]:0);

            int targetFreq = freq[target];
            int needConversion = diff[target]-targetFreq;

            int maxPossibleOp = min(needConversion,numOperations);

            ans = max(ans,maxPossibleOp+targetFreq);
        }

        return ans;
    }
};