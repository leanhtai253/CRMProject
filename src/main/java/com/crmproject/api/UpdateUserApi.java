package com.crmproject.api;

import com.crmproject.model.UserModel;
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
import java.util.Map;

@WebServlet(name="updateUserApiServlet", urlPatterns = {"/p/api/updateUser"})
public class UpdateUserApi extends HttpServlet {
    private UserService userService = new UserServiceImp();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResponseData responseData = new ResponseData();
        try {
            int viewUserId = Integer.parseInt((String) req.getSession().getAttribute("viewUserId"));
            JsonObject reqData = new Gson().fromJson(req.getReader(), JsonObject.class);
            UserModel userModel = new UserModel();
            userModel.setUserID(viewUserId);
            for (Map.Entry<String, JsonElement> entry: reqData.entrySet()) {
                if (entry.getKey().equals("firstName")) {
                    userModel.setFirstName(entry.getValue().getAsString());
                } else if (entry.getKey().equals("lastName")) {
                    userModel.setLastName(entry.getValue().getAsString());
                } else if (entry.getKey().equals("email")) {
                    userModel.setEmail(entry.getValue().getAsString());
                } else if (entry.getKey().equals("username")) {
                    userModel.setUsername(entry.getValue().getAsString());
                } else if (entry.getKey().equals("phone")) {
                    userModel.setPhone(entry.getValue().getAsString());
                } else if (entry.getKey().equals("country")) {
                    userModel.setCountry(entry.getValue().getAsString());
                }
            }
            boolean result = userService.modifyUser(userModel);
            responseData.setSuccess(result);
        } catch (Exception e) {
            System.out.println("Error API modify user:\n" + e);
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
