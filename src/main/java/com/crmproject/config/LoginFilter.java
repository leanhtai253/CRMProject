package com.crmproject.config;

import com.crmproject.settings.Redirect;
import com.google.gson.JsonObject;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

@WebFilter("/*")
public class LoginFilter implements Filter {
    Redirect redirect = null;
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
//        filterChain.doFilter(servletRequest, servletResponse);
        if (!req.getServletPath().equals("/login")) {
            if (req.getServletPath().startsWith("/js") || req.getServletPath().equals("/api/login")) {
                filterChain.doFilter(servletRequest,servletResponse);
            } else {
                if (req.getSession().getAttribute("currentUser")!=null) {
                    filterChain.doFilter(servletRequest,servletResponse);
                } else {
                    resp.sendRedirect(req.getContextPath()+"/login");
                }
            }

        } else {
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }
}
