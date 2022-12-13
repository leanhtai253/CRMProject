package com.crmproject.api;

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

@WebServlet(name="editTaskApiServlet", urlPatterns = "/api/taskToEdit")
public class EditTaskApi extends HttpServlet {
    TaskService taskService = new TaskServiceImp();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResponseData responseData = new ResponseData();
        try {
            int taskid = Integer.parseInt((String) req.getSession().getAttribute("taskToEdit"));
            System.out.println(taskid);
            responseData.setStatus(200);
            responseData.setSuccess(true);
            responseData.setData(taskService.getTaskById(taskid).get(0));
        } catch (Exception e) {
            System.out.println("Error GET API edit task:\n" + e);
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
        try {
            int taskid = Integer.parseInt((String) req.getSession().getAttribute("taskToEdit"));
            int statusid = Integer.parseInt(req.getParameter("statusId"));
            responseData.setStatus(200);
            if (taskService.updateTaskStatus(taskid, statusid)) {
                responseData.setSuccess(true);
            } else {
                responseData.setSuccess(false);
            }
            responseData.setData(taskService.getTaskById(taskid).get(0));
        } catch (Exception e) {
            System.out.println("Error POST API edit task status:\n" + e);
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
