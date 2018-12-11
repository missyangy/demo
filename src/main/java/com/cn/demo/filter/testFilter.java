package com.cn.demo.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @program: demo
 * @description: 模板Filter
 * @author: DongLianPo
 * @create: 2018/12/11 13:16
 **/
@WebFilter(filterName = "test", urlPatterns = "/*")
@Order(1)
public class testFilter implements Filter {
    private Logger logger = LoggerFactory.getLogger(testFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        String ext = httpServletRequest.getParameter("ext");
        String constant = "t";
        String method = "post";
        if (constant.equals(ext)) {
            return;
        } else if (!method.equalsIgnoreCase(httpServletRequest.getMethod())) {
            printErrorMessage(servletResponse, "wrong method");
            return;
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    @Override
    public void destroy() {

    }

    private void printErrorMessage(ServletResponse resp, String message) {
        logger.info(message);
        resp.setCharacterEncoding("UTF-8");
        PrintWriter pw;
        try {
            pw = resp.getWriter();
            pw.write(message);
            pw.flush();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
