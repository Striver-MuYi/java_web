<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加用户信息页面</title>
    <link href="resource/css/global.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<div id="main">
    <%@include file="header.jsp" %>
    <div id="right">
        <form action="${pageContext.request.contextPath}/sysUser/addSysUser" method="post">
            <p>
                <label>用户名：</label>
                <input name="username" type="text"/>
            </p>
            <p>
                <label> &nbsp;&nbsp;&nbsp;密码：</label>
                <input name="password" type="password" />
            </p>
            <button id="submit">保存</button>
            <button><a href="${pageContext.request.contextPath}/sysUser/listAll">返回</a></button>
<%--            <button id="reset">重置</button>--%>
        </form>
    </div>
</div>
<%@ include file="footer.jsp"%>
</body>
</html>
