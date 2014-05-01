package rb.home.blog.dao;

import org.apache.ibatis.annotations.Param;
import rb.home.blog.common.dao.CommonDao;
import rb.home.blog.domain.Post;

import java.util.List;

/**
 * Service for {@link rb.home.blog.domain.Post Post}}
 *
 * @author HOME
 */
public interface PostDao extends CommonDao<Post, Long> {

    String START_PARAM = "start";
    String COUNT_PARAM = "count";

    /**
     * Return posts with limit.
     *
     * @param start
     * @param count
     * @return
     */
    List<Post> find(@Param(START_PARAM) int start, @Param(COUNT_PARAM) int count);

    /**
     * Get count of posts.
     *
     * @return
     */
    Long count();
}
