package template.core;

/**
 * Created with IntelliJ IDEA.
 * User: zhouzh
 * Date: 12/26/12
 * Time: 1:26 PM
 * User object for segments and TWS system
 */
public class User {
    public final static String DEFAULT_NAME = "apimeta";
    public final static User DEFAULT_USER = new User(DEFAULT_NAME);
    private String userName;

    public User() {
        userName = DEFAULT_NAME;
    }

    public User(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
}
