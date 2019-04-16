package main.com.store.web.servlet;

import main.com.store.domain.User;
import main.com.store.service.UserService;
import main.com.store.service.impl.UserServiceImpl;
import main.com.store.utils.UUIDUtils;
import main.com.store.web.base.BaseServlet;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@WebServlet(name = "UserServlet", urlPatterns = "/UserServlet")
public class UserServlet extends BaseServlet {
    private UserService userService = new UserServiceImpl();

    public String registerUI(HttpServletRequest request, HttpServletResponse response) {
        return "/jsp/register.jsp";
    }

    public String register(HttpServletRequest request, HttpServletResponse response) {

        Map<String, String[]> parameters = request.getParameterMap();
        User user = new User();
        try {
            DateConverter dateConverter = new DateConverter();
            dateConverter.setPattern("yyyy-MM-dd");
            ConvertUtils.register(dateConverter, BeanUtils.class);
            BeanUtils.populate(user, parameters);
            user.setCode(UUIDUtils.getActiveCode());
            user.setUid(UUIDUtils.getCode());
            userService.registerUser(user);
            request.setAttribute("msg", "用户注册成功,激活邮件已发送至邮箱,请您尽快激活.");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "用户注册异常,请您稍后重试");
        }
        return "/jsp/info.jsp";
    }


    public String userActive(HttpServletRequest request, HttpServletResponse response) {
        String activeCode = request.getParameter("code");
        if (null == activeCode || "".equals(activeCode)) {
            request.setAttribute("msg", "激活链接无效");
        }
        try {
            userService.activeUser(activeCode);
            request.setAttribute("msg", "账户已激活,可以开启剁手模式啦~");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "账户激活异常,请稍后重试");
        }
        return "/jsp/info.jsp";
    }

    public String loginUI(HttpServletRequest request, HttpServletResponse response) {
        return "/jsp/login.jsp";
    }

    public String userLogin(HttpServletRequest request, HttpServletResponse response) {
        //String autoLogin = request.getParameter("autoLogin");
        String userName=request.getParameter("username");
        String password=request.getParameter("password");
        System.out.println(userName+"  "+password);
        try {
            User user=userService.userLogin(userName,password);
            if(user!=null){
                request.getSession().setAttribute("loginUser",user);
                return "/jsp/index.jsp";
            }else{
                request.setAttribute("msg","用户名不存在或密码错误");
                return "/jsp/login.jsp";
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg","系统异常,请稍后重试");
            return "/jsp/login.jsp";
        }
    }
    public String userLogout(HttpServletRequest request,HttpServletResponse response){
        User user=(User) request.getSession().getAttribute("loginUser");
        if(user!=null)
            request.getSession().setAttribute("loginUser",null);
        return "/jsp/index.jsp";
    }
}
