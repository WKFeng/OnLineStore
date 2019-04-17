package main.com.store.web.servlet;

import main.com.store.domain.Category;
import main.com.store.domain.Product;
import main.com.store.service.CategoryService;
import main.com.store.service.ProductService;
import main.com.store.service.impl.CategoryServiceImpl;
import main.com.store.service.impl.ProductServiceImpl;
import main.com.store.web.base.BaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(name = "IndexServlet", urlPatterns = "/IndexServlet")
public class IndexServlet extends BaseServlet {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
//        try {
//            CategoryService categoryService = new CategoryServiceImpl();
//            List<Category> categories = categoryService.findAllCategories();
//            if (categories != null) {
//                request.getSession().setAttribute("category", categories);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        ProductService productService = new ProductServiceImpl();
        try {
            List<Product> newProducts = productService.findNewProducts();
            List<Product> hotProducts = productService.findHotProducts();
            request.setAttribute("newProducts", newProducts);
            request.setAttribute("hotProducts", hotProducts);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "jsp/index.jsp";
    }
}
