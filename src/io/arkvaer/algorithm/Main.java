package io.arkvaer.algorithm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws Exception {
        //testCalender();
        calcWorkTime();
    }

    public static void calcWorkTime() throws ParseException {
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("HH.mm.ss");
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date now = new Date();
//        String today = dateFormat.format(now);
        System.out.print("请输入上班时间: ");
        Scanner scanner = new Scanner(System.in);
        Date arriveTime = dateTimeFormat.parse( scanner.nextLine() + ".00");
        System.out.println("上班时间: " + dateTimeFormat.format(arriveTime));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(arriveTime);
        calendar.add(Calendar.HOUR, 11);
        calendar.add(Calendar.MINUTE, 30);
        System.out.println("下班时间: " + dateTimeFormat.format(calendar.getTime()));
    }


    public static void  testCalender() throws Exception {
        SimpleDateFormat sfd = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = sfd.parse("2023-02-31");
        Calendar instance = Calendar.getInstance();
        instance.setTime(parse);
        instance.add(Calendar.WEEK_OF_YEAR, -1);
        System.out.println(sfd.format(instance.getTime()));
    }

}