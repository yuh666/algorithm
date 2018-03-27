package graph.component;

import graph.Graph;

/**
 * @author yuh 
 * 查看一个图的联通分量
 */
public class Components {

    /**
     * 节点是否被便利过
     */
    private boolean[] visited;

    /**
     * 节点属于哪个分量
     */
    private int[] id;

    /**
     * 联通分量id
     */
    private int ccount;

    private Graph graph;
    
    public Components(Graph graph){
        this.graph = graph;
        visited = new boolean[graph.V()];
        id = new int[graph.V()];
        ccount = 0;
        for (int i = 0; i < graph.V(); i++) {
            visited[i] = false;
            id[i] = -1;
        }

        for (int i = 0; i < graph.V(); i++) {
            if(!visited[i]){
                dfs(i);
                ccount ++;
            }
        }

    }

    /**
     * 遍历可达的路径
     * @param i
     */
    private void dfs(int i) {
        //取得和这个节点关联的集合
        Iterable<Integer> adj = graph.adj(i);
        visited[i] = true;
        id[i] = ccount;
        for (Integer node : adj) {
            if(!visited[node]){
                dfs(node);
            }
        }
    }

    /**
     * 几个分量
     * @return
     */
    public int getCcount(){
        return ccount;
    }

    public boolean isConnected(int v,int w){
        return id[v] == id[w];
    }


}
