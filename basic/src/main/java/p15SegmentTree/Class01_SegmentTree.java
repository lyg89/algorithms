package p15SegmentTree;

/**
 * @description:
 * @author: Flash
 * @create: 2022-04-17 16:34
 **/
public class Class01_SegmentTree {

    public static class SegmentTree {
        private int[] arr;
        private int[] sumArr;
        private int[] lazyArr;
        private int[] changeArr;
        private boolean[] updateArr;

        public SegmentTree(int[] origin) {
            int newNum = origin.length + 1;
            arr = new int[newNum];
            System.arraycopy(origin, 0, arr, 1, newNum - 1);
            sumArr = new int[newNum << 2];
            lazyArr = new int[newNum << 2];
            changeArr = new int[newNum << 2];
            updateArr = new boolean[newNum << 2];
        }

        private void pushUp(int rt) {
            sumArr[rt] = sumArr[rt << 1] + sumArr[rt << 1 | 1];
        }

        // 之前的，所有懒增加，和懒更新，从父范围，发给左右两个子范围
        // 分发策略是什么
        // ln表示左子树元素结点个数，rn表示右子树结点个数
        private void pushDown(int rt, int ln, int rn) {
            if (updateArr[rt]) {
                updateArr[rt << 1] = true;
                updateArr[rt << 1 | 1] = true;
                changeArr[rt << 1] = changeArr[rt];
                changeArr[rt << 1 | 1] = changeArr[rt];
                lazyArr[rt << 1] = 0;
                lazyArr[rt << 1 | 1] = 0;
                sumArr[rt << 1] = ln * changeArr[rt];
                sumArr[rt << 1 | 1] = rn * changeArr[rt];
                updateArr[rt] = false;
            }
            if (lazyArr[rt] != 0) {
                lazyArr[rt << 1] += lazyArr[rt];
                lazyArr[rt << 1 | 1] += lazyArr[rt];
                sumArr[rt << 1] += ln * lazyArr[rt];
                sumArr[rt << 1 | 1] += rn * lazyArr[rt];
                lazyArr[rt] = 0;
            }
        }

        // 在初始化阶段，先把sum数组，填好
        // 在arr[l~r]范围上，去build，1~N，
        // rt :  这个范围在sum中的下标
        public void build(int l, int r, int rt) {
            if (l == r) {
                sumArr[rt] = arr[l];
                return;
            }
            int mid = (l + r) >> 1;
            build(l, mid, rt << 1);
            build(mid + 1, r, rt << 1 | 1);
            pushUp(rt);
        }

        public void update(int L, int R, int C, int l, int r, int rt) {
            if (L <= l && R >= r) {
                sumArr[rt] = (r - l + 1) * C;
                changeArr[rt] = C;
                updateArr[rt] = true;
                lazyArr[rt] = 0;
                return;
            }
            int mid = (l + r) >> 1;
            pushDown(rt, mid - l + 1, r - mid);

            if (mid >= L) {
                update(L, R, C, l, mid, rt << 1);
            }

            if (R > mid) {
                update(L, R, C, mid + 1, r, rt << 1 | 1);
            }
            pushUp(rt);
        }

        // L..R -> 任务范围 ,所有的值累加上C
        // l,r -> 表达的范围
        // rt  去哪找l，r范围上的信息
        public void add(
                int L, int R, int C,
                int l, int r,
                int rt) {
            if (L <= l && R >= r) {
                sumArr[rt] += (r - l + 1) * C;
                lazyArr[rt] += C;
                return;
            }
            int mid = (l + r) >> 1;
            pushDown(rt, mid - l + 1, r - mid);

            if (mid >= L) {
                add(L, R, C, l, mid, rt << 1);
            }

            if (R > mid) {
                add(L, R, C, mid + 1, r, rt << 1 | 1);
            }
            pushUp(rt);
        }

