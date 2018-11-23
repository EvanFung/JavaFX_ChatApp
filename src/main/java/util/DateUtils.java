package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    private DateUtils() {}
    public static String getFormatDate() {
        Date date  = new Date();
        long times = date.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String dateString = format.format(date);
        return dateString;
    }
}
