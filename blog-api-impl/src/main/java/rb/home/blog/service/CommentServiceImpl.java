package rb.home.blog.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rb.home.blog.common.service.CommonServiceBase;
import rb.home.blog.dao.CommentDao;
import rb.home.blog.domain.Comment;

import java.util.List;

/**
 * Service implementation for {@link rb.home.blog.domain.Comment}
 *
 * @author HOME
 * @see rb.home.blog.service.CommentService CommentService
 */
@Service
public class CommentServiceImpl extends CommonServiceBase<Comment, Long> implements CommentService {
    private static final Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);

    @Autowired
    public CommentServiceImpl(CommentDao commentDao) {
        super(commentDao);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> findByPost(Long postId, int start, int count) {
        logger.debug("find comments by post with id = {} from {} count {}", new Object[]{postId, start, count});
        return ((CommentDao) commonDao).findByPost(postId, start, count);
    }

    @Override
    @Transactional(readOnly = true)
    public Long countByPost(Long postId) {
        logger.debug("count comments by post with id = {}", postId);
        return ((CommentDao) commonDao).countByPost(postId);
    }
}
