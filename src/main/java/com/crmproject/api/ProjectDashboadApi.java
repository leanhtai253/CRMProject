package com.crmproject.api;

import com.crmproject.model.StatusModel;
import com.crmproject.model.TasksByStatus;
import com.crmproject.model.inheritance.SummaryByStatus;
import com.crmproject.payload.ResponseData;
import com.crmproject.services.ProjectService;
import com.crmproject.services.ProjectServiceImp;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet(name="projectDashboardApiServlet", urlPatterns = {"/api/projectDashboard"})
public class ProjectDashboadApi extends HttpServlet {
    private ProjectService projectService = new ProjectServiceImp();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResponseData responseData = new ResponseData();
        try {
            int viewProjectId = (int) req.getSession().getAttribute("viewProjectId");
            List<TasksByStatus> data = projectService.countTasksByStatus(viewProjectId);
            responseData.setStatus(200);
            responseData.setSuccess(true);
            responseData.setData(data);
        } catch (Exception e) {
            System.out.println("Error API project dashboard:\n" + e);
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
        System.out.println("Post project dashboard");
        try {
            JsonObject reqData = new Gson().fromJson(req.getReader(), JsonObject.class);
            for (Map.Entry<String, JsonElement> entry: reqData.entrySet()) {
                if (entry.getKey().equals("projectid")) {
                    System.out.println("viewProjectID post: " + entry.getValue().getAsString());
                    req.getSession().setAttribute("viewProjectId",entry.getValue().getAsInt());
                }
            }
            responseData.setStatus(200);
            responseData.setSuccess(true);
        } catch (Exception e) {
            System.out.println("Error API post project dashboard:\n" + e);
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
