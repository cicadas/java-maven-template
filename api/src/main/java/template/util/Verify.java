package template.util;

import java.util.List;

public class Verify {

    public static int notValue(int value, int not, String name) {
        if (value == not)
            throw new IllegalArgumentException("The " + name + " cannot be set to " + not + ".");
        return value;
    }

    public static void notNull(Object value, String name) {
        if (value == null)
            throw new NullPointerException("The " + name + " cannot be null.");
    }

    public static String notNullOrEmpty(String value, String name) {
        if (value == null || value.isEmpty())
            throw new NullPointerException("The " + name + " cannot be null or empty.");
        return value;
    }

    public static <T> void notNullOrEmpty(List<T> list, String name) {
        if (list == null || list.isEmpty())
            throw new NullPointerException("The " + name + " cannot be null or empty.");
    }
}
