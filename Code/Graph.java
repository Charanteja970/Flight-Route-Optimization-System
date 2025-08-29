

import java.util.*;

public class Graph {
    private final Map<Airport, List<Edge>> adjacencyMap = new HashMap<>();

    public void addAirport(Airport airport) {
        adjacencyMap.putIfAbsent(airport, new ArrayList<>());
    }

    public void addRoute(Airport source, Airport destination, int weight) {
        adjacencyMap.get(source).add(new Edge(destination, weight));
    }

    public List<Edge> getEdges(Airport airport) {
        return adjacencyMap.getOrDefault(airport, new ArrayList<>());
    }

    public Set<Airport> getAirports() {
        return adjacencyMap.keySet();
    }

    public Airport getAirportByCode(String code) {
        for (Airport airport : adjacencyMap.keySet()) {
            if (airport.getCode().equalsIgnoreCase(code)) {
                return airport;
            }
        }
        return null;
    }
}
