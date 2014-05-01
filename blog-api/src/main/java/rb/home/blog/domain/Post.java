package rb.home.blog.domain;

import rb.home.blog.common.domain.CommonEntity;

import java.util.Date;
import java.util.List;

/**
 * Post of blog.
 *
 * @author HOME
 */
public interface Post extends CommonEntity<Long> {

    /**
     * @return get header of post.
     */
    String getHeader();

    /**
     * Set header for post.
     *
     * @param header
     */
    void setHeader(String header);

    /**
     * @return mesage of post.
     */
    String getText();

    /**
     * set message for post
     *
     * @param text
     */
    void setText(String text);

    /**
     * @return Creation date.
     */
    Date getDate();

    /**
     * Set creation date.
     *
     * @param date
     */
    void setDate(Date date);

    /**
     * @return author of post.
     */
    User getAuthor();

    /**
     * Set author of post.
     *
     * @param author
     */
    void setAuthor(User author);

    /**
     * @return comments list.
     */
    List<Comment> getComments();

    /**
     * Set comments list.
     *
     * @param comments
     */
    void setComments(List<Comment> comments);
}
