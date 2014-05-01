package rb.home.blog.domain;

import rb.home.blog.common.domain.CommonEntityBase;

import java.util.List;

/**
 * Implementation of user entity.
 *
 * @author HOME
 */
public class UserImpl extends CommonEntityBase implements User {
    private static final long serialVersionUID = -2288850789050591020L;

    private String login;
    private String password;
    List<Role> roles;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
