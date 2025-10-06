package amburoute;

import java.util.Scanner;
import java.util.List;

public class DispatchService {
    private Scanner sc = new Scanner(System.in);
    private Graph graph;
    private AmbulanceService ambulanceService;
    private HospitalService hospitalService;

    public DispatchService(Graph graph, AmbulanceService ambulanceService, HospitalService hospitalService) {
        this.graph = graph;
        this.ambulanceService = ambulanceService;
        this.hospitalService = hospitalService;
    }

    public void dispatch() {
        System.out.print("Enter Emergency Location: ");
        String patientLocation = sc.nextLine().trim();

        Ambulance amb = ambulanceService.findNearestAmbulance(patientLocation, graph);
        if (amb == null) {
            System.out.println("❌ No available ambulance found.");
            return;
        }

        Hospital hosp = hospitalService.findNearestHospital(patientLocation, graph);
        if (hosp == null) {
            System.out.println("❌ No hospital available in the system.");
            return;
        }

        Graph.PathResult routeToPatient = graph.dijkstra(amb.currentLocation, patientLocation);
        Graph.PathResult routeToHospital = graph.dijkstra(patientLocation, hosp.location);

        // mark ambulance busy and update current location to hospital (after dispatch) if you'd like to simulate
        amb.isAvailable = false;

        System.out.println("🚑 Dispatching ambulance " + amb.id);
        System.out.println("Route (ambulance -> patient): " + String.join(" -> ", routeToPatient.path) + "  [dist=" + routeToPatient.distance + "]");
        System.out.println("Route (patient -> hospital): " + String.join(" -> ", routeToHospital.path) + "  [dist=" + routeToHospital.distance + "]");
        System.out.println("Hospital assigned: " + hosp.name + " at " + hosp.location);
    }
}
