<%@ page import="StudentTimetablingSystem.model.Timetabler" %>
<%@ page import="StudentTimetablingSystem.model.DAO" %>
<%@ page import="StudentTimetablingSystem.model.Course" %>
<%@ page import="java.util.List" %>
<%@ page import="StudentTimetablingSystem.model.Module" %><%--
  Created by IntelliJ IDEA.
  User: shasa
  Date: 09/14/2020
  Time: 4:23 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Timetable</title>
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

            getModulesList();
        }
        function getModulesList() {
            var val = document.getElementById("courseName").value;
            var xhttp = new XMLHttpRequest();
            console.log(val);
            xhttp.onreadystatechange= function () {
                if(xhttp.readyState === 4 && xhttp.status === 200){
                    for(var i=0; i < 8; i++){
                        var monId = "mon-"+i;
                        var tueId = "tue-"+i;
                        var wedId = "wed-"+i;
                        var thurId = "thur-"+i;
                        var friId = "fri-"+i;
                        document.getElementById(monId).innerHTML = xhttp.responseText;
                        document.getElementById(tueId).innerHTML = xhttp.responseText;
                        document.getElementById(wedId).innerHTML = xhttp.responseText;
                        document.getElementById(thurId).innerHTML = xhttp.responseText;
                        document.getElementById(friId).innerHTML = xhttp.responseText;
                        console.log(xhttp.responseText);
                    }

                }

            };//valajax
            xhttp.open("POST","ModuleServlet?valajax="+ val,true);
            xhttp.send();
        }
    </script>
