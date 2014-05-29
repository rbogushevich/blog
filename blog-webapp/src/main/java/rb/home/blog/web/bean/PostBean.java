package rb.home.blog.web.bean;

import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rb.home.blog.domain.Comment;
import rb.home.blog.domain.Post;
import rb.home.blog.service.CommentService;
import rb.home.blog.service.PostService;
import rb.home.blog.web.form.CommentForm;
import rb.home.blog.web.util.mapper.WebMapperUtil;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author: HOME
 */

@ManagedBean
@ViewScoped
public class PostBean implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(PostBean.class);

    private static final String POST_ID = "postId";
    private static final String ADD_FORM_DIALOG_ID = "addForm:dialog";

    private LazyDataModel<Comment> model;
    private Post post;
    private CommentForm commentForm;

    @ManagedProperty(value = "#{postServiceImpl}")
    private PostService postService;

    @ManagedProperty(value = "#{commentServiceImpl}")
    private CommentService commentService;


    @PostConstruct
    private void init() {
        String postAsString = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(POST_ID);
        Long postId = Long.valueOf(postAsString);
        post = postService.findById(postId);
        model = new CommentLazyDataModel();
        logger.debug("post page init with post id = {}", postAsString);
    }

    public void onEdit(RowEditEvent event) {
        commentService.update((Comment) event.getObject());
    }

    public void delete(Comment comment) {
        commentService.delete(comment.getId());
    }

    public void initAddForm() {
        RequestContext.getCurrentInstance().reset(ADD_FORM_DIALOG_ID);
        commentForm = new CommentForm();
        commentForm.setDate(new Date());
        commentForm.setPost(post);
    }

    public void create() {
        Comment comment = WebMapperUtil.mapToComment(commentForm);
        commentService.create(comment);
    }

    public LazyDataModel<Comment> getModel() {
        return model;
    }

    public void setModel(LazyDataModel<Comment> model) {
        this.model = model;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public PostService getPostService() {
        return postService;
    }

    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    public CommentForm getCommentForm() {
        return commentForm;
    }

    public void setCommentForm(CommentForm commentForm) {
        this.commentForm = commentForm;
    }

    public CommentService getCommentService() {
        return commentService;
    }

    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    public class CommentLazyDataModel extends LazyDataModel<Comment> {
        private List<Comment> comments;

        @Override
        public Comment getRowData(String rowKey) {
            Comment result = null;
            for (Comment comment : comments) {
                if (comment.getId().toString().equals(rowKey)) {
                    result = comment;
                    break;
                }
            }
            return result;
        }

        @Override
        public Long getRowKey(Comment post) {
            return post.getId();
        }

        @Override
        public List<Comment> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
            logger.debug("load comments for post page with params: first {}, pageSize {}, sortField {}, sortOrder {}, filters{}",
                    first, pageSize, sortField, sortOrder, filters);
            comments = getCommentService().findByPost(post.getId(), first, pageSize);
            setRowCount(getCommentService().countByPost(post.getId()).intValue());
            return comments;
        }
    }
}