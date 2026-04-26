/*
410. Split Array Largest Sum
Solved
Hard
Topics
premium lock icon
Companies
Given an integer array nums and an integer k, split nums into k non-empty subarrays such that the largest sum of any subarray is minimized.

Return the minimized largest sum of the split.

A subarray is a contiguous part of the array.

 

Example 1:

Input: nums = [7,2,5,10,8], k = 2
Output: 18
Explanation: There are four ways to split nums into two subarrays.
The best way is to split it into [7,2,5] and [10,8], where the largest sum among the two subarrays is only 18.
Example 2:

Input: nums = [1,2,3,4,5], k = 2
Output: 9
Explanation: There are four ways to split nums into two subarrays.
The best way is to split it into [1,2,3] and [4,5], where the largest sum among the two subarrays is only 9.
 

Constraints:

1 <= nums.length <= 1000
0 <= nums[i] <= 106
1 <= k <= min(50, nums.length)
*/

#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    int helper(vector<int>& nums,int totalSum)
    {
        int k = 1;
        int sum = 0;
        for(int num : nums)
        {
            sum += num;

            if(sum > totalSum)
            {
                sum = num;
                k++;
            }
        }

        return k;
    }
    int splitArray(vector<int>& nums, int k) {
        int start = *max_element(nums.begin(),nums.end());
        int end = 0;

        for(auto num : nums)
            end += num;

        int ans = 0;

        while(start <= end)
        {
            int mid = start+(end-start)/2;

            if(helper(nums,mid) <= k)
            {
                ans = mid;
                end = mid-1;
            }
            else 
            {
                start = mid+1;
            }
            
        }

        return ans;
    }
};