        //   1~6 累加和是多少？ 1~8   rt
        public long query(int L, int R, int l, int r, int rt) {
            if (L <= l && R >= r) {
                return sumArr[rt];
            }
            int mid = (l + r) >> 1;
            pushDown(rt, mid - l + 1, r - mid);
            long ans = 0L;
            if (L <= mid) {
                ans += query(L, R, l, mid, rt << 1);
            }
            if (mid < R) {
                ans += query(L, R, mid + 1, r, rt << 1 | 1);
            }
            return ans;
        }

    }

    public static class Right {
        public int[] arr;

        public Right(int[] origin) {
            arr = new int[origin.length + 1];
            System.arraycopy(origin, 0, arr, 1, origin.length);
        }

        public void update(int L, int R, int C) {
            for (int i = L; i <= R; i++) {
                arr[i] = C;
            }
        }

        public void add(int L, int R, int C) {
            for (int i = L; i <= R; i++) {
                arr[i] += C;
            }
        }

        public long query(int L, int R) {
            long ans = 0;
            for (int i = L; i <= R; i++) {
                ans += arr[i];
            }
            return ans;
        }

    }

    public static int[] genarateRandomArray(int len, int max) {
        int size = (int) (Math.random() * len) + 1;
        int[] origin = new int[size];
        for (int i = 0; i < size; i++) {
            origin[i] = (int) (Math.random() * max) - (int) (Math.random() * max);
        }
        return origin;
    }

    public static boolean test() {
        int len = 100;
        int max = 1000;
        int testTimes = 5000;
        int addOrUpdateTimes = 1000;
        int queryTimes = 500;
        for (int i = 0; i < testTimes; i++) {
            int[] origin = genarateRandomArray(len, max);
            SegmentTree seg = new SegmentTree(origin);
            int S = 1;
            int N = origin.length;
            int root = 1;
            seg.build(S, N, root);
            Right rig = new Right(origin);
            for (int j = 0; j < addOrUpdateTimes; j++) {
                int num1 = (int) (Math.random() * N) + 1;
                int num2 = (int) (Math.random() * N) + 1;
                int L = Math.min(num1, num2);
                int R = Math.max(num1, num2);
                int C = (int) (Math.random() * max) - (int) (Math.random() * max);
                if (Math.random() < 0.5) {
                    seg.add(L, R, C, S, N, root);
                    rig.add(L, R, C);
                } else {
                    seg.update(L, R, C, S, N, root);
                    rig.update(L, R, C);
                }
            }
            for (int k = 0; k < queryTimes; k++) {
                int num1 = (int) (Math.random() * N) + 1;
                int num2 = (int) (Math.random() * N) + 1;
                int L = Math.min(num1, num2);
                int R = Math.max(num1, num2);
                long ans1 = seg.query(L, R, S, N, root);
                long ans2 = rig.query(L, R);
                if (ans1 != ans2) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] origin = {2, 1, 1, 2, 3, 4, 5};
        SegmentTree seg = new SegmentTree(origin);
        int S = 1; // 整个区间的开始位置，规定从1开始，不从0开始 -> 固定
        int N = origin.length; // 整个区间的结束位置，规定能到N，不是N-1 -> 固定
        int root = 1; // 整棵树的头节点位置，规定是1，不是0 -> 固定
        int L = 2; // 操作区间的开始位置 -> 可变
        int R = 5; // 操作区间的结束位置 -> 可变
        int C = 4; // 要加的数字或者要更新的数字 -> 可变
        // 区间生成，必须在[S,N]整个范围上build
        seg.build(S, N, root);
        // 区间修改，可以改变L、R和C的值，其他值不可改变
        seg.add(L, R, C, S, N, root);
        // 区间更新，可以改变L、R和C的值，其他值不可改变
        seg.update(L, R, C, S, N, root);
        // 区间查询，可以改变L和R的值，其他值不可改变
        long sum = seg.query(L, R, S, N, root);
        System.out.println(sum);

        System.out.println("对数器测试开始...");
        System.out.println("测试结果 : " + (test() ? "通过" : "未通过"));

    }
}
