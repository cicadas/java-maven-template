package template.core;


import java.util.Map;

public interface CreateAction<K, V, C extends Map<K, V>> {
    boolean create(C context) throws Exception;

    boolean undoCreate(C context) throws Exception;
}
