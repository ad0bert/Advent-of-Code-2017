package advent.util.day7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Node {
    private final String name;
    private final Integer weight;
    private Integer sumWeight;
    private List<Node> nodes = null;

    public Node(String name, Integer weight) {
        this.name = name;
        this.weight = weight;
    }

    public Node(String input) {
        String[] values = input.split("\\s\\(");
        this.name = values[0];
        this.weight = Integer.parseInt(values[1].substring(0, values[1].length() - 1));
    }

    public String getName() {
        return this.name;
    }

    public Integer getWeight() {
        return this.weight;
    }

    public void addNode(Node node) {
        if (this.nodes == null) {
            this.nodes = new ArrayList<Node>();
        }
        this.nodes.add(node);
    }

    public List<Node> getNodes() {
        return this.nodes;
    }

    public boolean fillNode(Node node) {
        if (nodes == null) {
            return false;
        }
        for (int i = 0; i < nodes.size(); ++i) {
            if (nodes.get(i).name.equals(node.name)) {
                if (nodes.get(i).weight.equals(-1)) {
                    nodes.set(i, node);
                    return true;
                }
            }
        }
        for (int i = 0; i < nodes.size(); ++i) {
            if (nodes.get(i).fillNode(node)) {
                return true;
            }
        }
        return false;
    }

    public boolean isBalanced() {
        if ((nodes == null) || (nodes.size() == 1)) {
            return true;
        }
        for (int i = 0; i < (nodes.size() - 1); ++i) {
            if (!nodes.get(i).sumWeight.equals(nodes.get(i + 1).sumWeight)) {
                return false;
            }
        }
        return true;
    }

    public int getIndexUnbalanced() {
        Collections.sort(nodes, new Comparator<Node>() {
            @Override
            public int compare(Node n1, Node n2) {
                return new Integer(n1.getSumWeight()).compareTo(n2.getSumWeight());
            }
        });
        if (nodes.get(nodes.size() - 1).getSumWeight() != nodes.get(0).getSumWeight()) {
            return nodes.size() - 1;
        }
        return -1;
    }

    public int calcUnbalancedValue() {
        this.calcWeight();
        int idx = getIndexUnbalanced();
        int balanceValue = nodes.get((getIndexUnbalanced() + 1) % nodes.size()).getSumWeight();
        Node curr = nodes.get(idx);
        idx = curr.getIndexUnbalanced();
        if (idx != -1) {
            do {
                balanceValue = curr.getNodes().get((idx + 1) % curr.getNodes().size()).getSumWeight();
                curr = curr.getNodes().get(idx);
                idx = curr.getIndexUnbalanced();
            } while (idx != -1);
        }
        return curr.getWeight() - (curr.getSumWeight() - balanceValue);
    }

    public int getSumWeight() {
        return this.sumWeight;
    }

    public int calcWeight() {
        sumWeight = this.weight;
        if (nodes == null) {
            return sumWeight;
        }
        for (Node n : nodes) {
            sumWeight += n.calcWeight();
        }
        return sumWeight;
    }
}
