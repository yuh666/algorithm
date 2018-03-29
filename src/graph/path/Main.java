package graph.path;


import graph.ReadGraph;
import graph.SparseGraph;

public class Main {

    // 测试寻路算法
    public static void main(String[] args) {



        //顶点数必须大于最后一个索引
        String filename = "testG.txt";
        SparseGraph g = new SparseGraph(101, false);
        ReadGraph readGraph = new ReadGraph(g, filename);
        g.show();
       System.out.println();

        ShortestPath path = new ShortestPath(g,0);
        System.out.println("Path from 0 to 6 : ");
        path.showPath(6);
    }
}

