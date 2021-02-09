<%@ page import="java.util.List" %>
<%@ page import="StudentTimetablingSystem.model.*" %>

<%--
  Created by IntelliJ IDEA.
  User: shasa
  Date: 07/13/2020
  Time: 8:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New Module</title>
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
            <li>
                <a href="#ManageLecturer" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">Manage
                    Lecturers</a>

                <ul class="collapse list-unstyled" id="ManageLecturer">

                    <li>
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
            <li class="active">
                <a href="#ManageCourses" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">Manage
                    Courses</a>

                <ul class="collapse list-unstyled" id="ManageCourses">

                    <li class="active">
                        <a href="addNewCourse.jsp">Add a New Course</a>
                    </li>
                    <li>
                        <a href="addNewModule.jsp">Add a New Module</a>
                    </li>
                    <li >
                        <a href="viewEditDeleteCourses.jsp">View / Edit / Delete a Particular Course</a>
                    </li>
                    <li >
                        <a href="viewEditDeleteModules.jsp">View / Edit / Delete a Particular Module</a>
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
                    <div class="col-lg-6 col-md-6 mt-0 mt-md-6 addModuleFormImage"></div>
                    <form class="scheduleCass-form" action="ModuleServlet" method="get" id="regForm">
                        <h2 class="h2">Add New Module</h2>
                        <c:set var="SuccessMsg" scope="session" value="${showSuccessMessage}"/>
                        <c:if test="${SuccessMsg == true}" >
                        <p disabled="<c:out value="${SuccessMsg}"/>" style="color: green;font-size: 14px;">${SuccessMsgBody}<p>
                        </c:if>
                        <c:set var="RegisterErrorMsg" scope="session" value="${showRegisterErrorMessage}"/>
                            <c:if test="${RegisterErrorMsg == true}" >
                        <p disabled="<c:out value="${RegisterErrorMsg}"/>" style="color: red;font-size: 14px;">${RegisterErrorMsgBody}<p>
                        </c:if>
                            <c:set var="idError" scope="session" value="${showInvalidIDError}"/>
                            <c:if test="${idError == true}" >
                        <p disabled="<c:out value="${idError}"/>" style="color: red;font-size: 14px;">${IDErrorMessage}<p>
                        </c:if>
                        <div class="form-group">
                            <input type="hidden" value="<%=currentUser.getAdminID()%>" name = "currentUserLoggedInAdmin">
                            <input type="hidden" value="registration" name = "action">
                            <label class="label">Select Course</label>
                            <select name="courseName" id ="courseName" class="form-control" required>
                                <option value="" disabled selected hidden>Select a Course</option>
                                <%
                                    DAO dao = new DAO();
                                    List<Course> listCourse = dao.getCourses();

                                    for (Course course : listCourse) {
                                %>
                                <option value="<%=course.getId()%>"><%=course.getCourseName()%></option>
                                <%
                                    }
                                %>

                            </select>
                        </div>
                        <div class="form-group">
                            <label for="moduleName" class="label">New Module Name:</label>
                            <input type="text" class="form-control" id="moduleName" name="moduleName" required>
                        </div>
                        <div class="form-group">
                            <label for="courseDescription" class="label">Module Description:</label>
                            <textarea type="text" class="form-control" id="courseDescription" name="courseDescription" maxlength="195" rows="4"></textarea>
                        </div>
                        <div class="form-group">
                            <label for="lecturerName" class="label">Assign Module to: </label>
                            <select name="lecturerName" id ="lecturerName" class="form-control" required>
                                <option value="" disabled selected hidden>Select a Lecturer </option>
                                <%
                                    DAO dao2 = new DAO();
                                    List<Lecturer> lecturerList = dao2.getAllLecturers();

                                    for (Lecturer lecturer : lecturerList) {
                                %>
                                <option value="<%=lecturer.getLecturerID()%>"><%=lecturer.getfName()%></option>
                                <%
                                    }
                                %>

                            </select>
                        </div>
                        <div class="form-group">
                            <label for="classroom" class="label">Assign Classroom for Module: </label>
                            <select name="classroom" id ="classroom" class="form-control" required>
                                <option value="" disabled selected hidden>Select a Classroom </option>
                                <%
                                    DAO dao3 = new DAO();
                                    List<Classroom> classroomList = dao3.getAvailableClassroomsForModules();

                                    for (Classroom classroom : classroomList) {
                                %>
                                <option value="<%=classroom.getId()%>"><%=classroom.getClassroomName()%></option>
                                <%
                                    }
                                %>

                            </select>
                        </div>
                        <div class="form-group">
                            <input type="submit" class="form-control btn btn-primary" value="Add">
                        </div>

                    </form>
                </div>
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
