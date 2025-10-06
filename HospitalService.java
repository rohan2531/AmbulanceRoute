package amburoute;

import java.util.*;

public class HospitalService {
    private List<Hospital> hospitals = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    public void addHospital() {
        System.out.print("Enter Hospital Name: ");
        String name = sc.nextLine().trim();
        System.out.print("Enter Location: ");
        String location = sc.nextLine().trim();
        hospitals.add(new Hospital(name, location));
        System.out.println("✅ Hospital added.");
    }

    public void viewHospitals() {
        if (hospitals.isEmpty()) {
            System.out.println("No hospitals found.");
            return;
        }
        System.out.println("Hospitals:");
        for (Hospital h : hospitals) {
            System.out.println("- " + h.name + " at " + h.location);
        }
    }

    public Hospital findNearestHospital(String location, Graph graph) {
        Hospital nearest = null;
        long best = Long.MAX_VALUE;
        for (Hospital h : hospitals) {
            Graph.PathResult res = graph.dijkstra(location, h.location);
            if (res.distance >= 0 && res.distance < best) {
                best = res.distance;
                nearest = h;
            }
        }
        return nearest;
    }

    public List<Hospital> getHospitals() {
        return hospitals;
    }
}
