package template.util;

/**
 * @author zhouzh
 */

public abstract class Retryer {
    public abstract boolean isDone() throws Exception;

    public abstract void postExceptionAction(final Exception ex) throws Exception;
}
