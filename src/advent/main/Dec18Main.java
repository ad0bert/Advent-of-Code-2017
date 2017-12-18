package advent.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
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

    private static List<Boolean> waitingList = new ArrayList<>();
    private static List<Long> sendCntList = new ArrayList<>();
    private static List<Queue<Long>> queueList = new ArrayList<>();

    private static final String INT_REGEX = "^[+-]?\\d+$";

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
            if ((values.get(line[1]) == null) && !line[1].matches(INT_REGEX)) {
                values.put(line[1], (long) pid);
            }
            val = line[line.length - 1].matches(INT_REGEX) ? Long.parseLong(line[line.length - 1])
                    : values.get(line[line.length - 1]);
            switch (Operator.getEnum(line[0])) {
            case ADD:
                values.put(line[1], values.get(line[1]) + val);
                break;
            case JGZ:
                long comperValue = line[1].matches(INT_REGEX) ? Long.parseLong(line[1]) : values.get(line[1]);
                pos += comperValue > 0 ? (val - 1) : 0;
                break;
            case MOD:
                values.put(line[1], values.get(line[1]) % val);
                break;
            case MUL:
                values.put(line[1], values.get(line[1]) * val);
                break;
            case RCV:
                Long reciveVal = null;
                waitingList.set(pid, queueList.get(other).isEmpty());
                do {
                    if (waitingList.get(pid) && waitingList.get(other)) {
                        return;
                    }
                    reciveVal = queueList.get(other).poll();
                } while (reciveVal == null);
                waitingList.set(pid, false);
                values.put(line[1], reciveVal);
                break;
            case SET:
                values.put(line[1], val);
                break;
            case SND:
                sendCntList.set(pid, sendCntList.get(pid) + 1);
                queueList.get(pid).add(val);
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
            val = line[line.length - 1].matches(INT_REGEX) ? Long.parseLong(line[line.length - 1])
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

    private static void init(int amount) {
        waitingList = new ArrayList<>();
        sendCntList = new ArrayList<>();
        queueList = new ArrayList<>();
        for (int i = 0; i < amount; ++i) {
            waitingList.add(false);
            sendCntList.add(0L);
            queueList.add(new LinkedBlockingQueue<>());
        }
    }

    private static void calc2(List<String> input) {
        init(2);
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                program(input, 0, 1);
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                program(input, 1, 0);
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

        System.out.println(sendCntList.get(0));
        System.out.println(sendCntList.get(1));
    }
}
