package io.arkvaer.algorithm;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws Exception {
        testCalender();
        //calcWorkTime();
        dis();
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

    public static void  dis() {

        List<Integer> list = Arrays.asList(33, 15, 15, 3, 3, 8, 14, 1, 33, 15, 38, 38, 15, 15, 62, 14, 1, 8, 33, 63, 1, 63, 95, 33, 33, 1, 3, 1, 33, 63, 15, 1, 27, 1, 63, 63, 33, 33, 3, 3, 38, 1, 14, 1, 181, 62, 62, 63, 77, 32, 144, 15, 14, 33, 75, 91, 27, 62, 1, 33, 15, 58, 15, 33, 15, 58, 181, 82, 15, 26, 75, 1, 3, 15, 82, 8, 38, 25, 167, 167, 14, 8, 82, 38, 26, 82, 25, 8, 33, 76, 167, 27, 1, 82, 26, 25, 91, 15, 82, 279, 1, 82, 3, 144, 1, 33, 14, 63, 63, 62, 25, 279, 91, 181, 38, 33, 144, 279, 3, 3, 15, 82, 1, 63, 25, 25, 1, 1, 1, 167, 38, 91, 95, 14, 25, 62, 75, 279, 33, 33, 32, 77, 8, 1, 15, 3, 373, 1, 167, 91, 173, 95, 1, 1, 33, 358, 358, 309, 358, 279, 38, 77, 8, 75, 1, 33, 77, 1, 1, 173, 25, 75, 144, 15, 32, 1, 38, 77, 1, 3, 15, 75, 279, 8, 279, 91, 38, 58, 173, 163, 15, 8, 75, 33, 181, 458, 309, 1, 14, 32, 26, 14, 8, 33, 33, 82, 95, 26, 3, 3, 75, 3, 91, 25, 283, 8, 3, 181, 279, 75, 3, 25, 163, 279, 283, 75, 8, 279, 26, 14, 181, 173, 95, 26, 82, 3, 25, 458, 26, 75, 181, 279, 75, 181, 283, 3, 75, 3, 15, 3, 8, 91, 163, 173, 82, 33, 3, 181, 15, 15, 27, 373, 32, 26, 163, 75, 3, 3, 77, 25, 144, 1, 75, 38, 25, 14, 163, 62, 373, 82, 25, 95, 181, 77, 3, 25, 181, 25, 283, 38, 38, 26, 58, 38, 91, 167, 167, 181, 95, 181, 144, 62, 163);
        HashSet<Integer> integers = new HashSet<>(list);
        System.out.println(integers);
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