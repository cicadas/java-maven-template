package template.core;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: zhouzh
 * Date: 1/29/13
 * Time: 10:16 AM
 * To change this template use File | Settings | File Templates.
 */
public interface DeleteAction<K, V, C extends Map<K, V>> {
    boolean delete(C context) throws Exception;

    boolean undoDelete(C context) throws Exception;
}
