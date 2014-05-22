package template.test.exception;

/**
 * @author zhouzh
 *         Date: 8/7/13
 *         Time: 6:40 PM
 */
public class NotSupportSpecTypeException extends Exception {
    public NotSupportSpecTypeException() {
        super();    //To change body of overridden methods use File | Settings | File Templates.
    }

    public NotSupportSpecTypeException(String message) {
        super(message);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public NotSupportSpecTypeException(String message, Throwable cause) {
        super(message, cause);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public NotSupportSpecTypeException(Throwable cause) {
        super(cause);    //To change body of overridden methods use File | Settings | File Templates.
    }

    protected NotSupportSpecTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
