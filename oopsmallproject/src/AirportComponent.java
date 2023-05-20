public abstract class AirportComponent {
    private String code;

    public AirportComponent(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        if (code == null || code.trim().isEmpty()) {
            throw new IllegalArgumentException("Code cannot be null or empty");
        }
        this.code = code;
    }

    public abstract String getType();

    @Override
    public String toString() {
        return getType() + ": " + code;
    }
}
