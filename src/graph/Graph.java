package graph;

/**
 * @author yuh
 * 图的接口
 */
public interface Graph {

    /**
     * 几个顶点
     * @return
     */
    int V();

    /**
     * 几条边
     * @return
     */
    int E();

    /**
     * 两个顶点之间是否有边
     * @param v
     * @param w
     * @return
     */
    boolean hasEdge(int v,int w);

    /**
     * 给定两个顶点添加一条边
     * @param v
     * @param w
     */
    void addEdge(int v,int w);

    /**
     * 打印整个连接情况
     */
    void show();

    /**
     * 打印和这个顶点关联的所有的边
     * @param v
     * @return
     */
    Iterable<Integer> adj(int v);

}
