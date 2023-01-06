package com.crmproject.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="notAllowedServlet", urlPatterns = {"/notAllowed"})
public class NotAllowedController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Get not allowed controller");
        req.getRequestDispatcher("404.html").forward(req,resp);
    }
}
