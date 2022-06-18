<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2022/5/15
  Time: 22:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.hzfmvc.forthexam.utils.*" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css">
</head>
<body>
<%--用户名:  <%=SecUtils.getCurrentUsername()%>--%>

<nav class="navbar navbar-expand-lg bg-light">
    <div class="container-fluid">
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">

                <li><strong><span
                        style="line-height:55px;">角色: <%=SecUtils.getCurrentAuthorities()%>&nbsp
              <% String currentUserRole = SecUtils.getCurrentAuthorities();

                  if(currentUserRole.equals("[ROLE_ADMIN]")) {%>
                    <sub>管理员[<%=SecUtils.getCurrentUsername()%>]</sub>
                    <%
                    } else if(currentUserRole.equals("[ROLE_TEACHER]")) {%>
                    <sub>教师</sub>
<%
} else {%>
      <sub>学生</sub>
<%
    }
%>
                </span></strong>
                </li>
                <li>
                    <!-- <a href="#"><span class="glyphicon glyphicon-envelope" style="font-size: 20px;"></a> -->
<%--                    <sec:authorize access="hasRole('ADMIN')">--%>
<%--                        <img src="${pageContext.request.contextPath }/images/avatar.png" class="img-circle"--%>
<%--                             style="width: 40px;height: 40px;">--%>
<%--                    </sec:authorize>--%>
<%--                    <sec:authorize access="hasRole('STUDENT')">--%>
<%--                        <img src="${pageContext.request.contextPath }/images/student.png" class="img-circle"--%>
<%--                             style="width: 40px;height: 40px;">--%>
<%--                    </sec:authorize>--%>
<%--                    <sec:authorize access="hasRole('TEACHER')">--%>
<%--                        <img src="${pageContext.request.contextPath }/images/teacher.png" class="img-circle"--%>
<%--                             style="width: 40px;height: 40px;">--%>
<%--                    </sec:authorize>--%>
<%--                    --%>
                </li>

            </ul>
            <form class="d-flex" role="search">
                <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                <button class="btn btn-outline-success" type="submit">Search</button>
            </form>
        </div>
    </div>
</nav>
<div style="text-align: center">
    <h1 class="text-center"><strong>更新密码</strong></h1>
</div>

<!-- 修改密码表单 -->
<div class="container mt-3 text-center">

    <form action="/hzfSpringmvcExam/updatepwdProcess" method="post">
        <%--    <input type="text" id="username" name="username"--%>
        <%--           class="form-control" placeholder="用户名" required value="${SecUtils.getCurrentUsername()}">--%>
        <%--    <input type="password" id="oldPassword" name="oldPassword"--%>
        <%--           class="form-control" placeholder="原密码" required>--%>
        <div class="col">
            <div class="form-group m-3">
                <input type="password" name="oldPassword"
                       class="form-control" placeholder="旧密码" required/>

            </div>

        </div>
        <div class="col">
            <div class="form-group m-3">
                <input type="password" name="newPassword"
                       class="form-control" placeholder="新密码" required/>
            </div>
        </div>


        <hr/>
        <button class="btn btn-primary" type="submit">确认</button>
        <a href="${pageContext.request.contextPath }/hzfSpringmvcExam"
           class="btn btn-warning"> 取消 </a>


    </form>
</div>
</body>
</html>
