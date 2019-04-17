package main.com.store.web.servlet;

import main.com.store.web.base.BaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "OrderServlet",urlPatterns = "/orderServlet")
public class OrderServlet extends BaseServlet {
    public String submitOrder(HttpServletRequest request, HttpServletResponse response){
        String pid=request.getParameter("pid");
        String quantity=request.getParameter("quantity");
        System.out.println(pid+" "+quantity);
        return null;
    }
}
