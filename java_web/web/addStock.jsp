<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加库存信息页面</title>
    <link href="resource/css/global.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<div id="main">
    <%@include file="header.jsp" %>
    <div id="right">
        <form action="${pageContext.request.contextPath}/stock/addStock" method="post">
            <p>
                <label>产品名称：</label>
                <input name="productName" type="text"/>
            </p>
            <p>
                <label> &nbsp;&nbsp;&nbsp;编码：</label>
                <input name="code" type="text" />
            </p>
            <p>
                <label> &nbsp;&nbsp;&nbsp;单价：</label>
                <input name="price" type="text" />
            </p>
            <p>
                <label> &nbsp;&nbsp;&nbsp;数量：</label>
                <input name="number" type="text" />
            </p>
            <p>
                <label> &nbsp;&nbsp;&nbsp;供应商：</label>
                <input name="supplier" type="text" />
            </p>

            <button id="submit">保存</button>
            <button><a href="${pageContext.request.contextPath}/stock/listAll">返回</a></button>
            <%--            <button id="reset">重置</button>--%>
        </form>
    </div>
</div>
<%@ include file="footer.jsp"%>
</body>
</html>
