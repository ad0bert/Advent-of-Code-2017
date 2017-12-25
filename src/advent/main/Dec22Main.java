package advent.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import advent.util.FileInputReader;
import advent.util.day19.Direction;

public class Dec22Main {

    private static final String inputFile1 = "./resources/inputDay22/input1.txt";
    private static final String inputFile2 = "./resources/inputDay22/input2.txt";

    private static final char INFECTED = '#';
    private static final char CLEAR = '.';

    public static void main(String[] args) {
        try {
            List<String> input1 = FileInputReader.readStringLineVertical(new File(inputFile1));
            List<String> input2 = FileInputReader.readStringLineVertical(new File(inputFile2));
            calc1(input1, 7);
            calc1(input1, 70);
            calc1(input1, 10000);
            calc1(input2, 10000);
            calc2(input1);
            calc2(input2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String createLine(int size) {
        String res = "";
        for (int i = 0; i < size; ++i) {
            res += CLEAR;
        }
        return res;
    }

    private static void calc1(List<String> input, int bursts) {
        int infectionBursts = 0;
        int posX = input.size() / 2;
        int posY = input.size() / 2;
        Direction currDirection = Direction.UP;

        for (int i = 0; i < bursts; ++i) {
            if (posY >= input.size() || posX >= input.size()) {
                input.add(createLine(input.size()));
                for (int j = 0; j < input.size(); ++j) {
                    input.set(j, input.get(j) + CLEAR);
                }
            }
            if (posY < 0 || posX < 0) {
                input.add(0, createLine(input.size()));
                for (int j = 0; j < input.size(); ++j) {
                    input.set(j, CLEAR + input.get(j));
                }
                posX++;
                posY++;
            }
            char[] line = input.get(posY).toCharArray();
            if (line[posX] == INFECTED) {
                currDirection = nextDirection(currDirection, false);
                line[posX] = CLEAR;
            } else {
                currDirection = nextDirection(currDirection, true);
                infectionBursts++;
                line[posX] = INFECTED;
            }
            input.set(posY, String.copyValueOf(line));
            switch (currDirection) {
            case UP:
                posY--;
                break;
            case DOWN:
                posY++;
                break;
            case LEFT:
                posX--;
                break;
            case RIGHT:
                posX++;
                break;
            }
        }
        printInput(input);
        System.out.println(infectionBursts);
    }

    private static void printInput(List<String> input) {
        for (String line : input) {
            System.out.println(line);
        }
        System.out.println();
    }

    private static Direction nextDirection(Direction currDirection, boolean clear) {
        switch (currDirection) {
        case UP:
            return clear ? Direction.LEFT : Direction.RIGHT;
        case DOWN:
            return clear ? Direction.RIGHT : Direction.LEFT;
        case LEFT:
            return clear ? Direction.DOWN : Direction.UP;
        case RIGHT:
            return clear ? Direction.UP : Direction.DOWN;
        }
        return null;
    }

    private static void calc2(List<String> input) {
    }
}
