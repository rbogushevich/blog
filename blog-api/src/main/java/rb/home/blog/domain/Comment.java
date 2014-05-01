package rb.home.blog.domain;

import rb.home.blog.common.domain.CommonEntity;

import java.util.Date;

/**
 * Comment entity.
 *
 * @author HOME
 */
public interface Comment extends CommonEntity<Long> {

    /**
     * @return Body of comment.
     */
    String getText();

    /**
     * Set comment body.
     *
     * @param text
     */
    void setText(String text);

    /**
     * Get creation date.
     *
     * @return
     */
    Date getDate();

    /**
     * Set creation date.
     *
     * @param date
     */
    void setDate(Date date);

    /**
     * @return relevant post.
     */
    Post getPost();

    /**
     * Set relevant post.
     *
     * @param post
     */
    void setPost(Post post);
}
