package graph.weight.prim;

import graph.weight.Edge;
import graph.weight.WeightGraph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 有权图的最小生成树
 */
public class LazyPrimMST<Weight extends Number & Comparable> {
    private WeightGraph graph;
    private boolean[] marked;
    private List<Edge<Weight>> edges;
    private Number totalWeight;
    private MinHeap<Edge<Weight>> minHeap;

    public LazyPrimMST(WeightGraph graph) {
        this.graph = graph;
        marked = new boolean[graph.V()];
        edges = new ArrayList<>();
        minHeap = new MinHeap<Edge<Weight>>(graph.E());
        //从第一个节点开始遍历
        visit(0);

        while (!minHeap.isEmpty()) {
            Edge<Weight> edge = minHeap.extractMin();
            if (marked[edge.v()] == marked[edge.w()]) {
                continue;
            }
            edges.add(edge);
            if (marked[edge.v()]) {
                visit(edge.w());
            } else {
                visit(edge.v());
            }
        }


        totalWeight = edges.get(0).wt().doubleValue();
        for (int i = 1; i < edges.size(); i++) {
            totalWeight = totalWeight.doubleValue() + edges.get(i).wt().doubleValue();
        }

    }

    private void visit(int i) {

        if (!marked[i]) {
            marked[i] = true;
        }

        Iterable<Edge<Weight>> iterable = graph.adj(i);
        for (Edge<Weight> edge : iterable) {
            if (!marked[edge.other(i)]) {
                minHeap.insert(edge);
            }
        }

    }

    // 返回最小生成树的所有边
    List<Edge<Weight>> mstEdges(){
        return edges;
    };

    // 返回最小生成树的权值
    Number result(){
        return totalWeight;
    };
}
