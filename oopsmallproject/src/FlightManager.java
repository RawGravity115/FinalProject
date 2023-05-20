import java.util.ArrayList;

public class FlightManager {
    private ArrayList<Flight> flights;

    public FlightManager() {
        flights = new ArrayList<>();
    }

    public void addFlight(Flight flight) {
        flights.add(flight);
    }

    public ArrayList<Flight> getFlights() {
        return flights;
    }
}
