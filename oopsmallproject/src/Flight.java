public class Flight extends AirportComponent{
    private String airlineCode;
    private String departureAirportCode;
    private String arrivalAirportCode;

    public Flight(String code, String airlineCode, String departureAirportCode, String arrivalAirportCode) {
        super(code);
        this.airlineCode = airlineCode;
        this.departureAirportCode = departureAirportCode;
        this.arrivalAirportCode = arrivalAirportCode;
    }

    public String getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        if (airlineCode == null || airlineCode.trim().isEmpty()) {
            throw new IllegalArgumentException("Airline code cannot be null or empty");
        }
        this.airlineCode = airlineCode;
    }

    public String getDepartureAirportCode() {
        return departureAirportCode;
    }

    public void setDepartureAirportCode(String departureAirportCode) {
        if (departureAirportCode == null || departureAirportCode.trim().isEmpty()) {
            throw new IllegalArgumentException("Departure airport code cannot be null or empty");
        }
        this.departureAirportCode = departureAirportCode;
    }

    public String getArrivalAirportCode() {
        return arrivalAirportCode;
    }

    public void setArrivalAirportCode(String arrivalAirportCode) {
        if (arrivalAirportCode == null || arrivalAirportCode.trim().isEmpty()) {
            throw new IllegalArgumentException("Arrival airport code cannot be null or empty");
        }
        this.arrivalAirportCode = arrivalAirportCode;
    }

    public String getType() {
        return "Flight";
    }

    @Override
    public String toString() {
        return getType() + ": " + getCode() + ", Airline: " + airlineCode + ", Departure: " + departureAirportCode + ", Arrival: " + arrivalAirportCode;
    }
}
