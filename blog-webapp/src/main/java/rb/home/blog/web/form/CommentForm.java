package rb.home.blog.web.form;

import rb.home.blog.domain.Post;

import javax.faces.bean.ManagedBean;
import java.util.Date;

/**
 * Bean for Comment form
 */
@ManagedBean
public class CommentForm {

    private Long id;
    private String text;
    private Date date;
    private Post post;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
