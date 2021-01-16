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
                <form class="navbar-form navbar-right" action="${pageContext.request.contextPath}/stock/findByName" method="post">
                    <label for="search"  style="color: white">产品名称：</label>
                    <input type="text" id="search" name="productName" class="form-control" placeholder="按产品名称查询">
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
                                data-target="#addStock">
                            新增
                        </button>

                    </div>
                </div>
                <table class="table table-striped table-hover">
                    <thead>
                    <tr>
                        <th>产品名称</th>
                        <th>产品编码</th>
                        <th>产品单价</th>
                        <th>产品数量</th>
                        <th>供应商</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${list}" varStatus="i" var="item">
                        <tr>
                            <td>${item.productName}</td>
                            <td>${item.code}</td>
                            <td>${item.price}</td>
                            <td>${item.number}</td>
                            <td>${item.supplier}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/stock/findById?sid=${item.id}">修改</a>
                                <a href="${pageContext.request.contextPath}/stock/deleteStockById?sid=${item.id}">删除</a>
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
<div class="modal fade" id="addStock" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">新增库存信息</h4>
            </div>
            <div class="modal-body">
                <form action="${pageContext.request.contextPath}/goods/addGoods" method="post">
                    <input type="hidden" name="id"/>
                    <div class="form-group">
                        <label for="productName">产品名称：</label>
                        <input type="text" name="productName" class="form-control" id="productName"
                               placeholder="ProductName"/>
                    </div>
                    <div class="form-group">
                        <label for="code">产品编号：</label>
                        <input type="text" name="code" class="form-control" id="code"
                               placeholder="Code"/>
                    </div>
                    <div class="form-group">
                        <label for="price">产品单价：</label>
                        <input type="text" name="price" class="form-control" id="price"
                               placeholder="Price"/>
                    </div>
                    <div class="form-group">
                        <label for="number">产品数量：</label>
                        <input type="text" name="number" class="form-control" id="number"
                               placeholder="Number"/>
                    </div>
                    <div class="form-group">
                        <label for="supplier">供应商：</label>
                        <input type="text" name="supplier" class="form-control" id="supplier"
                               placeholder="Supplier"/>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="addStock()">提交</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</body>
<script type="text/javascript">

    //  添加按钮事件
    function addStock() {
        var productName = $('#productName').val();
        var code = $('#code').val();
        var price = $('#price').val();
        var number = $('#number').val();
        var supplier = $('#supplier').val();
        var stock = {
            productName: productName,
            code: code,
            price: price,
            number: number,
            supplier: supplier
        };
        console.log("库存表单提交", stock);
        $.ajax({
            url: '/stock/addStock',
            method: 'POST',
            data: stock,
            success: function (resp) {
                console.log("表单提交成功");
                //  关闭模态框
                $('#addStock').modal('hide');
            }
        });
    }
</script>
</html>