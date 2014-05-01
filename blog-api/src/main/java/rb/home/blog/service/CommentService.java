package rb.home.blog.service;

import rb.home.blog.common.service.CommonService;
import rb.home.blog.domain.Comment;

import java.util.List;

/**
 * Service for {@link rb.home.blog.domain.Comment Comment}
 *
 * @author HOME
 */
public interface CommentService extends CommonService<Comment, Long> {
    /**
     * Find comments for post with id {@code postId} and with limit.
     *
     * @param postId
     * @param start
     * @param count
     * @return
     */
    List<Comment> findByPost(Long postId, int start, int count);

    /**
     * Get count of comments for post.
     *
     * @param postId
     * @return
     */
    Long countByPost(Long postId);
}
