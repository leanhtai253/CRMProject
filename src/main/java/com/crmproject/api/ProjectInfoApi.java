package com.crmproject.api;

import com.crmproject.model.ProjectModel;
import com.crmproject.payload.ResponseData;
import com.crmproject.services.ProjectService;
import com.crmproject.services.ProjectServiceImp;
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

@WebServlet(name="projectInfoApiServlet",urlPatterns = {"/api/projectInfo"})
public class ProjectInfoApi extends HttpServlet {
    ProjectService projectService = new ProjectServiceImp();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResponseData responseData = new ResponseData();
        try {
            int viewProjectId = (int) req.getSession().getAttribute("viewProjectId");
            ProjectModel project = projectService.getProjectById(viewProjectId);
            responseData.setData(project);
            responseData.setStatus(200);
            responseData.setSuccess(true);
        } catch (Exception e) {
            responseData.setSuccess(false);
            System.out.println("Error getting project info " + e);
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
