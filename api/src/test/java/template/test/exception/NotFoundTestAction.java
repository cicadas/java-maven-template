package template.test.exception;

/**
 * @author zhouzh
 *         Date: 8/14/13
 *         Time: 3:53 PM
 */
public class NotFoundTestAction extends Exception {

    public NotFoundTestAction() {
        super();
    }

    public NotFoundTestAction(String message) {
        super(message);
    }

    public NotFoundTestAction(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundTestAction(Throwable cause) {
        super(cause);
    }

    protected NotFoundTestAction(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
