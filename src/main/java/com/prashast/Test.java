package com.prashast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * test class to see working on calendar java object between timezones
 * use second approach below *** if you want to update timezone but keep the date&time same as in prev one.
 */

public class Test {

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

        System.out.printf("UTC Time  : %02d:%02d:%02d\n", hour, minute, second); //00:00

        calendar.setTimeZone(TimeZone.getTimeZone("America/New_York"));

        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
        second = calendar.get(Calendar.SECOND);

        System.out.printf("America time: %02d:%02d:%02d\n", hour, minute, second); //19:00

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        System.out.println("America Time printed in UTC Tz: " + sdf.format(calendar.getTime()));


        //************************************************************************

        Calendar localTime = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        localTime.set(Calendar.HOUR_OF_DAY, 0);
        localTime.set(Calendar.MINUTE, 0);
        localTime.set(Calendar.SECOND, 0);

        hour = localTime.get(Calendar.HOUR_OF_DAY);
        minute = localTime.get(Calendar.MINUTE);
        second = localTime.get(Calendar.SECOND);

        System.out.printf("UTC time  : %02d:%02d:%02d\n", hour, minute, second); //00:00

        Calendar americaTime = Calendar.getInstance(TimeZone.getTimeZone("America/New_York"));
        int year = localTime.get(Calendar.YEAR);
        int month = localTime.get(Calendar.MONTH);
        int day = localTime.get(Calendar.DAY_OF_MONTH);
        americaTime.set(year, month, day , hour, minute, second);


        System.out.printf("America time: %02d:%02d:%02d\n", hour, minute, second); //00:00

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
        sdf1.setTimeZone(TimeZone.getTimeZone("UTC"));
        System.out.println("America Time printed in UTC Tz: " +sdf1.format(americaTime.getTime()));


    }

}
