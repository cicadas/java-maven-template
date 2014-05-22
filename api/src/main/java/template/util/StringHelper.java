package template.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * User: zhouzh
 * Helper functions for extend String class
 */

public class StringHelper {
    /**
     * Convert a string to SHA-1 then parse it to hex string.
     * return null if input string is empty.
     *
     * @param input the input source string
     * @return SHA-1 hex char sequence
     */
    public static String shaHex(final String input) {
        if (StringUtils.isEmpty(input))
            return null;
        else
            return DigestUtils.shaHex(input);
    }

    /**
     * Split a string by max size setting.
     * For example, the input="abcdefg" size=2, then output list should be "ab","cd","ef","g".
     *
     * @param input the input string need be split
     * @param size  the max size of char piece
     * @return ArrayList of split result
     */
    public static List<String> splitBySize(final String input, final int size) {

        // return null if input/size invalidate
        if (StringUtils.isEmpty(input))
            return null;
        else if (size < 1)
            throw new IllegalArgumentException("size parameter must >= 1");

        final ArrayList<String> result = new ArrayList<>();
        final int sectionNum = input.length() / size + 1;
        int startPoint = 0;
        for (int i = 1; i < sectionNum; i++) {
            result.add(input.substring(startPoint, size * i));
            startPoint += size;
        }
        if (input.length() % size > 0)
            result.add(input.substring(startPoint));
        return result;
    }

    /**
     * Merge all string in the list to one sequence
     *
     * @param input the list of Strings
     * @return Combined string
     */
    public static String combineStringList(final List<String> input) {
        final StringBuilder builder = new StringBuilder();
        if (input == null)
            return null;
        for (final String item : input)
            builder.append(item);
        return builder.toString();
    }

    public static InputStream toInputStream(String input) {
        return new ByteArrayInputStream(input.getBytes());
    }

    public static String join(long[] values, char joinBy) {
        String result = "";
        for (int i = 0; i < values.length; i++)
            if (i == values.length - 1)
                result += values[i];
            else
                result += String.format("%s%s", values[i], joinBy);

        return result.trim();
    }
}
