package advent.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dec2Main {
    private static void init(List<List<Integer>> input, File f) throws FileNotFoundException, IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                List<Integer> tempLine = new ArrayList<Integer>();
                for (String s : line.split("\\s")) {
                    tempLine.add(Integer.parseInt(s));
                }
                Collections.sort(tempLine);
                input.add(tempLine);
            }
        }

    }

    private static int solve(List<List<Integer>> input) {
        int res = 0;
        for (List<Integer> row : input) {
            res += Math.abs(row.get(0) - row.get(row.size() - 1));
        }
        return res;
    }

    private static int solveDiv(List<List<Integer>> input) {
        int res = 0;
        for (List<Integer> row : input) {
            for (int i = 0; i < row.size(); ++i) {
                for (int j = i + 1; j < row.size(); ++j) {
                    if ((row.get(j) % row.get(i)) == 0) {
                        res += row.get(j) / row.get(i);
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        List<List<Integer>> input = new ArrayList<List<Integer>>();
        try {
            init(input, new File("input3.txt"));
            System.out.println(solve(input));
            System.out.println(solveDiv(input));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
