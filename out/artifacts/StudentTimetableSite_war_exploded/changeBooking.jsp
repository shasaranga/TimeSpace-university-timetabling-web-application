<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="StudentTimetablingSystem.model.DAO" %>
<%@ page import="StudentTimetablingSystem.model.Booking" %>
<%@ page import="StudentTimetablingSystem.model.Customer" %>
<%@ page import="java.util.List" %>
<%@ page import="StudentTimetablingSystem.model.Lecturer" %><%--
  Created by IntelliJ IDEA.
  User: shasa
  Date: 09/02/2020
  Time: 4:01 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Change Booking</title>
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
            <li >
                <a href="searchStudentTimeTableForLecturer.jsp">Search Student Timetable</a>
            </li>
            <li>
                <a href="searchLecturerTimeTableForLecturer.jsp">Search Lecturer Timetable</a>
            </li>
            <li class="active">
                <a href="#ManageBookings" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">Manage Bookings</a>

                <ul class="collapse list-unstyled" id="ManageBookings">

                    <li >
                        <a href="scheduleClass.jsp">Schedule a Class</a>
                    </li>
                    <li class="active">
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
        <c:set var="errorShow" scope="session" value="${errorShow}"/>
        <c:if test="${errorShow == true}" >
        <p disabled="<c:out value="${errorShow}"/>" style="color: red;font-size: 15px; font-weight: 600;">${errorMessage}<p>
        </c:if>
        <table class="table" style="color:black;">

            <thead class="thead-dark">
            <tr>
                <th scope="col">Booking ID</th>
                <th scope="col">Class Name</th>
                <th scope="col">Badge Name</th>
                <th scope="col">Lecturer Name</th>
                <th scope="col">Scheduled For (Date)</th>
                <th scope="col">Duration (hours)</th>
                <th scope="col">Action</th>
            </tr>
            </thead>
            <tbody>

            <%
                Lecturer currentUser = (Lecturer) session.getAttribute("currentSessionUser");
                DAO dao = new DAO();
                List<Booking> bookingList = dao.listBookings(currentUser.getLecturerID());


                for(int i =0; i < bookingList.size(); i++){
                    Booking booking = bookingList.get(i);
                    String loggedInUserID =  currentUser.getLecturerID();
                    System.out.println(booking.getClassID());
            %>
<%--            <input type="hidden" name="UserID" value="<%=currentUser.getLecturerID()%>">--%>
<%--            <c:url var ="editLink" value ="ControlPanelVerifyUser">--%>
<%--                <c:param name="action" value="Edit"/>--%>
<%--                <c:param name="UserID" value="<%=currentUser.getLecturerID()%>"/>--%>
<%--                <c:param name="SelectedClassID" value="<%=String.valueOf(booking.getClassID())%>"/>--%>
<%--                <c:param name="bookingID" value="<%=String.valueOf(booking.getBookingID())%>"/>--%>
<%--            </c:url>--%>

            <c:url var ="deleteLink" value ="ControlPanelVerifyUser">
                <c:param name="action" value="Delete"/>
                <c:param name="UserID" value="<%=currentUser.getLecturerID()%>"/>
                <c:param name="bookingID" value ="<%=String.valueOf(booking.getBookingID())%>"/>
            </c:url>

            <tr>
                <th scope="row"><%=booking.getBookingID()%></th>
                <td><%=booking.getClassName()%></td>
                <td><%=booking.getBadgeName()%></td>
                <td><%=booking.getLecturerName()%></td>
                <td><%=booking.getStartDate()%></td>
                <td><%=booking.getDuration()%></td>

                <td>
                    <button id="btnViewLicence2" onclick="sendEditSource('<%=String.valueOf(booking.getClassID())%>', '<%=String.valueOf(booking.getBookingID())%>', '<%=booking.getStartDate().toString()%>', '<%=booking.getEndDate().toString()%>', '<%=String.valueOf(booking.getDuration())%>')" type ="button"
                            class="btn btn-outline-primary rounded-pill" data-toggle="modal" data-target="#recordModal">
                        Edit
                    </button>
<%--                    <button class="btn btn-outline-success rounded-pill" onclick="sendEditSource(<%=masterData%>)"--%>
<%--                            data-toggle="modal" data-target="#recordModal">Edit </button>--%>
                    <a href="${deleteLink}"><button class="btn btn-outline-danger rounded-pill">Delete</button></a>

                </td>

            </tr>
            <div class="modal fade" id="recordModal" tabindex="-1" role="dialog" aria-labelledby="LICENCEModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="LICENCEModalLabel">Modal title</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body" id ="modalBODY">
                            <form action="ControlPanelVerifyUser" method="GET">
                                <div class="row">
                                    <div class="col-lg-6">
                                        <div class="form-group">
                                            <label style ="color:black;" class="label">Date</label>
                                            <input type="datetime-local" id="startDate" name="startDate" class="form-control" required />
                                        </div>
                                    </div>
                                    <div class="col-lg-6">
                                        <div class="form-group">
                                            <label style ="color:black;" class="label">Select Duration</label>
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
                                <input type="hidden" value="Edit" id = "action" name ="action">
                                <input type="hidden" value="" id = "UserID" name="UserID">
                                <input type="hidden" value="" id = "SelectedClassID" name="SelectedClassID">
                                <input type="hidden" value="" id = "bookingIDVal" name="bookingIDVal">

                                <input type ="hidden" value="" id= "oldStartDate" name ="oldStartDate">
                                <input type ="hidden" value="" id= "oldEndDate" name ="oldEndDate">
                                <input type ="hidden" value="" id= "oldDuration" name ="oldDuration">
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
    function sendEditSource(data1,data2,data3,data4,data5) {
        var userID = '<%=currentUser.getLecturerID().toString()%>';
        console.log(userID);
        var selectedClassID = data1;
       console.log(selectedClassID);
        var bookingIDVal =data2;
        console.log(data2);
        var oldStartDate = data3;
        console.log("old start date" + oldStartDate);
        var oldEndDate = data4;
        console.log("old end date" + oldEndDate);
        var oldDuration = data5;
        console.log("old Duration" + oldDuration);
        document.getElementById('UserID').value = userID;
        document.getElementById('SelectedClassID').value = selectedClassID;
        document.getElementById('bookingIDVal').value = bookingIDVal;
        document.getElementById('oldStartDate').value = oldStartDate;
        document.getElementById('oldEndDate').value = oldEndDate;
        document.getElementById('oldDuration').value = oldDuration;
    }
</script>
</body>
</html>
