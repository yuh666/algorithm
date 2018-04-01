package graph.path.bell;

import graph.weight.ReadWeightGraph;
import graph.weight.SparseWeightGraph;

/**
 * @Author yuh
 * @Date Created in 上午10:52 2018/4/1
 * @Description
 */
public class Main {

    // 测试我们的Bellman-Ford最短路径算法
    public static void main(String[] args) {

        //String filename = "testG2.txt";
        String filename = "testG_negative_circle.txt";
        int V = 5;

        SparseWeightGraph g = new SparseWeightGraph(V, true);
        ReadWeightGraph readGraph = new ReadWeightGraph(g, filename);

        System.out.println("Test Bellman-Ford:\n");
        BellManFord<Integer> bellmanFord = new BellManFord<Integer>(g, 0);
        if (bellmanFord.isHasCycle()) {
            System.out.println("The graph contain negative cycle!");
        } else
            for (int i = 1; i < V; i++) {
                if (bellmanFord.hasPath(i)) {
                    System.out.println("Shortest Path to " + i + " : ");
                    bellmanFord.showPath(i);
                } else
                    System.out.println("No Path to " + i);

                System.out.println("----------");
            }

    }

}
