package advent.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import advent.util.FileInputReader;

public class Dec11Main {
    private static final String inputFile1 = "./resources/inputDay11/input1.txt";
    private static final String inputFile2 = "./resources/inputDay11/input2.txt";

    public static void main(String[] args) {
        try {
            List<String> input1 = FileInputReader.readStringLineHorizontal(",", new File(inputFile1));
            List<String> input2 = FileInputReader.readStringLineHorizontal(",", new File(inputFile2));
            calcPart1(input1);
            calcPart1(input2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // n=x-1, s=x+1, e=y+1, w=y-1;
    private static void calcPart1(List<String> input) {
        int xPos = 0;
        int yPos = 0;
        int max = Integer.MIN_VALUE;
        for (String s : input) {
            if (s.equals("n")) {
                yPos--;
            } else if (s.equals("ne")) {
                xPos++;
                yPos--;
            } else if (s.equals("se")) {
                xPos++;
            } else if (s.equals("s")) {
                yPos++;
            } else if (s.equals("sw")) {
                xPos--;
                yPos++;
            } else if (s.equals("nw")) {
                xPos--;
            }
            if ((Math.abs(xPos) + Math.abs(yPos)) > max) {
                max = Math.abs(xPos) + Math.abs(yPos);
            }
        }
        // if one is negative and the other positive subtract 1
        System.out.println("Res Part 1: " + (Math.abs(xPos) + Math.abs(yPos)));
        System.out.println("Res Part 2: " + max);
    }

}
