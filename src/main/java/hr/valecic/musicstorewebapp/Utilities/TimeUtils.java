package hr.valecic.musicstorewebapp.Utilities;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class TimeUtils {
    private TimeUtils() {}

    public static Timestamp getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();

        return new java.sql.Timestamp(now.getTime());
    }
}
