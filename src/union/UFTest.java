package union;

public class UFTest {
    // 测试第五版本的并查集, 测试元素个数为n, 测试逻辑和之前是完全一样的
    public static void testUF5( int n ){

        UnionFind uf = new UnionFind(n);

        long startTime = System.currentTimeMillis();

        // 进行n次操作, 每次随机选择两个元素进行合并操作
        /*for( int i = 0 ; i < n ; i ++ ){
            int a = (int)(Math.random()*n);
            int b = (int)(Math.random()*n);
            uf.union(a,b);
        }*/


        for (int i = 0; i < n-1; i++) {
            uf.union(i,i+1);
        }
        for (int i = 0; i < n-1; i++) {
            uf.isConnected(i,i+1);
        }


        // 再进行n次操作, 每次随机选择两个元素, 查询他们是否同属一个集合
        /*for(int i = 0 ; i < n ; i ++ ){
            int a = (int)(Math.random()*n);
            int b = (int)(Math.random()*n);
            uf.isConnected(a,b);
        }*/
        long endTime = System.currentTimeMillis();

        // 打印输出对这2n个操作的耗时
        System.out.println("UF4, " + 2*n + " ops, " + (endTime-startTime) + "ms");
    }


    public static void main(String[] args) {
        testUF5(100000);
    }
}
