package rb.home.blog.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rb.home.blog.dao.UserDao;
import rb.home.blog.domain.Role;

import java.util.ArrayList;
import java.util.List;

/**
 * User detail service
 *
 * @author: HOME
 */
@Service
public class UserDetailService implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(UserDetailService.class);

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        logger.info("load user by login {}", login);
        rb.home.blog.domain.User user = userDao.findByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException("No such user: " + login);
        } else if (user.getRoles().isEmpty()) {
            throw new UsernameNotFoundException("User " + login + " has no authorities");
        }

        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        boolean isEnabled = true;

        logger.info("user with login {} logged successful. user roles: {}", login, user.getRoles());
        return new User(
                user.getLogin(),
                user.getPassword(),
                isEnabled,
                accountNonExpired,
                credentialsNonExpired,
                accountNonLocked,
                getAuthorities(user.getRoles()));
    }

    private List<GrantedAuthority> getAuthorities(List<Role> roles) {
        List<GrantedAuthority> result = new ArrayList<>();
        for (Role role : roles) {
            result.add(new org.springframework.security.core.authority.SimpleGrantedAuthority(role.getName()));
        }
        return result;
    }
}
