package com.crmproject.controller;

import com.crmproject.model.UserModel;
import com.crmproject.services.UserService;
import com.crmproject.services.UserServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name="dashboardServlet", urlPatterns = {"/dashboard"})
public class DashboardController extends HttpServlet {
    UserService userService = new UserServiceImp();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = (int) req.getSession().getAttribute("userId");
            UserModel currentUser = userService.findUserById(id).get(0);
            req.setAttribute("accountName", currentUser.getFirstName()+" "+currentUser.getLastName());
            req.setAttribute("accountAvatar", currentUser.getAvatar());
            req.getRequestDispatcher("/index.html").forward(req, resp);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
