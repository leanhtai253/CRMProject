package com.crmproject.api;

import com.crmproject.model.MembersTasksByStatus;
import com.crmproject.model.StatusModel;
import com.crmproject.model.TaskComplete;
import com.crmproject.model.UserModel;
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

@WebServlet(name="projectDetailsApiServlet", urlPatterns = {"/api/projectDetails"})
public class ProjectDetailsApi extends HttpServlet {
    private ProjectDetailsService projectDetailsService = new ProjectDetailsServiceImp();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResponseData responseData = new ResponseData();
        try {
            int viewProjectId = Integer.parseInt((String) req.getSession().getAttribute("viewProjectId"));
            List<MembersTasksByStatus> data = projectDetailsService.getProjectMembersDetails(viewProjectId);
            responseData.setSuccess(true);
            responseData.setStatus(200);
            responseData.setData(data);
        } catch (Exception e) {
            System.out.println("Error GET project details api " + e);
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
