package template.test.util;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: xiaoping
 * Date: 8/12/13
 * Time: 10:45 AM
 * To change this template use File | Settings | File Templates.
 */
public class ExchangeHelper {

    public static String getRequestContentString(String filePath) throws IOException {
        return FileHelper.getContentFromFileInJar(filePath);
    }

}
