/*
3558. Number of Ways to Assign Edge Weights I
Solved
Medium
Topics
premium lock icon
Companies
Hint
There is an undirected tree with n nodes labeled from 1 to n, rooted at node 1. The tree is represented by a 2D integer array edges of length n - 1, where edges[i] = [ui, vi] indicates that there is an edge between nodes ui and vi.

Initially, all edges have a weight of 0. You must assign each edge a weight of either 1 or 2.

The cost of a path between any two nodes u and v is the total weight of all edges in the path connecting them.

Select any one node x at the maximum depth. Return the number of ways to assign edge weights in the path from node 1 to x such that its total cost is odd.

Since the answer may be large, return it modulo 109 + 7.

Note: Ignore all edges not in the path from node 1 to x.

 

Example 1:



Input: edges = [[1,2]]

Output: 1

Explanation:

The path from Node 1 to Node 2 consists of one edge (1 → 2).
Assigning weight 1 makes the cost odd, while 2 makes it even. Thus, the number of valid assignments is 1.
Example 2:



Input: edges = [[1,2],[1,3],[3,4],[3,5]]

Output: 2

Explanation:

The maximum depth is 2, with nodes 4 and 5 at the same depth. Either node can be selected for processing.
For example, the path from Node 1 to Node 4 consists of two edges (1 → 3 and 3 → 4).
Assigning weights (1,2) or (2,1) results in an odd cost. Thus, the number of valid assignments is 2.
 

Constraints:

2 <= n <= 105
edges.length == n - 1
edges[i] == [ui, vi]
1 <= ui, vi <= n
edges represents a valid tree.
*/

class Solution {
    int[] visited;
    int MOD = 1000000007;

    int dfs(int node, List<List<Integer>> adj) {
        visited[node] = 1;

        int edges = 0;

        for (int adjNode : adj.get(node)) {
            if (visited[adjNode] == 0) {
                edges = Math.max(edges, dfs(adjNode, adj));
            }
        }

        return 1 + edges;
    }

    int pow(int x,int n)
    {
        int m = n;
        long ans = 1;

        while(n > 0)
        {
            if(n % 2 == 1)
            {
                ans = (ans * x) % MOD;
                n = n-1;
            }
            else
            {
                n/=2;
                x = (int)((1L *x*x) % MOD);
            }
        }

        return (int)ans;
    }

    public int assignEdgeWeights(int[][] edges) {
        int n = edges.length + 1;
        List<List<Integer>> adj = new ArrayList<>();

        for (int i = 0; i <= n; i++)
            adj.add(new ArrayList<>());

        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        visited = new int[n + 1];

        int k = dfs(1, adj) - 1;

        System.out.println(k);
       

        return pow(2,k-1);

    }
}