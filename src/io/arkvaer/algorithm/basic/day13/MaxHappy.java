package io.arkvaer.algorithm.basic.day13;

import java.util.ArrayList;
import java.util.List;

/**
 * 设定一颗多叉树代表一个公司的组织架构, 每个节点代表一个员工, 员工的直接子节点是该员工的下级, 每个节点的值代表该员工的Happy值
 * 现在可以要给该公司的所有人发请帖, 但不能同时邀请直属上下级
 * 求如何发帖才能使邀请到的人是Happy值最大
 */
public class MaxHappy {

    public static class Employee {
        public int happy;
        public List<Employee> subordinate;

        public Employee(int h) {
            happy = h;
            subordinate = new ArrayList<>();
        }
    }

    public static int maxHappy1(Employee employee) {
        if (employee == null) {
            return 0;
        }
        return process1(employee, false);
    }

    public static int process1(Employee employee, boolean isBossCome) {
        // 如果当前上级受邀, 则其直接下级都不能受邀
        if (isBossCome) {
            int happy = 0;
            for (Employee e : employee.subordinate) {
                happy += process1(e, false);
            }
            return happy;
        } else {
            int curComeHappy = employee.happy;
            int curNotComeHappy = 0;
            for (Employee e : employee.subordinate) {
                curComeHappy += process1(e, true);
                curNotComeHappy += process1(e, false);
            }
            return Math.max(curComeHappy, curNotComeHappy);
        }
    }


    public static class Info {
        /**
         * 当前节点受邀的happy值
         */
        public int comeHappyVal;
        /**
         * 当前节点不受邀的happy值
         */
        public int notComeHappyVal;

        public Info(int comeHappyVal, int notComeHappyVal) {
            this.comeHappyVal = comeHappyVal;
            this.notComeHappyVal = notComeHappyVal;
        }
    }

    public static int maxHappy2(Employee employee) {
        Info process = process(employee);
        return Math.max(process.comeHappyVal, process.notComeHappyVal);
    }

    public static Info process(Employee employee) {
        if (employee == null) {
            return new Info(0, 0);
        }
        int yes = employee.happy;
        int no = 0;
        for (Employee e : employee.subordinate) {
            Info info = process(e);
            yes += info.notComeHappyVal;
            no += Math.max(info.comeHappyVal, info.notComeHappyVal);
        }
        return new Info(yes, no);
    }


    // region for test
    public static Employee genarateBoss(int maxLevel, int maxNexts, int maxHappy) {
        if (Math.random() < 0.02) {
            return null;
        }
        Employee boss = new Employee((int) (Math.random() * (maxHappy + 1)));
        genarateNexts(boss, 1, maxLevel, maxNexts, maxHappy);
        return boss;
    }

    // for test
    public static void genarateNexts(Employee e, int level, int maxLevel, int maxNexts, int maxHappy) {
        if (level > maxLevel) {
            return;
        }
        int nextsSize = (int) (Math.random() * (maxNexts + 1));
        for (int i = 0; i < nextsSize; i++) {
            Employee next = new Employee((int) (Math.random() * (maxHappy + 1)));
            e.subordinate.add(next);
            genarateNexts(next, level + 1, maxLevel, maxNexts, maxHappy);
        }
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxNexts = 7;
        int maxHappy = 100;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            Employee boss = genarateBoss(maxLevel, maxNexts, maxHappy);
            if (maxHappy1(boss) != maxHappy2(boss)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

    // endregion
}
