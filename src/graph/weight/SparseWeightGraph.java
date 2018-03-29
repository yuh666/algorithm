package graph.weight;

import graph.Graph;

import java.util.LinkedList;

/**
 * @author yuh
 * 稀疏图 用邻接表表示
 */
public class SparseWeightGraph<Weight extends Number & Comparable> implements WeightGraph<Weight> {

    /**
     * 节点数
     */
    private int n;

    /**
     * 边数
     */
    private int m;

    /**
     * 是否为有向图
     */
    private boolean directed;

    /**
     * 每个元素是一个连接的顶点的集合
     */
    private LinkedList<Edge<Weight>>[] vectors;

    /**
     * 构造
     *
     * @param n        节点数
     * @param directed 是否为有向图
     */
    public SparseWeightGraph(int n, boolean directed) {
        this.n = n;
        this.directed = directed;
        m = 0;
        vectors = (LinkedList<Edge<Weight>>[]) new LinkedList[n];
        for (int i = 0; i < vectors.length; i++) {
            vectors[i] = new LinkedList<Edge<Weight>>();
        }
    }


    /**
     * 几个顶点
     *
     * @return
     */
    @Override
    public int V() {
        return n;
    }

    /**
     * 几条边
     *
     * @return
     */
    @Override
    public int E() {
        return m;
    }

    /**
     * 两个顶点之间是否有边
     *
     * @param v
     * @param w
     * @return
     */
    @Override
    public boolean hasEdge(int v, int w) {
        LinkedList<Edge<Weight>> vector = vectors[v];
        for (Edge<Weight> edge : vector) {
            if (edge.other(v) == w) {
                return true;
            }
        }
        return false;

    }

    /**
     * 添加一条边
     * @param edge
     */
    @Override
    public void addEdge(Edge<Weight> edge) {

        vectors[edge.v()].add(new Edge<Weight>(edge));
        if (!directed && edge.v() != edge.w()) {
            vectors[edge.w()].add(new Edge<Weight>(edge.w(),edge.v(),edge.wt()));
        }
        m++;
    }

    /**
     * 打印整个连接情况
     */
    @Override
    public void show() {
        for (int i = 0; i < vectors.length; i++) {
            System.out.print(i + "->");
            LinkedList<Edge<Weight>> vector = vectors[i];
            for (Edge<Weight> edge : vector) {
                System.out.print("to=" + edge.other(i) + "weight=" + edge.wt());
            }
            System.out.println();
        }
    }

    /**
     * 打印和这个顶点关联的所有的边
     *
     * @param v
     * @return
     */
    @Override
    public Iterable<Edge<Weight>> adj(int v) {
        return vectors[v];
    }
}
