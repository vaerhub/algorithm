package io.arkvaer.test;

import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CopyListTest {

    public static void main(String[] args) {
        CopyListTest copyListTest = new CopyListTest();
        copyListTest.copyListTest();
    }
    public void copyListTest() {
        Clazz clazz = new Clazz();
        List<InClass> inClassList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            InClass inClass = new InClass();
            List<Integer> ints = new ArrayList<>();
            ints.add(1);
            ints.add(2);
            ints.add(3);
            inClass.setList(ints);
            inClass.setAge(i);
            inClassList.add(inClass);
        }
        clazz.setInClassList(inClassList);


        Clazz copy = new Clazz();
        List<InClass> copyInClass = new ArrayList<>();

        Collections.copy(inClassList, copyInClass);

        copyInClass.remove(1);


        copy.setInClassList(copyInClass);

        System.out.println(JSONUtil.parse(clazz));
        System.out.println("==================");
        System.out.println(JSONUtil.parse(copy));
    }
}

class Clazz {
    private List<InClass> inClassList;

    public List<InClass> getInClassList() {
        return inClassList;
    }

    public void setInClassList(List<InClass> inClassList) {
        this.inClassList = inClassList;
    }
}

class InClass{
    private int age;

    private List<Integer> list;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Integer> getList() {
        return list;
    }

    public void setList(List<Integer> list) {
        this.list = list;
    }
}

