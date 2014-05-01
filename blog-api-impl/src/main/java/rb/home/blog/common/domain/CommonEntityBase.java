package rb.home.blog.common.domain;

/**
 * Base class for {@link rb.home.blog.common.domain.CommonEntity}
 *
 * @author: HOME
 */
public abstract class CommonEntityBase implements CommonEntity<Long> {
    private static final long serialVersionUID = 1L;

    /**
     * Id.
     */
    private Long id;

    /**
     * @return entity id
     */
    public Long getId() {
        return id;
    }

    /**
     * Set entity id.
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        CommonEntityBase that = (CommonEntityBase) obj;
        return getId() != null && getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : super.hashCode();
    }
}
