package advent.main;

import java.util.ArrayList;
import java.util.List;

public class Dec14Main {
    private static final int GRID_SIZE = 128;

    public static void main(String[] args) {
        String input1 = "flqrgnkx";
        String input2 = "amgozmfv";
        calc1(input1);
        calc1(input2);
        calc2(input1);
        calc2(input2);
    }

    private static void calc2(String input) {
        List<List<Integer>> grid = new ArrayList<List<Integer>>();
        for (int i = 0; i < GRID_SIZE; ++i) {
            char[] res = Dec10Main.runCalcPart2(input + "-" + i).toCharArray();
            List<Integer> line = new ArrayList<Integer>();
            for (char c : res) {
                for (char val : hexToBinary(c).toCharArray()) {
                    line.add(val == '1' ? -1 : 0);
                }
            }
            grid.add(line);
        }
        System.out.println("Result Part 2: " + cntCluster(grid));
    }

    private static void markAround(List<List<Integer>> grid, int posX, int posY, int markValue) {
        if ((posX > (GRID_SIZE - 1)) || (posX < 0) || (posY > (GRID_SIZE - 1)) || (posY < 0)) {
            return;
        }
        if ((grid.get(posX).get(posY) != -1) || (grid.get(posX).get(posY) == 0)) {
            return;
        }
        grid.get(posX).set(posY, markValue);
        markAround(grid, posX, posY - 1, markValue);
        markAround(grid, posX + 1, posY, markValue);
        markAround(grid, posX, posY + 1, markValue);
        markAround(grid, posX - 1, posY, markValue);
    }

    private static int cntCluster(List<List<Integer>> grid) {
        int clusterCnt = 0;
        for (int i = 0; i < GRID_SIZE; ++i) {
            for (int j = 0; j < GRID_SIZE; ++j) {
                markAround(grid, i, j, clusterCnt + 1);
                if ((grid.get(i).get(j) == (clusterCnt + 1))) {
                    clusterCnt++;
                }
            }
        }
        return clusterCnt;
    }

    private static String hexToBinary(char hex) {
        int i = Integer.parseInt(Character.toString(hex), 16);
        return String.format("%04d", Integer.parseInt(Integer.toBinaryString(i)));
    }

    private static void calc1(String input) {
        int cnt = 0;
        for (int i = 0; i < 128; ++i) {
            char[] res = Dec10Main.runCalcPart2(input + "-" + i).toCharArray();
            String binary = "";
            for (char c : res) {
                binary += hexToBinary(c);
            }
            cnt += cntUsed(binary);
        }
        System.out.println("Result Part 1: " + cnt);
    }

    private static int cntUsed(String binary) {
        int res = 0;
        for (char c : binary.toCharArray()) {
            res += c == '1' ? 1 : 0;
        }
        return res;
    }
}
