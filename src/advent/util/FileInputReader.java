package advent.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import advent.util.day7.Node;

public class FileInputReader {
    public static void readIntegerLineHorizontal(List<Integer> input, File f)
            throws FileNotFoundException, IOException {
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

    public static void readIntegerLineVertical(List<Integer> input, File f) throws FileNotFoundException, IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                input.add(Integer.parseInt(line));
            }
        }
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
}
