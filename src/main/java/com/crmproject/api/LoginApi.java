package com.crmproject.api;

import com.crmproject.model.UserModel;
import com.crmproject.payload.ResponseData;
import com.crmproject.services.LoginService;
import com.crmproject.services.LoginServiceImp;
import com.crmproject.services.UserService;
import com.crmproject.services.UserServiceImp;
import com.crmproject.settings.Redirect;
import com.crmproject.settings.RedirectImp;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@WebServlet(name="loginApiServlet", urlPatterns = {"/api/login"})
public class LoginApi extends HttpServlet {
    LoginService loginService = new LoginServiceImp();
    Redirect redirect = new RedirectImp();
    UserService userService = new UserServiceImp();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ResponseData responseData = new ResponseData();
        try {
            System.out.println(req.getSession().toString());
            int id = (int) req.getSession().getAttribute("userId");
            List<UserModel> currentUserList = userService.findUserById(id);
            if (currentUserList.size() > 0) {
                responseData.setStatus(200);
                responseData.setSuccess(true);
            } else {
                responseData.setStatus(500);
                responseData.setSuccess(false);
            }
            responseData.setData(currentUserList.get(0));

        } catch (Exception e) {
            System.out.println(e);
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
        responseData.setSuccess(false);
        System.out.println("Post API");
        try {
            String email=null;
            String password=null;
            JsonObject reqData = new Gson().fromJson(req.getReader(), JsonObject.class);
            for (Map.Entry<String, JsonElement> entry: reqData.entrySet()) {
                if (entry.getKey().equals("email")) {
                    email = entry.getValue().getAsString();
                }
                if (entry.getKey().equals("password")) {
                    password = entry.getValue().getAsString();
                }
            }

            if (email != null && password != null) {
                boolean isValidUser = loginService.checkLoginByEmailAndPassword(email, password);
                if (isValidUser) {
                    responseData.setSuccess(true);
                    List<UserModel> userList = userService.findUserByEmailAndPassword(email, password);
                    UserModel currentUser = userList.get(0);
                    req.getSession().setAttribute("userId", currentUser.getUserID());
                    req.getSession().setAttribute("currentUser", currentUser);
                    req.getSession().setMaxInactiveInterval(10*60);

                    responseData.setData(currentUser);
                }
            }
        } catch (Exception e) {
            System.out.println("Error Post API login " + e);
        } finally {
            responseData.setStatus(200);
            Gson gson = new Gson();
            System.out.println(responseData.isSuccess());
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
