package template.ws.exception;

import org.testng.annotations.Test;
import template.exception.InvalidOriginatorUpdateException;

/**
 * @author zhouzh
 *         Date: 9/25/13
 *         Time: 2:21 PM
 */
public class InvalidOriginatorUpdateExceptionTest {
    @Test(groups = {"unit_test"}, expectedExceptions = InvalidOriginatorUpdateException.class)
    public void testNewException() throws InvalidOriginatorUpdateException {
        throw new InvalidOriginatorUpdateException("test");
    }
}
