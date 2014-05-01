package rb.home.blog.domain;

import rb.home.blog.common.domain.CommonEntity;

/**
 * Role entity.
 *
 * @author HOME
 */
public interface Role extends CommonEntity<Long> {

    /**
     * Return role name.
     *
     * @return
     */
    String getName();

    /**
     * Set role name.
     *
     * @param name
     */
    void setName(String name);
}
