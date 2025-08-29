

public class Edge {
    private final  Airport destination;
    private final int weight; // Weight could be distance, cost, or time

    public Edge(Airport destination, int weight) {
        this.destination = destination;
        this.weight = weight;
    }

    public Airport getDestination() {
        return destination;
    }

    public int getWeight() {
        return weight;
    }
}
