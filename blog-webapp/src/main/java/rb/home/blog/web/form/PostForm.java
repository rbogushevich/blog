package rb.home.blog.web.form;

import rb.home.blog.domain.User;

import javax.faces.bean.ManagedBean;
import java.util.Date;

/**
 * Bean for Post form
 */
@ManagedBean
public class PostForm {

    private Long id;
    private String header;
    private String text;
    private Date date;
    private User author;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
}
