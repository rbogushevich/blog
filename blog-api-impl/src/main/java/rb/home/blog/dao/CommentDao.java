package rb.home.blog.dao;

import org.apache.ibatis.annotations.Param;
import rb.home.blog.common.dao.CommonDao;
import rb.home.blog.domain.Comment;

import java.util.List;

/**
 * Service for {@link rb.home.blog.domain.Comment Comment}}
 *
 * @author HOME
 */
public interface CommentDao extends CommonDao<Comment, Long> {
    /**
     * Find comments for post with id {@code postId} and with limit.
     *
     * @param postId
     * @param start
     * @param count
     * @return
     */
    List<Comment> findByPost(@Param("postId") Long postId, @Param("start") int start, @Param("count") int count);

    /**
     * Get count of comments for post.
     *
     * @return
     */
    Long countByPost(Long postId);
}
