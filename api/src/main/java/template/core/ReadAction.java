package template.core;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: zhouzh
 * Date: 1/29/13
 * Time: 10:14 AM
 * Read command object.
 */
public interface ReadAction<K, V, C extends Map<K, V>> {
    boolean read(C context) throws Exception;
}
