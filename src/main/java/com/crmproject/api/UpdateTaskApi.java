package com.crmproject.api;

import com.crmproject.model.ProjectModel;
import com.crmproject.model.TaskComplete;
import com.crmproject.model.TaskModel;
import com.crmproject.payload.ResponseData;
import com.crmproject.services.ProjectService;
import com.crmproject.services.ProjectServiceImp;
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

@WebServlet(name="updateTaskApiServlet", urlPatterns = {"/p/api/updateTask"})
public class UpdateTaskApi extends HttpServlet {
    private TaskService taskService = new TaskServiceImp();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResponseData responseData = new ResponseData();
        try {
            int taskid = Integer.parseInt((String) req.getSession().getAttribute("taskToEdit"));
            JsonObject reqData = new Gson().fromJson(req.getReader(), JsonObject.class);
            TaskModel task = new TaskModel();
            task.setId(taskid);
            for (Map.Entry<String, JsonElement> entry: reqData.entrySet()) {
                if (entry.getKey().equals("name")) {
                    task.setTask(entry.getValue().getAsString());
                } else if (entry.getKey().equals("start")) {
                    task.setStart_date(entry.getValue().getAsString());
                } else if (entry.getKey().equals("end")) {
                    task.setEnd_date(entry.getValue().getAsString());
                } else if (entry.getKey().equals("status")) {
                    task.setStatus(entry.getValue().getAsString());
                }
            }
            responseData.setSuccess(taskService.updateTask(task));
        } catch (Exception e){
            System.out.println("Error API edit task " + e);
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
