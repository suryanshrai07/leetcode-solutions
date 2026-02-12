/*
You are given a n,m which means the row and column of the 2D matrix and an array of  size k denoting the number of operations. Matrix elements is 0 if there is water or 1 if there is land. Originally, the 2D matrix is all 0 which means there is no land in the matrix. The array has k operator(s) and each operator has two integer A[i][0], A[i][1] means that you can change the cell matrix[A[i][0]][A[i][1]] from sea to island. Return how many island are there in the matrix after each operation.You need to return an array of size k.
Note : An island means group of 1s such that they share a common side.



Example 1:

Input: n = 4
m = 5
k = 4
A = {{1,1},{0,1},{3,3},{3,4}}

Output: 1 1 2 2
Explanation:
0.  00000
    00000
    00000
    00000
1.  00000
    01000
    00000
    00000
2.  01000
    01000
    00000
    00000
3.  01000
    01000
    00000
    00010
4.  01000
    01000
    00000
    00011




Example 2:

Input: n = 4
m = 5
k = 4
A = {{0,0},{1,1},{2,2},{3,3}}

Output: 1 2 3 4
Explanation:
0.  00000
    00000
    00000
    00000
1.  10000
    00000
    00000
    00000
2.  10000
    01000
    00000
    00000
3.  10000
    01000
    00100
    00000
4.  10000
    01000
    00100
    00010


Your Task:
You don't need to read or print anything. Your task is to complete the function numOfIslands() which takes an integer n denoting no. of rows in the matrix, an integer m denoting the number of columns in the matrix and a 2D array of size k denoting  the number of operators.

Expected Time Complexity: O(m * n)
Expected Auxiliary Space: O(m * n)

Constraints:

1 <= n,m <= 100
1 <= k <= 1000


*/

// User function Template for C++
#include <iostream>
#include <bits/stdc++.h>
using namespace std;

class disjoint{
    public :
    
    vector<int>rank;
    vector<int>parent;
    
    disjoint(int n){
        rank.resize(n+1,0);
        parent.resize(n+1);
        
        for(int i=0;i<=n;i++)
        parent[i] = i;
    }
    
    int findParent(int u)
    {
        if(u == parent[u]) return u;
        
        return parent[u] = findParent(parent[u]);
    }
    
    void unionByRank(int u,int v)
    {
        int ul_u = findParent(u);
        int ul_v = findParent(v);
        
        if(ul_u == ul_v) return;
        
        if(rank[ul_u] > rank[ul_v])
        {
            parent[ul_v] = ul_u;
        }
        else if(rank[ul_v] > rank[ul_u])
        {
            parent[ul_u] = ul_v;
        }
        else{
            parent[ul_v] = ul_u;
            rank[ul_u]++;
        }
    }
};

class Solution {
  public:
    int r,c;
    int row[4] = {1,0,-1,0};
    int col[4] = {0,-1,0,1};
    
    bool check(int i,int j){
        return i>=0 && i<r && j>=0 && j<c;
    }
    
    vector<int> numOfIslands(int n, int m, vector<vector<int>> &operators) {
        // code here
        r = n, c = m;
        disjoint ds(n*m);
        
        int cnt = 0;
        vector<vector<int>>visited(n,vector<int>(m,0));
        vector<int>ans;
        
        for(int it = 0;it<operators.size();it++)
        {
            int u = operators[it][0];
            int v = operators[it][1];
            
            if(visited[u][v] == 1){
                ans.push_back(cnt);
                continue;
            }
            
            visited[u][v] = 1;
            cnt++;
            
            for(int k=0;k<4;k++)
            {
                int new_u = u+row[k];
                int new_v = v+col[k];
                
                if(check(new_u,new_v)){
                    if(visited[new_u][new_v] == 1){
                        int node = u * m + v;
                        int adjNode = new_u * m + new_v;
                        
                        if(ds.findParent(node) != ds.findParent(adjNode))
                        {
                            cnt--;
                            ds.unionByRank(node,adjNode);
                        }
                    }
                }
            }
            ans.push_back(cnt);
        }
        
        return ans;
        
        
    }
};
