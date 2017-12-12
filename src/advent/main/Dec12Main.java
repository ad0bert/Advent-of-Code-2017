package advent.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import advent.util.FileInputReader;

public class Dec12Main {
    private static final String inputFile1 = "./resources/inputDay12/input1.txt";
    private static final String inputFile2 = "./resources/inputDay12/input2.txt";

    public static void main(String[] args) {
        try {
            Map<Integer, List<Integer>> input1 = FileInputReader.readMapLineByLine("\\<-\\>", ",",
                    new File(inputFile1));
            Map<Integer, List<Integer>> input2 = FileInputReader.readMapLineByLine("\\<-\\>", ",",
                    new File(inputFile2));
            System.out.println("Res Part 1: " + calcPart1(input1, 0).size());
            System.out.println("Res Part 1: " + calcPart1(input2, 0).size());
            // ---------------
            System.out.println("Res Part 2: " + calcPart2(input1));
            System.out.println("Res Part 2: " + calcPart2(input2));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Set<Integer> calcPart1(Map<Integer, List<Integer>> input, Integer val) {
        Set<Integer> res = new TreeSet<Integer>();
        Set<Integer> visited = new TreeSet<Integer>();
        res.add(val);
        while (visited.size() != res.size()) {
            for (Integer toVisit : res) {
                if (visited.contains(toVisit)) {
                    continue;
                }
                res.addAll(input.get(toVisit));
                visited.add(toVisit);
                break;
            }
        }
        return res;
    }

    private static int calcPart2(Map<Integer, List<Integer>> input) {
        int groupCnt = 0;
        int inputSize = input.size();
        for (int i = 0; i < inputSize; ++i) {
            if (input.get(i) != null) {
                Set<Integer> group = calcPart1(input, i);
                for (Integer toRemove : group) {
                    input.remove(toRemove);
                }
                groupCnt++;
            }
        }
        return groupCnt;
    }

}
