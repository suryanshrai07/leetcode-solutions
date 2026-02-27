import java.util.*;

class Pair{
    int neigh;
    int wt;
    
    Pair(int v,int w){
        neigh = v;
        wt = w;
    }
}

public class Longest_Path_In_DAG {
    public static void topoSort(int node,ArrayList<ArrayList<Pair>>adj,Stack<Integer>st,int []visited)
    {
        visited[node] = 1;
        
        for(Pair adjNode : adj.get(node))
        {
            int v = adjNode.neigh;
            
            if(visited[v] == 0){
                topoSort(v,adj,st,visited);
            }
        }
        
        st.push(node);
    }
    public static int[] maximumDistance(int v, int e, int src, int[][] edges) {
        // code here
        ArrayList<ArrayList<Pair>>adj = new ArrayList<>();
        
        for(int i=0;i<v;i++)
        adj.add(new ArrayList<Pair>());
        
        for(int i=0;i<e;i++)
        {
            int u = edges[i][0];
            int neigh = edges[i][1];
            int w = edges[i][2];
            
            adj.get(u).add(new Pair(neigh,w));
        }
        
        Stack<Integer>st = new Stack<>();
        
        int []visited = new int[v];
        for(int i=0;i<v;i++)
        {
            if(visited[i] == 0)
            {
                topoSort(i,adj,st,visited);
            }
        }
        
        int []dist = new int[v];
        
        for(int i=0;i<v;i++)
        dist[i] = Integer.MIN_VALUE;
        
        dist[src] = 0;
        
        while(!st.empty())
        {
            int node = st.pop();
            
            for(Pair it : adj.get(node))
            {
                int neigh = it.neigh;
                int w = it.wt;
                
                if(dist[node] == Integer.MIN_VALUE) continue;
                
                if(dist[neigh] < dist[node] + w ){
                    dist[neigh] = dist[node]+w;
                }
            
            }
        }
        
        return dist;
    }
}
