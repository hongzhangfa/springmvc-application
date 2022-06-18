<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2022/5/15
  Time: 20:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Title</title>
    <link type="text/css" href="/css/login.css" rel="stylesheet"/>
</head>
<body>
<%--<h2>用户登录</h2>--%>

<%--<form:form action="checktheUser" method="post">--%>
<%--&lt;%&ndash;    <span>param.error</span> &ndash;%&gt;--%>
<%--    ${param.error}--%>
<%--    <c:if test="${ param.error != null }">--%>
<%--        <i class="failed"> 认证失败！(用户名/密码错误)</i>--%>
<%--    </c:if>--%>

<%--    <input type="text" name="username" placeholder="输入用户名" />--%>
<%--    <input type="text" name="password" placeholder="输入用户名" />--%>
<%--    <input type="submit" value="Login"/>--%>
<%--</form:form>--%>


<form:form action="checktheUser" class="login" method="post">
    <h1>Sign in</h1>
    <input type="text" name="username" placeholder="Username" />
    <input type="password" name="password" placeholder="Password" />
    <input type="submit" value="Login" />

    ${param.error}
    <c:if test="${ param.error != null }">
        <i style="color: red"> 认证失败！(用户名/密码错误)</i>
    </c:if>

</form:form>


</body>
</html>
