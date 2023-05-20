public class Gate extends AirportComponent {
    private String terminalCode;

    public Gate(String code, String terminalCode) {
        super(code);
        this.terminalCode = terminalCode;
    }

    public String getTerminalCode() {
        return terminalCode;
    }

    public void setTerminalCode(String terminalCode) {
        if (terminalCode == null || terminalCode.trim().isEmpty()) {
            throw new IllegalArgumentException("Terminal code cannot be null or empty");
        }
        this.terminalCode = terminalCode;
    }

    public String getType() {
        return "Gate";
    }

    @Override
    public String toString() {
        return getType() + ": " + getCode() + ", Terminal: " + terminalCode;
    }
}
