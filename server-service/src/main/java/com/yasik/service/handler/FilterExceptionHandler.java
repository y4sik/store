//package com.yasik.service.excetpion.handle;
//
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.context.ApplicationContext;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.context.support.WebApplicationContextUtils;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//
//@Component
//public class FilterExceptionHandler  implements Filter {
//
//    private GlobalExceptionHandler handler;
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        ApplicationContext ctx = WebApplicationContextUtils
//                .getRequiredWebApplicationContext(filterConfig.getServletContext());
//
//        //MyExceptionHanlder is now accessible because I loaded it manually
//        this.handler = ctx.getBean(GlobalExceptionHandler.class);
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest req = (HttpServletRequest) servletRequest;
//        HttpServletResponse res = (HttpServletResponse) servletResponse;
//
//        try {
//            //code that throws exception
//        } catch(Exception ex) {
//            //MyObject is whatever the output of the below method
//            ResponseEntity<ErrorResponseBody> error = handler.handleException(ex);
//
//            //set the response object
//            res.setStatus(error.getStatusCodeValue());
//            res.setContentType("application/json");
//
//            //pass down the actual obj that exception handler normally send
//            ObjectMapper mapper = new ObjectMapper();
//            PrintWriter out = res.getWriter();
//            out.print(mapper.writeValueAsString(error));
//            out.flush();
//
//            return;
//        }
//
//        //proceed normally otherwise
//        filterChain.doFilter(servletRequest, servletResponse);
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//
////    @Override
////    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
////        try {
////            filterChain.doFilter(httpServletRequest, httpServletResponse);
////        } catch (RuntimeException e) {
////            ErrorResponseBody error = new ErrorResponseBody(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), LocalDateTime.now());
////            String answer = new ObjectMapper().writeValueAsString(error);
////            httpServletResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
////            httpServletResponse.getWriter().write(answer);
////        }
////    }
//}
