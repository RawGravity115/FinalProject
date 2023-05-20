import java.util.ArrayList;

public class AirportManagementSystem {
    public static void main(String[] args) {
        Airport airport = new Airport("LAX");

        Flight flight1 = new Flight("AA100", "AA", "LAX", "JFK");
        Flight flight2 = new Flight("DL200", "DL", "LAX", "ATL");

        airport.addComponent(flight1);
        airport.addComponent(flight2);

        ArrayList<AirportComponent> components = airport.getComponents();
        for (AirportComponent component : components) {
            System.out.println(component);
        }
        FlightManager manager = new FlightManager();
        manager.addFlight(flight1);
        System.out.println(manager.getFlights());
    }
}
