<%@ page import="StudentTimetablingSystem.model.Admin" %>
<%@ page import="StudentTimetablingSystem.model.DAO" %>
<%@ page import="StudentTimetablingSystem.model.Course" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: shasa
  Date: 09/21/2020
  Time: 9:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register Lecturer</title>
    <link href="https://fonts.googleapis.com/css?family=Poppins:200,300,400,500,600,700,800&amp;display=swap"
          rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css"
          integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="css/controlPanelStyle.css">
</head>
<body>
<div class="wrapper">
    <nav id="sidebar">
        <div class="sidebar-header">
            <a href="#" class="navbar-brand logo"><img class="logo" src="images/logo.png">Time <span class="brandHalf">Space</span></a>
        </div>

        <ul class="list-unstyled components">
            <p style="font-weight: bold;">Control Options</p>
            <li>
                <a href="searchStudentTimeTableForAdmin.jsp">Search Student Timetable</a>
            </li>
            <li>
                <a href="searchLecturerTimeTableForAdmin.jsp">Search Lecturer Timetable</a>
            </li>
            <li>
                <a href="#ManageStudent" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">Manage
                    Students</a>

                <ul class="collapse list-unstyled" id="ManageStudent">

                    <li>
                        <a href="addStudent.jsp">Add a New Student</a>
                    </li>
                    <li >
                        <a href="viewEditDeleteStudent.jsp">View / Edit / Delete a Particular Student</a>
                    </li>

                </ul>
            </li>
            <li class="active">
                <a href="#ManageLecturer" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">Manage
                    Lecturers</a>

                <ul class="collapse list-unstyled" id="ManageLecturer">

                    <li class="active">
                        <a href="addLecturer.jsp">Add a New Lecturer</a>
                    </li>
                    <li >
                        <a href="viewEditDeleteLecturer.jsp">View / Edit / Delete a Particular Lecturer</a>
                    </li>

                </ul>

            </li>
            <li>
                <a href="#ManageTimetabler" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">Manage
                    Timatablers</a>

                <ul class="collapse list-unstyled" id="ManageTimetabler">

                    <li>
                        <a href="addTimetabler.jsp">Add a New Timetabler</a>
                    </li>
                    <li >
                        <a href="viewEditDeleteTimetabler.jsp">View / Edit / Delete a Particular Timetabler</a>
                    </li>

                </ul>

            </li>
            <li>
                <a href="#ManageCourses" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">Manage
                    Courses</a>

                <ul class="collapse list-unstyled" id="ManageCourses">

                    <li >
                        <a href="addNewCourse.jsp">Add a New Course</a>
                    </li>
                    <li >
                        <a href="addNewModule.jsp">Add a New Module</a>
                    </li>
                    <li >
                        <a href="viewEditDeleteCourses.jsp">View / Edit / Delete a Particular Course</a>
                    </li>
                    <li >
                        <a href="#">View / Edit / Delete a Particular Module</a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="#ManageClass" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">Manage
                    Classrooms</a>

                <ul class="collapse list-unstyled" id="ManageClass">

                    <li>
                        <a href="addClass.jsp">Add a Classroom</a>
                    </li>
                    <li >
                        <a href="viewEditDeleteClassrooms.jsp">View / Edit / Delete a Classroom</a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="#ManageBadge" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">Manage
                    Badges</a>

                <ul class="collapse list-unstyled" id="ManageBadge">

                    <li>
                        <a href="addNewBadge.jsp">Add a Badge</a>
                    </li>
                    <li >
                        <a href="viewEditDeleteBadges.jsp">View / Edit / Delete a Badge</a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="LogOutServlet">Logout</a>
            </li>
        </ul>
    </nav>
    <div id="content">
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container-fluid">

                <button type="button" id="sidebarCollapse" class="btn btn-info">
                    <i class="fas fa-align-left"></i>
                </button>

            </div>
        </nav>
        <%
            Admin currentUser = (Admin) session.getAttribute("currentSessionUser");
        %>
        <section class="scheduleClassFormWrappper ">
            <div class="container row no-gutters justify-content-start align-items-center ">
                <div class="card-wrapper navChangeLimit">
                    <div class="col-lg-6 col-md-6 mt-0 mt-md-6 lecturerRegFormImage"></div>
                    <form class="scheduleCass-form" action="LecturerServlet" method="get" id="regForm"
                          enctype="multipart/form-data">
                        <h2 class="h2">Lecturer Registration</h2>
                        <c:set var="SuccessMsg" scope="session" value="${showSuccessMessage}"/>
                        <c:if test="${SuccessMsg == true}" >
                        <p disabled="<c:out value="${SuccessMsg}"/>" style="color: green;font-size: 14px;">${SuccessMsgBody}<p>
                        </c:if>
                        <c:set var="RegisterErrorMsg" scope="session" value="${showRegisterErrorMessage}"/>
                        <c:if test="${RegisterErrorMsg == true}" >
                        <p disabled="<c:out value="${RegisterErrorMsg}"/>" style="color: red;font-size: 14px;">${RegisterErrorMsgBody}<p>
                        </c:if>
                        <div class="form-group">
                            <div class="form-group">

                                <input type="hidden" value="<%=currentUser.getAdminID()%>" name = "currentUserLoggedInAdmin">
                                <input type="hidden" value="registration" name = "action">
                                <label for="lecturerID" class="label">Lecturer ID (ex: LE0000):</label>
                                <input type="text" class="form-control" id="lecturerID" name="lecturerID" maxlength="10" required>
                            </div>
                            <c:set var="idError" scope="session" value="${showInvalidIDError}"/>
                            <c:if test="${idError == true}" >
                        <p disabled="<c:out value="${idError}"/>" style="color: red;font-size: 14px;">${IDErrorMessage}<p>
                        </c:if>
                </div>
                <div class="form-group">
                    <label for="fName" class="label">First Name :</label>
                    <input type="text" class="form-control" id="fName" name="fName" required>
                </div>
                <div class="form-group">
                    <div class="form-group">
                        <label for="lName" class="label">Last Name :</label>
                        <input type="text" class="form-control" id="lName" name="lName" required>
                    </div>
                </div>

                <div class="form-group">
                    <div class="form-group">
                        <label for="address" class="label">Address :</label>
                        <input type="text" class="form-control" id="address" name="address" maxlength="100" required>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-6">
                        <div class="form-group">
                            <div class="form-group">
                                <label for="email" class="label">Email :</label>
                                <input type="email" class="form-control" id="email" name="email" maxlength="100" required>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="form-group">
                            <label for="pwd" class="label">Password :</label>
                            <input type="password" class="form-control" id="pwd" name="pwd" maxlength="100" required>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-6">
                        <div class="form-group">
                            <label for="mobileNumber" class="label">Mobile Number :</label>
                            <input type="text" class="form-control" id="mobileNumber" name="mobileNumber"
                                   title="Error Message" pattern="[0]{1}[0-9]{9}" required>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="form-group">
                            <label for="emMobileNumber" class="label">Emergency Mobile Number :</label>
                            <input type="text" class="form-control" id="emMobileNumber" name="emMobileNumber"
                                   title="Error Message" pattern="[0]{1}[0-9]{9}" required>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <input type="submit" class="form-control btn btn-primary" value="Register">
                </div>

                </form>
            </div>


        </section>
    </div>
</div>


<script src="js/jquery.min.js"></script>
<script src="js/popper.js"></script>
<script src="js/bootstrap.min.js"></script>

<script src="js/navBar_ScrollEffect.js"></script>
<script type="text/javascript">
    $(document).ready(function () {

        $('#sidebarCollapse').on('click', function () {
            $('#sidebar').toggleClass('active');
        });

    });


</script>
</body>
</html>
