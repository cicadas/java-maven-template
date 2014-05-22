package template.core;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created with IntelliJ IDEA.
 * User: zhouzh
 * Date: 1/16/13
 * Time: 11:04 AM
 * Singleton helper for injector
 */
@ThreadSafe
public class InjectorManager {
    private final static ConcurrentHashMap<Class<? extends AbstractModule>, Injector> injectors = new ConcurrentHashMap<>();

    public static Injector get(Class<? extends AbstractModule> type) throws IllegalAccessException, InstantiationException {
        if (!injectors.contains(type)) {
            injectors.put(type, Guice.createInjector(type.newInstance()));
        }
        return injectors.get(type);
    }

    public static <T> T getInstance(Class<? extends AbstractModule> type, Class<T> newInstance) throws IllegalAccessException, InstantiationException {
        return get(type).getInstance(newInstance);
    }
}

