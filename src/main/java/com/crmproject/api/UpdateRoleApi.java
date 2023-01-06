package com.crmproject.api;

import com.crmproject.model.RoleModel;
import com.crmproject.model.UserModel;
import com.crmproject.payload.ResponseData;
import com.crmproject.services.RoleService;
import com.crmproject.services.RoleServiceImp;
import com.crmproject.services.UserService;
import com.crmproject.services.UserServiceImp;
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

@WebServlet(name="updateRoleApiServlet", urlPatterns = {"/p/api/updateRole"})
public class UpdateRoleApi extends HttpServlet {
    private RoleService roleService = new RoleServiceImp();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResponseData responseData = new ResponseData();
        try {
            int viewRoleId = (int) req.getSession().getAttribute("viewRoleId");
            JsonObject reqData = new Gson().fromJson(req.getReader(), JsonObject.class);
            RoleModel role = new RoleModel();
            for (Map.Entry<String, JsonElement> entry: reqData.entrySet()) {
                if (entry.getKey().equals("name")) {
                    role.setName(entry.getValue().getAsString());
                } else if (entry.getKey().equals("descr")) {
                    role.setDescr(entry.getValue().getAsString());
                }
            }
            boolean result = roleService.updateRole(viewRoleId,role);
            responseData.setSuccess(result);
        } catch (Exception e) {
            System.out.println("Error API modify role:\n" + e);
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
