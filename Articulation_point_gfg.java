/*
You are given an undirected graph with V vertices and E edges. The graph is represented as a 2D array edges[][], where each element edges[i] = [u, v] indicates an undirected edge between vertices u and v.
Your task is to return all the articulation points (or cut vertices) in the graph.
An articulation point is a vertex whose removal, along with all its connected edges, increases the number of connected components in the graph.

Note: The graph may be disconnected, i.e., it may consist of more than one connected component.
If no such point exists, return {-1}.

Examples :

Input: V = 5, edges[][] = [[0, 1], [1, 4], [4, 3], [4, 2], [2, 3]]

Output: [1, 4]
Explanation: Removing the vertex 1 or 4 will disconnects the graph as-
   
Input: V = 4, edges[][] = [[0, 1], [0, 2]]
Output: [0]
Explanation: Removing the vertex 0 will increase the number of disconnected components to 3.  
Constraints:
1 ≤ V, E ≤ 104


*/
import java.util.*;

public class Articulation_point_gfg {
   
    static int timer = 0;
    
    static void dfs(int node,ArrayList<ArrayList<Integer>>adj,int []low,int []tin,int []mark,int []visited,int parent)
    {
        visited[node] = 1;
        tin[node] = low[node] = timer;
        timer++;
        
        int child = 0;
        
        for(int neigh : adj.get(node))
        {
            if(neigh == parent) continue;
            
            if(visited[neigh] == 0)
            {
                dfs(neigh,adj,low,tin,mark,visited,node);
                
                low[node] = Math.min(low[node],low[neigh]);
                if(low[neigh] >= tin[node] && parent != -1)
                {
                    mark[node] = 1;
                }
                 child++;
            }
            else {
                low[node] = Math.min(low[node],tin[neigh]);
            }
            
           
        }
        
        if(child > 1 && parent == -1){
            mark[node] = 1;
        }
    }
    static ArrayList<Integer> articulationPoints(int V, int[][] edges) {
        // code here
    int []low = new int[V];
    int []tin = new int[V];
    int []mark = new int[V];
    int []visited = new int[V];
    ArrayList<ArrayList<Integer>>adj = new ArrayList<>();
    
    for(int i=0 ;i<V;i++){
        adj.add(new ArrayList<>());
    }
    
    for(int [] row : edges){
        adj.get(row[0]).add(row[1]);
        adj.get(row[1]).add(row[0]);
    }
    
    for(int i=0;i<V;i++)
    {
    if(visited[i] == 0)
    dfs(i,adj,low,tin,mark,visited,-1);
    }
    
    ArrayList<Integer>ans = new ArrayList<>();
    for(int i=0;i<V;i++)
    {
        if(mark[i] == 1)
        ans.add(i);
    }
    
    if(ans.size() == 0) {
        ans.add(-1);
        return ans;
    }
    return ans;
}
}