package rb.home.blog.domain;

import rb.home.blog.common.domain.CommonEntityBase;

import java.util.Date;

/**
 * Comment entity implementation.
 *
 * @author HOME
 * @see rb.home.blog.domain.Comment Comment
 */
public class CommentImpl extends CommonEntityBase implements Comment {
    private static final long serialVersionUID = -8024327583192434386L;

    private String text;
    private Date date;
    private Post post;

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

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
