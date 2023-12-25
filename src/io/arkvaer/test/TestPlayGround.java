package io.arkvaer.test;

import java.util.ArrayList;
import java.util.List;

public class TestPlayGround {
    public static void main(String[] args) {
        List<String> a = new ArrayList<>();
        a.add("A");
        a.add("B");
        a.add("D");
        List<String> b = new ArrayList<>();
        b.add("A");
        b.add("C");
        b.add("D");
        List<String> ret = ret(a, b);
        System.out.println(ret);
        System.out.println(a);
        System.out.println(b);
        double m = Math.PI;
        System.out.println((Math.ceil(m) /2 == 2) );
    }

    public static List<String> ret(List<String> a, List<String> b) {
        List<String> c = new ArrayList<>(a);
        c.retainAll(b);
        return c;
    }
}