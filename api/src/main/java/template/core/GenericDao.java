package template.core;

/**
 * Created with IntelliJ IDEA.
 * User: zhouzh
 * Date: 12/28/12
 * Time: 2:35 PM
 * CRUD operations for DAO layer
 */
public interface GenericDao<T> {
    /**
     * Create current object in persistence store
     * the input object will be decorated after call finished
     *
     * @param in input object to create
     * @return object with latest status
     */
    void create(final T in) throws Exception;

    /**
     * Get the object from persistence store
     * the input object will be decorated after call finished
     *
     * @param in identifier of the object
     * @return the object
     */
    void read(final T in) throws Exception;

    /**
     * Update object, pk should be included
     *
     * @param in input object for updating
     */
    void update(final T in) throws Exception;

    /**
     * Remove object for persistence store
     *
     * @param in the matcher object for search
     */
    void delete(final T in) throws Exception;

}
