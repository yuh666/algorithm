package graph;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author yuh
 * 稠密图 用邻接矩阵表示
 */
public class DenseGraph implements Graph {

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
    private Boolean[][] matrix;

    /**
     * 构造
     *
     * @param n        节点数
     * @param directed 是否为有向图
     */
    public DenseGraph(int n, boolean directed) {
        this.n = n;
        this.directed = directed;
        m = 0;
        matrix = new Boolean[n][n];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = false;
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
        return matrix[v][w];
    }

    /**
     * 给定两个顶点添加一条边
     *
     * @param v
     * @param w
     */
    @Override
    public void addEdge(int v, int w) {
        if (hasEdge(v, w)) {
            return;
        }
        matrix[v][w] = true;
        if (!directed) {
            matrix[w][v] = true;
        }
        m ++;
    }

    /**
     * 打印整个连接情况
     */
    @Override
    public void show() {
        for (int i = 0; i < matrix.length; i++) {
            Boolean[] vectors = this.matrix[i];
            for (int j = 0; j < vectors.length; j++) {
                System.out.print(vectors[j] + " ");
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
    public Iterable<Integer> adj(int v) {
        LinkedList<Integer> vectors = new LinkedList<>();
        Boolean[] matrix = this.matrix[v];
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i]) {
                vectors.add(i);
            }
        }
        return vectors;
    }
}
