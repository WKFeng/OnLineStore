package main.com.store.web.base;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String methodName = req.getParameter("method");
        if (methodName.equals("") || methodName == null) {
            methodName = "execute";
        }
        Class clazz = this.getClass();
        Method method = null;
        try {
            method = clazz.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            String path = (String) method.invoke(this, req, resp);
            if (path != null) {
                req.getRequestDispatcher(path).forward(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
