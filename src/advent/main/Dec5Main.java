package advent.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Dec5Main {
    private static void init(List<Integer> input, File f) throws FileNotFoundException, IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                input.add(Integer.parseInt(line));
            }
        }
    }

    public static void main(String[] args) {
        List<Integer> input1 = new ArrayList<Integer>();
        List<Integer> input2 = new ArrayList<Integer>();
        try {
            init(input1, new File("./resources/inputDay5/input1.txt"));
            init(input2, new File("./resources/inputDay5/input2.txt"));
            System.out.println(cntValid1(input1));
            System.out.println(cntValid2(input2));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int cntValid1(List<Integer> input) {
        int steps = 0;
        for (int i = 0; i < input.size();) {
            int temp = input.get(i);
            input.set(i, temp + 1);
            i += temp;
            steps++;
        }
        return steps;

    }

    private static int cntValid2(List<Integer> input) {
        int steps = 0;
        for (int i = 0; i < input.size();) {
            int temp = input.get(i);
            if (temp >= 3) {
                input.set(i, temp - 1);
            } else {
                input.set(i, temp + 1);
            }
            i += temp;
            steps++;
        }
        return steps;
    }
}
