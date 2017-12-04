package advent.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Dec4Main {
    private static void init(List<List<String>> input, File f) throws FileNotFoundException, IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                List<String> tempLine = new ArrayList<String>();
                for (String s : line.split("\\s")) {
                    tempLine.add(s);
                }
                input.add(tempLine);
            }
        }

    }

    public static void main(String[] args) {
        List<List<String>> input1 = new ArrayList<List<String>>();
        List<List<String>> input2 = new ArrayList<List<String>>();
        try {
            init(input1, new File("input5.txt"));
            init(input2, new File("input6.txt"));
            System.out.println(cntValid1(input1));
            System.out.println(cntValid2(input2));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int cntValid1(List<List<String>> input) {
        int res = 0;
        boolean duplicate = false;
        for (List<String> list : input) {
            duplicate = false;
            Set<String> set = new HashSet<String>();
            for (String s : list) {
                if (!set.add(s)) {
                    duplicate = true;
                    break;
                }
            }
            if (!duplicate) {
                res++;
            }
        }

        return res;

    }

    private static int cntValid2(List<List<String>> input) {
        int res = 0;
        boolean duplicate = false;
        for (List<String> list : input) {
            duplicate = false;
            Set<String> set = new HashSet<String>();
            for (String s : list) {
                char[] charArray = s.toCharArray();
                Arrays.sort(charArray);
                String sortedString = new String(charArray);
                if (!set.add(sortedString)) {
                    duplicate = true;
                    break;
                }
            }
            if (!duplicate) {
                res++;
            }
        }

        return res;

    }
}
