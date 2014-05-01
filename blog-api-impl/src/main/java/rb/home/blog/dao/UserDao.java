package rb.home.blog.dao;

import rb.home.blog.common.dao.CommonDao;
import rb.home.blog.domain.User;

/**
 * Service for {@link rb.home.blog.domain.User User}}
 *
 * @author HOME
 */
public interface UserDao extends CommonDao<User, Long> {

    /**
     * @param login
     * @return user by login.
     */
    User findByLogin(String login);
}
