package main.com.store.web.servlet;

import main.com.store.domain.Category;
import main.com.store.service.CategoryService;
import main.com.store.service.impl.CategoryServiceImpl;
import main.com.store.utils.JedisUtils;
import main.com.store.web.base.BaseServlet;
import net.sf.json.JSONArray;
import redis.clients.jedis.Jedis;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(name = "CategoryServlet", urlPatterns = "/categoryServlet")
public class CategoryServlet extends BaseServlet {
    private CategoryService categoryService = new CategoryServiceImpl();

    public String findAllCategories(HttpServletRequest request, HttpServletResponse response) {
        try {
            //从Redis中获取数据
            Jedis jedis = JedisUtils.getJedis();
            String allCategoriesStr = jedis.get("AllCategories");
            if (allCategoriesStr == null || allCategoriesStr.equals("")) {//Redis中没有数据
                List<Category> categories = categoryService.findAllCategories();
                JSONArray jsonArray = JSONArray.fromObject(categories);
                allCategoriesStr = jsonArray.toString();
                jedis.set("AllCategories", allCategoriesStr);
            }
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(allCategoriesStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
