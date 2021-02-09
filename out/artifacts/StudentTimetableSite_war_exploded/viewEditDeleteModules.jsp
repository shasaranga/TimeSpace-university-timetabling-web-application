<%@ page import="java.util.List" %>
<%@ page import="StudentTimetablingSystem.model.*" %>
<%@ page import="StudentTimetablingSystem.model.Module" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: shasa
  Date: 09/14/2020
  Time: 4:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manage Modules Information</title>
    <link href="https://fonts.googleapis.com/css?family=Poppins:200,300,400,500,600,700,800&amp;display=swap"
          rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css"
          integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="css/controlPanelStyle.css">
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
                    <li>
                        <a href="viewEditDeleteStudent.jsp">View / Edit / Delete a Particular Student</a>
                    </li>

                </ul>
            </li>
            <li>
                <a href="#ManageLecturer" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">Manage
                    Lecturers</a>

                <ul class="collapse list-unstyled" id="ManageLecturer">

                    <li >
                        <a href="addLecturer.jsp">Add a New Lecturer</a>
                    </li>
                    <li>
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

                    <li >
                        <a href="addNewCourse.jsp">Add a New Course</a>
                    </li>
                    <li >
                        <a href="addNewModule.jsp">Add a New Module</a>
                    </li>
                    <li>
                        <a href="viewEditDeleteCourses.jsp">View / Edit / Delete a Particular Course</a>
                    </li>
                    <li class="active">
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
                    <%--                    <span>Toggle Sidebar</span>--%>
                </button>
                <form class="form-inline my-2 my-lg-0">
<%--                    <input type="hidden" value="filterStudentByID" name="action">--%>
                    <input class="form-control mr-sm-2" type="search" id ="searchInput" onkeyup="myFunction()"  placeholder="Filter Modules by Course Name" aria-label="Search">
<%--                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>--%>
                </form>


            </div>

        </nav>
