package advent.util.day8;

public enum KeyWord {
    IF("if");
    private final String text;

    private KeyWord(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return this.text;
    }

    public static KeyWord getEnum(String value) {
        for (KeyWord v : values()) {
            if (v.toString().equalsIgnoreCase(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException();
    }
}
