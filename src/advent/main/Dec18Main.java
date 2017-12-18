package advent.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import advent.util.FileInputReader;
import advent.util.day8.Operator;

public class Dec18Main {
    private static final String inputFile1 = "./resources/inputDay18/input1.txt";
    private static final String inputFile2 = "./resources/inputDay18/input2.txt";

    private static boolean isWaiting1 = false;
    private static boolean isWaiting2 = false;

    private static long sendCnt1 = 0L;
    private static long sendCnt2 = 0L;

    private static Queue<Long> queue1;
    private static Queue<Long> queue2;

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

    private static void program(List<String> input, int pid, int other) {
        Map<String, Long> values = new HashMap<String, Long>();
        int pos = 0;
        long val = 0;
        while (pos < input.size()) {
            String[] line = input.get(pos++).split("\\s");
            if ((values.get(line[1]) == null) && !line[1].matches("^[+-]?\\d+$")) {
                values.put(line[1], pid - 1L);
            }
            val = line[line.length - 1].matches("^[+-]?\\d+$") ? Long.parseLong(line[line.length - 1])
                    : values.get(line[line.length - 1]);
            switch (Operator.getEnum(line[0])) {
            case ADD:
                values.put(line[1], values.get(line[1]) + val);
                break;
            case JGZ:
                long fuckU = line[1].matches("^[+-]?\\d+$") ? Long.parseLong(line[1]) : values.get(line[1]);
                pos += fuckU > 0 ? (val - 1) : 0;
                break;
            case MOD:
                values.put(line[1], values.get(line[1]) % val);
                break;
            case MUL:
                values.put(line[1], values.get(line[1]) * val);
                break;
            case RCV:
                Long reciveVal = null;
                if (pid == 1) {
                    isWaiting1 = queue2.isEmpty();
                    do {
                        if (isWaiting1 && isWaiting2) {
                            return;
                        }
                        reciveVal = queue2.poll();
                    } while (reciveVal == null);
                    isWaiting1 = false;
                    values.put(line[1], reciveVal);
                } else {
                    isWaiting2 = queue1.isEmpty();
                    do {
                        if (isWaiting1 && isWaiting2) {
                            return;
                        }
                        reciveVal = queue1.poll();
                    } while (reciveVal == null);
                    isWaiting2 = false;
                    values.put(line[1], reciveVal);
                }
                break;
            case SET:
                values.put(line[1], val);
                break;
            case SND:
                if (pid == 1) {
                    sendCnt1++;
                    queue1.add(val);
                } else if (pid == 2) {
                    sendCnt2++;
                    queue2.add(val);
                }
                break;
            default:
                break;
            }
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
            val = line[line.length - 1].matches("^[+-]?\\d+$") ? Long.parseLong(line[line.length - 1])
                    : values.get(line[line.length - 1]);
            switch (Operator.getEnum(line[0])) {
            case ADD:
                values.put(line[1], values.get(line[1]) + val);
                break;
            case JGZ:
                pos += values.get(line[1]) > 0 ? (val - 1) : 0;
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

    private static void init() {
        isWaiting1 = false;
        isWaiting2 = false;

        sendCnt1 = 0L;
        sendCnt2 = 0L;

        queue1 = new LinkedBlockingQueue<>();
        queue2 = new LinkedBlockingQueue<>();

    }

    private static void calc2(List<String> input) {
        init();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                program(input, 1, 2);
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                program(input, 2, 1);
            }
        });
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(sendCnt1);
        System.out.println(sendCnt2);
    }
}