<%--        <c:set var="errorShow" scope="session" value="${errorShow}"/>--%>
<%--        <c:if test="${errorShow == true}" >--%>
<%--        <p disabled="<c:out value="${errorShow}"/>" style="color: red;font-size: 15px; font-weight: 600;">${errorMessage}<p>--%>
<%--        </c:if>--%>

        <c:set var="SuccessMsg" scope="session" value="${showSuccessMessage}"/>
        <c:if test="${SuccessMsg == true}" >
        <p disabled="<c:out value="${SuccessMsg}"/>" style="color: green;font-size: 14px;">${SuccessMsgBody}<p>
        </c:if>

        <c:set var="ErrorMsg" scope="session" value="${showErrorMessage}"/>
        <c:if test="${ErrorMsg == true}" >
        <p disabled="<c:out value="${ErrorMsg}"/>" style="color: red;font-size: 14px;">${ErrorMsgBody}<p>
        </c:if>

        <table class="table" id ="studentTable" style="color:black;">

            <thead class="thead-dark">
            <tr>
                <th scope="col">#</th>
                <th class ="hello d-none" scope="col">Course</th>
                <th scope="col">Module Name</th>
                <th scope="col">Module Description</th>
                <th scope="col">Assigned Lecturer</th>
                <th scope="col">Assigned Classroom</th>
                <th scope="col">Action</th>
            </tr>
            </thead>
            <tbody>

            <%
                Admin currentUser = (Admin) session.getAttribute("currentSessionUser");
                DAO dao = new DAO();
                List<Module> moduleList = dao.getModules();

                int y =1;
                for(int i = 0; i < moduleList.size(); i++){
                    Module module = moduleList.get(i);
                    String loggedInUserID =  currentUser.getAdminID();


            %>
            <%--            <input type="hidden" name="UserID" value="<%=currentUser.getLecturerID()%>">--%>
            <%--            <c:url var ="editLink" value ="ControlPanelVerifyUser">--%>
            <%--                <c:param name="action" value="Edit"/>--%>
            <%--                <c:param name="UserID" value="<%=currentUser.getLecturerID()%>"/>--%>
            <%--                <c:param name="SelectedClassID" value="<%=String.valueOf(booking.getClassID())%>"/>--%>
            <%--                <c:param name="bookingID" value="<%=String.valueOf(booking.getBookingID())%>"/>--%>
            <%--            </c:url>--%>

            <c:url var ="deleteLink" value ="ModuleServlet">
                <c:param name="action" value="Delete"/>
                <c:param name="moduleID" value="<%=String.valueOf(module.getModuleID())%>"/>
                <c:param name="courseID" value="<%=String.valueOf(module.getCourseID())%>"/>
                <c:param name="loggedInUserID" value="<%=currentUser.getAdminID()%>"/>
            </c:url>

            <tr>
                <th scope="row"><%=y%></th>
                <td class ="hello d-none"><%=module.getCourseName()%></td>
                <td><%=module.getModuleName()%></td>
                <td><%=module.getModuleDescription()%></td>
                <td><%=module.getLecturerFullName()%></td>
                <td><%=module.getClassName()%></td>

                <td>
                    <button id="btnViewLicence2" onclick="sendEditSource('<%=String.valueOf(module.getCourseID())%>', '<%=String.valueOf(module.getModuleID())%>',  '<%=module.getModuleName()%>', '<%=module.getModuleDescription()%>', '<%=module.getLecturerID()%>', '<%=module.getClassID()%>')" type ="button"
                            class="btn btn-outline-primary rounded-pill" data-toggle="modal" data-target="#recordModal">
                        Edit
                    </button>
                    <%--                    <button class="btn btn-outline-success rounded-pill" onclick="sendEditSource(<%=masterData%>)"--%>
                    <%--                            data-toggle="modal" data-target="#recordModal">Edit </button>--%>
                    <a href="${deleteLink}"><button class="btn btn-outline-danger rounded-pill" style="margin-top: 5px;">Delete</button></a>

                </td>

            </tr>
            <div class="modal fade" id="recordModal" tabindex="-1" role="dialog" aria-labelledby="LICENCEModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="LICENCEModalLabel">Edit Course Details</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body" id ="modalBODY">
                            <form action="ModuleServlet" method="GET">
                                <div class="row">
                                    <div class="col-lg-6">
                                        <div class="form-group">
                                            <label style ="color:black;" for="moduleName" class="label">Module Name :</label>
                                            <input type="text" class="form-control" id="moduleName" name="moduleName" maxlength="100" required>
                                        </div>
                                    </div>
                                    <div class="col-lg-6">
                                        <div class="form-group">
                                            <label style ="color:black;" for="moduleDesc" class="label">Module Description :</label>
                                            <input type="text" class="form-control" id="moduleDesc" name="moduleDesc" maxlength="100" required>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-lg-6">
                                        <div class="form-group">
                                            <label style ="color:black;" for="lecturerName" class="label">Lecturer Name:</label>
                                            <select name="lecturerName" id="lecturerName" class="form-control" required>
                                                <option value="" disabled selected hidden>Select a Lecturer</option>
                                                <%
                                                    DAO dao1 = new DAO();
                                                    List<Lecturer> listLecturer = dao1.getAllLecturers();

                                                    for (Lecturer lecturer : listLecturer) {
                                                %>
                                                <option value="<%=lecturer.getLecturerID()%>"><%=lecturer.getfName() + " " + lecturer.getlName()%></option>
                                                <%
                                                    }
                                                %>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-lg-6">
                                        <div class="form-group">

                                            <label class="label">Select Class Room</label>
                                            <select name="classRoom" id ="classRoom" class="form-control" required>
                                                <option value="" disabled selected hidden>Select a Class Room</option>
                                                <%

                                                    List<Classroom> listClassroom = dao.getAvailableClassroomsForModules();
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
                                </div>
                                <input type="hidden" value="Edit" id = "action" name ="action">
                                <input type="hidden" value="" id = "AdminUserID" name="AdminUserID">
                                <input type="hidden" value="" id = "selectedModuleID" name="selectedModuleID">
                                <input type="hidden" value="" id = "selectedCourseID" name="selectedCourseID">
                                <div class="modal-footer">
                                    <button type="submit" class="btn btn-secondary" style="background-color: blue;">Save</button>
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                </div>

                            </form>


                        </div>

                    </div>
                </div>
            </div>
            <%
                    y++;
                }
            %>
            </tbody>

        </table>

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
    function sendEditSource(data1,data2,data3,data4,data5,data6) {



        var userID = '<%=currentUser.getAdminID()%>';
        console.log(userID);
        var selectedCourseID = data1;
        console.log("Course ID : "+selectedCourseID);
        var selectedModuleID =data2;
        console.log("Module ID : "+selectedModuleID);
        var selectedModuleName = data3;
        console.log("Module Name : " +selectedModuleName);
        var selectedModuleDescription = data4;
        console.log("Module Description : " +selectedModuleDescription);
        var lecturerName = data5;
        console.log("Lecturer Name : " +lecturerName);
        var selectedClassID = data6;
        console.log("Class ID : " +selectedClassID);
        document.getElementById('AdminUserID').value = userID;
        document.getElementById('selectedModuleID').value = selectedModuleID;
        document.getElementById('selectedCourseID').value = selectedCourseID;
        document.getElementById('moduleName').value = selectedModuleName;
        document.getElementById('moduleDesc').value = selectedModuleDescription;
        document.getElementById('lecturerName').value = lecturerName;
        document.getElementById('classRoom').value = selectedClassID;

    }
</script>

</body>
</html>
