package template.ws.util;


import org.testng.Assert;
import org.testng.annotations.Test;
import template.util.Logger;
import template.util.StringHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: xiaoping
 * Date: 8/21/13
 * Time: 2:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class StringHelperTest {


    @Test(groups = {"unit_test"})
    public void testSplitBySize() {

        Assert.assertNull(StringHelper.splitBySize("", 1));
        Assert.assertNull(StringHelper.splitBySize(null, 1));

        try {
            StringHelper.splitBySize("test", -234234);
        } catch (IllegalArgumentException e) {
            Logger.info("Test negative args for StringHelper.splitBySize, should throw IllegalArgumentException");
        }

        String input = "abcdefg";
        int size = 2;

        List<String> list = StringHelper.splitBySize(input, size);
        Assert.assertEquals(list.get(0), "ab");
        Assert.assertEquals(list.get(1), "cd");
        Assert.assertEquals(list.get(2), "ef");
        Assert.assertEquals(list.get(3), "g");
    }

    @Test(groups = {"unit_test"})
    public void testCombineStringList() {
        Assert.assertNull(StringHelper.combineStringList(null));
        String left = "left";
        String right = "right";
        List<String> list = new ArrayList<String>();
        list.add(left);
        list.add(right);

        Assert.assertEquals(StringHelper.combineStringList(list), "leftright", "string in list should be merged");
    }

    @Test(groups = {"unit_test"})
    public void testJoin() {
        long[] num = {1234, 5678, 90};
        char sep = '+';

        String expect = "1234+5678+90";
        Assert.assertEquals(StringHelper.join(num, sep), expect, "array of long should be joined together");
    }

    @Test(groups = {"unit_test"})
    public void testNullSha1() {
        Assert.assertNull(StringHelper.shaHex(null), "should return null when input is null");
    }
}
