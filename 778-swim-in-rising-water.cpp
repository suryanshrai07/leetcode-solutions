/* 
You are given an n x n integer matrix grid where each value grid[i][j] represents the elevation at that point (i, j).

It starts raining, and water gradually rises over time. At time t, the water level is t, meaning any cell with elevation less than equal to t is submerged or reachable.

You can swim from a square to another 4-directionally adjacent square if and only if the elevation of both squares individually are at most t. You can swim infinite distances in zero time. Of course, you must stay within the boundaries of the grid during your swim.

Return the minimum time until you can reach the bottom right square (n - 1, n - 1) if you start at the top left square (0, 0).

 

Example 1:


Input: grid = [[0,2],[1,3]]
Output: 3
Explanation:
At time 0, you are in grid location (0, 0).
You cannot go anywhere else because 4-directionally adjacent neighbors have a higher elevation than t = 0.
You cannot reach point (1, 1) until time 3.
When the depth of water is 3, we can swim anywhere inside the grid.
Example 2:


Input: grid = [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
Output: 16
Explanation: The final route is shown.
We need to wait until time 16 so that (0, 0) and (4, 4) are connected.
 

Constraints:

n == grid.length
n == grid[i].length
1 <= n <= 50
0 <= grid[i][j] < n2
Each value grid[i][j] is unique.

*/
#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    int n;
    int row[4] = {1,0,-1,0};
    int col[4] = {0,1,0,-1};

    bool valid(int i,int j)
    {
        return i>=0 && i<n && j>=0 && j<n;
    }

    int swimInWater(vector<vector<int>>& grid) {
        n = grid.size();

        vector<vector<int>>visited(n,vector<int>(n,0));
        priority_queue< pair<int,pair<int,int>> ,vector< pair<int,pair<int,int>> >, greater< pair<int,pair<int,int>> > >pq;

        pq.push({grid[0][0],{0,0}});
        visited[0][0] = 1;

        while(!pq.empty())
        {
            int timer = pq.top().first;
            int i = pq.top().second.first;
            int j = pq.top().second.second;
            pq.pop();

            if(i== n-1 && j== n-1) return timer;

            for(int k=0;k<4;k++)
            {
                int new_i = i+row[k];
                int new_j = j+col[k];

                if(valid(new_i,new_j) && !visited[new_i][new_j])
                {
                    if(timer >= grid[new_i][new_j])
                    {
                        pq.push({timer,{new_i,new_j}});
                        visited[new_i][new_j] = 1;
                    }
                    else
                    {
                        pq.push({grid[new_i][new_j],{new_i,new_j}});
                        visited[new_i][new_j] = 1;
                    }
                }
            }

        }
        return 0;
    }
};