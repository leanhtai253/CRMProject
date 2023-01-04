package com.crmproject.api;

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

@WebServlet(name="deleteProjectApiServlet", urlPatterns = {"/api/deleteProject"})
public class DeleteProjectApi extends HttpServlet {
    private ProjectService projectService = new ProjectServiceImp();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResponseData responseData = new ResponseData();
        try {
            int id = (int) req.getSession().getAttribute("viewProjectId");
            boolean result = projectService.deleteProjectById(id);
            responseData.setSuccess(result);
        } catch (Exception e) {
            System.out.println("Error API delete project:\n" + e);
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
