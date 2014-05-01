package rb.home.blog.web.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.FacesException;
import javax.faces.application.NavigationHandler;
import javax.faces.application.ProjectStage;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;
import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.Map;

/**
 * Exception Handler
 */
public class DefaultExceptionHandler extends ExceptionHandlerWrapper {

    private static final Logger logger = LoggerFactory.getLogger(DefaultExceptionHandler.class);
    private static final String ERROR_MESSAGE = "errorMessage";
    private static final String PAGES_HOME = "/pages/postList.jsf";
    private static final String PAGES_ERROR = "/pages/error.jsf";
    public static final String EXCEPTION_CODE = "Exception";
    public static final String FACES_EXCEPTION_CODE = "FacesException";

    /**
     * {@link ExceptionHandler}.
     */
    private ExceptionHandler wrapped;

    /**
     * Constructor.
     *
     * @param wrapped
     */
    public DefaultExceptionHandler(ExceptionHandler wrapped) {
        this.wrapped = wrapped;
    }

    /**
     * @see ExceptionHandlerWrapper#getWrapped()
     */
    @Override
    public ExceptionHandler getWrapped() {
        return this.wrapped;
    }

    /**
     * @see ExceptionHandlerWrapper#handle()
     */
    @Override
    public void handle() throws FacesException {
        FacesContext fc = FacesContext.getCurrentInstance();
        final Map<String, Object> requestMap = fc.getExternalContext().getRequestMap();
        final NavigationHandler nav = fc.getApplication().getNavigationHandler();
        if (fc.isProjectStage(ProjectStage.Development)) {
            // Code for development mode. E.g. let the parent handle exceptions
            getWrapped().handle();
        } else {
            for (Iterator<ExceptionQueuedEvent> i = getUnhandledExceptionQueuedEvents().iterator(); i.hasNext(); ) {
                ExceptionQueuedEvent event = i.next();
                ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();
                String redirectPage = null;
                Throwable t = context.getException();
                try {
                    if (t instanceof ViewExpiredException) {
                        logger.debug("View '" + ((ViewExpiredException) t).getViewId() + "' is expired", t);
                        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
                        if (session != null) {
                            session.invalidate();
                        }
                        // redirect to the login page
                        redirectPage = PAGES_HOME + "?faces-redirect=true";
                    } else {
                        // custom handling of unexpected exceptions can be done
                        // in the method handleUnexpected
                        String messageKey = handleUnexpected(fc, t);
                        redirectPage = PAGES_ERROR + "?faces-redirect=true&errorCode=" + messageKey;
                        fc.getExternalContext().getSessionMap().put(DefaultExceptionHandler.ERROR_MESSAGE, t.getLocalizedMessage());
                    }
                } finally {
                    i.remove();
                }
                requestMap.put("exceptionMessage", t.getMessage());
                nav.handleNavigation(fc, null, redirectPage);
                fc.renderResponse();
                break;
            }
        }
    }

    /**
     * Use this method to handle any unexpected exception.
     *
     * @param facesContext faces context
     * @param t            throwable
     * @return error code
     */
    protected String handleUnexpected(FacesContext facesContext, final Throwable t) {
        logger.error("An unexpected internal error has occurred", t);
        String errorCode = EXCEPTION_CODE;
        if (t instanceof FacesException) {
            errorCode = FACES_EXCEPTION_CODE;
        }
        return errorCode;
    }
}
