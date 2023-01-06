package com.crmproject.api;

import com.crmproject.payload.ResponseData;
import com.crmproject.services.RoleService;
import com.crmproject.services.RoleServiceImp;
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

@WebServlet(name="deleteRoleApiServlet", urlPatterns = {"/p/api/deleteRole"})
public class DeleteRoleApi extends HttpServlet {
    RoleService roleService = new RoleServiceImp();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResponseData responseData = new ResponseData();
        try {
            int viewRoleId = Integer.parseInt((String) req.getSession().getAttribute("viewRoleId"));
            boolean result = roleService.deleteRoleById(viewRoleId);
            responseData.setSuccess(result);
        } catch (Exception e) {
            System.out.println("Error API delete role:\n" + e);
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
