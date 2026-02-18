/*
There are n servers numbered from 0 to n - 1 connected by undirected server-to-server connections forming a network where connections[i] = [ai, bi] represents a connection between servers ai and bi. Any server can reach other servers directly or indirectly through the network.

A critical connection is a connection that, if removed, will make some servers unable to reach some other server.

Return all critical connections in the network in any order.

 

Example 1:


Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
Output: [[1,3]]
Explanation: [[3,1]] is also accepted.
Example 2:

Input: n = 2, connections = [[0,1]]
Output: [[0,1]]
 

Constraints:

2 <= n <= 105
n - 1 <= connections.length <= 105
0 <= ai, bi <= n - 1
ai != bi
There are no repeated connections.

*/
import java.util.*;
class Solution {
    List<List<Integer>>bridges = new ArrayList<>();
    ArrayList<ArrayList<Integer>>adj = new ArrayList<>();
    int timer = 1;

    void dfs(int node,int parent,int visited[],int low[] , int tin[] )
    {
        visited[node] = 1;
        low[node] = tin[node] = timer;
        timer++;

        for(Integer it : adj.get(node))
        {
            if(parent == it) continue;
            if(visited[it] == 0)
            {
                dfs(it,node,visited,low,tin);

                low[node] = Math.min(low[node],low[it]);

                if(low[it] > tin[node]){
                    bridges.add(Arrays.asList(node,it));
                }
            }

            else{
                low[node] = Math.min(low[node],low[it]);
            }
        }
    }
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {

        for(int i=0;i<n;i++)
        {
            adj.add(new ArrayList<>());
        }

        for(List<Integer> it : connections)
        {
            int u = it.get(0);
            int v = it.get(1);

            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        int []visited = new int [n];
        int []tin = new int [n];
        int []low = new int [n];

        dfs(0,-1,visited,low,tin);

        return bridges;
    }
}