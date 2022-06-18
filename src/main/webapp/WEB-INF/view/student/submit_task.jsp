<%@ page import="com.hzfmvc.forthexam.utils.SecUtils" %><%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2022/5/18
  Time: 14:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
                    <sub>教师</sub>
<%
} else {%>
      <sub>学生[<%=SecUtils.getCurrentUsername()%>]</sub>
<%
    }
%>
                </span></strong>
                </li>
                <li>

                    <sec:authorize access="hasRole('STUDENT')">
                        <img src="${pageContext.request.contextPath }/images/student.png" class="img-circle"
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
    <h1 class="text-center"><strong>提交作业</strong></h1>
</div>


<div class="container mt-3 text-center">
    <%--@elvariable id="task" type="com.hzfmvc.forthexam.entity.Task"--%>
    <form:form action="addSubmitProcess" modelAttribute="task" method="post">
    <!-- 判断是否存在同一id数据，更新 !重要 -->
        <form:hidden path="id"/>

    <div class="col-auto">
        <div class="col">
            <div class="form-group mb-3">
                <label for="title">作业描述</label>
                <form:input type="text" class="form-control" id="title"
                            path="title" placeholder="Enter title"/>
            </div>
        </div>

        <div class="col">
            <div class="form-group mb-3">
                <label for="deadline">截止时间</label>
                <form:input type="date" class="form-control" id="deadline"
                            path="deadline" placeholder="Enter deadline" />
            </div>
        </div>

        <%--@elvariable id="submission" type="com.hzfmvc.forthexam.entity.Submission"--%>
        <form:form action="addSubmitProcess" modelAttribute="submission" method="post">
            <%-- 	<form:hidden path="id"/> --%>
            <div class="col">
                <div class="form-group mb-3">
                    <label for="content">作业内容</label>
                    <form:input type="text" class="form-control" id="content"
                                path="content" placeholder="Enter content" />
                </div>
            </div>
            <br/>
            <button type="submit" class="btn btn-primary">提交</button>
            <a href="${pageContext.request.contextPath }/hzfSpringmvcExam/student/submitTask"
               class="btn btn-warning"> 返回 </a>
        </form:form>
    </div>
    <hr>
    </form:form>




</body>
</html>
