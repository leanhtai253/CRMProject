package com.crmproject.api;

import com.crmproject.model.StatusModel;
import com.crmproject.model.UserDetails;
import com.crmproject.model.inheritance.SummaryByStatus;
import com.crmproject.payload.ResponseData;
import com.crmproject.services.*;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name="taskStatusApiServlet", urlPatterns = {"/api/tasksByStatus"})
public class TaskStatusApi extends HttpServlet {
    private UserService userService = new UserServiceImp();
    private StatusService statusService = new StatusServiceImp();
    private TaskService taskService = new TaskServiceImp();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResponseData responseData = new ResponseData();
        try {
            int viewUserId = Integer.parseInt((String) req.getSession().getAttribute("viewUserId"));
            List<StatusModel> status = statusService.getAllStatus();
            List<SummaryByStatus> data = new ArrayList<>();
            for (StatusModel model : status) {
                SummaryByStatus record = new SummaryByStatus();
                int statusID = model.getStatusID();
                record.setStatusID(model.getStatusID());
                record.setStatus(model.getName());
                record.setTasks(taskService.findTasksByUserAndStatus(viewUserId, statusID));

                data.add(record);
            }
            responseData.setStatus(200);
            responseData.setSuccess(true);
            responseData.setData(data);
        } catch (Exception e) {
            System.out.println("Error API tasks status:\n" + e);
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
