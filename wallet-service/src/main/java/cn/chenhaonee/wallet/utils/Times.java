package cn.chenhaonee.wallet.utils;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by chenhaonee at 2019/2/1
 */
public class Times {

    public static Date toZero(Date date) {
        long zero = (date.getTime() ) / (24 * 3600 * 1000) * (24 * 3600 * 1000) - TimeZone.getDefault().getRawOffset();
        return new Date(zero);
    }

    public static Date toNight(Date date) {
        long night = (date.getTime()) / (24 * 3600 * 1000) * (24 * 3600 * 1000) - TimeZone.getDefault().getRawOffset() + 24*3600*1000 - 1;
        return new Date(night);
    }

    public static Date[] dayRange(Date date) {
        return new Date[]{toZero(date), toNight(date)};
    }

    public static Date[] monthRange() {
        LocalDate now = LocalDate.now();
        int year = now.getYear();
        int month = now.getMonthValue();
        int length = now.lengthOfMonth();

        Calendar from = Calendar.getInstance();
        from.set(year, month - 1, 1);
        Date monthFrom = toZero(from.getTime());
        Calendar to = Calendar.getInstance();
        to.set(year, month - 1, length);
        Date monthTo = toNight(to.getTime());

        return new Date[]{monthFrom, monthTo};
    }

    public static Date[] monthRange(int year,int month) {
        LocalDate now = LocalDate.now();
        int length = now.lengthOfMonth();

        Calendar from = Calendar.getInstance();
        from.set(year, month - 1, 1);
        Date monthFrom = toZero(from.getTime());
        Calendar to = Calendar.getInstance();
        to.set(year, month - 1, length);
        Date monthTo = toNight(to.getTime());

        return new Date[]{monthFrom, monthTo};
    }
}
