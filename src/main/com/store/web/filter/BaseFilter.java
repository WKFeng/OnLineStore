package main.com.store.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@WebFilter(filterName = "BaseFilter", urlPatterns = "/*")
public class BaseFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        chain.doFilter(new MyRequest(request), resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

    class MyRequest extends HttpServletRequestWrapper {
        private HttpServletRequest request;


        public MyRequest(HttpServletRequest request) {
            super(request);
            this.request = request;
        }


        @Override
        public String getParameter(String name) {
            String method = request.getMethod();
            if (method.equalsIgnoreCase("get")) {
                //get请求
                String value = request.getParameter(name);
                try {
                    value = new String(value.getBytes("iso-8859-1"), "utf-8");
                    return value;
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            } else if (method.equalsIgnoreCase("post")) {
                //post请求
                try {
                    request.setCharacterEncoding("utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            return super.getParameter(name);
        }

        @Override
        public Map<String, String[]> getParameterMap() {
            String method = request.getMethod();
            try {
                if (method.equalsIgnoreCase("post")) {//post请求只需要将字符集设置为UTF-8即可
                    request.setCharacterEncoding("utf-8");
                } else if (method.equalsIgnoreCase("get")) {//get请求需要将每个key对应的value重新编码
                    Map<String, String[]> newMap = new HashMap<>();
                    Map<String, String[]> map = request.getParameterMap();
                    for (String key : map.keySet()) {
                        String[] values = map.get(key);
                        for (String value : values) {
                            value = new String(value.getBytes("iso-8859-1"), "utf-8");
                        }
                        newMap.put(key, values);
                    }
                    return newMap;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return super.getParameterMap();
        }

        @Override
        public String[] getParameterValues(String name) {
            String method = request.getMethod();
            try {
                if (method.equalsIgnoreCase("post")) {
                    request.setCharacterEncoding("utf-8");
                } else if (method.equalsIgnoreCase("get")) {
                    String[] values = request.getParameterValues(name);
                    for (String value : values) {
                        value = new String(value.getBytes("iso-8859-1"), "utf-8");
                    }
                    return values;
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return super.getParameterValues(name);
        }
    }

}


