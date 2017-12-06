package advent.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Dec6Main {
    private static void init(List<Integer> input, File f) throws FileNotFoundException, IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] entries = line.split("\\s+");
                for (String s : entries) {
                    input.add(Integer.parseInt(s));
                }
            }
        }
    }

    public static void main(String[] args) {
        List<Integer> input1 = new ArrayList<Integer>();
        List<Integer> input2 = new ArrayList<Integer>();
        try {
            init(input1, new File("input9.txt"));
            init(input2, new File("input10.txt"));
            System.out.println(redestribute1(input1));
            System.out.println(redestribute2(input2));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int findIndexBiggest(List<Integer> input) {
        int max = Integer.MIN_VALUE;
        int pos = 0;
        for (int i = 0; i < input.size(); ++i) {
            if (input.get(i) > max) {
                max = input.get(i);
                pos = i;
            }
        }
        return pos;
    }

    private static int redestribute1(List<Integer> input) {
        int steps = 0;
        List<String> backup = new ArrayList<String>();
        do {
            int pos = findIndexBiggest(input);
            int redestributeValue = input.get(pos);
            backup.add(input.toString());
            input.set(pos, 0);
            pos++;
            while (redestributeValue > 0) {
                int currPos = pos % input.size();
                input.set(currPos, input.get(currPos) + 1);
                --redestributeValue;
                pos++;
            }
            steps++;
        } while (!backup.contains(input.toString()));
        return steps;
    }

    private static int redestribute2(List<Integer> input) {
        List<String> backup = new ArrayList<String>();
        do {
            int pos = findIndexBiggest(input);
            int redestributeValue = input.get(pos);
            backup.add(input.toString());
            input.set(pos, 0);
            pos++;
            while (redestributeValue > 0) {
                int currPos = pos % input.size();
                input.set(currPos, input.get(currPos) + 1);
                --redestributeValue;
                pos++;
            }
        } while (!backup.contains(input.toString()));
        return backup.size() - backup.indexOf(input.toString());
    }
}
