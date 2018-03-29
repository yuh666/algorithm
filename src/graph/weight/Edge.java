package graph.weight;

/**
 * 带权重的边
 *
 * @param <Weight>
 */
public class Edge<Weight extends Number & Comparable> implements Comparable<Edge> {

    private int v;
    private int w;
    private Weight weight;

    public Edge(int v, int w, Weight weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public Edge(Edge<Weight> edge) {
        this.v = edge.v;
        this.w = edge.w;
        this.weight = edge.weight;
    }

    public int v (){
        return v;
    }

    public int w(){
        return w;
    }

    public int other(int v) {
        return this.v == v ? this.w : this.v;
    }

    @Override
    public String toString() {
        return "v=" + v + "w=" + w + "weight=" + weight;
    }

    public Weight wt(){
        return weight;
    }

    @Override
    public int compareTo(Edge o) {
        if (this.weight.compareTo(o.weight) > 0) {
            return 1;
        } else if (this.weight.compareTo(o.weight) < 0) {
            return -1;
        } else {
            return 0;
        }
    }
}
