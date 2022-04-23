package run.halo.app.wrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import org.apache.commons.text.StringEscapeUtils;


public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request the {@link HttpServletRequest} to be wrapped.
     *
     */

    public XssHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getHeader(String name) {
        String value = super.getHeader(name);
        return StringEscapeUtils.escapeHtml4(value);
    }

    @Override
    public String getParameter(String name) {
        String value = super.getParameter(name);
        return StringEscapeUtils.escapeHtml4(value);
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] values = super.getParameterValues(name);
        if (values != null) {
            int length = values.length;
            String[] escapeValues = new String[length];
            for (int i = 0; i < length; i++) {
                escapeValues[i] = StringEscapeUtils.escapeHtml4(values[i]);
            }
            return escapeValues;
        }
        return super.getParameterValues(name);
    }
}
