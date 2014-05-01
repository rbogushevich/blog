package rb.home.blog.persistence;

import org.testng.annotations.Test;
import rb.home.blog.helper.EntityCreationHelper;
import rb.home.blog.common.persistence.BaseDaoTest;
import rb.home.blog.domain.User;

import java.util.List;

import static org.testng.Assert.*;

/**
 * Test for UserDao
 *
 * @author: HOME
 */
public class UserDaoTest extends BaseDaoTest {

    @Test
    public void testCRUD() {
        User user = EntityCreationHelper.createUser(TEST_LOGIN, TEST_PASSWORD);
        userDao.create(user);
        assertNotNull(user.getId());
        User userFromDB = userDao.findById(user.getId());
        assertEquals(TEST_LOGIN, userFromDB.getLogin());
        assertEquals(TEST_PASSWORD, user.getPassword());

        User userByLogin = userDao.findByLogin(TEST_LOGIN);
        assertEquals(userByLogin.getId(), userFromDB.getId());

        userFromDB.setLogin(TEST_PASSWORD);
        userFromDB.setPassword(TEST_LOGIN);
        userDao.update(userFromDB);

        userFromDB = userDao.findByLogin(TEST_PASSWORD);
        assertNotNull(userFromDB);
        assertEquals(TEST_LOGIN, userFromDB.getPassword());

        Long userFromDBId = userFromDB.getId();
        userDao.deleteById(userFromDBId);
        User deletedUserFromDB = userDao.findById(userFromDBId);
        assertNull(deletedUserFromDB);
    }


    @Test
    public void testFind() {
        List<User> originalUsers = userDao.findAll();
        User user1 = EntityCreationHelper.createUser(TEST_LOGIN, TEST_PASSWORD);
        User user2 = EntityCreationHelper.createUser(TEST_PASSWORD, TEST_LOGIN);
        userDao.create(user1);
        userDao.create(user2);
        List<User> updatedUsers = userDao.findAll();
        assertEquals(originalUsers.size() + 2, updatedUsers.size());
    }
}
