package com.crmproject.api;

import com.crmproject.model.StatusModel;
import com.crmproject.model.TasksByStatus;
import com.crmproject.model.UserDetails;
import com.crmproject.payload.ResponseData;
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
import java.util.List;
import java.util.Map;

@WebServlet(name="userDetailsApiServlet", urlPatterns = {"/api/userDetails"})
public class UserDetailsApi extends HttpServlet {
    private UserService userService = new UserServiceImp();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ResponseData responseData = new ResponseData();
        try {
            JsonObject reqData = new Gson().fromJson(req.getReader(), JsonObject.class);
            for (Map.Entry<String, JsonElement> entry: reqData.entrySet()) {
                if (entry.getKey().equals("userid")) {
                    req.getSession().setAttribute("viewUserId",entry.getValue().getAsString());
                }
            }
            responseData.setStatus(200);
            responseData.setSuccess(true);
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
