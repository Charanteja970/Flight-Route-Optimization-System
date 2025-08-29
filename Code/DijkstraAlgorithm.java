

import java.util.*;

public class DijkstraAlgorithm {
    public static class Result {
        private final Map<Airport, Integer> distances;
        private final Map<Airport, Airport> predecessors;

        public Result(Map<Airport, Integer> distances, Map<Airport, Airport> predecessors) {
            this.distances = distances;
            this.predecessors = predecessors;
        }

        public Map<Airport, Integer> getDistances() {
            return distances;
        }

        public Map<Airport, Airport> getPredecessors() {
            return predecessors;
        }
    }

    public Result computeShortestPaths(Graph graph, Airport source) {
        Map<Airport, Integer> distances = new HashMap<>();
        Map<Airport, Airport> predecessors = new HashMap<>();
        PriorityQueue<AirportDistancePair> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(airportDistancePair -> airportDistancePair.distance));

        for (Airport airport : graph.getAirports()) {
            distances.put(airport, Integer.MAX_VALUE);
        }
        distances.put(source, 0);
        priorityQueue.add(new AirportDistancePair(source, 0));

        while (!priorityQueue.isEmpty()) {
            AirportDistancePair currentPair = priorityQueue.poll();
            Airport current = currentPair.airport;

            for (Edge edge : graph.getEdges(current)) {
                Airport neighbor = edge.getDestination();
                int newDist = distances.get(current) + edge.getWeight();
                if (newDist < distances.get(neighbor)) {
                    distances.put(neighbor, newDist);
                    predecessors.put(neighbor, current);
                    priorityQueue.add(new AirportDistancePair(neighbor, newDist));
                }
            }
        }

        return new Result(distances, predecessors);
    }

    private static class AirportDistancePair {
        Airport airport;
        int distance;

        public AirportDistancePair(Airport airport, int distance) {
            this.airport = airport;
            this.distance = distance;
        }
    }

    public List<Airport> getShortestPath(Airport destination, Map<Airport, Airport> predecessors) {
        List<Airport> path = new LinkedList<>();
        Airport step = destination;
        if (predecessors.get(step) == null) {
            return path; // no path found
        }
        path.add(step);
        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.add(0, step);
        }
        return path;
    }
}
