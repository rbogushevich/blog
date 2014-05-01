package rb.home.blog.common.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import rb.home.blog.common.dao.CommonDao;
import rb.home.blog.common.domain.CommonEntity;

import java.util.List;

/**
 * Common service.
 *
 * @author: HOME
 */
public abstract class CommonServiceBase<CE extends CommonEntity, ID extends Long> implements CommonService<CE, ID> {
    private static final Logger logger = LoggerFactory.getLogger(CommonServiceBase.class);

    protected CommonDao commonDao;

    public CommonServiceBase(CommonDao commonDao) {
        this.commonDao = commonDao;
    }

    @Override
    @Transactional
    public void create(CE entity) {
        commonDao.create(entity);
        logger.debug("entity with id = {} was created. Entity class is {}", entity.getId(), entity.getClass());
    }

    @Override
    @Transactional
    public void update(CE entity) {
        commonDao.update(entity);
        logger.debug("entity with id = {} was updated. Entity class is {}", entity.getId(), entity.getClass());
    }

    @Override
    @Transactional
    public void delete(ID id) {
        commonDao.deleteById(id);
        logger.debug("entity with id = {} was deleted.", id);
    }

    @Override
    @Transactional(readOnly = true)
    public CE findById(ID id) {
        logger.debug("find by id = ", id);
        return (CE) commonDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CE> findAll() {
        logger.debug("find all");
        return (List<CE>) commonDao.findAll();
    }
}
