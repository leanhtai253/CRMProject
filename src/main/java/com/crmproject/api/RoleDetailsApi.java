package com.crmproject.api;

import com.crmproject.model.RoleModel;
import com.crmproject.model.UserModel;
import com.crmproject.payload.ResponseData;
import com.crmproject.services.RoleService;
import com.crmproject.services.RoleServiceImp;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(name="roleDetailsApiServlet", urlPatterns = {"/api/roleDetails"})
public class RoleDetailsApi extends HttpServlet {
    private RoleService roleService = new RoleServiceImp();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResponseData responseData = new ResponseData();
        try {
            int viewRoleId = (int) req.getSession().getAttribute("viewRoleId");
            RoleModel role = roleService.findRoleById(viewRoleId);
            responseData.setStatus(200);
            responseData.setSuccess(true);
            responseData.setData(role);
        } catch (Exception e) {
            System.out.println("Error API role:\n" + e);
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResponseData responseData = new ResponseData();
        try {
            JsonObject reqData = new Gson().fromJson(req.getReader(), JsonObject.class);
            for (Map.Entry<String, JsonElement> entry: reqData.entrySet()) {
                if (entry.getKey().equals("roleid")) {
                    req.getSession().setAttribute("viewRoleId",entry.getValue().getAsInt());
                }
            }
            responseData.setStatus(200);
            responseData.setSuccess(true);
        } catch (Exception e) {
            System.out.println("Error API role details:\n" + e);
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
