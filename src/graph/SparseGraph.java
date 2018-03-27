package graph;

import java.util.LinkedList;

/**
 * @author yuh
 * 稀疏图 用邻接表表示
 */
public class SparseGraph implements Graph {

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
    private LinkedList<Integer>[] vectors;

    /**
     * 构造
     *
     * @param n        节点数
     * @param directed 是否为有向图
     */
    public SparseGraph(int n, boolean directed) {
        this.n = n;
        this.directed = directed;
        m = 0;
        vectors = (LinkedList<Integer>[]) new LinkedList[n];
        for (int i = 0; i < vectors.length; i++) {
            vectors[i] = new LinkedList<Integer>();
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
        return vectors[v].contains(w);
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
        vectors[v].add(w);
        if (!directed && v != w) {
            vectors[w].add(v);
        }
        m++;
    }

    /**
     * 打印整个连接情况
     */
    @Override
    public void show() {
        for (int i = 0; i < vectors.length; i++) {
            System.out.print(i+"->");
            System.out.println(vectors[i]);
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
        return vectors[v];
    }
}
