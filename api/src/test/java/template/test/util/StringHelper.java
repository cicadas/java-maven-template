package template.test.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.UUID;

/**
 * @author zhouzh
 *         Date: 8/1/13
 *         Time: 3:43 PM
 */
public class StringHelper {
    public static String randomizeMessage(String message, String placeHolder) {
        String timestamp = new Date().getTime() + "";
        return message.replace(placeHolder, placeHolder + timestamp);
    }

    public static String getRandom() {
        return UUID.randomUUID().toString().replaceAll("-", StringUtils.EMPTY).substring(20);
    }
}
