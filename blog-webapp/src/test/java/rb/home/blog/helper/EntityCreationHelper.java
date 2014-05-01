package rb.home.blog.helper;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import rb.home.blog.domain.Comment;
import rb.home.blog.domain.CommentImpl;
import rb.home.blog.domain.Post;
import rb.home.blog.domain.PostImpl;
import rb.home.blog.domain.User;
import rb.home.blog.domain.UserImpl;

import java.util.Date;

/**
 * @author: HOME
 */
@Service
public class EntityCreationHelper {
    public static User createUser(String login, String password) {
        User user = new UserImpl();
        user.setLogin(login);
        user.setPassword(password);
        return user;
    }

    public static Post createPost(String header, String text) {
        Post post = new PostImpl();
        post.setHeader(header);
        post.setText(text);
        post.setDate(new Date());
        post.setAuthor((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return post;
    }

    public static Comment createComment(String text, Post post) {
        Comment comment = new CommentImpl();
        comment.setDate(new Date());
        comment.setText(text);
        comment.setPost(post);
        return comment;
    }

}
