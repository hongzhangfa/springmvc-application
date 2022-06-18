<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2022/5/17
  Time: 7:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.hzfmvc.forthexam.utils.SecUtils" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css">
    <script src="/js/bootstrap.min.js"></script>
<%--    <script src="/js/jquery-3.6.0.min.js"></script>--%>
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
    <h1 class="text-center"><strong>作业列表</strong></h1>
</div>

<c:if test="${message != null}">
<div class="alert alert-success text-center alert-dismissible fade show">
    <strong>Info!</strong> ${message}
    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
</div>
</c:if>

<c:if test="${del != null}">
    <div class="alert alert-success text-center alert-dismissible fade show">
        <strong>Info!</strong> ${del}
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
</c:if>

<%--<script type="text/javascript">--%>
<%--        $(document).ready(function () {--%>
<%--        window.setTimeout(function() {--%>
<%--            $(".alert").fadeTo(1000, 0).slideUp(1000, function(){--%>
<%--                $(this).remove();--%>
<%--            });--%>
<%--        }, 3000);--%>
<%--    });--%>
<%--</script>--%>


<div class="container mt-3">
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
                <th scope="col">修改</th>
                <th scope="col">删除</th>
                <th scope="col">数据统计</th>
                <!-- 	<th scope="col">Delete</th>  -->
            </tr>
            </thead>
            <tbody>
            <c:forEach var="task" items="${taskList}">
                <tr>
                    <td class="table-plus">${task.id}</td>
                    <td>${task.title}</td>
<%--                    <fmt:parseDate value="${requireInfoBean.releaseTime}" var="releaseTime" pattern="yyyy-MM-dd"/>--%>

                    <td><fmt:formatDate value="${task.startTime}" pattern="yyyy-MM-dd"/></td>
                    <td><fmt:formatDate value="${task.deadline}" pattern="yyyy-MM-dd"/></td>

<%--                    <td>${task.startTime}</td>--%>
<%--                    <td>${task.deadline}</td>--%>
                    <td>${task.arranger}</td>

                    <td><a href="edittask?taskid=${task.id}"
                           class="btn btn-outline-warning">编辑</a>	</td>
                    <td> <a href="deletetask?taskid=${task.id}"
                            class="btn btn-danger"> 删除 </a></td>
                    <td><a href="checktask?taskid=${task.id}"
                           class="btn">提交列表</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<div style="text-align: center">
<a href="${pageContext.request.contextPath }/hzfSpringmvcExam"
   class="btn btn-primary"> 返回 </a>

</div>


</body>
</html>
