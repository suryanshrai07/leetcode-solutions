/*

930. Binary Subarrays With Sum
Solved
Medium
Topics
premium lock icon
Companies
Given a binary array nums and an integer goal, return the number of non-empty subarrays with a sum goal.

A subarray is a contiguous part of the array.

 

Example 1:

Input: nums = [1,0,1,0,1], goal = 2
Output: 4
Explanation: The 4 subarrays are bolded and underlined below:
[1,0,1,0,1]
[1,0,1,0,1]
[1,0,1,0,1]
[1,0,1,0,1]
Example 2:

Input: nums = [0,0,0,0,0], goal = 0
Output: 15
 

Constraints:

1 <= nums.length <= 3 * 104
nums[i] is either 0 or 1.
0 <= goal <= nums.length
 

*/
#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    int subArrays(vector<int>& nums, int goal)
    {   
        if(goal < 0)
        return 0;

        int start = 0, end =0, count = 0;
        int n = nums.size();      
        int sum = 0;

        while(end < n)
        {
            sum += nums[end];

            while(sum > goal)
            {
                sum -= nums[start];
                start++;
            }

            count += (end-start+1);
            end++;
        }

        return count;
    }
    int numSubarraysWithSum(vector<int>& nums, int goal) {
        
        return subArrays(nums,goal) - subArrays(nums,goal-1);
    }
};