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

@WebServlet(name="addRoleApiServlet", urlPatterns = {"/p/api/addRole"})
public class AddRoleApi extends HttpServlet {
    private RoleService roleService = new RoleServiceImp();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResponseData responseData = new ResponseData();
        try {
            JsonObject reqData = new Gson().fromJson(req.getReader(), JsonObject.class);
            RoleModel role = new RoleModel();
            for (Map.Entry<String, JsonElement> entry: reqData.entrySet()) {
                if (entry.getKey().equals("roleName")) {
                    role.setName(entry.getValue().getAsString());
                } else if (entry.getKey().equals("roleDescr")) {
                    role.setDescr(entry.getValue().getAsString());
                }
            }
            responseData.setSuccess(roleService.addRole(role));
        } catch (Exception e){
            System.out.println("Error API add role " + e);
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
