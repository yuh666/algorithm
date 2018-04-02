package graph.weight.prim2;

import graph.weight.Edge;
import graph.weight.WeightGraph;

import java.util.ArrayList;
import java.util.List;

/**
 * 有权图的最小生成树
 */
public class PrimMST<Weight extends Number & Comparable> {
    private WeightGraph graph;
    private boolean[] marked;
    private List<Edge<Weight>> edges;
    private Number totalWeight;
    private IndexMinHeap<Weight> minIndexHeap;
    private Edge<Weight>[] edgeTo;

    public PrimMST(WeightGraph graph) {
        this.graph = graph;
        marked = new boolean[graph.V()];
        edgeTo = (Edge<Weight>[]) new Edge[graph.V()];
        edges = new ArrayList<Edge<Weight>>();
        minIndexHeap = new IndexMinHeap<>(graph.V());
        //从第一个节点开始遍历
        visit(0);

        while (!minIndexHeap.isEmpty()) {
            int index = minIndexHeap.extractMinIndex();
            Edge<Weight> weightEdge = edgeTo[index];
            edges.add(weightEdge);
            visit(index);

        }
        totalWeight = edges.get(0).wt().doubleValue();
        for (int i = 1; i < edges.size(); i++) {
            totalWeight = totalWeight.doubleValue() + edges.get(i).wt().doubleValue();
        }

    }

    private void visit(int i) {
        marked[i] = true;
        Iterable<Edge<Weight>> iterable = graph.adj(i);
        for (Edge<Weight> edge : iterable) {
            int other = edge.other(i);
            if (!marked[other]) {
                if (edgeTo[other] == null) {
                    minIndexHeap.insert(other, edge.wt());
                    edgeTo[other] = edge;
                } else if (edgeTo[other].wt().compareTo(edge.wt()) > 0) {
                    minIndexHeap.change(other, edge.wt());
                    edgeTo[other] = edge;
                }
            }
        }
    }

    // 返回最小生成树的所有边
    List<Edge<Weight>> mstEdges() {
        return edges;
    }

    // 返回最小生成树的权值
    Number result() {
        return totalWeight;
    }
}
