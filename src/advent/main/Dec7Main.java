package advent.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import advent.util.FileInputReader;
import advent.util.day7.Node;

public class Dec7Main {
    public static void main(String[] args) {
        try {
            List<Node> treeList1 = FileInputReader.readTree(new File("./resources/inputDay7/input1.txt"));
            List<Node> treeList2 = FileInputReader.readTree(new File("./resources/inputDay7/input2.txt"));
            Node tree1 = buildTree(treeList1);
            Node tree2 = buildTree(treeList2);
            System.out.println(tree1.getName());
            System.out.println(tree2.getName());
            System.out.println();
            System.out.println(balanceTree(tree1));
            System.out.println(balanceTree(tree2));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Node buildTree(List<Node> treeList) {
        do {
            for (int i = 0; i < treeList.size(); ++i) {
                for (int j = 0; j < treeList.size(); ++j) {
                    if (treeList.get(j) != null) {
                        if (treeList.get(j).fillNode(treeList.get(i))) {
                            treeList.set(i, null);
                            break;
                        }
                    }
                }
            }
            treeList.removeAll(Collections.singleton(null));
        } while (treeList.size() > 1);
        return treeList.get(0);
    }

    private static int balanceTree(Node tree) {
        return tree.calcUnbalancedValue();
    }
}
