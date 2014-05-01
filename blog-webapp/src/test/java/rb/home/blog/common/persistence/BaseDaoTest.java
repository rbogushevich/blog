package rb.home.blog.common.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.testng.annotations.BeforeClass;
import rb.home.blog.helper.EntityCreationHelper;
import rb.home.blog.dao.CommentDao;
import rb.home.blog.dao.PostDao;
import rb.home.blog.dao.UserDao;
import rb.home.blog.domain.User;

import java.util.Arrays;

/**
 * Base test class for dao.
 *
 * @author: HOME
 */
@ContextConfiguration(locations = {"classpath:spring-global.xml"})
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
public class BaseDaoTest extends AbstractTransactionalTestNGSpringContextTests {
    protected static final String TEST_LOGIN = "test_login";
    protected static final String LOGIN = "login";
    protected static final String TEST_PASSWORD = "test_password";
    protected static final String PASSWORD = "password";
    protected static final String TEST_ROLE = "TEST_ROLE";
    protected static final String TEST_HEADER = "test_header";
    protected static final String TEST_TEXT = "test_text";
    protected static final String COMMENT = "comment";
    protected static final String COMMENT_1 = "comment1";
    protected static final String COMMENT_2 = "comment2";

    @Autowired
    protected PostDao postDao;
    @Autowired
    protected CommentDao commentDao;
    @Autowired
    protected UserDao userDao;

    @BeforeClass
    public void loginUser() {
        User user = EntityCreationHelper.createUser(LOGIN, PASSWORD);
        userDao.create(user);
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user, Arrays.asList(TEST_ROLE)));
    }
}
