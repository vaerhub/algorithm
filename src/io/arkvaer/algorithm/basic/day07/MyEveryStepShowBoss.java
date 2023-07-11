package io.arkvaer.algorithm.basic.day07;

import java.util.*;

/**
 * @author waver
 * @date 2023/7/9 15:50
 */
public class MyEveryStepShowBoss {


    /**
     * 获奖区比较器
     */
    public static class AwardComparator implements Comparator<Customer> {
        @Override
        public int compare(Customer c1, Customer c2) {
            return c1.buy != c2.buy ? c1.buy - c2.buy : c1.enterTime - c2.enterTime;
        }
    }

    public static class CandidateComparator implements Comparator<Customer> {
        @Override
        public int compare(Customer o1, Customer o2) {
            return o1.buy != o2.buy ? o2.buy - o1.buy : o1.enterTime - o2.enterTime;
        }
    }

    public static class Customer {
        private int id;
        private int buy;
        private int enterTime;

        public Customer(int id, int buy, int enterTime) {
            this.id = id;
            this.buy = buy;
            this.enterTime = enterTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getBuy() {
            return buy;
        }

        public void setBuy(int buy) {
            this.buy = buy;
        }

        public int getEnterTime() {
            return enterTime;
        }

        public void setEnterTime(int enterTime) {
            this.enterTime = enterTime;
        }
    }


    public static class GetAllAward {

        private HashMap<Integer, Customer> customers;
        /**
         * 获奖区
         */
        private MyHeapGreater<Customer> awardHeap;
        /**
         * 候选区
         */
        private MyHeapGreater<Customer> candidateHeap;
        /**
         * 最大获奖数
         */
        private int awardLimit;

        public GetAllAward(int awardLimit) {
            this.customers = new HashMap<>();
            this.awardHeap = new MyHeapGreater<>(new AwardComparator());
            this.candidateHeap = new MyHeapGreater<>(new CandidateComparator());
            this.awardLimit = awardLimit;
        }

        public void operate(int id, boolean isBuy, int time) {
            // 当前获奖区和候选区都不存在该用户, 且操作为退货时, 直接忽略
            if (!customers.containsKey(id) && !isBuy) {
                return;
            }
            // 该用户第一次操作, 且不是退货
            if (!customers.containsKey(id)) {
                customers.put(id, new Customer(id, 0, 0));
            }
            Customer customer = customers.get(id);
            if (isBuy) {
                customer.buy++;
            } else {
                customer.buy--;
            }
            // 当用户购买数为0时, 从用户列表中移除
            if (customer.buy == 0) {
                customers.remove(id);
            }
            if (!awardHeap.contains(customer) && !candidateHeap.contains(customer)) {
                // 符合这个分支的条件: 1. 为第一操作 2. 且不是退货 即第一次发生购买操作
                if (awardHeap.size() < awardLimit) {
                    awardHeap.push(customer);
                } else {
                    candidateHeap.push(customer);
                }
                // 只有在当前分支重新设置时间, 时间表示的是用户第一次进入候选区/获奖区的时间
                customer.setEnterTime(time);
            } else if (candidateHeap.contains(customer)) {
                if (customer.buy == 0) {
                    candidateHeap.remove(customer);
                } else {
                    candidateHeap.resign(customer);
                }
            } else {
                if (customer.buy == 0) {
                    awardHeap.remove(customer);
                } else {
                    awardHeap.resign(customer);
                }
            }
            refreshAward(time);
        }

        /**
         * 重新根据购买数量调整获奖区和候选区
         */
        public void refreshAward(int time) {
            if (candidateHeap.isEmpty()) {
                return;
            }

            if (awardHeap.size() < awardLimit) {
                Customer candidate = candidateHeap.pop();
                candidate.setEnterTime(time);
                awardHeap.push(candidate);
            } else {
                if (candidateHeap.peek().getBuy() > awardHeap.peek().getBuy()) {
                    Customer candidate = candidateHeap.pop();
                    Customer award = awardHeap.pop();
                    candidate.setEnterTime(time);
                    award.setEnterTime(time);
                    candidateHeap.push(award);
                    awardHeap.push(candidate);
                }
            }
        }

        public List<Integer> getAwards() {
            List<Customer> allElements = awardHeap.getAllElements();
            List<Integer> resultList = new ArrayList<>();
            for (Customer allElement : allElements) {
                resultList.add(allElement.getId());
            }
            Collections.sort(resultList);
            return resultList;
        }


    }


    public static List<List<Integer>> topK(int[] user, boolean[] op, int awardLimit) {
        List<List<Integer>> awardListResult = new ArrayList<>();
        GetAllAward award = new GetAllAward(awardLimit);
        for (int i = 0; i < user.length; i++) {
            award.operate(user[i], op[i], i);
            awardListResult.add(award.getAwards());
        }
        return awardListResult;
    }


