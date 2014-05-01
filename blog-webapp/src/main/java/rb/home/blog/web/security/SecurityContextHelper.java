package rb.home.blog.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import rb.home.blog.dao.UserDao;
import rb.home.blog.domain.User;

/**
 * @author: HOME
 */
@Service
public class SecurityContextHelper {
    @Autowired
    private UserDao userDao;

    public User getCurrentUser() {
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)
                SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDao.findByLogin(user.getUsername());
    }
}
