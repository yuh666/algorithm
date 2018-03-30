package graph.weight.prim;

import graph.weight.Edge;
import graph.weight.ReadWeightGraph;
import graph.weight.SparseWeightGraph;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        String filename = "testG1.txt";
        int V = 8;

        SparseWeightGraph<Double> g = new SparseWeightGraph<Double>(V, false);
        ReadWeightGraph readGraph = new ReadWeightGraph(g, filename);

        // Test Lazy Prim MST
        System.out.println("Test Lazy Prim MST:");
        LazyPrimMST<Double> lazyPrimMST = new LazyPrimMST<Double>(g);
        List<Edge<Double>> mst = lazyPrimMST.mstEdges();
        for (int i = 0; i < mst.size(); i++) {

            System.out.println(mst.get(i));
        }
        System.out.println("The MST weight is: " + lazyPrimMST.result());

        System.out.println();
    }
}