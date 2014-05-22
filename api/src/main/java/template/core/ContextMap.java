package template.core;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhouzh
 *         Used as context object for all activities between controler/dao.
 */
public class ContextMap<K, V> extends ConcurrentHashMap<K, V> implements Context<K, V> {
    /**
     * Creates a new, empty Context with a default initial capacity, load factor, and concurrencyLevel.
     */
    public ContextMap() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends V> T retrieve(K key) {
        return (T) get(key);
    }
}
