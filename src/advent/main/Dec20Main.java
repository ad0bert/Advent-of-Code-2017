package advent.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

import advent.util.FileInputReader;
import advent.util.day20.Point;

public class Dec20Main {
    private static final String inputFile1 = "./resources/inputDay20/input1.txt";
    private static final String inputFile2 = "./resources/inputDay20/input2.txt";
    private static final String inputFile3 = "./resources/inputDay20/input3.txt";

    public static void main(String[] args) {
        try {
            List<String> input1 = FileInputReader.readStringLineVertical(new File(inputFile1));
            List<String> input2 = FileInputReader.readStringLineVertical(new File(inputFile2));
            List<String> input3 = FileInputReader.readStringLineVertical(new File(inputFile3));
            calc1(input1);
            calc1(input2);
            calc2(input3, 3);
            calc2(input2, 39);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void calc1(List<String> input) {
        List<Point> pointCloud = createPointCloud(input);
        int minValue = Integer.MAX_VALUE;
        int minPos = 0;
        for (int i = 0; i < pointCloud.size(); ++i) {
            if (pointCloud.get(i).sumA() < minValue) {
                minValue = pointCloud.get(i).sumA();
                minPos = i;
            }
        }
        System.out.println(minValue + " - " + minPos);
    }

    private static List<Point> createPointCloud(List<String> input) {
        List<Point> pointList = new ArrayList<Point>();
        for (String point : input) {
            pointList.add(new Point(point));
        }
        return pointList;
    }

    private static void calc2(List<String> input, int simulationTicks) {
        List<Point> pointCloud = createPointCloud(input);
        do {
            List<Point> removeList = new ArrayList<Point>();
            for (Point curr : pointCloud) {
                if (Collections.frequency(pointCloud, curr) > 1) {
                    removeList.add(curr);
                }
            }
            pointCloud.removeAll(removeList);
            pointCloud.forEach(new Consumer<Point>() {
                @Override
                public void accept(Point point) {
                    point.update();
                }
            });
            } while (simulationTicks-- > 0);
        System.out.println(pointCloud.size());
    }
}
