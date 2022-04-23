package p15SegmentTree;

/**
 * @description:
 * @author: Flash
 * @create: 2022-04-23 16:28
 **/
public class Code03_IndexTree {

    public static class IndexTree {
        private int[] tree;
        private int size;

        public IndexTree(int size) {
            this.size = size;
            this.tree = new int[size + 1];
        }

        public int sum(int index) {
            int res = 0;
            while (index > 0) {
                res += tree[index];
                index -= (index & -index);
            }
            return res;
        }

        public void add(int index, int d) {
            while (index <= size) {
                tree[index] += d;
                index += (index & -index);
            }
        }
    }

    public static class Right {
        private int[] arr;
        private int size;

        public Right(int size) {
            this.size = size + 1;
            this.arr = new int[this.size];
        }

        public int sum(int index) {
            int res = 0;
            for (int i = 0; i <= index; i++) {
                res += arr[i];
            }
            return res;
        }

        public void add(int index, int d) {
            arr[index] += d;
        }
    }

    public static void main(String[] args) {
        int N = 100;
        int V = 100;
        int testTime = 2000000;
        IndexTree tree = new IndexTree(N);
        Right test = new Right(N);
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int index = (int) (Math.random() * N) + 1;
            if (Math.random() <= 0.5) {
                int add = (int) (Math.random() * V);
                tree.add(index, add);
                test.add(index, add);
            } else {
                if (tree.sum(index) != test.sum(index)) {
                    System.out.println("Oops!");
                }
            }
        }
        System.out.println("test finish");
    }
}
