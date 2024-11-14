package ru.auth.server.config;

import jakarta.servlet.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class LogThreadNameFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("Имя потока для запроса HTTP: " + Thread.currentThread().getName());
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
