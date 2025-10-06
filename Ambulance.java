package amburoute;

public class Ambulance {
    String id;
    String currentLocation;
    boolean isAvailable;

    public Ambulance(String id, String currentLocation) {
        this.id = id;
        this.currentLocation = currentLocation;
        this.isAvailable = true;
    }
}
