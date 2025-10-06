package amburoute;

import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    // inside Main class
    static Graph cityGraph = new Graph();
    static HospitalService hospitalService = new HospitalService();
    static AmbulanceService ambulanceService = new AmbulanceService();
    static DispatchService dispatchService = new DispatchService(cityGraph, ambulanceService, hospitalService);

    
    public static void main(String[] args) {
        preloadCityMap(); 
        int choice;

        do {
            System.out.println("\\n--- AmbuRoute Menu ---");
            System.out.println("1. Add Hospital");
            System.out.println("2. Add Ambulance");
            System.out.println("3. View Hospitals");
            System.out.println("4. View Ambulances");
            System.out.println("5. Dispatch Ambulance");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> hospitalService.addHospital();
                case 2 -> ambulanceService.addAmbulance();
                case 3 -> hospitalService.viewHospitals();
                case 4 -> ambulanceService.viewAmbulances();
                case 5 -> dispatchService.dispatch();
                case 6 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 6);
    }

    public static void preloadCityMap() {
        cityGraph.addEdge("A", "B", 4);
        cityGraph.addEdge("A", "C", 2);
        cityGraph.addEdge("B", "D", 5);
        cityGraph.addEdge("C", "D", 1);
        cityGraph.addEdge("C", "E", 8);
        cityGraph.addEdge("D", "E", 3);
        // Add more for realism
    }
}
