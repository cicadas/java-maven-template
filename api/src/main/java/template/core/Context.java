package template.core;

import java.util.Map;

/**
 * @author zhouzh
 *         The contest object is a global variant when workflow running.  All shared information are in the context.
 */
public interface Context<K, V> extends Map<K, V> {
    <T extends V> T retrieve(K key);
}
