<%@ page import="com.hzfmvc.forthexam.utils.SecUtils" %><%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2022/5/18
  Time: 14:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css">
    <script src="/js/bootstrap.min.js"></script>
</head>
<body>
<c:if test="${msg != null}">
    <div class="alert alert-success text-center alert-dismissible fade show">
        <strong>Info!</strong> ${msg}
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
</c:if>


<c:if test="${del != null}">
    <div class="alert alert-success text-center alert-dismissible fade show">
        <strong>Info!</strong> ${del}
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
</c:if>

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
    <h1 class="text-center"><strong>作业列表</strong></h1>
</div>

<div class="container mt-3 text-center">
<%--    <a href="#" class="btn btn-primary">作业列表</a>--%>
    <br>
    <hr>
    <div class="row">
        <table class="table table-hover">
            <caption>List of tasks</caption>
            <thead class="table-light">
            <tr>
                <th scope="col">序号</th>
                <th scope="col">作业描述</th>
                <th scope="col">布置时间</th>
                <th scope="col">截止时间</th>
                <th scope="col">布置人</th>
                <th scope="col">提交人数</th>
                <th scope="col">修改</th>
                <th scope="col">删除</th>
                <th scope="col">完成进度</th>
                <!-- 	<th scope="col">Delete</th>  -->
            </tr>
            </thead>
            <tbody>


            <c:forEach var="task" items="${taskList}">
                <tr>
                    <td class="table-plus">${task.id}</td>
                    <td>${task.title}</td>
<%--                    <td>${task.startTime}</td>--%>
<%--                    <td>${task.deadline}</td>--%>
                    <td><fmt:formatDate value="${task.startTime}" pattern="yyyy-MM-dd"/></td>
                    <td><fmt:formatDate value="${task.deadline}" pattern="yyyy-MM-dd"/></td>

                    <td>${task.arranger}</td>
                    <td>${task.peopleNum}</td>

                    <td><a href="editsubmit?taskid=${task.id}"
                           class="btn btn-outline-warning">编辑</a>	</td>
                    <td> <a href="deletesubmit?taskid=${task.id}"
                            class="btn btn-danger"> 删除 </a></td>

                    <c:set var="subids" value="${submissionId}"/>
                    <c:set var="tid" value="${task.id}"/>

                    <c:if test="${fn:contains( subids, tid)}" var="flag" scope="session">
                        <td><a href="newsubmit?taskid=${task.id}" class="btn disabled">已提交</a></td>
                    </c:if>
                    <c:if test="${not flag}">
                        <td><a href="newsubmit?taskid=${task.id}" class="btn">提交作业</a></td>
                    </c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <a href="${pageContext.request.contextPath }/hzfSpringmvcExam"
       class="btn btn-primary"> 返回 </a>

</div>


</body>
</html>
