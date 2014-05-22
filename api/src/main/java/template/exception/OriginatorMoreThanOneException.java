package template.exception;

/**
 * Created with IntelliJ IDEA.
 * User: taoluo
 * Date: 9/10/13
 * Time: 2:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class OriginatorMoreThanOneException extends Exception {

    public OriginatorMoreThanOneException(String message) {
        super(message);
    }
}
