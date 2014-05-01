package rb.home.blog.web.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Locale;

/**
 * Locale controller.
 */
@ManagedBean
@SessionScoped
public class LocaleBean implements Serializable {

    private static final long serialVersionUID = -7522609234232367231L;

    /**
     * Locale.
     */
    private Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    /**
     * Set language.
     *
     * @param language new language
     */
    public void setLanguage(String language) {
        locale = new Locale(language);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
    }

    /**
     * @return the locale
     */
    public Locale getLocale() {
        return locale;
    }

    /**
     * @param locale the locale to set
     */
    public void setLocale(Locale locale) {
        this.locale = locale;
    }

}
