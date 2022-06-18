<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2022/5/15
  Time: 8:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%!%>
<%@ page import="com.hzfmvc.forthexam.utils.*" %>
<%@ page import="aj.org.objectweb.asm.ConstantDynamic" %>

<html>
<head>
    <title>Title</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css">
<%--    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/style.css">--%>
        <script src="/js/bootstrap.min.js"></script>
<%--    <script src="/js/index.js"></script>--%>
</head>
<body>
<c:if test="${success != null}">
    <div class="alert alert-success text-center alert-dismissible fade show">
        <strong>Info!</strong> ${success}
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
</c:if>

<c:if test="${msg != null}">
    <div class="alert alert-warning text-center alert-dismissible fade show">
        <strong>Info!</strong> ${msg}
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
</c:if>

<c:set var="currentUsername" scope="session" value="${SecUtils.getCurrentUsername()}"/>
<c:set var="currentUserRole" scope="session" value="${SecUtils.getCurrentAuthorities()}"/>

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
                    <sub>教师[<%=SecUtils.getCurrentUsername()%>]</sub>
<%
    } else {%>
      <sub>学生[<%=SecUtils.getCurrentUsername()%>]</sub>
<%
    }
%>
                </span></strong>
                </li>
                <li>
                    <!-- <a href="#"><span class="glyphicon glyphicon-envelope" style="font-size: 20px;"></a> -->
                    <sec:authorize access="hasRole('ADMIN')">
                        <img src="${pageContext.request.contextPath }/images/avatar.png" class="img-circle"
                             style="width: 40px;height: 40px;">
                    </sec:authorize>
                    <sec:authorize access="hasRole('STUDENT')">
                        <img src="${pageContext.request.contextPath }/images/student.png" class="img-circle"
                             style="width: 40px;height: 40px;">
                    </sec:authorize>
                    <sec:authorize access="hasRole('TEACHER')">
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
<h1 class="text-center"><strong>SpringMVC WebApp</strong></h1>
</div>


            <hr>

            <%--<%=SecUtils.getCurrentUsername()%>--%>

            <%--<c:out value="${currentUsername}"/>--%>

            <div style="text-align: center">
                <section sec:authorize="isAuthenticated()">

                    <p> 欢迎回来, 用户: <b><sec:authentication property="principal.username"/> </b>
                        <%--    角色: <sec:authentication property="principal.authorities" />--%>
                    </p>

                </section>
                <%--STUDENT / ADMIN / TEACHER --%>
                <sec:authorize access="hasRole('ADMIN')">
                    <%--    <a href="${pageContext.request.contextPath}/admin/showLog">系统日志</a> (Administrator访问) <hr>--%>
                    <form:form action="${pageContext.request.contextPath}/hzfSpringmvcExam/admin/showLogs"
                               method="POST">
                        <input type="submit" value="系统日志" class="btn btn-primary" style="width: 450px;"/>
                    </form:form>
                </sec:authorize>

                <sec:authorize access="hasRole('TEACHER')">
                    <%--    <a href="./teacher/newTask">发布作业</a> (Teacher访问)--%>
                    <form:form action="${pageContext.request.contextPath}/hzfSpringmvcExam/teacher/newTask"
                               method="POST">
                        <input type="submit" value="发布作业" class="btn btn-primary" style="width: 450px;"/>
                    </form:form>

                    <form:form action="${pageContext.request.contextPath}/hzfSpringmvcExam/teacher/listTask"
                               method="POST">
                        <input type="submit" value="作业列表" class="btn btn-primary" style="width: 450px;"/>
                    </form:form>

                </sec:authorize>

                <sec:authorize access="hasRole('STUDENT')">
                    <%--    <a href="/student/submitTsak">提交作业</a> (Student访问)--%>
                    <form:form action="${pageContext.request.contextPath}/hzfSpringmvcExam/student/submitTask"
                               method="POST">
                        <input type="submit" value="提交作业" class="btn btn-primary" style="width: 450px;"/>
                    </form:form>

                </sec:authorize>

                <br>
                <form:form action="${pageContext.request.contextPath}/hzfSpringmvcExam/updatepwd"
                           method="POST">
                    <input type="submit" value="修改密码" class="btn btn-primary" style="width: 450px;"/>
                </form:form>

                <hr>

                <form:form action="logout" method="post">
                    <input type="submit" value="LogOut" class="btn btn-danger"/>
                </form:form>

            </div>


</body>
</html>
