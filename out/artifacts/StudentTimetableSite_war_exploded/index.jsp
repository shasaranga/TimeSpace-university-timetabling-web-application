<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="StudentTimetablingSystem.model.DAO " %>
<%@ page import="java.util.List" %>


<%--
  Created by IntelliJ IDEA.
  User: shasa
  Date: 07/11/2020
  Time: 10:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Student Timetabling System</title>
    <%--    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"--%>
    <%--        crossorigin="anonymous">--%>
    <link href="https://fonts.googleapis.com/css?family=Poppins:200,300,400,500,600,700,800&amp;display=swap"
          rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css"
          integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="css/styles.css">
<%--    <link rel="stylesheet" href="css">--%>
</head>
<body>

<section class="loginFormWrappper ">
    <div class="overlays">
        <div class="h-txt text-light">
            <div class="container row no-gutters justify-content-start align-items-center ">
                <div class ="card-wrapper navChangeLimit">
                    <div class="col-lg-6 col-md-6 mt-0 mt-md-6 loginImage" ></div>
                    <form class="login-form" action="UserLoginServlet" method="post" id="logForm" >
                        <h2 class="h2">Sign In</h2>
                        <div class="form-group">
                            <label for="username" class="label">Email Address:</label>
                            <input type="text" class="form-control" id="username" name="username">
                        </div>
                        <div class="form-group">
                            <div class="form-group">
                                <label for="pass" class="label">Password :</label>
                                <input type="password" class="form-control" id="pass" name="pass">
                            </div>
                        </div>
                        <c:set var="regError" scope="session" value="${showLoginError}"/>
                        <c:if test="${regError == true}" >
                        <p disabled="<c:out value="${regError}"/>" style="color: red;font-size: 14px;">Incorrect Username or Password!<p>
                        </c:if>
                        <c:set var="logoutMess" scope="session" value="${showLogout}"/>
                            <c:if test="${logoutMess == true}" >
                        <p disabled="<c:out value="${logoutMess}"/>" style="color: green;font-size: 14px;">${showLogoutMsg}<p>
                        </c:if>
                        <div class="form-group">

                            <input type="submit" class="form-control btn btn-primary" value="Sign In">
                        </div>

                    </form>
                </div>
            </div>
        </div>
    </div>
</section>





<script src="js/jquery.min.js"></script>
<script src="js/popper.js"></script>
<script src="js/bootstrap.min.js"></script>

<script src="js/navBar_ScrollEffect.js"></script>
</body>
</html>
