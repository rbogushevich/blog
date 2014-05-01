package rb.home.blog.service;

import rb.home.blog.common.service.CommonService;
import rb.home.blog.domain.Post;

import java.util.List;

/**
 * Service for {@link Post Post}
 *
 * @author HOME
 */
public interface PostService extends CommonService<Post, Long> {

    /**
     * Return posts with limit.
     *
     * @param start
     * @param count
     * @return
     */
    List<Post> find(int start, int count);

    /**
     * Get count of posts.
     *
     * @return
     */
    Long count();
}
