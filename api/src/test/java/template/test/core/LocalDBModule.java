package template.test.core;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import template.data.DbManager;

/**
 * @author zhouzh
 *         Date: 8/9/13
 *         Time: 1:50 PM
 */
public class LocalDBModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(DbManager.class).to(LocalDBManager.class).in(Singleton.class);
    }
}
