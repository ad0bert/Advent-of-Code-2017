package advent.util.day8;

public enum Operator {
                      INC("inc"), DEC("dec"), SND("snd"), SET("set"), ADD("add"), MUL("mul"), MOD("mod"), RCV("rcv"), JGZ("jgz");
    private final String text;

    private Operator(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return this.text;
    }

    public static Operator getEnum(String value) {
        for (Operator v : values()) {
            if (v.toString().equalsIgnoreCase(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException();
    }
}
