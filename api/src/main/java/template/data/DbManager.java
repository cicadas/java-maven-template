package template.data;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: zhouzh
 * Date: 6/26/12
 * Time: 2:08 PM
 * Need this interface to mock DB manager for testing
 */
public interface DbManager {
    Connection getConnection() throws SQLException;
}
