package amburoute;

import java.util.*;

public class AmbulanceService {
    private List<Ambulance> ambulances = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    public void addAmbulance() {
        System.out.print("Enter Ambulance ID: ");
        String id = sc.nextLine().trim();
        System.out.print("Enter Current Location: ");
        String location = sc.nextLine().trim();
        ambulances.add(new Ambulance(id, location));
        System.out.println("✅ Ambulance added.");
    }

    public void viewAmbulances() {
        if (ambulances.isEmpty()) {
            System.out.println("No ambulances registered.");
            return;
        }
        System.out.println("Ambulances:");
        for (Ambulance a : ambulances) {
            System.out.println("- " + a.id + " at " + a.currentLocation + " (available: " + a.isAvailable + ")");
        }
    }

    // Find nearest available ambulance to a given location using graph.dijkstra
    public Ambulance findNearestAmbulance(String location, Graph graph) {
        Ambulance nearest = null;
        long best = Long.MAX_VALUE;
        for (Ambulance a : ambulances) {
            if (!a.isAvailable) continue;
            Graph.PathResult res = graph.dijkstra(a.currentLocation, location);
            if (res.distance >= 0 && res.distance < best) {
                best = res.distance;
                nearest = a;
            }
        }
        return nearest;
    }

    public List<Ambulance> getAmbulances() {
        return ambulances;
    }
}
