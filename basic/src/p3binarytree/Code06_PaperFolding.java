package p3binarytree;

/**
 * @author liyaguang11
 * @date 2022/2/9
 */
public class Code06_PaperFolding {

    public static void main(String[] args) {
        int N = 4;
        printAllFolds(N);
    }

    public static void printAllFolds(int N) {
        printFolding(1, N, true);
        System.out.println();
    }

    public static void printFolding(int curLevel, int maxLevel, boolean down) {
        if (curLevel > maxLevel) {
            return;
        }

        printFolding(curLevel + 1, maxLevel, true);
        System.out.print(down ? "凹" : "凸");
        printFolding(curLevel + 1, maxLevel, false);

    }
}
