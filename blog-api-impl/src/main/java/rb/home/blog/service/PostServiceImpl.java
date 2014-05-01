package rb.home.blog.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rb.home.blog.common.service.CommonServiceBase;
import rb.home.blog.dao.PostDao;
import rb.home.blog.domain.Post;

import java.util.List;

/**
 * Service implementation for {@link rb.home.blog.domain.Post}
 *
 * @author HOME
 * @see rb.home.blog.service.PostService PostService
 */
@Service
public class PostServiceImpl extends CommonServiceBase<Post, Long> implements PostService {
    private static final Logger logger = LoggerFactory.getLogger(PostServiceImpl.class);

    @Autowired
    public PostServiceImpl(PostDao postDao) {
        super(postDao);
    }

    @Override
    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public List<Post> find(int first, int count) {
        logger.debug("find posts from {} count {}", first, count);
        return ((PostDao) commonDao).find(first, count);
    }

    @Override
    @Transactional(readOnly = true)
    public Long count() {
        logger.debug("getting posts count");
        return ((PostDao) commonDao).count();
    }
}
