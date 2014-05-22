package template.core;

/**
 * Created with IntelliJ IDEA.
 * User: zhouzh
 * Date: 12/26/12
 * Time: 1:54 PM
 * Original for BCookie and LCookie support
 */
public class Cookies {
    private boolean isBCookieSupported = true;
    private boolean isLCookieSupported = false;

    public boolean isBCookieSupported() {
        return isBCookieSupported;
    }

    public void setBCookieSupported(final boolean BCookieSupported) {
        isBCookieSupported = BCookieSupported;
    }

    public boolean isLCookieSupported() {
        return isLCookieSupported;
    }

    public void setLCookieSupported(final boolean LCookieSupported) {
        isLCookieSupported = LCookieSupported;
    }
}
