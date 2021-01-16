<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>添加商品信息页面</title>
    <link href="resource/css/global.css" type="text/css" rel="stylesheet"/>
</head>

<body>
<div id="main">
    <%@include file="header.jsp" %>
    <div id="right">
        <form action="${pageContext.request.contextPath}/goods/addGoods" method="post">
            <p>
                <label>商品名称：</label>
                <input name="goodsName" id="goodsName" type="text" />
            </p>
            <p>
                <label>商品价格：</label>
                <input name="goodsPrice" id="goodsPrice" type="text" />
            </p>
            <p>
                <label>商品类型：</label>
                <input name="goodsType" id="goodsType" type="text" />
            </p>
            <button id="submit">保存</button>
            <button><a href="${pageContext.request.contextPath}/goods/listAll">返回</a></button>
        </form>
    </div>

</div>
<%@ include file="footer.jsp"%>
</body>
</html>
