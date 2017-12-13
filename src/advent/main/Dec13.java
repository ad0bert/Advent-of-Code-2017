package advent.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import advent.util.FileInputReader;

public class Dec13Main {
    private static final String inputFile1 = "./resources/inputDay13/input1.txt";
    private static final String inputFile2 = "./resources/inputDay13/input2.txt";

    public static void main(String[] args) {
        try {
            List<Integer> input1 = FileInputReader.readIntegerLineVerticalDay13(": ", new File(inputFile1));
            List<Integer> input2 = FileInputReader.readIntegerLineVerticalDay13(": ", new File(inputFile2));
            System.out.println("Res Part 1: " + calcPart1(input1, 0, true));
            System.out.println("Res Part 1: " + calcPart1(input2, 0, true));
            // ----------------
            System.out.println("Res Part 2: " + calcPart2(input1));
            System.out.println("Res Part 2: " + calcPart2(input2));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean checkIsScanHit(int time, int depth) {
        if ((depth == 0) || (depth == 1)) {
            return true;
        }
        depth--;
        if (((time / depth) % 2) == 0) {
            return (time % depth) == 0;
        } else {
            return (depth - (time % depth)) == 0;
        }
    }

    private static int calcPart1(List<Integer> input, int delay, boolean day1) {
        int res = 0;
        for (int i = 0; i < input.size(); ++i) {
            if (input.get(i).equals(0)) {
                continue;
            }
            if (checkIsScanHit(i + delay, input.get(i))) {
                if (!day1) {
                    return -1;
                }
                res += (i * input.get(i));
            }
        }
        return res;
    }

    private static int calcPart2(List<Integer> input) {
        int delay = 0;
        while (calcPart1(input, delay, false) != 0) {
            delay++;
        }
        return delay;
    }
}
