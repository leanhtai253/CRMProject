package com.crmproject.api;

import com.crmproject.model.TaskComplete;
import com.crmproject.model.TaskModel;
import com.crmproject.payload.ResponseData;
import com.crmproject.services.TaskService;
import com.crmproject.services.TaskServiceImp;
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

@WebServlet(name="taskCompleteApiServlet", urlPatterns = {"/api/completeTasks"})
public class TaskCompleteApi extends HttpServlet {
    TaskService taskService = new TaskServiceImp();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResponseData responseData = new ResponseData();
        try {
            List<TaskComplete> list = taskService.getCompleteTasksList();
            responseData.setStatus(200);
            responseData.setSuccess(true);
            responseData.setData(list);
        } catch (SQLException throwables) {
            System.out.println("Error Get Tasks List");
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
