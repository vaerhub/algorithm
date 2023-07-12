package io.arkvaer.algorithm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws ParseException {
        calcWorkTime();
    }

    public static void calcWorkTime() throws ParseException {
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        String today = dateFormat.format(now);
        System.out.print("请输入上班时间: ");
        Scanner scanner = new Scanner(System.in);
        Date arriveTime = dateTimeFormat.parse(today + " " + scanner.nextLine() + ".00");
        System.out.println("上班时间: " + dateTimeFormat.format(arriveTime));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(arriveTime);
        calendar.add(Calendar.HOUR, 11);
        calendar.add(Calendar.MINUTE, 30);
        System.out.println("下班时间: " + dateTimeFormat.format(calendar.getTime()));
    }
}