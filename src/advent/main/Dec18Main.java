package advent.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import advent.util.FileInputReader;
import advent.util.day8.Operator;

public class Dec18Main {
    private static final String inputFile1 = "./resources/inputDay18/input1.txt";
    private static final String inputFile2 = "./resources/inputDay18/input2.txt";

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

    private static void calc1(List<String> input) {
        Map<String, Long> values = new HashMap<String, Long>();
        long mem = 0;
        int pos = 0;
        long val = 0;
        while (pos < input.size()) {
            String[] line = input.get(pos++).split("\\s");
            if (values.get(line[1]) == null) {
                values.put(line[1], 0L);
            }
            val = line[line.length - 1].matches("^[+-]?\\d+$") ? Long.parseLong(line[line.length - 1]) : values.get(line[line.length - 1]);
            switch (Operator.getEnum(line[0])) {
                case ADD:
                    values.put(line[1], values.get(line[1]) + val);
                    break;
                case JGZ:
                    pos += values.get(line[1]) > 0 ? (val-1) : 0;
                    break;
                case MOD:
                    values.put(line[1], values.get(line[1]) % val);
                    break;
                case MUL:
                    values.put(line[1], values.get(line[1]) * val);
                    break;
                case RCV:
                    if (val != 0) {
                        System.out.println(mem);
                        return;
                    }
                    break;
                case SET:
                    values.put(line[1], val);
                    break;
                case SND:
                    mem = val;
                    break;
                default:
                    break;
            }
        }
    }

    private static void calc2(List<String> input) {
    }
}
