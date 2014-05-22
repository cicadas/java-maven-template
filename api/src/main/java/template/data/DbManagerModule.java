/*
 * Created by IntelliJ IDEA.
 * User: zhouzh
 * Date: 6/26/12
 * Time: 2:34 PM
 */
package template.data;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

/**
 * This class is for new database access layer
 */
public class DbManagerModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(DbManager.class).to(TomcatDBManager.class).in(Singleton.class);
    }
}
