package graph.weight;

import graph.Graph;

import java.util.LinkedList;

/**
 * @author yuh
 * 有权稠密图
 */
public class DenseWeightGraph<Weight extends Number & Comparable> implements WeightGraph<Weight> {

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
     * 二维数组 表示两个顶点之间的连接情况
     */
    private Edge[][] matrix;

    /**
     * 构造
     *
     * @param n        节点数
     * @param directed 是否为有向图
     */
    public DenseWeightGraph(int n, boolean directed) {
        this.n = n;
        this.directed = directed;
        m = 0;
        matrix = new Edge[n][n];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = null;
            }
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
        return matrix[v][w] == null;
    }

    /**
     * 添加一条边
     * @param edge
     */
    @Override
    public void addEdge(Edge<Weight> edge) {
        if (hasEdge(edge.v(), edge.w())) {
            matrix[edge.v()][ edge.w()] = null;
            if (!directed) {
                matrix[ edge.v()][ edge.w()] = null;
            }
            m--;
        }
        matrix[ edge.v()][ edge.w()] = new Edge<Weight>(edge);
        if (!directed) {
            matrix[ edge.w()][ edge.v()] = new Edge<Weight>(edge);
        }
        m++;
    }

    /**
     * 打印整个连接情况
     */
    @Override
    public void show() {
        for (int i = 0; i < matrix.length; i++) {
            Edge<Weight>[] vectors = this.matrix[i];
            for (int j = 0; j < vectors.length; j++) {
                if(vectors[j] == null){
                    System.out.print("NULL\t");
                }
                System.out.print(vectors[j] + "\t");
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
        LinkedList<Edge<Weight>> vectors = new LinkedList<>();
        Edge<Weight>[] matrix = this.matrix[v];
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i] != null) {
                vectors.add(matrix[i]);
            }
        }
        return vectors;
    }
}
