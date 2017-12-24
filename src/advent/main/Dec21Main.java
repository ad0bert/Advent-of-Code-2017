package advent.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import advent.util.FileInputReader;

public class Dec21Main {

    private static final String inputFile1 = "./resources/inputDay21/input1.txt";
    private static final String inputFile2 = "./resources/inputDay21/input2.txt";

    public static void main(String[] args) {
        try {
            List<String> input1 = FileInputReader.readStringLineVertical(new File(inputFile1));
            List<String> input2 = FileInputReader.readStringLineVertical(new File(inputFile2));
            String picture = ".#./..#/###";
            calc1(picture, createRuleBook(input1), 2);
            calc1(picture, createRuleBook(input2), 5);
            calc1(picture, createRuleBook(input2), 18);
            calc2();
            calc2();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Map<String, String> createRuleBook(List<String> input) {
        Map<String, String> ruleBook = new HashMap<String, String>();
        for (String line : input) {
            String[] mapping = line.split("\\s=\\>\\s");
            ruleBook.put(mapping[0], mapping[1]);
        }
        return ruleBook;
    }

    private static void calc1(String picture, Map<String, String> ruleBook, int iterations) {
        for (int steps = 0; steps < iterations; ++steps) {
            int size = 0;

            int pictureLength = picture.split("/")[0].length();
            if (pictureLength % 2 == 0) {
                size = 2;
            } else {
                size = 3;
            }
            List<List<String>> pictureBuffer = new ArrayList<List<String>>();
            for (int i = 0; i < pictureLength; i += size) {
                List<String> lineBuffer = new ArrayList<String>();
                for (int j = 0; j < (pictureLength / size); ++j) {
                    String square = buildSquare(picture, i, j, size);
                    lineBuffer.add(expandSquare(square, ruleBook));
                }
                pictureBuffer.add(lineBuffer);
            }
            picture = buildNewPicture(pictureBuffer, size + 1);
            System.out.println(steps);
        }
        printPicture(picture);
        System.out.println(countPixels(picture, '#'));
    }

    private static void printPicture(String picture) {
        for (String line : picture.split("/")) {
            System.out.println(line);
        }
    }

    private static int countPixels(String picture, char pixelValue) {
        int res = 0;
        for (char c : picture.toCharArray()) {
            if (c == pixelValue) {
                res++;
            }
        }
        return res;
    }

    private static String buildNewPicture(List<List<String>> pictureBuffer, int size) {
        StringBuilder sb = new StringBuilder();
        for (List<String> line : pictureBuffer) {
            for (int i = 0; i < size; ++i) {
                for (String s : line) {
                    sb.append(s.split("/")[i]);
                }
                sb.append("/");
            }
        }
        return sb.toString().substring(0, sb.length() - 1);
    }

    private static String expandSquare(String square, Map<String, String> ruleBook) {
        boolean doFlip = true;
        String res = null;
        do {
            res = ruleBook.get(square);
            if (res != null) {
                return res;
            }
            if (doFlip) {
                square = flipSquare(square);
                doFlip = false;
            } else {
                square = flipSquare(square);
                square = rotateSquare(square);
                doFlip = true;
            }
        } while (true);
    }

    private static String flipSquare(String square) {
        String[] lines = square.split("/");

        for (int i = 0; i < lines.length; ++i) {
            char[] helper = lines[i].toCharArray();
            char temp = helper[0];
            helper[0] = helper[helper.length - 1];
            helper[helper.length - 1] = temp;
            lines[i] = String.valueOf(helper);
        }

        StringBuilder sb = new StringBuilder();
        for (String s : lines) {
            sb.append(s).append("/");
        }
        return sb.toString().substring(0, sb.length() - 1);
    }

    private static String rotateSquare(String square) {
        String[] lines = square.split("/");
        String[] rotationArray = new String[lines.length];
        for (int i = 0; i < lines.length; ++i) {
            String line = "";
            for (int j = 0; j < lines.length; ++j) {
                line = lines[j].charAt(i) + line;
            }
            rotationArray[i] = line;
        }
        StringBuilder sb = new StringBuilder();
        for (String s : rotationArray) {
            sb.append(s).append("/");
        }
        return sb.toString().substring(0, sb.length() - 1);
    }

    private static String buildSquare(String picture, int i, int j, int size) {
        String[] input = picture.split("/");
        StringBuilder sb = new StringBuilder();
        for (int pos = i; pos < i + size; ++pos) {
            sb.append(input[pos].substring(j * size, (j * size) + size)).append("/");
        }

        return sb.toString().substring(0, sb.length() - 1);
    }

    private static void calc2() {
    }
}
