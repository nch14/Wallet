package com.chenhaonee.wallet.utils;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by chenhaonee at 2019/2/1
 */
public class Times {

    public static Date toZero(Date date) {
        long zero = (date.getTime() - 8 * 3600 * 1000) / (24 * 3600 * 1000) * (24 * 3600 * 1000) + 8 * 3600 * 1000;
        return new Date(zero);
    }

    public static Date toNight(Date date) {
        long night = (date.getTime() - 8 * 3600 * 1000) / (24 * 3600 * 1000) * (24 * 3600 * 1000) + 8 * 3600 * 1000 + 24 * 3600 * 1000 - 1;
        return new Date(night);
    }

    public static Date[] monthRange() {
        LocalDate now = LocalDate.now();
        int year = now.getYear();
        int month = now.getMonthValue();
        int length = now.lengthOfMonth();

        Calendar from = Calendar.getInstance();
        from.set(year, month - 1, 1);
        Date monthFrom = from.getTime();
        Calendar to = Calendar.getInstance();
        to.set(year, month - 1, length - 1);
        Date monthTo = to.getTime();

        return new Date[]{monthFrom, monthTo};
    }
}
