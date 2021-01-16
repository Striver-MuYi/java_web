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
    <link rel="stylesheet" href="<%=basePath%>/resource/css/bootstrap.min.css">
<%--    <link rel="stylesheet" type="text/css">--%>
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
                <form class="navbar-form navbar-right" action="${pageContext.request.contextPath}/goods/findByName" method="post">
                    <label for="search"  style="color: white">商品名称：</label>
                    <input type="text" id="search" name="goodsName" class="form-control" placeholder="按名称查询">
                    <button type="submit" class="btn btn-primary">查询</button>
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
            <div class="col-md-10" style="float: left;padding-top: 60px;">
                <div style="width: 100%">
                    <div style="width: 92%; float: left">
                        <h4>数据列表</h4>
                    </div>
                    <div style="float: left">
                        <button type="button" class="btn btn-primary" style="width: 80px" data-toggle="modal"
                                data-target="#addGoods">
<%--                            <span class="glyphicon glyphicon-user" aria-hidden="true"></span>--%>
                            新增
                        </button>

                    </div>
                </div>
                <table class="table table-striped table-hover">
                    <thead>
                    <tr>
                        <th>商品编号</th>
                        <th>商品名称</th>
                        <th>商品价格</th>
                        <th>商品类型</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="item" items="${list}" varStatus="i">
                        <tr>
                            <td>${item.id}</td>
                            <td>${item.goodsName}</td>
                            <td>${item.goodsPrice}</td>
                            <td>${item.goodsType}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/goods/findById?gid=${item.id}">修改</a>
                                <a href="${pageContext.request.contextPath}/goods/deleteGoodsById?gid=${item.id}">删除</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<!-- 模态框 -->
<div class="modal fade" id="addGoods" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">
                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增商品信息
                </h4>
            </div>
            <div class="modal-body">
                <form action="${pageContext.request.contextPath}/goods/addGoods" method="post">
                    <input type="hidden" name="id"/>
                    <div class="form-group">
                        <label for="goodsName">商品名称：</label>
                        <input type="text" name="goodsName" class="form-control" id="goodsName"
                               placeholder="GoodsName"/>
                    </div>
                    <div class="form-group">
                        <label for="goodsPrice">商品价格：</label>
                        <input type="text" name="goodsPrice" class="form-control" id="goodsPrice"
                               placeholder="GoodsPrice"/>
                    </div>
                    <div class="form-group">
                        <label for="goodsType">商品类型：</label>
                        <input type="text" name="goodsType" class="form-control" id="goodsType"
                               placeholder="GoodsType"/>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="addGoods()">提交</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</body>
<script type="text/javascript">

    //  添加按钮事件
    function addGoods() {
        var goodsName = $('#goodsName').val();
        var goodsPrice = $('#goodsPrice').val();
        var goodsType = $('#goodsType').val();
        var goods = {
            goodsName: goodsName,
            goodsPrice: goodsPrice,
            goodsType: goodsType
        };
        console.log("商品表单提交", goods);
        $.ajax({
            url: '/goods/addGoods',
            method: 'POST',
            data: goods,
            success: function (resp) {
                console.log("表单提交成功");
                //  关闭模态框
                $('#addGoods').modal('hide');
            }
        });
    }
</script>
</html>
