import java.util.ArrayList;

public class Airport extends AirportComponent {
    private ArrayList<AirportComponent> components;

    public Airport(String code) {
        super(code);
        components = new ArrayList<>();
    }

    public void addComponent(AirportComponent component) {
        components.add(component);
    }

    public ArrayList<AirportComponent> getComponents() {
        return components;
    }

    public String getType() {
        return "Airport";
    }
}
