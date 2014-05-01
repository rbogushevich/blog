package rb.home.blog.web.util.redirect;

/**
 * @author: HOME
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.context.FacesContext;
import java.io.IOException;
import java.text.MessageFormat;

public class RedirectHelper {
    private static final Logger logger = LoggerFactory.getLogger(RedirectHelper.class);

    public static void redirectToPost(Long postId) {
        redirect("/pages/post.jsf?postId={0,number,#}", postId);
    }

    private static void redirect(String url, Object... paramValues) {
        String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        if (!url.startsWith(path)) {
            url = path + url;
        }
        try {
            url = MessageFormat.format(url, paramValues);
            FacesContext.getCurrentInstance().getExternalContext().redirect(url);
        } catch (IOException e) {
            logger.error("Could not redirect to url={}", url, e);
        } catch (Exception e) {
            logger.error("unexpected exception while redirect to url={}", url, e);
        }
    }
}