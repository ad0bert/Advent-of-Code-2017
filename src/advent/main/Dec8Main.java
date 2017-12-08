package advent.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import advent.util.FileInputReader;
import advent.util.day8.AssemblyCalc;

public class Dec8Main {

    private static final String inputFile1 = "./resources/inputDay8/input1.txt";
    private static final String inputFile2 = "./resources/inputDay8/input2.txt";

    public static void main(String[] args) {
        try {
            List<String> input1 = FileInputReader.readStringLineVertical(new File(inputFile1));
            List<String> input2 = FileInputReader.readStringLineVertical(new File(inputFile2));
            runCalc(input1);
            runCalc(input2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void runCalc(List<String> input) {
        Map<String, Integer> stack = new HashMap<String, Integer>();
        List<AssemblyCalc> program = new ArrayList<AssemblyCalc>();
        for (String line : input) {
            AssemblyCalc ac = new AssemblyCalc(line);
            program.add(ac);
            stack.put(ac.getVarName(), 0);
        }

        int maxValueEver = Integer.MIN_VALUE;
        for (AssemblyCalc ac : program) {
            ac.executeCalculation(stack);
            int val = maxValueStack(stack);
            if (val > maxValueEver) {
                maxValueEver = val;
            }
        }

        int maxValue = Integer.MIN_VALUE;
        for (Entry<String, Integer> entry : stack.entrySet()) {
            if (entry.getValue() > maxValue) {
                maxValue = entry.getValue();
            }
        }

        System.out.println("Max value after  calculation: " + maxValue);
        System.out.println("Max value during calculation: " + maxValueEver);
    }

    private static int maxValueStack(Map<String, Integer> stack) {
        int maxValue = Integer.MIN_VALUE;
        for (Entry<String, Integer> entry : stack.entrySet()) {
            if (entry.getValue() > maxValue) {
                maxValue = entry.getValue();
            }
        }
        return maxValue;
    }

}
