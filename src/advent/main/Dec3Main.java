package advent.main;

public class Dec3Main {

    private static Integer calc(Integer size) {
        Integer res = 0;
        double root = Math.sqrt(size);
        Integer dimension = (root - (int) root) == 0 ? (int) root : (int) root + 1;
        if ((dimension % 2) == 0) {
            dimension++;
        }
        Integer[][] field = new Integer[dimension][dimension];

        System.out.println("Field dimension: " + dimension);

        Integer startPosX = dimension / 2;
        Integer startPosY = dimension / 2;
        Integer endPosX = dimension - 1;
        Integer endPosY = dimension - 1;

        field[startPosX][startPosY] = 1;
        field[endPosX][endPosY] = (int) Math.pow(dimension, 2);

        Integer dist = field[endPosX][endPosY] - size;

        boolean isForward = false;

        while (dist != 0) {
            if (endPosX.equals(endPosY)) {
                if (((dimension - 1) - dist) < 0) {
                    dist -= dimension - 1;
                    endPosX = isForward ? dimension - 1 : 0;
                } else {
                    endPosX = isForward ? endPosX + dist : endPosX - dist;
                    dist = 0;
                }
            } else {
                if (((dimension - 1) - dist) < 0) {
                    dist -= dimension - 1;
                    endPosY = isForward ? dimension - 1 : 0;
                    isForward = true;
                } else {
                    endPosY = isForward ? endPosY + dist : endPosY - dist;
                    dist = 0;
                }
            }

        }

        System.out.println("StartPos: (" + startPosX + "," + startPosY + ")");
        System.out.println("EndPos: (" + endPosX + "," + endPosY + ")");

        field[dimension - 1][dimension - 1] = null;

        Integer currX = startPosX;
        Integer currY = startPosY;
        Integer currStep = 1;
        Integer steps = 1;
        Integer doneSteps = 0;
        isForward = true;
        int j = currY;
        for (int i = currX + 1; i < (dimension - 1); i = isForward ? i + 1 : i - 1) {
            field[i][j] = checkValuesArround(field, i, j);
            currStep++;
            doneSteps++;
            if (field[i][j] > size) {
                System.out.println("Res Part Two: " + field[i][j]);
                System.exit(0);
            }
            if (steps.equals(doneSteps)) {
                doneSteps = 0;
                isForward = !isForward;
                j = isForward ? j + 1 : j - 1;
                for (; j < (dimension - 1); j = isForward ? j + 1 : j - 1) {
                    field[i][j] = checkValuesArround(field, i, j);
                    currStep++;
                    doneSteps++;
                    if (field[i][j] > size) {
                        System.out.println("Res Part Two: " + field[i][j]);
                        System.exit(0);
                    }
                    if (steps.equals(doneSteps)) {
                        doneSteps = 0;
                        steps++;
                        break;
                    }
                }
            }
        }

        System.out.println("Res Part Two: " + currStep);

        res = Math.abs((startPosX - endPosX)) + Math.abs((startPosY - endPosY));

        return res;
    }

    private static Integer checkValuesArround(Integer[][] field, int i, int j) {
        Integer dimension = field.length;
        Integer res = 0;
        if (i < (dimension - 1)) {
            if (field[i + 1][j] != null) {
                res += field[i + 1][j];
            }
        }

        if (i > 0) {
            if (field[i - 1][j] != null) {
                res += field[i - 1][j];
            }
        }

        if (j < (dimension - 1)) {
            if (field[i][j + 1] != null) {
                res += field[i][j + 1];
            }
        }

        if (j > 0) {
            if (field[i][j - 1] != null) {
                res += field[i][j - 1];
            }
        }

        if ((j < (dimension - 1)) && (i < (dimension - 1))) {
            if (field[i + 1][j + 1] != null) {
                res += field[i + 1][j + 1];
            }

        }

        if ((j > 0) && (i > 0)) {
            if (field[i - 1][j - 1] != null) {
                res += field[i - 1][j - 1];
            }
        }

        if ((j > 0) && (i < (dimension - 1))) {
            if (field[i + 1][j - 1] != null) {
                res += field[i + 1][j - 1];
            }
        }

        if ((i > 0) && (j < (dimension - 1))) {
            if (field[i - 1][j + 1] != null) {
                res += field[i - 1][j + 1];
            }
        }

        return res;
    }

    public static void main(String[] args) {
        Integer size1 = 1;
        Integer size2 = 12;
        Integer size3 = 23;
        Integer size4 = 1024;
        Integer size5 = 289326;

        // System.out.println("Res for " + size1 + ": " + calc(size1) + "\n");
        // System.out.println("Res for " + size2 + ": " + calc(size2) + "\n");
        // System.out.println("Res for " + size3 + ": " + calc(size3) + "\n");
        // System.out.println("Res for " + size4 + ": " + calc(size4) + "\n");
        System.out.println("Res for " + size5 + ": " + calc(size5) + "\n");
    }
}
