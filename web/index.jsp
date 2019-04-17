<%@page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>
<%--获取首页分页方式一
    通过加载IndexServlet的方式,执行里面的execute方法,再通过请求转发的方式转发到
    jsp/index.jsp,这种方式将首页分类放置到session中,这种只是实现了功能,但是耗费性能
    原因是: 当用户访问量很大时,session需要在内存中独立开辟出一块内存空间,这时极耗费内存
--%>
<%--<jsp:forward page="/IndexServlet?method=execute"/>--%>
<jsp:forward page="/IndexServlet"/>
