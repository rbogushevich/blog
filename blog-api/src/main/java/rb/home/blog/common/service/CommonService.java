package rb.home.blog.common.service;

import rb.home.blog.common.domain.CommonEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Common interface for common service
 *
 * @author: HOME
 */
public interface CommonService<CE extends CommonEntity, ID extends Serializable> {
    /**
     * Create in database
     *
     * @param entity
     */
    void create(CE entity);

    /**
     * Update entity in database
     *
     * @param entity entity to update
     */
    void update(CE entity);

    /**
     * Delete from database by id
     *
     * @param id entity's id to delete
     */
    void delete(ID id);

    /**
     * Find from database by id
     *
     * @param id
     * @return entity
     */
    CE findById(ID id);

    /**
     * Find all entries from database
     *
     * @return
     */
    List<CE> findAll();
}
