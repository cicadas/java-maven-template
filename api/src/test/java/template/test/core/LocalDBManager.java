package template.test.core;

import template.test.util.TestConfig;
import oracle.jdbc.pool.OracleDataSource;
import template.core.InjectorManager;
import template.data.DbManager;
import template.data.TomcatDBManager;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author zhouzh
 *         Date: 8/9/13
 *         Time: 1:49 PM
 */
public class LocalDBManager implements DbManager {
    @Override
    public Connection getConnection() throws SQLException {
        OracleDataSource ods = new OracleDataSource();
        ods.setUser(TestConfig.INSTANCE.get(TestConfig.DB_USERNAME));
        ods.setPassword(TestConfig.INSTANCE.get(TestConfig.DB_PASSWORD));
        ods.setURL(TestConfig.INSTANCE.get(TestConfig.DB_CONNECTION_STRING));
        return new net.sf.log4jdbc.ConnectionSpy(ods.getConnection());
//        return ods.getConnection();
    }

    public static void setLocalDBDriver() throws InstantiationException, IllegalAccessException {
        DbManager manager = InjectorManager.getInstance(LocalDBModule.class, LocalDBManager.class);
        TomcatDBManager.set(manager);
    }
}
