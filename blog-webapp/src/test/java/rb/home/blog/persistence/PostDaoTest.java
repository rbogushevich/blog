package rb.home.blog.persistence;

import org.springframework.security.core.context.SecurityContextHolder;
import org.testng.annotations.Test;
import rb.home.blog.common.persistence.BaseDaoTest;
import rb.home.blog.domain.Comment;
import rb.home.blog.domain.Post;
import rb.home.blog.helper.EntityCreationHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

/**
 * Test for Post.
 *
 * @author: HOME
 */
public class PostDaoTest extends BaseDaoTest {

    @Test
    public void testCRUD() {
        Post post = EntityCreationHelper.createPost(TEST_HEADER, TEST_TEXT);
        postDao.create(post);
        assertNotNull(post.getId());

        addComments(post, Arrays.asList(COMMENT_1, COMMENT_2));

        Post postFromDB = postDao.findById(post.getId());
        postFromDB.setComments(commentDao.findByPost(postFromDB.getId(), 0, -1));
        assertNotNull(postFromDB.getDate());
        assertEquals(postFromDB.getHeader(), TEST_HEADER);
        assertEquals(postFromDB.getText(), TEST_TEXT);

        assertEquals(postFromDB.getComments().size(), 2);
        assertEquals(SecurityContextHolder.getContext().getAuthentication().getPrincipal(), postFromDB.getAuthor());

        postFromDB.setText(TEST_HEADER);
        postFromDB.setHeader(TEST_TEXT);
        postDao.update(postFromDB);
        assertEquals(postFromDB.getHeader(), TEST_TEXT);
        assertEquals(postFromDB.getText(), TEST_HEADER);


        Long postFromDBId = postFromDB.getId();
        assertNotNull(postFromDBId);

        List<Comment> comments = postFromDB.getComments();
        for (Comment comment : comments) {
            assertNotNull(comment.getId());
        }
        postDao.deleteById(postFromDBId);

        Post deletedPost = postDao.findById(postFromDBId);
        assertNull(deletedPost);

        for (Comment comment : comments) {
            assertNull(commentDao.findById(comment.getId()));
        }
    }

    @Test
    public void testFind() {
        List<Post> originalPosts = postDao.findAll();
        Long originalCount = postDao.count();
        assertEquals(originalCount.intValue(), originalPosts.size());
        if (originalCount > 0) {
            List<Post> postWithLimit = postDao.find(0, originalCount.intValue() - 1);
            assertEquals(originalCount.intValue() - 1, postWithLimit.size());
        }
    }

    private void addComments(Post post, List<String> comments) {
        post.setComments(new ArrayList<Comment>());
        for (String commentText : comments) {
            Comment comment = EntityCreationHelper.createComment(commentText, post);
            commentDao.create(comment);
            post.getComments().add(comment);
        }
    }
}
