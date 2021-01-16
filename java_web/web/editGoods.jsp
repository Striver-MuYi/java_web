<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>商品管理系统</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/resource/css/bootstrap.min.css">
    <!-- href：里面的路径是你导入在static文件夹里面下面bootstrap.min.css所在的路径，下面两个属性一样 -->
    <script type="text/javascript" src="<%=basePath%>/resource/js/jquery-3.5.1.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/resource/js/bootstrap.min.js"></script>

</head>
<body>
<div id="main">
    <nav class="navbar navbar-inverse navbar-fixed-top">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                        aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="${pageContext.request.contextPath}/index.jsp">木易商品管理系统</a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="#">下载</a></li>
                    <li><a href="#">设置</a></li>
                    <li><a href="#">简介</a></li>
                    <li><a href="#">帮助</a></li>
                </ul>
                <form class="navbar-form navbar-right">
                    <input type="text" class="form-control" placeholder="Search...">
                </form>
            </div>
        </div>
    </nav>
    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-3 col-md-2 sidebar"
                 style="height: 100%; float: left; padding-top: 60px; background-color:#F5F5F5">
                <ul class="nav nav-sidebar">
                    <li><a href="${pageContext.request.contextPath}/goods/listAll">商品列表</a></li>
                    <li><a href="${pageContext.request.contextPath}/sysUser/listAll">用户列表</a></li>
                    <li><a href="${pageContext.request.contextPath}/order/listAll">订单列表</a></li>
                    <li><a href="${pageContext.request.contextPath}/stock/listAll">库存列表</a></li>
                </ul>
                <ul class="nav nav-sidebar">
                    <li><a href="">信息管理</a></li>
                    <li><a href="">商家管理</a></li>
                    <li><a href="">运输管理</a></li>
                    <li><a href="">业绩管理</a></li>
                    <li><a href="">活动管理</a></li>
                </ul>
                <ul class="nav nav-sidebar">
                    <li><a href="">系统管理</a></li>
                    <li><a href="">系统日志</a></li>
                    <li><a href="">角色管理</a></li>
                </ul>
            </div>
            <div class="col-md-3 col-md-offset-3" style="float: left;padding-top: 90px;">
                <form action="${psageContext.request.contextPath}/goods/editGoods" method="post">
                    <input type="hidden" name="id" value="${goods.id}"/>
                    <div class="form-group">
                        <label for="goodsName">商品名称：</label>
                        <input type="text" name="goodsName" class="form-control" id="goodsName"
                               placeholder="GoodsName" value="${goods.goodsName}"/>
                    </div>
                    <div class="form-group">
                        <label for="goodsPrice">商品编码：</label>
                        <input type="text" name="goodsPrice" class="form-control" id="goodsPrice"
                               placeholder="GoodsPrice" value="${goods.goodsPrice}"/>
                    </div>
                    <div class="form-group">
                        <label for="goodsType">商品价格：</label>
                        <input type="text" name="goodsType" class="form-control" id="goodsType"
                               placeholder="GoodsType" value="${goods.goodsType}"/>
                    </div>
                    <div style="padding-left: 100px">
                        <button class="btn btn-primary" style="width: 80px">
                            保存
                        </button>
                        <button class="btn btn-default" style="width: 80px;">
                            <a href="${pageContext.request.contextPath}/goods/listAll">返回</a>
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>