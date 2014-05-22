package template.ws.exception;

import org.testng.annotations.Test;
import template.exception.InvalidRmxCredentialException;

/**
 * @author zhouzh
 *         Date: 9/25/13
 *         Time: 2:31 PM
 */
public class InvalidRmxCredentialExceptionTest {
    @Test(groups = {"unit_test"}, expectedExceptions = InvalidRmxCredentialException.class)
    public void testNewException() throws InvalidRmxCredentialException {
        throw new InvalidRmxCredentialException("test");
    }

    @Test(groups = {"unit_test"}, expectedExceptions = InvalidRmxCredentialException.class)
    public void testNestException() throws InvalidRmxCredentialException {
        throw new InvalidRmxCredentialException(new Exception("test"));
    }
}
