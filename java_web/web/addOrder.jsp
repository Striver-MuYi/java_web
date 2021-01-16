<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加订单信息页面</title>
    <link href="resource/css/global.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<div id="main">
    <%@include file="header.jsp" %>
    <div id="right">
        <form action="${pageContext.request.contextPath}/order/addOrder" method="post">
            <p>
                <label>订单名称：</label>
                <input name="orderName" type="text"/>
            </p>
            <p>
                <label> &nbsp;&nbsp;&nbsp;订单总金额：</label>
                <input name="orderPrice" type="text" />
            </p>
            <p>
                <label> &nbsp;&nbsp;&nbsp;订单数量：</label>
                <input name="orderNum" type="text" />
            </p>
            <p>
                <label> &nbsp;&nbsp;&nbsp;地址：</label>
                <input name="address" type="text" />
            </p>
            <p>
                <label> &nbsp;&nbsp;&nbsp;电话：</label>
                <input name="phone" type="text" />
            </p>
            <p>
                <label> &nbsp;&nbsp;&nbsp;用户：</label>
                <input name="user_name" type="text" />
            </p>

            <button id="submit">保存</button>
            <button><a href="${pageContext.request.contextPath}/order/listAll">返回</a></button>
            <%--            <button id="reset">重置</button>--%>
        </form>
    </div>
</div>
<%@ include file="footer.jsp"%>
</body>
</html>
