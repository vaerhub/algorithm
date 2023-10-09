package io.arkvaer.algorithm.basic.day31;

public class SegmentTreeClass {
    public static class SegmentTree {
        private int MAXN;
        /**
         * arr[] 原序列的信息从0开始，但在arr里是从1开始的
         */
        private int[] arr;
        /**
         * sum[]模拟线段树维护区间和
         */
        private int[] sum;
        /**
         * lazy[]为累加和懒惰标记
         */
        private int[] lazy;
        /**
         * change[]为更新的值
         */
        private int[] change;
        /**
         * update[]为更新慵懒标记
         */
        private boolean[] update;

        public SegmentTree(int[] origin) {
            MAXN = origin.length + 1;
            arr = new int[MAXN];
            System.arraycopy(origin, 0, arr, 1, MAXN - 1);
            sum = new int[MAXN << 2];
            lazy = new int[MAXN << 2];
            change = new int[MAXN << 2];
            update = new boolean[MAXN << 2];
        }

        private void pushUp(int root) {
            sum[root] = sum[root << 1] + sum[root << 1 | 1];
        }

        private void pushDown(int root, int lCount, int rCount) {
            if (update[root]) {
                update[root << 1] = true;
                update[root << 1 | 1] = true;
                change[root << 1] = change[root];
                change[root << 1 | 1] = change[root];
                lazy[root << 1] = 0;
                lazy[root << 1 | 1] = 0;
                sum[root << 1] = change[root] * lCount;
                sum[root << 1 | 1] = change[root] * rCount;
                update[root] = false;
            }
            if (lazy[root] != 0) {
                lazy[root << 1] += lazy[root];
                sum[root << 1] += lazy[root] * lCount;
                lazy[root << 1 | 1] += lazy[root];
                sum[root << 1 | 1] += lazy[root] * rCount;
                lazy[root] = 0;
            }
        }

        public void build(int l, int r, int root) {
            if (l == r) {
                sum[root] = arr[l];
                return;
            }
            int mid = (l + r) >> 1;
            build(l, mid, root << 1);
            build(mid + 1, r, root << 1 | 1);
            pushUp(root);
        }

        public void update(int L, int R, int C, int l, int r, int root) {
            if (L <= l && r <= R) {
                update[root] = true;
                change[root] = C;
                sum[root] = C * (r - l + 1);
                lazy[root] = 0;
                return;
            }
            int mid = (l + r) >> 1;
            pushDown(root, mid - l + 1, r - mid);
            if (L <= mid) {
                update(L, R, C, l, mid, root << 1);
            }
            if (R > mid) {
                update(L, R, C, mid + 1, r, root << 1 | 1);
            }
            pushUp(root);
        }

        public void add(int L, int R, int C, int l, int r, int root) {
            if (L <= l && r <= R) {
                sum[root] += C * (r - l + 1);
                lazy[root] += C;
                return;
            }
            int mid = (l + r) >> 1;
            pushDown(root, mid - l + 1, r - mid);
            if (L <= mid) {
                add(L, R, C, l, mid, root << 1);
            }
            if (R > mid) {
                add(L, R, C, mid + 1, r, root << 1 | 1);
            }
            pushUp(root);
        }

        public long query(int L, int R, int l, int r, int root) {
            if (L <= l && r <= R) {
                return sum[root];
            }
            int mid = (l + r) >> 1;
            pushDown(root, mid - l + 1, r - mid);
            long ans = 0;
            if (L <= mid) {
                ans += query(L, R, l, mid, root << 1);
            }
            if (R > mid) {
                ans += query(L, R, mid +1, r, root << 1 |1);
            }
            return ans;
        }

    }

    public static class Right {
        public int[] arr;

        public Right(int[] origin) {
            arr = new int[origin.length + 1];
            for (int i = 0; i < origin.length; i++) {
                arr[i + 1] = origin[i];
            }
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
        int[] origin = { 2, 1, 1, 2, 3, 4, 5 };
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
