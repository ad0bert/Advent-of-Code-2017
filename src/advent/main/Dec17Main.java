package advent.main;

import java.util.ArrayList;
import java.util.List;

public class Dec17Main {
    private static final int STEP_SIZE_1 = 3;
    private static final int STEP_SIZE_2 = 380;
    private static final int ITERATIONS_1 = 2017;
    private static final int ITERATIONS_2 = 50000000;

    public static void main(String[] args) {
        calc1(ITERATIONS_1, STEP_SIZE_1);
        calc1(ITERATIONS_1, STEP_SIZE_2);

        calc2(ITERATIONS_2, STEP_SIZE_1);
        calc2(ITERATIONS_2, STEP_SIZE_2);
    }

    private static void calc1(final int iterations, final int stepSize) {
        List<Integer> buffer = new ArrayList<Integer>();
        buffer.add(0);
        int currPos = 0;

        for (int i = 0; i < iterations; ++i) {
            buffer.add(((currPos + stepSize) % buffer.size()) + 1, i + 1);
            currPos = ((currPos + stepSize) % (buffer.size() - 1)) + 1;
        }
        System.out.println(buffer.get(currPos + 1));
    }

    private static void calc2(final int iterations, final int stepSize) {
        int currPos = 0;
        int val = 0;
        for (int i = 0; i < iterations; ++i) {
            currPos = ((currPos + stepSize) % (i + 1)) + 1;
            if (currPos == 1) {
                val = i + 1;
            }
        }
        System.out.println(val);
    }

}
