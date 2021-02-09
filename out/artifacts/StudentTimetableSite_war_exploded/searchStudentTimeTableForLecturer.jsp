<%--
  Created by IntelliJ IDEA.
  User: shasa
  Date: 08/29/2020
  Time: 5:09 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.util.List" %>
<%@ page import="StudentTimetablingSystem.model.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student Timetable</title>
    <link href="https://fonts.googleapis.com/css?family=Poppins:200,300,400,500,600,700,800&amp;display=swap"
          rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css"
          integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="css/controlPanelStyle.css">
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
    </script>
</head>
<body>
<div class="wrapper">
    <!-- Sidebar -->
    <nav id="sidebar">
        <div class="sidebar-header">
            <a href="#" class="navbar-brand logo"><img class="logo" src="images/logo.png">Time <span class="brandHalf">Space</span></a>
        </div>

        <ul class="list-unstyled components">
            <p style="font-weight: bold;">Control Options</p>

            <li class="active">
                <a href="searchStudentTimeTableForLecturer.jsp">Search Student Timetable</a>
            </li>
            <li>
                <a href="searchLecturerTimeTableForLecturer.jsp">Search Lecturer Timetable</a>
            </li>
            <li>
                <a href="#ManageBookings" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">Manage Bookings</a>

                <ul class="collapse list-unstyled" id="ManageBookings">

                    <li>
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
                <form class="form-inline my-2 my-lg-0">
                    <%--                    <input type="hidden" value="filterStudentByID" name="action">--%>
                    <input class="form-control mr-sm-2" type="search" id ="searchInput" onkeyup="myFunction()"  placeholder="Filter by Week Day" aria-label="Search">
                    <%--                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>--%>
                </form>


            </div>

        </nav>

        <c:set var="SuccessMsg" scope="session" value="${showSuccessMessage}"/>
        <c:if test="${SuccessMsg == true}" >
        <p disabled="<c:out value="${SuccessMsg}"/>" style="color: green;font-size: 14px;">${SuccessMsgBody}<p>
        </c:if>

        <c:set var="ErrorMsg" scope="session" value="${showErrorMessage}"/>
        <c:if test="${ErrorMsg == true}" >
        <p disabled="<c:out value="${ErrorMsg}"/>" style="color: red;font-size: 14px;">${ErrorMsgBody}<p>
        </c:if>
        <form action="CourseTimetableServlet" method="get" style = "padding-top: 10px;">
            <div class="row">
                <div class="col-lg-4">
                    <div class="form-group">
<%--                        <label class="label">Select Course</label>--%>
                        <select name="courseName" id="courseName" class="form-control" onchange="getBadgesList()" required>
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
                <div class="col-lg-4">
                    <div class="form-group">
<%--                        <label class="label">Select Badge (only when user type is "student")</label>--%>
                        <select name="badge" id ="badge" class="form-control" required>
                            <option value="" disabled selected hidden>Select a Badge (only when user type is "student")</option>

                        </select>
                    </div>
                </div>
                <div class="col-lg-4">
                    <div class="form-group">
                        <input type="hidden" value="search" name="action" id="action">
                        <input type="hidden" value="student" name="userType" id="userType">
                        <input type="hidden" value="commonStudentViewForLecturer" name="viewType" id="viewType">
                        <button type="submit" class="form-control btn btn-secondary" style="background-color: #005bff;">Search</button>
                    </div>
                </div>

            </div>
        </form>

        <c:set var="Show" scope="session" value="${showTable}"/>
        <c:if test="${Show == true}" >
            <table class="table" id ="studentTable" style="color:black;">

                <thead class="thead-dark">
                <tr>
                    <th scope="col">Week Day</th>
                    <th scope="col">Start Time</th>
                    <th scope="col">End Time</th>
                    <th scope="col">Module</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="record" items="${searchResult}">

                    <tr>
                        <td>${record.weekday}</td>
                        <td>${record.startTime}</td>
                        <td>${record.endTime}</td>
                        <td>${record.moduleName}</td>

                    </tr>
                </c:forEach>
                </tbody>

            </table>
        </c:if>
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
<script>
    function myFunction() {
        var input, filter, table, tr, td, i, txtValue;
        input = document.getElementById("searchInput");
        filter = input.value.toUpperCase();
        table = document.getElementById("studentTable");
        tr = table.getElementsByTagName("tr");
        for (i = 0; i < tr.length; i++) {
            td = tr[i].getElementsByTagName("td")[0];
            if (td) {
                txtValue = td.textContent || td.innerText;
                if (txtValue.toUpperCase().indexOf(filter) > -1) {
                    tr[i].style.display = "";
                } else {
                    tr[i].style.display = "none";
                }
            }
        }
    }
    function sendEditSource(data1,data2,data3) {



        var userID = "";
        console.log(userID);
        var selectedCourseID = data1;
        console.log("Course ID : "+selectedCourseID);
        var courseName =data2;
        console.log("Course Name : "+courseName);
        var courseField = data3;
        console.log("Course Field : " + courseField);

        document.getElementById('AdminUserID').value = userID;
        document.getElementById('selectedCourseID').value = selectedCourseID;
        document.getElementById('courseName').value = courseName;
        document.getElementById('courseField').value = courseField;

    }
</script>
</body>
</html>
