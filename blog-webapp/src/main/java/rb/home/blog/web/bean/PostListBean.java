package rb.home.blog.web.bean;

import org.primefaces.context.RequestContext;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rb.home.blog.domain.Post;
import rb.home.blog.domain.User;
import rb.home.blog.service.PostService;
import rb.home.blog.web.form.PostForm;
import rb.home.blog.web.security.SecurityContextHelper;
import rb.home.blog.web.util.mapper.WebMapperUtil;
import rb.home.blog.web.util.redirect.RedirectHelper;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Bean for posts list form
 *
 * @author: HOME
 */

@ManagedBean
@ViewScoped
public class PostListBean implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(PostListBean.class);
    private static final String ADD_FORM_DIALOG_ID = "addForm:dialog";

    private LazyDataModel<Post> model;
    private Post selectedItem;
    private PostForm postForm;
    private boolean editMode;

    @ManagedProperty(value = "#{postServiceImpl}")
    private PostService postService;

    @ManagedProperty(value = "#{securityContextHelper}")
    private SecurityContextHelper securityContextHelper;

    public SecurityContextHelper getSecurityContextHelper() {
        return securityContextHelper;
    }

    public void setSecurityContextHelper(SecurityContextHelper securityContextHelper) {
        this.securityContextHelper = securityContextHelper;
    }

    public PostListBean() {
        model = new PostLazyDataModel();
    }

    public void addFormSubmit() {
        Post post = WebMapperUtil.mapToPost(postForm);
        if (editMode) {
            postService.update(post);
        } else {
            postService.create(post);
        }
    }

    public void redirectToPost() {
        RedirectHelper.redirectToPost(selectedItem.getId());
    }

    public void delete() {
        postService.delete(selectedItem.getId());
        selectedItem = null;
    }

    public void prepareAddForm() {
        initPostForm();
        User user = securityContextHelper.getCurrentUser();
        postForm.setAuthor(user);
        editMode = false;
    }

    public void prepareEditForm() {
        initPostForm();
        postForm.setHeader(selectedItem.getHeader());
        postForm.setText(selectedItem.getText());
        postForm.setAuthor(selectedItem.getAuthor());
        postForm.setId(selectedItem.getId());
        editMode = true;
    }

    public void unselect() {
        setSelectedItem(null);
    }

    private void initPostForm() {
        RequestContext.getCurrentInstance().reset(ADD_FORM_DIALOG_ID);
        postForm = new PostForm();
        postForm.setDate(new Date());
    }

    public LazyDataModel<Post> getModel() {
        return model;
    }

    public void setModel(LazyDataModel<Post> model) {
        this.model = model;
    }

    public Post getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(Post selectedItem) {
        this.selectedItem = selectedItem;
    }

    public PostService getPostService() {
        return postService;
    }

    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    public PostForm getPostForm() {
        return postForm;
    }

    public void setPostForm(PostForm postForm) {
        this.postForm = postForm;
    }

    public boolean isEditMode() {
        return editMode;
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }

    public class PostLazyDataModel extends LazyDataModel<Post> {
        private List<Post> posts;

        @Override
        public Post getRowData(String rowKey) {
            Post result = null;
            for (Post post : posts) {
                if (post.getHeader().equals(rowKey)) {
                    result = post;
                    break;
                }
            }
            return result;
        }

        @Override
        public Object getRowKey(Post post) {
            return post.getHeader();
        }

        @Override
        public List<Post> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
            logger.debug("load posts for postList page with params: first {}, pageSize {}, sortField {}, sortOrder {}, filters{}",
                    new Object[]{first, pageSize, sortField, sortOrder, filters});
            posts = getPostService().find(first, pageSize);
            setRowCount(getPostService().count().intValue());
            return posts;
        }
    }
}