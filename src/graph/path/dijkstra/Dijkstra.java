package graph.path.dijkstra;

import graph.Graph;
import graph.weight.Edge;
import graph.weight.WeightGraph;
import graph.weight.prim2.IndexMinHeap;

import java.util.LinkedList;

/**
 * @Author yuh
 * @Date Created in 上午8:34 2018/4/1
 * @Description 求有权的最短路径
 */
public class Dijkstra<Weight extends Number & Comparable> {

    private double[] distTo;
    private IndexMinHeap<Double> indexMinHeap;
    private Edge<Weight>[] from;
    private int s;
    private WeightGraph graph;
    private boolean[] markerd;

    public Dijkstra(WeightGraph graph, int s) {
        this.graph = graph;
        this.s = s;
        indexMinHeap = new IndexMinHeap<>(graph.V());
        from = (Edge<Weight>[]) new Edge[graph.V()];
        markerd = new boolean[graph.V()];
        distTo = new double[graph.V()];

        //dijkstra算法
        distTo[s] = 0D;
        markerd[s] = true;
        indexMinHeap.insert(s, distTo[s]);
        while (!indexMinHeap.isEmpty()) {
            int v = indexMinHeap.extractMinIndex();
            markerd[v] = true;
            //relaxion算法
            Iterable<Edge<Weight>> adj = graph.adj(v);
            for (Edge<Weight> edge : adj) {
                int other = edge.other(v);
                if (!markerd[other]) {
                    if (from[other] == null || distTo[other] > distTo[v] + edge.wt().doubleValue()) {
                        //松弛算法
                        from[other] = edge;
                        distTo[other] = distTo[v] + edge.wt().doubleValue();
                        if (indexMinHeap.contain(other)) {
                            indexMinHeap.change(other, distTo[other]);
                        } else {
                            indexMinHeap.insert(other, distTo[other]);
                        }
                    }
                }
            }
        }
    }


    public boolean hasPath(int v) {
        return markerd[v];
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