</head>
<body>
<div class="wrapper">
    <nav id="sidebar">
        <div class="sidebar-header">
            <a href="#" class="navbar-brand logo"><img class="logo" src="images/logo.png">Time <span class="brandHalf">Space</span></a>
        </div>

        <ul class="list-unstyled components">
            <p style="font-weight: bold;">Control Options</p>
            <li class="active">
                <a href="#ManageTimtables" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">Manage
                    Timetables</a>

                <ul class="collapse list-unstyled" id="ManageTimtables">

                    <li class="active">
                        <a href="createCourseBadgeTimetable.jsp">Create Timetable</a>
                    </li>
                    <li>
                        <a href="searchStudentTimeTable.jsp">Search Student Timetable</a>
                    </li>
                    <li>
                        <a href="searchLecturerTimeTable.jsp">Search Lecturer Timetable</a>
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
            Timetabler currentUser = (Timetabler) session.getAttribute("currentSessionUser");
        %>

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
                <div class="col-lg-6">
                    <div class="form-group">
                        <input type ="hidden" value="<%=currentUser.getTimetablerID()%>" name ="loggedTimetabler">
                        <select name="courseName" id="courseName" class="form-control" onchange="getBadgesList()" required>
                            <option value="" disabled selected hidden>Select a Course</option>
                            <%
                                DAO dao = new DAO();
                                List<Course> listCourse = dao.getCourses();

                                for (Course course : listCourse) {
                            %>
                            <option value="<%=course.getId()%>"><%=course.getCourseName()%>
                            </option>
                            <%
                                }
                            %>
                        </select>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="form-group">
                        <select name="badge" id ="badge" class="form-control" required>
                            <option value="" disabled selected hidden>Select a Badge</option>

                        </select>

                    </div>
                </div>
            </div>

        <c:set var="showTimetable" scope="session" value="${showTimetableVal}"/>
        <c:if test="${showTimetable != true}" >
            <table disabled="<c:out value="${showTimetable}"/> " class="table" id ="courseTimeTable" style="color:black;">
                <thead class="thead-dark">
                <tr>
                    <th scope="col">Start Time</th>
                    <th scope="col">Duration</th>
                    <th scope="col">Monday</th>
                    <th scope="col">Tuesday</th>
                    <th scope="col">Wednesday</th>
                    <th scope="col">Thursday</th>
                    <th scope="col">Friday</th>

                </tr>
                </thead>
                <div>
                    <td><input class="form-control" name ="startTime-0" type="time"></td>
                    <td>
                        <div class="form-group">
                             <input name ="duration-0" class="form-control" type="number" placeholder="Select Duration">
                        </div>
                    </td>

                    <td>
                        <div class="form-group">
                            <select name="mon-0" id="mon-0" class="form-control" >
                                <option value="" disabled selected hidden>Select a Module</option>

                            </select>
                        </div>
                    </td>
                    <td>
                        <div class="form-group">
                            <select name="tue-0" id="tue-0" class="form-control" >
                                <option value="" disabled selected hidden>Select a Module</option>

                            </select>
                        </div>
                    </td>
                    <td>
                        <div class="form-group">
                            <select name="wed-0" id="wed-0" class="form-control" >
                                <option value="" disabled selected hidden>Select a Module</option>
                            </select>
                        </div>
                    </td>
                    <td>
                        <div class="form-group">
                            <select name="thur-0" id="thur-0" class="form-control" >
                                <option value="" disabled selected hidden>Select a Module</option>
                            </select>
                        </div>
                    </td>
                    <td>
                        <div class="form-group">
                            <select name="fri-0" id="fri-0" class="form-control" >
                                <option value="" disabled selected hidden>Select a Module</option>
                            </select>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td><input class="form-control" name ="startTime-1" type="time"></td>
                    <td>
                        <div class="form-group">
                            <input class="form-control" name ="duration-1" type="number" placeholder="Select Duration">
                        </div>
                    </td>
                    <td>
                        <div class="form-group">
                            <select name="mon-1" id="mon-1" class="form-control" >
                                <option value="" disabled selected hidden>Select a Module</option>
                            </select>
                        </div>
                    </td>
                    <td>
                        <div class="form-group">
                            <select name="tue-1" id="tue-1" class="form-control" >
                                <option value="" disabled selected hidden>Select a Module</option>
                            </select>
                        </div>
                    </td>
                    <td>
                        <div class="form-group">
                            <select name="wed-1" id="wed-1" class="form-control" >
                                <option value="" disabled selected hidden>Select a Module</option>
                            </select>
                        </div>
                    </td>
                    <td>
                        <div class="form-group">
                            <select name="thur-1" id="thur-1" class="form-control" >
                                <option value="" disabled selected hidden>Select a Module</option>
                            </select>
                        </div>
                    </td>
                    <td>
                        <div class="form-group">
                             <select name="fri-1" id="fri-1" class="form-control" >
                                <option value="" disabled selected hidden>Select a Module</option>
                            </select>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td><input class="form-control" name ="startTime-2" type="time"></td>
                    <td>
                        <div class="form-group">
                            <input class="form-control" name ="duration-2" type="number" placeholder="Select Duration">
                        </div>
                    </td>
                    <td>
                        <div class="form-group">
                            <select name="mon-2" id="mon-2" class="form-control" >
                                <option value="" disabled selected hidden>Select a Module</option>
                            </select>
                        </div>
                    </td>
                    <td>
                        <div class="form-group">
                            <select name="tue-2" id="tue-2" class="form-control" >
                                <option value="" disabled selected hidden>Select a Module</option>
                            </select>
                        </div>
                    </td>
                    <td>
                        <div class="form-group">
                            <select name="wed-2" id="wed-2" class="form-control" >
                                <option value="" disabled selected hidden>Select a Module</option>
                            </select>
                        </div>
                    </td>
                    <td>
                        <div class="form-group">
                            <select name="thur-2" id="thur-2" class="form-control" >
                                <option value="" disabled selected hidden>Select a Module</option>
                            </select>
                        </div>
                    </td>
                    <td>
                        <div class="form-group">
                            <select name="fri-2" id="fri-2" class="form-control" >
                                <option value="" disabled selected hidden>Select a Module</option>
                            </select>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td><input class="form-control" name ="startTime-3" type="time"></td>
                    <td>
                        <div class="form-group">
                            <input class="form-control" name ="duration-3" type="number" placeholder="Select Duration">
                        </div>
                    </td>
                    <td>
                        <div class="form-group">
                            <select name="mon-3" id="mon-3" class="form-control" >
                                <option value="" disabled selected hidden>Select a Module</option>
                            </select>
                        </div>
                    </td>
                    <td>
                        <div class="form-group">
                            <select name="tue-3" id="tue-3" class="form-control" >
                                <option value="" disabled selected hidden>Select a Module</option>
                            </select>
                        </div>
                    </td>
                    <td>
                        <div class="form-group">
                            <select name="wed-3" id="wed-3" class="form-control" >
                                <option value="" disabled selected hidden>Select a Module</option>
                            </select>
                        </div>
                    </td>
                    <td>
                        <div class="form-group">
                            <select name="thur-3" id="thur-3" class="form-control" >
                                <option value="" disabled selected hidden>Select a Module</option>
                            </select>
                        </div>
                    </td>
                    <td>
                        <div class="form-group">
                            <select name="fri-3" id="fri-3" class="form-control" >
                                <option value="" disabled selected hidden>Select a Module</option>
                            </select>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td><input class="form-control" name ="startTime-4" type="time"></td>
                    <td>
                        <div class="form-group">
                            <input class="form-control" name ="duration-4" type="number" placeholder="Select Duration">
                        </div>
                    </td>
                    <td>
                        <div class="form-group">
                            <select name="mon-4" id="mon-4" class="form-control" >
                                <option value="" disabled selected hidden>Select a Module</option>
                            </select>
                        </div>
                    </td>
                    <td>
                        <div class="form-group">
                            <select name="tue-4" id="tue-4" class="form-control" >
                                <option value="" disabled selected hidden>Select a Module</option>
                            </select>
                        </div>
                    </td>
                    <td>
                        <div class="form-group">
                            <select name="wed-4" id="wed-4" class="form-control" >
                                <option value="" disabled selected hidden>Select a Module</option>
                            </select>
                        </div>
                    </td>
                    <td>
                        <div class="form-group">
                            <select name="thur-4" id="thur-4" class="form-control" >
                                <option value="" disabled selected hidden>Select a Module</option>
                            </select>
                        </div>
                    </td>
                    <td>
                        <div class="form-group">
                            <select name="fri-4" id="fri-4" class="form-control" >
                                <option value="" disabled selected hidden>Select a Module</option>
                            </select>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td><input class="form-control" name ="startTime-5" type="time"></td>
                    <td>
                        <div class="form-group">
                             <input class="form-control" name ="duration-5" type="number" placeholder="Select Duration">
                        </div>
                    </td>
                    <td>
                        <div class="form-group">
                            <select name="mon-5" id="mon-5" class="form-control" >
                                <option value="" disabled selected hidden>Select a Module</option>
                            </select>
                        </div>
                    </td>
                    <td>
                        <div class="form-group">
                            <select name="tue-5" id="tue-5" class="form-control" >
                                <option value="" disabled selected hidden>Select a Module</option>
                            </select>
                        </div>
                    </td>
                    <td>
                        <div class="form-group">
                            <select name="wed-5" id="wed-5" class="form-control" >
                                <option value="" disabled selected hidden>Select a Module</option>
                            </select>
                        </div>
                    </td>
                    <td>
                        <div class="form-group">
                            <select name="thur-5" id="thur-5" class="form-control" >
                                <option value="" disabled selected hidden>Select a Module</option>
                            </select>
                        </div>
                    </td>
                    <td>
                        <div class="form-group">
                            <select name="fri-5" id="fri-5" class="form-control" >
                                <option value="" disabled selected hidden>Select a Module</option>
                            </select>
                        </div>
                    </td>
                </tr>

                <tr>
                    <td><input class="form-control" name ="startTime-6" type="time"></td>
                    <td>
                        <div class="form-group">
                            <input class="form-control" name ="duration-6" type="number" placeholder="Select Duration">
                        </div>
                    </td>
                    <td>
                        <div class="form-group">
                            <select name="mon-6" id="mon-6" class="form-control" >
                                <option value="" disabled selected hidden>Select a Module</option>
                            </select>
                        </div>
                    </td>
                    <td>
                        <div class="form-group">
                            <select name="tue-6" id="tue-6" class="form-control" >
                                <option value="" disabled selected hidden>Select a Module</option>
                            </select>
                        </div>
                    </td>
                    <td>
                        <div class="form-group">
                            <select name="wed-6" id="wed-6" class="form-control" >
                                <option value="" disabled selected hidden>Select a Module</option>
                            </select>
                        </div>
                    </td>
                    <td>
                        <div class="form-group">
                            <select name="thur-6" id="thur-6" class="form-control" >
                                <option value="" disabled selected hidden>Select a Module</option>
                            </select>
                        </div>
                    </td>
                    <td>
                        <div class="form-group">
                            <select name="fri-6" id="fri-6" class="form-control" >
                                <option value="" disabled selected hidden>Select a Module</option>
                            </select>
                        </div>
                    </td>
                </tr>

                <tr>
                    <td><input class="form-control" name ="startTime-4" type="time"></td>
                    <td>
                        <div class="form-group">
                             <input class="form-control" name ="duration-4" type="number" placeholder="Select Duration">
                        </div>
                    </td>
                    <td>
                        <div class="form-group">
                            <select name="mon-7" id="mon-7" class="form-control" >
                                <option value="" disabled selected hidden>Select a Module</option>
                            </select>
                        </div>
                    </td>
                    <td>
                        <div class="form-group">
                            <select name="tue-7" id="tue-7" class="form-control" >
                                <option value="" disabled selected hidden>Select a Module</option>
                            </select>
                        </div>
                    </td>
                    <td>
                        <div class="form-group">
                            <select name="wed-7" id="wed-7" class="form-control" >
                                <option value="" disabled selected hidden>Select a Module</option>
                            </select>
                        </div>
                    </td>
                    <td>
                        <div class="form-group">
                            <select name="thur-7" id="thur-7" class="form-control" >
                                <option value="" disabled selected hidden>Select a Module</option>
                            </select>
                        </div>
                    </td>
                    <td>
                        <div class="form-group">
                            <select name="fri-7" id="fri-7" class="form-control" >
                                <option value="" disabled selected hidden>Select a Module</option>
                            </select>
                        </div>
                    </td>
                </tr>
            </table>
        </c:if>
            <div class="form-group">
                <input type="hidden" name="action" value="createTimetable">
                <input type="submit" class="form-control btn btn-primary" value="Save">
            </div>
        </form>


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
