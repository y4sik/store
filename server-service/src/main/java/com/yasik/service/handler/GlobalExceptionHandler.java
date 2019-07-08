package com.yasik.service.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yasik.service.exception.EmailExistsException;
import com.yasik.service.exception.EntityNotFoundException;
import com.yasik.service.exception.ErrorResponseBody;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler extends BasicAuthenticationEntryPoint /*implements AuthenticationEntryPoint*/ {

    private static final Logger LOGGER = LogManager.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler
    public ResponseEntity<ErrorResponseBody> handleException(EntityNotFoundException e) {
        LOGGER.error(e.getMessage());
        ErrorResponseBody error = new ErrorResponseBody(HttpStatus.NOT_FOUND.value(), e.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<ErrorResponseBody> handleException(EmailExistsException e) {
        LOGGER.error(e.getMessage());
        ErrorResponseBody error = new ErrorResponseBody(HttpStatus.BAD_REQUEST.value(), e.getMessage(), LocalDateTime.now());;
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    public ResponseEntity<ErrorResponseBody> handleException(Exception e) {
        LOGGER.error(e.getMessage());
        ErrorResponseBody error = new ErrorResponseBody(HttpStatus.BAD_REQUEST.value(), e.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<ErrorResponseBody> handleException(AccessDeniedException e, WebRequest request) {
        LOGGER.error(e.getMessage());
        ErrorResponseBody error = new ErrorResponseBody(HttpStatus.BAD_REQUEST.value(), e.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }


    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        LOGGER.error(e.getMessage());
        ErrorResponseBody error = new ErrorResponseBody(HttpStatus.UNAUTHORIZED.value(), "HTTP Status 401 : " + e.getMessage(), LocalDateTime.now());
        String answer = new ObjectMapper().writeValueAsString(error);
        httpServletResponse.addHeader("WWW-Authenticate", "Basic realm=" + getRealmName());
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpServletResponse.getOutputStream().println(answer);
//        PrintWriter writer = httpServletResponse.getWriter();
//        writer.println("HTTP Status 401 - " + e.getMessage());
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        setRealmName("MY_TEST_REALM");
        super.afterPropertiesSet();
    }

}
