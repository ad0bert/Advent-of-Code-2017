package advent.util.day8;

public enum Operation {
    EQU("=="), NEQ("!="), LSS("<"), LEQ("<="), GRT(">"), GEQ(">=");
    private final String text;

    private Operation(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return this.text;
    }

    public static Operation getEnum(String value) {
        for (Operation v : values()) {
            if (v.toString().equalsIgnoreCase(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException();
    }
}
