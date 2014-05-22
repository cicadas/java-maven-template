package template.data;


import template.core.InjectorManager;
import template.util.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: zhouzh
 * Date: 6/26/12
 * Time: 2:18 PM
 * This is a wrapper of MetaDBManager
 * New bow code will be using this class for query data from database
 */

public class TomcatDBManager implements DbManager {
    private final DataSource dataSource;
    private volatile static DbManager dbManager;

    public synchronized static Connection get() throws Exception {
        if (dbManager == null)
            dbManager = InjectorManager.getInstance(DbManagerModule.class, TomcatDBManager.class);
        return dbManager.getConnection();
    }

    public static void set(DbManager dbManager) {
        TomcatDBManager.dbManager = dbManager;
    }


    public TomcatDBManager() throws NamingException {
        Context initContext = new InitialContext();
        Context envContext = (Context) initContext.lookup("java:/comp/env");
        dataSource = (DataSource) envContext.lookup("jdbc/oracle");
        Logger.info("Initialized Oracle DataSource");
    }

    @Override
    public Connection getConnection() throws SQLException {
        Logger.debug("Get database connection from data source");
        return new ConnectionWrapper(dataSource.getConnection());
    }
}
