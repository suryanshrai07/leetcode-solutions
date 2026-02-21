/*
Given an adjacency list, adj of Directed Graph, Find the number of strongly connected components in the graph

Examples :

Input: adj[][] = [[2, 3], [0], [1], [4], []]


Output: 3
Explanation: We can clearly see that there are 3 Strongly Connected Components in the Graph.
 
Input: adj[][] = [[1], [2], [0]]

Output: 1
Explanation: All of the nodes are connected to each other. So, there's only one SCC.
Input: adj[][] = [[1], []]
Output: 2
Constraints:
2<=adj.size()<=106
0<=edges<=adj.size()-1


*/


import java.util.*;

public class strongly_connected_component_gfg {
    // Function to find number of strongly connected components in the graph.
    Stack<Integer>st = new Stack<>();
    int [] visited;
    
    void topological(int node, ArrayList<ArrayList<Integer>> adj)
    {
        visited[node] = 1;
        
        for(Integer adjNode : adj.get(node))
        {
            if(visited[adjNode] == 0)
            {
                topological(adjNode,adj);
            }
        }
        
        st.push(node);
    }
    
    void dfs(int node,ArrayList<ArrayList<Integer>>adj2 )
    {
        visited[node] = 1;
        
        for(Integer adjNode : adj2.get(node))
        {
            if(visited[adjNode] == 0)
            dfs(adjNode,adj2);
        }
    }
    
    public int kosaraju(ArrayList<ArrayList<Integer>> adj) {
        // code here
        int n = adj.size();
        visited = new int [n];
        
        for(int i=0;i<n;i++)
        if(visited[i] == 0)
        topological(i,adj);
        
        ArrayList<ArrayList<Integer>>adj2 = new ArrayList<>();
        
        for(int i=0;i<n;i++)
        adj2.add(new ArrayList<Integer>());
        
        for(int i=0;i<n;i++)
        {
            for(Integer j : adj.get(i))
            {
                adj2.get(j).add(i);
            }
        }
        
        visited = new int[n];
        int ans =0;
        
        while(!st.empty())
        {
            int node = st.pop();
            
            if(visited[node] == 0)
            {
                dfs(node,adj2);
                ans++;
            }
        }
        
        return ans;
    }
}