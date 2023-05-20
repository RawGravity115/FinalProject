import java.util.ArrayList;

public class Terminal extends AirportComponent {
    private ArrayList<Gate> gates;

    public Terminal(String code) {
        super(code);
        gates = new ArrayList<>();
    }

    public void addGate(Gate gate) {
        gates.add(gate);
    }

    public ArrayList<Gate> getGates() {
        return gates;
    }

    public String getType() {
        return "Terminal";
    }
}
