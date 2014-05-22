package template.core;

import java.util.Map;

/**
 * @author zhouzh
 */
public interface Convertible<K, V, C extends Map<K, V>> {
    boolean setValueByDataObject(C context) throws Exception;

    boolean decorateDataObject(C context) throws Exception;
}
