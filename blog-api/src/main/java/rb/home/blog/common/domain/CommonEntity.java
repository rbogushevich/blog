package rb.home.blog.common.domain;

import java.io.Serializable;

/**
 * Common interface for business entity
 *
 * @author: HOME
 */
public interface CommonEntity<ID extends Serializable> extends Serializable {

    ID getId();

    void setId(ID id);
}
