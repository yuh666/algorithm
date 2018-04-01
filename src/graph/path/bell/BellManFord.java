package graph.path.bell;

import graph.weight.Edge;
import graph.weight.WeightGraph;
import graph.weight.prim2.IndexMinHeap;

import java.util.LinkedList;

/**
 * @Author yuh
 * @Date Created in 上午8:34 2018/4/1
 * @Description 求有权的最短路径
 */
public class BellManFord<Weight extends Number & Comparable> {

    private double[] distTo;
    private boolean hasCycle;
    private Edge<Weight>[] from;
    private int s;
    private WeightGraph graph;

    public BellManFord(WeightGraph graph, int s) {
        this.graph = graph;
        this.s = s;
        from = (Edge<Weight>[]) new Edge[graph.V()];
        distTo = new double[graph.V()];

        //dijkstra算法
        distTo[s] = 0D;
        for (int i = 1; i <= graph.V() - 1; i++) {
            for (int j = 0; j < graph.V(); j++) {
                //relaxion算法
                Iterable<Edge<Weight>> adj = graph.adj(j);
                for (Edge<Weight> edge : adj) {
                    int other = edge.other(j);
                    if (from[other] == null || distTo[other] > distTo[j] + edge.wt().doubleValue()) {
                        //松弛算法
                        from[other] = edge;
                        distTo[other] = distTo[j] + edge.wt().doubleValue();
                    }
                }
            }
        }
        hasCycle = hasCycle();
    }

    public boolean isHasCycle(){
        return hasCycle;
    }

    private boolean hasCycle() {
        for (int j = 0; j < graph.V(); j++) {
            //relaxion算法
            Iterable<Edge<Weight>> adj = graph.adj(j);
            for (Edge<Weight> edge : adj) {
                int other = edge.other(j);
                if (from[other] == null || distTo[other] > distTo[j] + edge.wt().doubleValue()) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean hasPath(int v) {
        return from[v] != null;
    }


    public LinkedList<Edge<Weight>> path(int v) {
        LinkedList<Edge<Weight>> paths = new LinkedList<>();
        while (from[v] != null) {
            paths.addFirst(from[v]);
            v = from[v].other(v);
        }
        return paths;
    }

    public void showPath(int v) {
        LinkedList<Edge<Weight>> path = path(v);
        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i).v() + "->" + ((i == path.size() - 1) ? v : ""));
        }
        System.out.println();
    }


}
