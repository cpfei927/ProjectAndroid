package com.cpfei.project;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by cpfei on 2018/4/11.
 */

public class JavaMain {


    public static void main(String[] args) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(System.currentTimeMillis()));


        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        System.out.println(format.format(calendar.getTime()));

        calendar.add(Calendar.DAY_OF_MONTH, -1);

        System.out.println(format.format(calendar.getTime()));

        calendar.add(Calendar.DAY_OF_MONTH, 2);

        System.out.println(format.format(calendar.getTime()));


    }


}
