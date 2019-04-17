<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/3
  Time: 22:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>XXX网络商城</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css"/>
    <script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
</head>
<body>
<!--
        描述：菜单栏
    -->
<div class="container-fluid">
    <div class="col-md-4">
        <img src="${pageContext.request.contextPath}/img/logo2.png"/>
    </div>
    <div class="col-md-5">
        <img src="${pageContext.request.contextPath}/img/header.png"/>
    </div>
    <div class="col-md-3" style="padding-top:20px">
        <ol class="list-inline">
            <c:if test="${loginUser==null}">
                <%--<li><a href="${pageContext.request.contextPath}/jsp/login.jsp">登录</a></li>--%>
                <li><a href="${pageContext.request.contextPath}/UserServlet?method=loginUI">登录</a></li>
                <%--<li><a href="${pageContext.request.contextPath}/jsp/register.jsp">注册</a></li>--%>
                <li><a href="${pageContext.request.contextPath}/UserServlet?method=registerUI">注册</a></li>
            </c:if>
            <c:if test="${loginUser!=null}">
                <li>欢迎您,${loginUser.username}</li>
                <%--<li><a href="${pageContext.request.contextPath}/jsp/register.jsp">注册</a></li>--%>
                <li><a href="${pageContext.request.contextPath}/UserServlet?method=userLogout">退出</a></li>

                <li><a href="${pageContext.request.contextPath}/jsp/cart.jsp">购物车</a></li>
                <li><a href="${pageContext.request.contextPath}/jsp/order_list.jsp">我的订单</a></li>
            </c:if>

        </ol>
    </div>
</div>
<!--
    描述：导航条
-->
<div class="container-fluid">
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                        data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="${pageContext.request.contextPath}">首页</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav" id="categoryUl">
                    <%--<c:forEach items="${category}" var="item">--%>
                        <%--<li class="active"><a href="${pageContext.request.contextPath}/jsp/product_list.jsp">${item.cname}<span--%>
                                <%--class="sr-only">(current)</span></a></li>--%>
                    <%--</c:forEach>--%>
                </ul>
                <form class="navbar-form navbar-right" role="search">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Search">
                    </div>
                    <button type="submit" class="btn btn-default">Submit</button>
                </form>

            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>
</div>
</body>
<script type="text/javascript">
    <%--第二种方式:异步从数据库里面加载分类
        弊端,当多次刷新首页时就会多次从数据库中获取分类信息,给数据库增加压力,因此也不再采用这种方式
    --%>
    $(function () {
        $.post("http://localhost:8080/${pageContext.request.contextPath}/categoryServlet",{"method":"findAllCategories"},function (data) {
            $.each(data,function (i,n) {
                var li="<li class='active'><a href=\'${pageContext.request.contextPath}/productServlet?method="+
                    "findProductsByCategoryWithPage&num=1&cid="+n.cid+"'>"+n.cname+"</a></li>";
                $("#categoryUl").append(li);
            })
        },"json");
    });
</script>
</html>
