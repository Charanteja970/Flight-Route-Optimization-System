
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Graph graph = new Graph();

        // Sample airports
        Airport a1 = new Airport("JFK", "John F Kennedy International");
        Airport a2 = new Airport("LAX", "Los Angeles International");
        Airport a3 = new Airport("ORD", "Chicago O'Hare");
        Airport a4 = new Airport("DFW", "Dallas/Fort Worth International");
        Airport a5 = new Airport("MIA", "Miami International");

        // Add airports to graph
        graph.addAirport(a1);
        graph.addAirport(a2);
        graph.addAirport(a3);
        graph.addAirport(a4);
        graph.addAirport(a5);

        // Add routes (source, destination, weight)
        graph.addRoute(a1, a2, 2475);
        graph.addRoute(a1, a3, 740);
        graph.addRoute(a2, a4, 1235);
        graph.addRoute(a3, a4, 800);
        graph.addRoute(a3, a5, 1197);
        graph.addRoute(a4, a5, 1121);

        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm();

        while (true) {
            System.out.println("\nAvailable Airports:");
            for (Airport airport : graph.getAirports()) {
                System.out.println(airport);
            }

            System.out.print("\nEnter Source Airport Code (or 'exit' to quit): ");
            String sourceCode = scanner.nextLine();
            if (sourceCode.equalsIgnoreCase("exit")) break;
            Airport sourceAirport = graph.getAirportByCode(sourceCode);
            if (sourceAirport == null) {
                System.out.println("Invalid source airport code!");
                continue;
            }

            System.out.print("Enter Destination Airport Code: ");
            String destCode = scanner.nextLine();
            Airport destAirport = graph.getAirportByCode(destCode);
            if (destAirport == null) {
                System.out.println("Invalid destination airport code!");
                continue;
            }

            DijkstraAlgorithm.Result result = dijkstra.computeShortestPaths(graph, sourceAirport);
            List<Airport> path = dijkstra.getShortestPath(destAirport, result.getPredecessors());

            if (path.isEmpty()) {
                System.out.println("No path found from " + sourceCode + " to " + destCode);
            } else {
                System.out.println("Optimized Route:");
                for (Airport airport : path) {
                    System.out.print(airport.getCode() + " ");
                }
                System.out.println("\nTotal Distance: " + result.getDistances().get(destAirport) + " miles");
            }
        }

        scanner.close();
        System.out.println("Exiting Flight Route Optimization System.");
    }
}
