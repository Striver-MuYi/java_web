<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>消息页面</title>
</head>
<body>
    <h4>${msg}</h4>
    <button>
        <a href="${pageContext.request.contextPath}/sysUser/listAll">${returnPageName}</a>
    </button>
</body>
</html>
