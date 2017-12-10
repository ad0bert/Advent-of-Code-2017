package advent.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import advent.util.FileInputReader;

public class Dec10Main {
    // part 1
    private static final String inputFile1 = "./resources/inputDay10/input1.txt";
    private static final String inputFile2 = "./resources/inputDay10/input2.txt";
    // part 2
    private static final String inputFile3 = "./resources/inputDay10/input3.txt";
    private static final String inputFile4 = "./resources/inputDay10/input4.txt";
    private static final String inputFile5 = "./resources/inputDay10/input5.txt";
    private static final String inputFile6 = "./resources/inputDay10/input6.txt";
    private static final String inputFile7 = "./resources/inputDay10/input2.txt"; // result

    private static Integer skipSize;
    private static Integer currPos;

    private static void init() {
        skipSize = 0;
        currPos = 0;
    }

    public static void main(String[] args) {
        try {
            List<Integer> input1Part1 = FileInputReader.readIntegerLineHorizontal(",", new File(inputFile1));
            List<Integer> input2Part1 = FileInputReader.readIntegerLineHorizontal(",", new File(inputFile2));
            System.out.println(runCalcPart1(input1Part1, createAndInitIntArray(5), true));
            System.out.println(runCalcPart1(input2Part1, createAndInitIntArray(256), true));

            String input1Part2 = FileInputReader.readSingleLineAsString(new File(inputFile3));
            String input2Part2 = FileInputReader.readSingleLineAsString(new File(inputFile4));
            String input3Part2 = FileInputReader.readSingleLineAsString(new File(inputFile5));
            String input4Part2 = FileInputReader.readSingleLineAsString(new File(inputFile6));
            String input5Part2 = FileInputReader.readSingleLineAsString(new File(inputFile7));

            System.out.println(runCalcPart2(input1Part2));
            System.out.println(runCalcPart2(input2Part2));
            System.out.println(runCalcPart2(input3Part2));
            System.out.println(runCalcPart2(input4Part2));
            System.out.println(runCalcPart2(input5Part2));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Integer> createASCIIList(String input) {
        List<Integer> array = new ArrayList<Integer>();
        if ((input != null) && !input.equals("")) {
            for (char c : input.toCharArray()) {
                array.add((int) c);
            }
        }
        array.add(17);
        array.add(31);
        array.add(73);
        array.add(47);
        array.add(23);
        return array;
    }

    private static List<Integer> createHash(int[] arr) {
        List<Integer> res = new ArrayList<Integer>();
        int hash = 0;
        for (int i = 0; i < arr.length; ++i) {
            hash ^= arr[i];
            if ((i % 16) == 15) {
                res.add(hash);
                hash = 0;
            }
        }
        return res;
    }

    private static String runCalcPart2(String input) {
        int[] arr = createAndInitIntArray(256);
        List<Integer> inputList = createASCIIList(input);
        init();
        for (int i = 0; i < 64; ++i) {
            runCalcPart1(inputList, arr, false);
        }

        StringBuffer sb = new StringBuffer();
        for (Integer i : createHash(arr)) {
            sb.append(String.format("%02x", i));
        }
        return (sb.toString());
    }

    private static int[] createAndInitIntArray(int size) {
        int[] intArray = new int[size];
        for (int i = 0; i < size; ++i) {
            intArray[i] = i;
        }
        return intArray;
    }

    private static String runCalcPart1(List<Integer> input, int[] intArray, boolean doInit) {
        if (doInit) {
            init();
        }
        for (Integer pos : input) {
            reverseSubList(intArray, currPos, pos - 1);
            currPos += (skipSize + pos);
            skipSize++;
        }
        return ("Result Part 1: " + (intArray[0] * intArray[1]));
    }

    private static void reverseSubList(int[] intArray, int currPos, int subListLength) {
        int endPos = currPos + subListLength;
        int startPos = currPos;

        for (int i = subListLength; i > -1; i -= 2) {
            int temp = intArray[startPos % intArray.length];
            intArray[startPos % intArray.length] = intArray[endPos % intArray.length];
            intArray[endPos % intArray.length] = temp;
            startPos++;
            endPos--;
        }
    }

}
