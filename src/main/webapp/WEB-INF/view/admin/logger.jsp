<%@ page import="com.hzfmvc.forthexam.utils.SecUtils" %><%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2022/5/16
  Time: 16:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<%--    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"--%>
<%--          rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">--%>

</head>
<body>
<%--<c:out value="${pageContext.request.contextPath}"></c:out>--%>

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

                    <sec:authorize access="hasRole('ADMIN')">
                        <img src="${pageContext.request.contextPath }/images/avatar.png" class="img-circle"
                             style="width: 40px;height: 40px;">
                    </sec:authorize>

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
    <h1 class="text-center"><strong>日志列表</strong></h1>
</div>


<a href="${pageContext.request.contextPath }/hzfSpringmvcExam"> <b>返回</b> </a>

<div class="container mt-3">
    <div class="row">
        <table class="table table-hover">
            <thead class="table-light">
            <tr>
                <th scope="col">序列号</th>
                <th scope="col">操作事件</th>
                <th scope="col">Controller URL</th>
                <th scope="col">执行时间</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="log" items="${loggerList}">
                <tr>
                    <td class="table-plus">${log.id}</td>
                    <td>${log.operation}</td>
                    <td>${log.reqURL}</td>
                    <td>${log.spendTime}(ms)</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

</body>

</html>
