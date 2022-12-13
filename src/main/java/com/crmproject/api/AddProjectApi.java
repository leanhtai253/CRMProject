package com.crmproject.api;

import com.crmproject.model.ProjectModel;
import com.crmproject.model.RoleModel;
import com.crmproject.payload.ResponseData;
import com.crmproject.services.ProjectService;
import com.crmproject.services.ProjectServiceImp;
import com.crmproject.services.RoleService;
import com.crmproject.services.RoleServiceImp;
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

@WebServlet(name="addProjectApiServlet", urlPatterns = {"/api/addProject"})
public class AddProjectApi extends HttpServlet {
    private ProjectService projectService = new ProjectServiceImp();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResponseData responseData = new ResponseData();
        try {
            JsonObject reqData = new Gson().fromJson(req.getReader(), JsonObject.class);
            ProjectModel project = new ProjectModel();
            for (Map.Entry<String, JsonElement> entry: reqData.entrySet()) {
                if (entry.getKey().equals("name")) {
                    project.setName(entry.getValue().getAsString());
                } else if (entry.getKey().equals("start")) {
                    project.setStartD(entry.getValue().getAsString());
                } else if (entry.getKey().equals("end")) {
                    project.setEndD(entry.getValue().getAsString());
                }
            }
            responseData.setSuccess(projectService.addProject(project));
        } catch (Exception e){
            System.out.println("Error API add project " + e);
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
