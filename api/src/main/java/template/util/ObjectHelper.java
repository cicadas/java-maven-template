package template.util;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;

public class ObjectHelper {
    /**
     * Copy POJO data form source to destination
     *
     * @param source      POJO
     * @param destination POJO
     * @param <T>         custom type
     * @throws IntrospectionException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static <T> void copy(T source, T destination) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        for (PropertyDescriptor propertyDescriptor : Introspector.getBeanInfo(source.getClass()).getPropertyDescriptors()) {
            if (propertyDescriptor.getReadMethod() != null &&
                    propertyDescriptor.getWriteMethod() != null &&
                    !"class".equals(propertyDescriptor.getName())) {
                propertyDescriptor.getWriteMethod().invoke(destination,
                        propertyDescriptor.getReadMethod().invoke(source));
            }
        }
    }
}
