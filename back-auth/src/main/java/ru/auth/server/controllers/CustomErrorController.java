package ru.auth.server.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.HashMap;
import java.util.Map;

@Controller
public class CustomErrorController implements ErrorController {

    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    @ResponseBody
    public Map<String, Object> error(Map<String, Object> model, ErrorAttributes errorAttributes) {
        HttpStatus status = getStatus(model, errorAttributes);
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", System.currentTimeMillis());
        body.put("status", status.value());
        body.put("message", status.getReasonPhrase());
        body.put("path", getPathWithQueryParameters(errorAttributes));
        return body;
    }

    private String getPathWithQueryParameters(ErrorAttributes errorAttributes) {
        String contextPath = ((HttpServletRequest) RequestContextHolder.getRequestAttributes())
                .getServletPath();
        String pathWithQuery = contextPath;
//        String queryString = Optional.ofNullable(errorAttributes.webRequest.getParameterMap())
//                .map(params -> "?" + String.join("&", params.keySet()))
//                .orElse("");
        return pathWithQuery ;
    }

    private HttpStatus getStatus(Map<String, Object> model, ErrorAttributes errorAttributes) {
        Integer statusCode = (Integer) model.get("status");
        return statusCode != null ? HttpStatus.valueOf(statusCode) : HttpStatus.INTERNAL_SERVER_ERROR;
    }


    public String getErrorPath() {
        return PATH;
    }
}
