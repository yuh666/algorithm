package graph.weight;

public class Main {

    // 测试通过文件读取图的信息
    public static void main(String[] args) {

        // 使用两种图的存储方式读取testG1.txt文件
        String filename = "testG1.txt";
        SparseWeightGraph<Double> g1 = new SparseWeightGraph<Double>(8, false);
        ReadWeightGraph readGraph1 = new ReadWeightGraph(g1, filename);
        System.out.println("test G1 in Sparse Weighted Graph:");
        g1.show();

        System.out.println();

        DenseWeightGraph<Double> g2 = new DenseWeightGraph<Double>(8, false);
        ReadWeightGraph readGraph2 = new ReadWeightGraph(g2, filename);
        System.out.println("test G1 in Dense Graph:");
        g2.show();

        System.out.println("");
    }
}
