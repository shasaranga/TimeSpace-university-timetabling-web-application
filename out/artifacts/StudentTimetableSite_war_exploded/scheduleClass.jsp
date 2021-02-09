<%@ page import="java.util.List" %>
<%@ page import="StudentTimetablingSystem.model.*" %><%--
  Created by IntelliJ IDEA.
  User: shasa
  Date: 08/31/2020
  Time: 10:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Schedule Class</title>
    <link href="https://fonts.googleapis.com/css?family=Poppins:200,300,400,500,600,700,800&amp;display=swap"
          rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css"
          integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="css/controlPanelStyle.css">
<%--    <link rel="stylesheet" type="text/css" href="css/styles.css">--%>
    <script>
        function getBadgesList() {
            var val = document.getElementById("courseName").value;
            var xhttp = new XMLHttpRequest();
            console.log(val);
            xhttp.onreadystatechange= function () {
                if(xhttp.readyState === 4 && xhttp.status === 200){
                    document.getElementById("badge").innerHTML = xhttp.responseText;
                    console.log(xhttp.responseText);
                }

            };//valajax
            xhttp.open("POST","BadgesServlet?valajax="+ val,true);
            xhttp.send();
        }

        window.onload = function () { setTime(); }
        function setTime() {
            var today=new Date();
            document.getElementById('datetimepicker-default').innerHTML.value = new Date();
        }


    </script>

</head>
<body>
<div class="wrapper">
    <!-- Sidebar -->
    <nav id="sidebar">
        <div class="sidebar-header">
            <a href="#" class="navbar-brand logo"><img class = "logo" src="images/logo.png">Time <span class="brandHalf">Space</span></a>
        </div>

        <ul class="list-unstyled components">
            <p style="font-weight: bold;">Control Options</p>
            <li >
                <a href="searchStudentTimeTableForLecturer.jsp">Search Student Timetable</a>
            </li>
            <li>
                <a href="searchStudentTimeTableForLecturer.jsp">Search Lecturer Timetable</a>
            </li>
            <li class="active">
                <a href="#ManageBookings" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">Manage Bookings</a>

                <ul class="collapse list-unstyled" id="ManageBookings">

                    <li class="active">
                        <a href="scheduleClass.jsp">Schedule a Class</a>
                    </li>
                    <li>
                        <a href="changeBooking.jsp">Change/Delete a Booking</a>
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
                    <%--                    <span>Toggle Sidebar</span>--%>
                </button>

            </div>
        </nav>

        <section class="scheduleClassFormWrappper ">
            <div class="container row no-gutters justify-content-start align-items-center ">
                <div class ="card-wrapper navChangeLimit">
                    <div class="col-lg-6 col-md-6 mt-0 mt-md-6 addClassFormImage" ></div>
                    <form class="scheduleCass-form" action="ScheduleClassServlet" method="POST" id="scheduleClassForm">
                        <h2 class="h2">Schedule Class</h2>
                        <div class="row">
                            <div class="col-lg-6">
                                <div class="form-group">
                                    <label class="label">1) Select Course</label>
                                    <select name="courseName" id ="courseName" class="form-control" onchange="getBadgesList()" required>
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
                            </div>
                            <div class="col-lg-6">
                                <div class="form-group">

                                    <label class="label">2) Select Badge</label>
                                    <select name="badge" id ="badge" class="form-control" required>
                                        <option value="" disabled selected hidden>Select a Badge</option>

                                    </select>

                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-6">
                                <div class="form-group">
                                    <label class="label">3) Select Class Room</label>
                                    <select name="classRoom" id ="classRoom" class="form-control" required>
                                        <option value="" disabled selected hidden>Select a Class Room</option>
                                        <%
                                            List<Classroom> listClassroom = dao.getAvailableClassrooms();
                                            if(listClassroom.size() != 0){
                                                for (Classroom classroom : listClassroom) {
                                        %>
                                                    <option value="<%=classroom.getId()%>"><%=classroom.getClassroomName()%></option>
                                        <%
                                                }
                                            }else{


                                        %>
                                            <option value="No Class Available">No Class Available</option>
                                        <%
                                            }
                                        %>
                                    </select>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="form-group">
                                    <label class="label">4) Date</label>
                                    <input type="datetime-local" id="startDate" name="startDate" class="form-control" required />
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-6">
                                <div class="form-group">
                                    <label class="label">5) Select Duration</label>
                                    <select name="classDuration" id ="classDuration" class="form-control" required>
                                        <option value="" disabled selected hidden>Select Duration (in hours)</option>
                                        <option value="1">1 hour</option>
                                        <option value="2">2 hours</option>
                                        <option value="3">3 hours</option>
                                        <option value="4">4 hours</option>
                                        <option value="5">5 hours</option>
                                        <option value="6">6 hours</option>
                                        <option value="7">7 hours</option>
                                    </select>
                                </div>
                            </div>
                        </div>

                        <c:set var="scheduleError" scope="session" value="${showScheduleError}"/>
                        <c:set var="scheduleSuccess" scope="session" value="${showScheduleSuccess}"/>
                        <c:if test="${scheduleError == true}" >
                        <p disabled="<c:out value="${scheduleError}"/>" style="color: red;font-size: 11px;">${ScheduleErrorAlert}<p>
                        </c:if>
                        <c:if test="${scheduleSuccess == true}" >
                        <p disabled="<c:out value="${scheduleError}"/>" style="color: green;font-size: 11px;">${ScheduleSuccessAlertMessage}<p>
                        </c:if>
                            <% Lecturer currentUser = (Lecturer) session.getAttribute("currentSessionUser");%>
                        <input type="hidden" name = "lectureID" value = "<%= currentUser.getLecturerID()%>" >
                        <input type="hidden" name = "lectureFullName" value = "<%= currentUser.getfName() + " " + currentUser.getlName()%>" >
                        <div class="form-group" style="padding-top: 26px;padding-bottom: 0px">
                            <input type="submit" class="form-control btn btn-primary" value="Schedule" >
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

    function setDateFunction() {
        var x = document.getElementById("startDate").value;

    }
</script>

</body>
</html>
