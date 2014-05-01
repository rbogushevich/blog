package rb.home.blog.domain;

import rb.home.blog.common.domain.CommonEntityBase;

/**
 * Implementation of user entity.
 *
 * @author HOME
 */
public class RoleImpl extends CommonEntityBase implements Role {
    private static final long serialVersionUID = -2288850789050591020L;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getName();
    }
}
