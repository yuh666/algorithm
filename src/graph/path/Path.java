package graph.path;

import graph.Graph;
import sun.awt.image.ImageWatched;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author yuh 
 * 获取一个路径
 **/
public class Path {

    /**
     * 节点是否被便利过
     */
    private boolean[] visited;

    /**
     * 节点是从哪个节点来的
     */
    private int[] from;

    /**
     * 图引用
     */
    private Graph graph;

    /**
     * 原始节点
     */
    private int src;

    public Path(Graph graph,int src){
        this.src = src;
        this.graph = graph;
        visited = new boolean[graph.V()];
        from = new int[graph.V()];
        for (int i = 0; i < graph.V(); i++) {
            visited[i] = false;
            from[i] = -1;
        }
        dfs(src);
    }

    /**
     * 遍历可达的路径
     * @param i
     */
    private void dfs(int i) {
        //取得和这个节点关联的集合
        Iterable<Integer> adj = graph.adj(i);
        visited[i] = true;
        for (Integer node : adj) {
            if(!visited[node]){
                from[node] = i;
                dfs(node);
            }
        }
    }

    /**
     * 节点是否可达
     * @param v
     * @return
     */
   public boolean hasPath(int v){
        return visited[v];
   }

    /**
     * 获取一个节点的路径
     * @param v
     * @return
     */
   public ArrayList<Integer> path(int v){
       LinkedList<Integer> list = new LinkedList<>();
       while(from[v] != -1){
           list.addFirst(from[v]);
           v = from[v];
       }

       return new ArrayList<Integer>(list);
   }

    /**
     * 显示一个节点的路径
     * @param v
     */
   public void showPath(int v){
       List<Integer> path = path(v);
       for (int i = 0; i < path.size(); i++) {
           System.out.print(path.get(i)+"->");
       }
       System.out.print(v+"\n");
   }



}
