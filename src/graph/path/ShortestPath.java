package graph.path;

import graph.Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author yuh
 * 获取一个最短路径 广度优先遍历
 **/
public class ShortestPath {

    /**
     * 节点是否被便利过
     */
    private boolean[] visited;

    /**
     * 节点是从哪个节点来的
     */
    private int[] from;

    /**
     * 每个节点到指定点的距离
     */
    private int[] ord;

    /**
     * 图引用
     */
    private Graph graph;

    /**
     * 原始节点
     */
    private int src;

    public ShortestPath(Graph graph, int src) {
        this.src = src;
        this.graph = graph;
        visited = new boolean[graph.V()];
        from = new int[graph.V()];
        ord = new int[graph.V()];
        for (int i = 0; i < graph.V(); i++) {
            visited[i] = false;
            from[i] = -1;
        }
        dfs(src);
    }

    /**
     * 遍历可达的路径
     *
     * @param i
     */
    private void dfs(int i) {

        //利用一个队列
        LinkedList<Integer> queue = new LinkedList<>();
        queue.addFirst(i);
        visited[i] = true;
        ord[i] = 0;

        while (!queue.isEmpty()) {
            Integer last = queue.removeLast();
            Iterable<Integer> adj = graph.adj(last);
            for (Integer node : adj) {
                if (!visited[node]) {
                    queue.add(node);
                    visited[node] = true;
                    from[node] = i;
                    ord[node] = ord[i] + 1;
                }
            }
        }
    }

    /**
     * 节点是否可达
     *
     * @param v
     * @return
     */
    public boolean hasPath(int v) {
        return visited[v];
    }

    /**
     * 获取一个节点的路径
     *
     * @param v
     * @return
     */
    public ArrayList<Integer> path(int v) {
        LinkedList<Integer> list = new LinkedList<>();
        while (from[v] != -1) {
            list.addFirst(from[v]);
            v = from[v];
        }

        return new ArrayList<Integer>(list);
    }

    /**
     * 显示一个节点的路径
     *
     * @param v
     */
    public void showPath(int v) {
        List<Integer> path = path(v);
        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i) + "->");
        }
        System.out.print(v + "\n");
    }


}
