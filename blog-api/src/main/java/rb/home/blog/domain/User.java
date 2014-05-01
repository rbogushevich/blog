package rb.home.blog.domain;

import rb.home.blog.common.domain.CommonEntity;

import java.util.List;

/**
 * User entity.
 *
 * @author HOME
 */
public interface User extends CommonEntity<Long> {

    /**
     * Return login.
     *
     * @return
     */
    String getLogin();

    /**
     * Set login.
     *
     * @param login
     */
    void setLogin(String login);

    /**
     * Return password/
     *
     * @return
     */
    String getPassword();

    /**
     * Set password.
     *
     * @param password
     */
    void setPassword(String password);

    /**
     * Return roles for user.
     *
     * @return
     */
    List<Role> getRoles();

    /**
     * Set roles for user.
     *
     * @param roles
     */
    void setRoles(List<Role> roles);
}
