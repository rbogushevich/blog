package rb.home.blog.web.util.mapper;

import rb.home.blog.domain.Comment;
import rb.home.blog.domain.CommentImpl;
import rb.home.blog.domain.Post;
import rb.home.blog.domain.PostImpl;
import rb.home.blog.web.form.CommentForm;
import rb.home.blog.web.form.PostForm;

/**
 * Simply util for map for to entity.
 */
public final class WebMapperUtil {

    public static Post mapToPost(PostForm postForm) {
        Post post = new PostImpl();
        post.setId(postForm.getId());
        post.setHeader(postForm.getHeader());
        post.setText(postForm.getText());
        post.setAuthor(postForm.getAuthor());
        post.setDate(postForm.getDate());
        return post;
    }

    public static Comment mapToComment(CommentForm commentForm) {
        Comment comment = new CommentImpl();
        comment.setId(commentForm.getId());
        comment.setText(commentForm.getText());
        comment.setDate(commentForm.getDate());
        comment.setPost(commentForm.getPost());
        return comment;
    }
}
