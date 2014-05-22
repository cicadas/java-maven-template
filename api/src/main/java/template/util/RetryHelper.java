package template.util;

/**
 * @author zhouzh
 */
public final class RetryHelper {
    public static boolean retryByTimer(Retryer retryer, int count) throws Exception {
        Logger.info("Start retry...");
        int ongoing = 0;
        boolean isDone = false;
        boolean isTimeout = false;
        Exception temp = null;
        while (!isDone && !isTimeout) try {
            isDone = retryer.isDone();
        } catch (Exception e) {
            temp = e;
            e.printStackTrace();
            Logger.error(e.getMessage());
            isDone = false;
            isTimeout = false;
            retryer.postExceptionAction(e);
        } finally {
            ongoing++;
            if (ongoing >= count)
                isTimeout = true;
            Logger.info("Retry count:" + ongoing);
        }
        if (isTimeout && temp != null)
            throw temp;
        Logger.info("End retry.  isDone:" + isDone + " isTimeout:" + isTimeout);
        return isDone && !isTimeout;
    }
}

