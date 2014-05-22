package template.exception;

/**
 * Created with IntelliJ IDEA.
 * User: dlzhu
 * Date: 13-10-16
 * Time: AM10:53
 * To change this template use File | Settings | File Templates.
 */
public class BowAttributeNotExistException extends Exception {

    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public BowAttributeNotExistException(String message) {
        super(message);
    }
}
