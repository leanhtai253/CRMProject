package com.crmproject.settings;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Redirect {
    public void toLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException;
    public void toDashboard(HttpServletRequest req, HttpServletResponse resp) throws IOException;
}
