package advent.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import advent.util.FileInputReader;

public class Dec9Main {

    private static final String inputFile1 = "./resources/inputDay9/input1.txt";
    private static final String inputFile2 = "./resources/inputDay9/input2.txt";

    public static void main(String[] args) {
        try {
            String input1 = FileInputReader.readSingleLineAsString(new File(inputFile1));
            String input2 = FileInputReader.readSingleLineAsString(new File(inputFile2));
            runCalc(input1);
            runCalc(input2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void runCalc(String input) {
        String noEscapeChars = removeAllEscapeChars(input, '!');
        String noGarbage = reomveGarbage(noEscapeChars, '<', '>');
        int groupCnt = countGroups(noGarbage, '{', '}', ',');
        int garbageCnt = countGarbageChars(noEscapeChars, '<', '>');
        System.out.println(input);
        System.out.println(noEscapeChars);
        System.out.println(noGarbage);
        System.out.println(groupCnt);
        System.out.println(garbageCnt);
    }

    private static int countGarbageChars(String input, char left, char right) {
        String tempString = input;
        int res = 0;
        do {
            int leftIdx = tempString.indexOf(left) + 2;
            int rightIdx = tempString.indexOf(right) + 1;
            res += (rightIdx - leftIdx);
            tempString = tempString.substring(rightIdx);
        } while (tempString.indexOf(left) != -1);
        return res;
    }

    private static int countGroups(String input, char left, char right, char seperator) {
        int cnt = 0;
        int curVal = 0;
        char[] inputField = input.toCharArray();
        for (char element : inputField) {
            if (element == left) {
                curVal++;
                cnt += curVal;
            } else if (element == right) {
                curVal--;
            } else if (element == seperator) {

            } // ignore rest
        }
        return cnt;
    }

    private static String reomveGarbage(String input, char left, char right) {
        return input.replaceAll("\\" + left + ".*?" + "\\" + right, "");
    }

    private static String removeAllEscapeChars(String input, char escapeChar) {
        return input.replaceAll(escapeChar + "{1}.", "");
    }

}
