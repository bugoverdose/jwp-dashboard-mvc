package nextstep.mvc.view;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class JspView implements View {

    private static final Logger log = LoggerFactory.getLogger(JspView.class);

    private final String viewName;

    public JspView(final String viewName) {
        this.viewName = viewName;
    }

    @Override
    public void render(final Map<String, ?> model, final HttpServletRequest request, final HttpServletResponse response)
            throws Exception {
        for (final var key : model.keySet()) {
            request.setAttribute(key, model.get(key));
            log.debug("attribute name : {}, value : {}", key, model.get(key));
        }
        final var requestDispatcher = request.getRequestDispatcher(viewName);
        if (requestDispatcher != null) {
            requestDispatcher.forward(request, response);
        }
    }
}
