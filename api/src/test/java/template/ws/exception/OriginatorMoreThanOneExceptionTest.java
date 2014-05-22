package template.ws.exception;

import org.testng.annotations.Test;
import template.exception.OriginatorMoreThanOneException;

/**
 * @author zhouzh
 *         Date: 9/25/13
 *         Time: 1:21 PM
 */
public class OriginatorMoreThanOneExceptionTest {

    @Test(groups = {"unit_test"}, expectedExceptions = OriginatorMoreThanOneException.class)
    public void testNewException() throws OriginatorMoreThanOneException {
        throw new OriginatorMoreThanOneException("test");
    }
}
