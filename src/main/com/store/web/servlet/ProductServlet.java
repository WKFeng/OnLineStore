package main.com.store.web.servlet;

import main.com.store.domain.Cart;
import main.com.store.domain.CartItem;
import main.com.store.domain.PageModel;
import main.com.store.domain.Product;
import main.com.store.service.ProductService;
import main.com.store.service.impl.ProductServiceImpl;
import main.com.store.web.base.BaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "ProductServlet", urlPatterns = "/productServlet")
public class ProductServlet extends BaseServlet {
    private ProductService productService = new ProductServiceImpl();

    public String findProductsByCategoryWithPage(HttpServletRequest request, HttpServletResponse response) {
        String currentNum = request.getParameter("num");
        String cid = request.getParameter("cid");
        try {
            PageModel pageModel = productService.findProductsByCategoryWithPage(currentNum, cid);
            //System.out.println(pageModel);
            request.setAttribute("page", pageModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/jsp/product_list.jsp";
    }

    public String getProductInfoByPid(HttpServletRequest request, HttpServletResponse response) {
        String pid = request.getParameter("pid");
        Product product = null;
        try {
            product = productService.getProductInfoByPid(pid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("product", product);
        return "/jsp/product_info.jsp";
    }

    public String addToCart(HttpServletRequest request, HttpServletResponse response) {
        String quantity = request.getParameter("quantity");
        int num = Integer.parseInt(quantity);
        String pid = request.getParameter("pid");
        //System.out.println(quantity + " " + pid);
        CartItem cartItem = null;
        try {
            Product product = productService.getProductInfoByPid(pid);
            cartItem = new CartItem(product, num);
            Cart cart = (Cart) request.getSession().getAttribute("cart");
            if (cart == null) {
                //购物车没有东西
                Map<String, CartItem> cartItemMap = new HashMap<>();
                cartItemMap.put(pid, cartItem);
                cart = new Cart(cartItemMap);
                request.getSession().setAttribute("cart", cart);
            } else {
                //购物车不为空
                if (cart.getMap().containsKey(pid)) {
                    //购物车中已经包含同种商品
                    int oldNum = cart.getMap().get(pid).getNum();
                    cart.getMap().get(pid).setNum(oldNum + num);
                } else {
                    cart.getMap().put(pid, cartItem);
                }
                request.getSession().setAttribute("cart", cart);
            }
            response.sendRedirect("http://localhost:8080/OnLineStore/jsp/cart.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String removeCartItem(HttpServletRequest request, HttpServletResponse response) {
        String pid = request.getParameter("pid");
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart != null) {
            cart.removeCartItem(pid);
            request.getSession().setAttribute("cart", cart);
        }
        return "/jsp/cart.jsp";
    }

    public String clearCart(HttpServletRequest request, HttpServletResponse response) {
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart != null) {
            request.getSession().setAttribute("cart", null);
        }
        return "/jsp/cart.jsp";
    }
}
