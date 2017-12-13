package advent.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import advent.util.day7.Node;

public class FileInputReader {
    public static List<Integer> readIntegerLineHorizontal(String seperatorRegex, File f)
            throws FileNotFoundException, IOException {
        List<Integer> res = new ArrayList<Integer>();
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] entries = line.split(seperatorRegex);
                for (String s : entries) {
                    res.add(Integer.parseInt(s));
                }
            }
        }
        return res;
    }

    public static void readIntegerLineVertical(List<Integer> input, File f) throws FileNotFoundException, IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                input.add(Integer.parseInt(line));
            }
        }
    }

    public static List<String> readStringLineVertical(String seperator, File f)
            throws FileNotFoundException, IOException {
        List<String> res = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] seperated = line.split(seperator);
                for (String s : seperated) {
                    res.add(s);
                }
            }
        }
        return res;
    }

    public static List<String> readStringLineVertical(File f) throws FileNotFoundException, IOException {
        List<String> res = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                res.add(line);
            }
        }
        return res;
    }

    public static List<Node> readTree(File f) throws FileNotFoundException, IOException {
        List<Node> input = new ArrayList<Node>();
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] nodeSplit = line.split(" -> ");
                Node n = new Node(nodeSplit[0]);
                if (nodeSplit.length == 2) {
                    String[] nodes = nodeSplit[1].split(", ");
                    for (String node : nodes) {
                        Node shadow = new Node(node, -1);
                        n.addNode(shadow);
                    }
                }
                input.add(n);
            }
        }
        return input;
    }

    public static String readSingleLineAsString(File file) throws IOException {
        String res;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            res = br.readLine();
        }
        return res;
    }

    public static Map<Integer, List<Integer>> readMapLineByLine(String mapSeperator, String listSeperator, File file)
            throws FileNotFoundException, IOException {
        Map<Integer, List<Integer>> res = new HashMap<Integer, List<Integer>>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] mapString = line.split(mapSeperator);
                String[] listEntries = mapString[1].split(listSeperator);
                Integer key = Integer.parseInt(mapString[0].trim());
                res.put(key, new ArrayList<Integer>());
                for (String s : listEntries) {
                    res.get(key).add(Integer.parseInt(s.trim()));
                }
            }
        }
        return res;
    }
    public static List<Integer> readIntegerLineVerticalDay13(String seperator, File file) throws FileNotFoundException, IOException {
        List<Integer> res = new ArrayList<Integer>();
        int cnt = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] input = line.split(seperator);
                if (Integer.parseInt(input[0]) == cnt) {
                    res.add(Integer.parseInt(input[1]));
                } else {
                    while (Integer.parseInt(input[0]) > res.size()) {
                        res.add(0);
                        cnt++;
                    }
                    res.add(Integer.parseInt(input[1]));
                }
                cnt++;
            }
        }
        return res;
    }
}
