package com.crmproject.api;

import com.crmproject.model.CountryModel;
import com.crmproject.payload.ResponseData;
import com.crmproject.services.UserService;
import com.crmproject.services.UserServiceImp;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name="countryApiServlet", urlPatterns = "/api/countries")
public class CountryApi extends HttpServlet {
    UserService userService = new UserServiceImp();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResponseData responseData = new ResponseData();
        try {
            List<CountryModel> countries = userService.getCountries();
            responseData.setData(countries);
            responseData.setSuccess(true);
        } catch (Exception e) {
            responseData.setSuccess(false);
        } finally {
            responseData.setStatus(200);
            Gson gson = new Gson();
            String json = gson.toJson(responseData);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            PrintWriter out = resp.getWriter();
            out.print(json);
            out.flush();
        }
    }
}
