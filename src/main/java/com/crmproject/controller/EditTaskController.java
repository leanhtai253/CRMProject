package com.crmproject.controller;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(name="editTaskServlet", urlPatterns = {"/editTask"})
public class EditTaskController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("profile-edit.html").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonObject reqData = new Gson().fromJson(req.getReader(), JsonObject.class);
        System.out.println("doPost EditTask: " + reqData);
        for (Map.Entry<String, JsonElement> entry: reqData.entrySet()) {
            if (entry.getKey().equals("taskid")) {
                req.getSession().setAttribute("taskToEdit",entry.getValue().getAsString());
                System.out.println(req.getSession().getAttribute("taskToEdit"));
            }
        }
    }
}
