package ru.auth.server.config.filters;

import jakarta.servlet.*;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.logging.Logger;

@Component
public class CsrfTokenLogger implements Filter {
    private Logger logger =
            Logger.getLogger(CsrfTokenLogger.class.getName());

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {
        CsrfToken o = (CsrfToken) request.getAttribute("_csrf");
        logger.info("CSRF token " + o.getToken());
        filterChain.doFilter(request, response);
    }
}