    public static List<List<Integer>> compare(int[] arr, boolean[] op, int k) {
        HashMap<Integer, EveryStepShowBoss.Customer> map = new HashMap<>();
        ArrayList<EveryStepShowBoss.Customer> cands = new ArrayList<>();
        ArrayList<EveryStepShowBoss.Customer> daddy = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            int id = arr[i];
            boolean buyOrRefund = op[i];
            if (!buyOrRefund && !map.containsKey(id)) {
                ans.add(getCurAns(daddy));
                continue;
            }
            // 没有发生：用户购买数为0并且又退货了
            // 用户之前购买数是0，此时买货事件
            // 用户之前购买数>0， 此时买货
            // 用户之前购买数>0, 此时退货
            if (!map.containsKey(id)) {
                map.put(id, new EveryStepShowBoss.Customer(id, 0, 0));
            }
            // 买、卖
            EveryStepShowBoss.Customer c = map.get(id);
            if (buyOrRefund) {
                c.buy++;
            } else {
                c.buy--;
            }
            if (c.buy == 0) {
                map.remove(id);
            }
            // c
            // 下面做
            if (!cands.contains(c) && !daddy.contains(c)) {
                if (daddy.size() < k) {
                    c.enterTime = i;
                    daddy.add(c);
                } else {
                    c.enterTime = i;
                    cands.add(c);
                }
            }
            cleanZeroBuy(cands);
            cleanZeroBuy(daddy);
            cands.sort(new EveryStepShowBoss.CandidateComparator());
            daddy.sort(new EveryStepShowBoss.DaddyComparator());
            move(cands, daddy, k, i);
            ans.add(getCurAns(daddy));
        }
        return ans;
    }

    public static void move(List<EveryStepShowBoss.Customer> cands, List<EveryStepShowBoss.Customer> daddy, int k, int time) {
        if (cands.isEmpty()) {
            return;
        }
        // 候选区不为空
        if (daddy.size() < k) {
            EveryStepShowBoss.Customer c = cands.get(0);
            c.enterTime = time;
            daddy.add(c);
            cands.remove(0);
        } else { // 等奖区满了，候选区有东西
            if (cands.get(0).buy > daddy.get(0).buy) {
                EveryStepShowBoss.Customer oldDaddy = daddy.get(0);
                daddy.remove(0);
                EveryStepShowBoss.Customer newDaddy = cands.get(0);
                cands.remove(0);
                newDaddy.enterTime = time;
                oldDaddy.enterTime = time;
                daddy.add(newDaddy);
                cands.add(oldDaddy);
            }
        }
    }

    public static void cleanZeroBuy(List<EveryStepShowBoss.Customer> arr) {
        List<EveryStepShowBoss.Customer> noZero = new ArrayList<>();
        for (EveryStepShowBoss.Customer c : arr) {
            if (c.buy != 0) {
                noZero.add(c);
            }
        }
        arr.clear();
        for (EveryStepShowBoss.Customer c : noZero) {
            arr.add(c);
        }
    }

    public static List<Integer> getCurAns(ArrayList<EveryStepShowBoss.Customer> daddy) {
        List<Integer> ans = new ArrayList<>();
        for (EveryStepShowBoss.Customer c : daddy) {
            ans.add(c.id);
        }
        Collections.sort(ans);
        return ans;
    }

    // 为了测试
    public static class Data {
        public int[] arr;
        public boolean[] op;

        public Data(int[] a, boolean[] o) {
            arr = a;
            op = o;
        }
    }

    // 为了测试
    public static Data randomData(int maxValue, int maxLen) {
        int len = (int) (Math.random() * maxLen) + 1;
        int[] arr = new int[len];
        boolean[] op = new boolean[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * maxValue);
            op[i] = Math.random() < 0.5 ? true : false;
        }
        return new Data(arr, op);
    }

    // 为了测试
    public static boolean sameAnswer(List<List<Integer>> ans1, List<List<Integer>> ans2) {
        if (ans1.size() != ans2.size()) {
            return false;
        }
        for (int i = 0; i < ans1.size(); i++) {
            List<Integer> cur1 = ans1.get(i);
            List<Integer> cur2 = ans2.get(i);
            if (cur1.size() != cur2.size()) {
                return false;
            }
            cur1.sort(Comparator.comparingInt(a -> a));
            cur2.sort(Comparator.comparingInt(a -> a));
            for (int j = 0; j < cur1.size(); j++) {
                if (!cur1.get(j).equals(cur2.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int maxValue = 10;
        int maxLen = 100;
        int maxK = 6;
        int testTimes = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            Data testData = randomData(maxValue, maxLen);
            int k = (int) (Math.random() * maxK) + 1;
            int[] arr = testData.arr;
            boolean[] op = testData.op;
            List<List<Integer>> ans1 = topK(arr, op, k);
            List<List<Integer>> ans2 = compare(arr, op, k);
            if (!sameAnswer(ans1, ans2)) {
                for (int j = 0; j < arr.length; j++) {
                    System.out.println(arr[j] + " , " + op[j]);
                }
                System.out.println(k);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("出错了！");
                break;
            }
        }
        System.out.println("测试结束");

        //[[7], [5, 7], [2, 5, 7], [2, 5, 7, 9], [2, 5, 7, 9], [2, 5, 7, 9], [2, 5, 7, 9], [2, 5, 6, 9], [2, 5, 6, 9], [2, 4, 5, 9]]
/*        int[] arr = {7, 5, 2, 9, 8, 6, 8, 7, 4, 4};
        boolean[] op = {true, true, true, true, true, true, false, false, true, true};
        List<List<Integer>> lists = topK(arr, op, 4);
        List<List<Integer>> compare = compare(arr, op, 4);
        System.out.println(lists);
        System.out.println(compare);*/

    }
}
