package com.crmproject.api;

import com.crmproject.model.TasksByStatus;
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

@WebServlet(name="memberDashboardApiServlet", urlPatterns = {"/api/memberDashboard"})
public class MemberDashboardApi extends HttpServlet {
    UserService userService = new UserServiceImp();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResponseData responseData = new ResponseData();
        try {
            int viewUserId = Integer.parseInt((String) req.getSession().getAttribute("viewUserId"));
            List<TasksByStatus> list = userService.countTasksByStatus(viewUserId);
            if (list.size() > 0) {
                responseData.setStatus(200);
                responseData.setSuccess(true);
            } else {
                responseData.setStatus(500);
                responseData.setSuccess(false);
            }
            responseData.setData(list);

        } catch (Exception e) {
            System.out.println("Error Dashboard API" + e);
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
