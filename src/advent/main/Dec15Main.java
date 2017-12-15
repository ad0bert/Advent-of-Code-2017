package advent.main;

import java.util.ArrayList;
import java.util.List;

public class Dec15Main {
    private static final long DIVISOR = 2147483647L;
    private static final long BIT_MASK = 0xFFFF;
    public static void main(String[] args) {
        long startValue1 = 65L;
        long startValue2 = 8921L;
        long startValue3 = 783L;
        long startValue4 = 325L;
        calc1(startValue1, startValue2);
        calc1(startValue3, startValue4);
        calc2(startValue1, startValue2);
        calc2(startValue3, startValue4);
    }

    private static void calc1(long startValue1, long startValue2) {
        long modifierA = 16807L;
        long modifierB = 48271L;
        long calcValueA = startValue1;
        long calcValueB = startValue2;
        int resCnt = 0;

        for (long i = 0; i < 40000000; ++i) {
            calcValueA = (modifierA * calcValueA) % DIVISOR;
            calcValueB = (modifierB * calcValueB) % DIVISOR;

            if ((calcValueA & BIT_MASK) == (calcValueB & BIT_MASK)) {
                resCnt++;
            }
        }

        System.out.println("Result Part 1: " + resCnt);

    }

    private static List<Long> calc5Mil(long calcValue, long modifier, long shiftValue) {
        List<Long> res = new ArrayList<Long>();

        while (res.size() != 5000000) {
            calcValue = (modifier * calcValue) % DIVISOR;
            if ((calcValue << -shiftValue) == 0) {
                res.add(calcValue);
            }
        }
        return res;
    }

    private static void calc2(long startValue1, long startValue2) {
        List<Long> genA = calc5Mil(startValue1, 16807L, 2);
        List<Long> genB = calc5Mil(startValue2, 48271L, 3);
        int res = 0;
        for (int i = 0; i < 5000000; ++i) {
            if ((genA.get(i) & BIT_MASK) == (genB.get(i) & BIT_MASK)) {
                res++;
            }
        }
        System.out.println("Result Part 2: " + res);
    }


}
