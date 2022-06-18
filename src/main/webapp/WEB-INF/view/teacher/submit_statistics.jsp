<%@ page import="com.hzfmvc.forthexam.utils.SecUtils" %><%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2022/5/18
  Time: 7:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css">

</head>
<body>
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
                    <sub>管理员</sub>
                    <%
                    } else if(currentUserRole.equals("[ROLE_TEACHER]")) {%>
                    <sub>教师[<%=SecUtils.getCurrentUsername()%>]</sub>
<%
} else {%>
      <sub>学生</sub>
<%
    }
%>
                </span></strong>
                </li>
                <li>

                    <sec:authorize access="hasRole('STUDENT')">
                        <img src="${pageContext.request.contextPath }/images/teacher.png" class="img-circle"
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
    <h1 class="text-center"><strong>作业-${param.taskid}-提交列表</strong></h1>
</div>

<div class="container mt-3">
    <br>
    <hr>
    <div class="row">
        <table class="table table-hover">
            <caption>List of submissions</caption>
            <thead class="table-light">
            <tr>
                <th scope="col">序号</th>
                <th scope="col">作业描述</th>
                <th scope="col">学生姓名</th>
                <th scope="col">提交时间</th>
                <th scope="col">提交内容</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="submission" items="${submissionList}">
                <tr>
                    <td class="table-plus">${submission.id}</td>
                    <td>${submission.title}</td>
                    <td>${submission.studentName}</td>
                    <td><fmt:formatDate value="${submission.submitTime}" pattern="yyyy-MM-dd"/></td>
                     <td>${submission.content}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<div style="text-align: center">
<a href="${pageContext.request.contextPath }/hzfSpringmvcExam/teacher/listTask"
   class="btn btn-warning"> 返回 </a>
</div>
</body>
</html>
