package com.crmproject.api;

import com.crmproject.model.StatusModel;
import com.crmproject.payload.ResponseData;
import com.crmproject.services.StatusService;
import com.crmproject.services.StatusServiceImp;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name="statusApiServlet", urlPatterns = "/api/status")
public class StatusApi extends HttpServlet {
    StatusService statusService = new StatusServiceImp();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResponseData responseData = new ResponseData();
        try {
            List<StatusModel> list = statusService.getAllStatus();
            responseData.setStatus(200);
            responseData.setSuccess(true);
            responseData.setData(list);
        } catch (Exception e) {
            System.out.println("Error API edit task:\n" + e);
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
