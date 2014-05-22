package template.test.util;

import org.joda.time.DateTime;

import java.util.Date;

/**
 * @author zhouzh
 *         Date: 8/12/13
 *         Time: 5:38 PM
 */
public class DatetimeHelper {

    /**
     * Identify if the {@code input} happened only before {@code hours}
     *
     * @param input timestamp of the date
     * @return if in the period return true, otherwise return false
     */
    public static boolean isHappenedInOneDay(Date input) {
        int before = new DateTime(input).getDayOfYear();
        int now = new DateTime().getDayOfYear();
        return now - before <= 1;
    }
}