package template.core;

import java.util.Map;

/**
 * @author zhouzh
 *         Provide update operations
 */
public interface UpdateAction<K, V, C extends Map<K, V>> {
    boolean update(C context) throws Exception;

    boolean undoUpdate(C context) throws Exception;
}
