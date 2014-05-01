package rb.home.blog.common.dao;

import rb.home.blog.common.domain.CommonEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Common dao interface.
 *
 * @author: HOME
 */
public interface CommonDao<CE extends CommonEntity, ID extends Serializable> {

    /**
     * Create in database
     *
     * @param entity
     * @return id of persisted entity
     */
    void create(CE entity);

    /**
     * Update entity in database
     *
     * @param entity entity to update
     * @return persisted entity
     */
    void update(CE entity);

    /**
     * Delete from database by id
     *
     * @param id entity's id to delete
     */
    void deleteById(ID id);

    /**
     * Find from database by id
     *
     * @param id
     * @return
     */
    CE findById(ID id);

    /**
     * Find all entries from database
     *
     * @return
     */
    List<CE> findAll();
}
