package rb.home.blog.persistence;

import org.testng.annotations.Test;
import rb.home.blog.helper.EntityCreationHelper;
import rb.home.blog.common.persistence.BaseDaoTest;
import rb.home.blog.domain.Comment;
import rb.home.blog.domain.Post;

import java.util.List;

import static org.testng.Assert.*;

/**
 * Test for Comment
 *
 * @author: HOME
 */
public class CommentDaoTest extends BaseDaoTest {

    @Test
    public void testCRUD() {
        Post post = EntityCreationHelper.createPost(TEST_HEADER, TEST_TEXT);
        postDao.create(post);
        Comment comment = EntityCreationHelper.createComment(COMMENT, post);
        commentDao.create(comment);
        Long commentId = comment.getId();
        assertNotNull(commentId);
        assertEquals(COMMENT, comment.getText());
        assertEquals(comment.getPost(), post);
        assertNotNull(comment.getDate());

        comment.setText(COMMENT_1);
        commentDao.update(comment);
        assertEquals(COMMENT_1, comment.getText());

        commentDao.deleteById(commentId);
        Comment deletedComment = commentDao.findById(commentId);
        assertNull(deletedComment);
    }

    @Test
    public void testFind() {
        List<Comment> originalComments = commentDao.findAll();
//        prepare comments
        Post post1 = EntityCreationHelper.createPost(TEST_HEADER, TEST_TEXT);
        Post post2 = EntityCreationHelper.createPost(COMMENT_1, COMMENT_2);
        postDao.create(post1);
        postDao.create(post2);
        Comment comment1 = EntityCreationHelper.createComment(COMMENT_1, post1);
        Comment comment2 = EntityCreationHelper.createComment(COMMENT_2, post1);
        Comment comment3 = EntityCreationHelper.createComment(COMMENT, post2);
        commentDao.create(comment1);
        commentDao.create(comment2);
        commentDao.create(comment3);


        List<Comment> allList = commentDao.findAll();

        assertEquals(allList.size(), originalComments.size() + 3);
        assertTrue(allList.contains(comment1));
        assertTrue(allList.contains(comment2));
        assertTrue(allList.contains(comment3));

        List<Comment> firstPostComments = commentDao.findByPost(post1.getId(), 0, -1);
        List<Comment> secondPostComments = commentDao.findByPost(post2.getId(), 0, -1);
        long firstCommentCount = commentDao.countByPost(post1.getId());
        long secondCommentCount = commentDao.countByPost(post2.getId());
        assertEquals(firstCommentCount, firstPostComments.size());
        assertEquals(2, firstPostComments.size());
        assertEquals(secondCommentCount, secondPostComments.size());
        assertEquals(1, secondPostComments.size());
    }
}
