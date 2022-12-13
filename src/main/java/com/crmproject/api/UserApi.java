package com.crmproject.api;

import com.crmproject.model.UserDetails;
import com.crmproject.model.UserModel;
import com.crmproject.payload.ResponseData;
import com.crmproject.services.UserService;
import com.crmproject.services.UserServiceImp;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name="userApiServlet", urlPatterns = {"/api/userApi"})
public class UserApi extends HttpServlet {
    private UserService userService = new UserServiceImp();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResponseData responseData = new ResponseData();
        try {
            int viewUserId = Integer.parseInt((String) req.getSession().getAttribute("viewUserId"));
            UserModel user = userService.findUserById(viewUserId).get(0);
            responseData.setStatus(200);
            responseData.setSuccess(true);
            responseData.setData(user);
        } catch (Exception e) {
            System.out.println("Error API edit task:\n" + e);
        } finally {
            Gson gson = new Gson();
            String json = gson.toJson(responseData);
            resp.setStatus(200);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            PrintWriter out = resp.getWriter();
            out.print(json);
            out.flush();
        }
    }
}
