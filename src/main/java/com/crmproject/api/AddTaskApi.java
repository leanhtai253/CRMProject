package com.crmproject.api;

import com.crmproject.model.TaskComplete;
import com.crmproject.payload.ResponseData;
import com.crmproject.services.TaskService;
import com.crmproject.services.TaskServiceImp;
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

@WebServlet(name="addTaskApiServlet", urlPatterns = {"/p/api/addTask"})
public class AddTaskApi extends HttpServlet {
    private Gson gson = new Gson();
    private TaskService taskService = new TaskServiceImp();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResponseData responseData = new ResponseData();
        try {
            JsonObject reqData = new Gson().fromJson(req.getReader(), JsonObject.class);
            TaskComplete task = new TaskComplete();
            for (Map.Entry<String, JsonElement> entry: reqData.entrySet()) {
                if (entry.getKey().equals("project")) {
                    task.setProject(entry.getValue().getAsString());
                } else if (entry.getKey().equals("member")) {
                    task.setMember(entry.getValue().getAsString());
                } else if (entry.getKey().equals("task")) {
                    task.setTask(entry.getValue().getAsString());
                } else if (entry.getKey().equals("start")) {
                    task.setStartD(entry.getValue().getAsString());
                } else if (entry.getKey().equals("end")) {
                    task.setEndD(entry.getValue().getAsString());
                }
            }
            responseData.setSuccess(taskService.addTask(task));
        } catch (Exception e) {
            System.out.println("Error api add task " + e);
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
