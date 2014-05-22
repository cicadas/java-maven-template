package template.util;

import org.slf4j.LoggerFactory;

/**
 * @author zhouzh
 *         Date: 10/22/13
 *         Time: 11:14 AM
 */
public class Logger {

    public static org.slf4j.Logger get(Class<?> input) {
        return LoggerFactory.getLogger(input);
    }

    private static org.slf4j.Logger log() {
        return LoggerFactory.getLogger(new Throwable().getStackTrace()[2].getClassName());
    }

    public static void trace(String s) {
        log().trace(s);
    }


    public static void trace(String s, Throwable t) {
        log().trace(s, t);
    }

    public static void debug(String s) {
        log().debug(s);
    }


    public static void debug(String s, Throwable t) {
        log().debug(s, t);
    }


    public static void info(String s) {
        log().info(s);
    }


    public static void info(String s, Throwable t) {
        log().info(s, t);
    }


    public static void warn(String s) {
        log().warn(s);
    }


    public static void warn(String s, Throwable t) {
        log().warn(s, t);
    }


    public static void error(String s) {
        log().error(s);
    }


    public static void error(String s, Throwable t) {
        log().error(s, t);
    }
}
