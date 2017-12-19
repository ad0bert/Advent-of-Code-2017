package advent.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import advent.util.FileInputReader;
import advent.util.day19.Direction;

public class Dec19Main {
    private static final String inputFile1 = "./resources/inputDay19/input1.txt";
    private static final String inputFile2 = "./resources/inputDay19/input2.txt";

    private static final String ALPHABETH = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static void main(String[] args) {
        try {
            List<String> input1 = FileInputReader.readStringLineVertical(new File(inputFile1));
            List<String> input2 = FileInputReader.readStringLineVertical(new File(inputFile2));
            calc1(input1);
            calc1(input2);
            calc2(input1);
            calc2(input2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int findStartPos(String firstLine) {
        for (int i = 0; i < firstLine.length(); ++i) {
            if (firstLine.charAt(i) == '|') {
                return i;
            }
        }
        return -1;
    }

    private static Direction getNextDirection(Direction currDirection, int xPos, int yPos, List<String> labyirnth) {
        String charString = "";
        if (currDirection.equals(Direction.UP) || currDirection.equals(Direction.DOWN)) {
            if ((xPos + 1) < labyirnth.get(0).length()) {
                charString = String.valueOf(labyirnth.get(yPos).charAt(xPos+1));
                if (charString.equals("-") || ALPHABETH.contains(charString)) {
                    return Direction.RIGHT;
                }
            }
            if ((xPos - 1) >= 0) {
                charString = String.valueOf(labyirnth.get(yPos).charAt(xPos-1));
                if (charString.equals("-") || ALPHABETH.contains(charString)) {
                    return Direction.LEFT;
                }
            }
        } else {
            if ((yPos + 1) < labyirnth.size()) {
                charString = String.valueOf(labyirnth.get(yPos+1).charAt(xPos));
                if (charString.equals("|") || ALPHABETH.contains(charString)) {
                    return Direction.DOWN;
                }
            }
            if ((yPos - 1) >= 0) {
                charString = String.valueOf(labyirnth.get(yPos-1).charAt(xPos));
                if (charString.equals("|") || ALPHABETH.contains(charString)) {
                    return Direction.UP;
                }
            }
        }
        return null;
    }

    private static void calc1(List<String> input) {
        int xPos = findStartPos(input.get(0));
        String currChar = "|";
        int yPos = 0;
        int steps = 0;
        Direction currDirection = Direction.DOWN;
        do {
            switch (currDirection) {
                case UP:
                    yPos--;
                    break;
                case DOWN:
                    yPos++;
                    break;
                case LEFT:
                    xPos--;
                    break;
                case RIGHT:
                    xPos++;
                    break;
                default:
                    break;
            }
            currChar = String.valueOf(input.get(yPos).charAt(xPos));
            if (currChar.equals("+")) {
                currDirection = getNextDirection(currDirection, xPos, yPos, input);
            } else if (ALPHABETH.contains(currChar)) {
                System.out.print(currChar);
            }
            steps++;
        } while (!currChar.equals(" "));
        System.out.println();
        System.out.println(steps);
    }

    private static void calc2(List<String> input) {
    }
}
