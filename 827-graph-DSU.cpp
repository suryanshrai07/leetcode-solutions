/*
You are given an n x n binary matrix grid. You are allowed to change at most one 0 to be 1.

Return the size of the largest island in grid after applying this operation.

An island is a 4-directionally connected group of 1s.

 

Example 1:

Input: grid = [[1,0],[0,1]]
Output: 3
Explanation: Change one 0 to 1 and connect two 1s, then we get an island with area = 3.
Example 2:

Input: grid = [[1,1],[1,0]]
Output: 4
Explanation: Change the 0 to 1 and make the island bigger, only one island with area = 4.
Example 3:

Input: grid = [[1,1],[1,1]]
Output: 4
Explanation: Can't change any 0 to 1, only one island with area = 4.
 

Constraints:

n == grid.length
n == grid[i].length
1 <= n <= 500
grid[i][j] is either 0 or 1.
*/
#include <iostream>
#include <bits/stdc++.h>
using namespace std;

class disjoint{
    public:
    vector<int>size;
    vector<int>parent;

    disjoint(int n){
        size.resize(n+1,1);
        parent.resize(n+1);

        for(int i=0;i<=n;i++)
        parent[i] = i;
    }

    int findParent(int u)
    {
        if(u == parent[u])
        return u;

        return parent[u] = findParent(parent[u]);
    }

    void unionBySize(int u, int v)
    {
        int ulp_u = findParent(u);
        int ulp_v = findParent(v);

        if(ulp_u == ulp_v) return;

        if(size[ulp_v] > size[ulp_u] ){
            parent[ulp_u] = ulp_v;
            size[ulp_v]+=size[ulp_u];
        }
        else{
            parent[ulp_v] = ulp_u;
            size[ulp_u]+=size[ulp_v];
        }
    }
};

class Solution {
public:
    int n;
    int row[4] = {1,0,-1,0};
    int col[4] = {0,-1,0,1};

    bool valid(int i,int j)
    {
        return i>=0 && i<n && j>=0 && j<n;
    }

    int largestIsland(vector<vector<int>>& grid) {
        n = grid.size();
        disjoint ds(n*n);

        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(grid[i][j] == 0) continue;

                for(int k=0;k<4;k++)
                {
                    int new_i = i+row[k];
                    int new_j = j+col[k];

                    if(valid(new_i,new_j))
                    {
                        if(grid[new_i][new_j] == 1)
                        {
                            int row = i * n + j;
                            int adjRow = new_i * n + new_j;
                            ds.unionBySize(row,adjRow);
                        }
                    }
                }
            }
        }
        int ans = 0;

        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(grid[i][j] == 1) continue;
                unordered_set<int>st;
                

                for(int k=0;k<4;k++)
                {
                    int new_i = i+row[k];
                    int new_j = j+col[k];

                    if(valid(new_i,new_j))
                    {
                        if(grid[new_i][new_j] == 1)
                        {
                            int adjRow = new_i * n + new_j;

                            st.insert(ds.findParent(adjRow));
                        }
                    }
                }

                int totalSum = 0;
                for(auto it : st){
                    totalSum+=ds.size[it];
                }

                ans = max(ans,totalSum+1);
            }
        }

        return ans == 0 ? n*n : ans;
    }
};