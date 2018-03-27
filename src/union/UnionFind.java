package union;

/**
 * @author yuh
 * 并查集实现
 */
public class UnionFind {

    /**
     * 元素数量
     */
    private int count;
    /**
     * 元素的父指针
     */
    private int[] parent;
    /**
     * 根节点的树的高度
     */
    private int[] rank;

    /**
     * 构造一个并查集
     *
     * @param count
     */
    public UnionFind(int count) {
        this.count = count;
        parent = new int[count];
        rank = new int[count];
        for (int i = 0; i < count; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    /**
     * 查找一个元素的根节点
     *
     * @param p
     * @return
     */
    public int find(int p) {
        //根节点的特征是根节点就是自身
        // while(p != parent[p]){
        //路径压缩 让节点指向父节点的父节点
        //     parent[p] = parent[parent[p]];
        //   p = parent[p];
        // }

        //递归路径压缩
        if (p != parent[p]) {
            parent[p] = find(parent[p]);
            p = parent[p];
        }

        return p;
    }

    /**
     * 两个节点是否相连
     *
     * @param a
     * @param b
     * @return
     */
    public boolean isConnected(int a, int b) {
        return find(a) == find(b);
    }

    /**
     * 组合两个元素
     *
     * @param a
     * @param b
     */
    public void union(int a, int b) {
        //将树低的组合给树高的 若相等就随意吧
        int pRoot = find(a);
        int qRoot = find(b);
        if (pRoot == qRoot) {
            return;
        }
        if (rank[pRoot] < rank[qRoot]) {
            parent[pRoot] = qRoot;
        } else if (rank[pRoot] > rank[qRoot]) {
            parent[qRoot] = pRoot;
        } else {
            parent[pRoot] = qRoot;
            rank[qRoot] += 1;
        }
    }


}
