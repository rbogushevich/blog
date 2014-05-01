package rb.home.blog.domain;

import rb.home.blog.common.domain.CommonEntityBase;

import java.util.Date;
import java.util.List;

/**
 * Implementation of post.
 *
 * @author HOME
 * @see rb.home.blog.domain.Post Post
 */
public class PostImpl extends CommonEntityBase implements Post {
    private static final long serialVersionUID = -3946062465580107669L;

    private String header;
    private String text;
    private Date date;
    private User author;
    private List<Comment> comments;


    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

}
