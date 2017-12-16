package advent.main;

import java.io.File;
import java.io.IOException;
import java.util.List;

import advent.util.FileInputReader;

public class Dec16Main {
    private static final String inputFile1 = "./resources/inputDay16/input1.txt";
    private static final String inputFile2 = "./resources/inputDay16/input2.txt";
    private static final String PROGRAMS1 = "abcde";
    private static final String PROGRAMS2 = "abcdefghijklmnop";

    public static void main(String[] args) {
        try {
            List<String> input1 = FileInputReader.readStringLineVertical(",", new File(inputFile1));
            List<String> input2 = FileInputReader.readStringLineVertical(",", new File(inputFile2));

            System.out.println(String.copyValueOf(calc1(input1, PROGRAMS1.toCharArray())));
            System.out.println(String.copyValueOf(calc1(input2, PROGRAMS2.toCharArray())));

            System.out.println(String.copyValueOf(calc2New(input1, PROGRAMS1.toCharArray())));
            System.out.println(String.copyValueOf(calc2New(input2, PROGRAMS2.toCharArray())));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static char[] calc1(List<String> input, char[] programs) {
        for (String s : input) {
            switch (s.charAt(0)) {
            case 's':
                programs = doSpin(programs, s);
                break;
            case 'x':
                programs = doExchange(programs, s);
                break;
            case 'p':
                programs = doParnter(programs, s);
                break;
            }
        }
        return programs;
    }

    private static char[] calc2New(List<String> input, char[] programs) {
        String orig = String.copyValueOf(programs);
        long circleCnt = 0L;
        while (true) {
            programs = calc1(input, programs);
            ++circleCnt;
            if (String.copyValueOf(programs).equals(orig)) {
                break;
            }
        }
        programs = orig.toCharArray();
        for (long i = 0; i < (1000000000L % circleCnt); ++i) {
            programs = calc1(input, programs);
        }
        return programs;
    }

    // this takes days
    @SuppressWarnings("unused")
    private static char[] calc2(List<String> input, char[] programs) {
        for (long i = 0; i < 1000000000L; ++i) {
            programs = calc1(input, programs);
        }
        return programs;
    }

    private static char[] doExchange(char[] programs, String input) {
        String[] values = input.substring(1).split("/");
        int lH = Integer.parseInt(values[0]);
        int rH = Integer.parseInt(values[1]);
        char temp = programs[lH];
        programs[lH] = programs[rH];
        programs[rH] = temp;
        return programs;
    }

    private static char[] doParnter(char[] programs, String input) {
        String[] values = input.substring(1).split("/");
        char lH = values[0].charAt(0);
        char rH = values[1].charAt(0);
        int posL = String.copyValueOf(programs).indexOf(lH);
        int posR = String.copyValueOf(programs).indexOf(rH);
        char temp = programs[posL];
        programs[posL] = programs[posR];
        programs[posR] = temp;
        return programs;
    }

    private static char[] doSpin(char[] programs, String input) {
        int spinSize = Integer.parseInt(input.substring(1));
        String res = String.copyValueOf(programs);
        String cut = res.substring(res.length() - spinSize, res.length());
        res = cut + res;
        return res.substring(0, programs.length).toCharArray();
    }
}
