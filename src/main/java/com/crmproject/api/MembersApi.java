package com.crmproject.api;

import com.crmproject.model.Members;
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
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "membersApiSerlvet", urlPatterns = {"/api/members"})
public class MembersApi extends HttpServlet {
    UserService userService = new UserServiceImp();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResponseData responseData = new ResponseData();
        try {
            List<Members> membersList = userService.findAllMembers();
            if (membersList.size() > 0) {
                responseData.setData(membersList);
                responseData.setStatus(200);
            } else {
                responseData.setSuccess(false);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
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
