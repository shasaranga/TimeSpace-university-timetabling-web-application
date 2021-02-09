package StudentTimetablingSystem.controllers;

import StudentTimetablingSystem.model.DAO;
import StudentTimetablingSystem.model.MessageClass;
import com.google.gson.Gson;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import static java.lang.Integer.parseInt;

@WebServlet(name = "ScheduleClassServlet", urlPatterns = {"/ScheduleClassServlet"})
public class ScheduleClassServlet extends HttpServlet {
    private final Gson gson = new Gson();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        String classroom = request.getParameter("classRoom");
        String device = request.getParameter("device");

        if(device != null && device !="" && device.equals("Mobile") ){
            String courseID = request.getParameter("courseName"); //not needed for DB
            String badgeID = request.getParameter("badge");

            String duration = request.getParameter("classDuration");
            String onDate = request.getParameter("startDate");
            String recordCreatedUserID = request.getParameter("lectureID");
            String recordCreatedUserFName = request.getParameter("lectureFullName");

            if(courseID != null && badgeID != null && duration != null && onDate != null && classroom != null && recordCreatedUserID != null && recordCreatedUserFName != null){
                int courseIDInt = parseInt(courseID);
                int badgeIDInt = parseInt(badgeID);
                int classroomIDInt = parseInt(classroom);
                int durationIDInt = parseInt(duration);

                String timeSection = null;
                String formattedOnDate = onDate;

                System.out.println("Formatted Date : "+ formattedOnDate);

                Timestamp onDateTimeStamp = Timestamp.valueOf(formattedOnDate);
                System.out.println("Converted OnDate Timestamp "+ onDateTimeStamp);

                LocalDateTime localDateTime =onDateTimeStamp.toLocalDateTime();
                LocalDateTime endDateLocal = localDateTime.plusHours(durationIDInt);
                System.out.println("End Date Local Format : " + endDateLocal);

                String dateSection = endDateLocal.toString().split("T")[0];
                timeSection = endDateLocal.toString().split("T")[1];
                String seconds = ":00";
                timeSection = timeSection + seconds;
                formattedOnDate = dateSection + " "+timeSection;
                System.out.println(formattedOnDate);

                Timestamp endDateTimeStamp = Timestamp.valueOf(formattedOnDate);
                System.out.println("Converted EndDate Timestamp "+ endDateTimeStamp);

                DAO dao1 = new DAO();
                boolean isSelectedClassAvailable = dao1.verifySelectedClass(classroomIDInt,onDateTimeStamp,endDateTimeStamp);

                if(isSelectedClassAvailable == true){
                    DAO dao2 = new DAO();
                    boolean addedSuccessfully = dao2.addBooking(badgeIDInt,classroomIDInt,durationIDInt, onDateTimeStamp, endDateTimeStamp,recordCreatedUserID,recordCreatedUserFName);
                    if(addedSuccessfully == true){
                        MessageClass messageClass = new MessageClass(addedSuccessfully, "Booking is successful!");
                        String output = this.gson.toJson(messageClass);
                        PrintWriter out = response.getWriter();
                        response.setContentType("application/json");
                        response.setCharacterEncoding("UTF-8");
                        out.print(output);
                        out.flush();
                    }else{
                        MessageClass messageClass = new MessageClass(addedSuccessfully, "Booking is unsuccessful!");
                        String output = this.gson.toJson(messageClass);
                        PrintWriter out = response.getWriter();
                        response.setContentType("application/json");
                        response.setCharacterEncoding("UTF-8");
                        out.print(output);
                        out.flush();
                    }
                }else{
                    MessageClass messageClass = new MessageClass(isSelectedClassAvailable, "Selected Class is not available!");
                    String output = this.gson.toJson(messageClass);
                    PrintWriter out = response.getWriter();
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    out.print(output);
                    out.flush();
                }
            }
            else{
                System.out.println("NOT FOUND NEEDED INPUT FOR BOOKING....");
                System.out.println("Course ID : "+ courseID);
                System.out.println("Badge ID : "+ badgeID);
                System.out.println("Duration : " + duration);
                System.out.println("On Date : " + onDate);
                System.out.println("Classroom ID : "+ classroom);
            }

        }
        else{
            if(classroom.equals("No Class Available")){
                request.setAttribute("scheduleSuccess", false);
                request.setAttribute("showScheduleError", true);
                request.setAttribute("ScheduleErrorAlert", "No Scheduling can be done due to lack of classroom availability!");
                request.getRequestDispatcher("scheduleClass.jsp").forward(request, response);
            }else{
                String courseID = request.getParameter("courseName"); //not needed for DB
                String badgeID = request.getParameter("badge");

                String duration = request.getParameter("classDuration");
                String onDate = request.getParameter("startDate");
                System.out.println("String courseID : "+ courseID);
                System.out.println("String badgeID : " + badgeID);
                System.out.println("String classroomID : "+ classroom);
                System.out.println("String duration : " + duration);
                System.out.println("String onDate : " + onDate);
                DAO dao = new DAO();
                int badgeIDInt = parseInt(badgeID);
                int classroomIDInt = parseInt(classroom);
                int durationIDInt = parseInt(duration);
                String dateSection = null;
                String timeSection = null;
                String formattedOnDate = null;
                if(!onDate.isEmpty()){
                    dateSection = onDate.split("T")[0];
                    timeSection = onDate.split("T")[1];
                    String seconds = ":00";
                    timeSection = timeSection + seconds;
                    formattedOnDate = dateSection + " "+timeSection;
                    System.out.println(formattedOnDate);
                }
                //LocalDateTime localDateTime = LocalDateTime.parse(formattedOnDate);
                Timestamp onDateTimeStamp = Timestamp.valueOf(formattedOnDate);
                System.out.println("Converted OnDate Timestamp "+ onDateTimeStamp);
                LocalDateTime localDateTime =onDateTimeStamp.toLocalDateTime();
                LocalDateTime endDateLocal = localDateTime.plusHours(durationIDInt);
                System.out.println("End Date Local Format : " + endDateLocal);

                dateSection = endDateLocal.toString().split("T")[0];
                timeSection = endDateLocal.toString().split("T")[1];
                String seconds = ":00";
                timeSection = timeSection + seconds;
                formattedOnDate = dateSection + " "+timeSection;
                System.out.println(formattedOnDate);

                Timestamp endDateTimeStamp = Timestamp.valueOf(formattedOnDate);
                System.out.println("Converted EndDate Timestamp "+ endDateTimeStamp);

                String recordCreatedUserID = request.getParameter("lectureID");
                String recordCreatedUserFName = request.getParameter("lectureFullName");
                System.out.println("Converted Class ID : "+ badgeIDInt);
                System.out.println("Converted Class ID : "+ classroomIDInt);
                System.out.println("Converted Class ID : "+ durationIDInt);

                boolean isSelectedClassAvailable = dao.verifySelectedClass(classroomIDInt,onDateTimeStamp,endDateTimeStamp);

                if(isSelectedClassAvailable == true){
                    boolean addedSuccessfully = dao.addBooking(badgeIDInt,classroomIDInt,durationIDInt, onDateTimeStamp, endDateTimeStamp,recordCreatedUserID,recordCreatedUserFName);
                    if(addedSuccessfully == true){
                        request.setAttribute("showScheduleError", false);
                        request.setAttribute("scheduleSuccess", true);
                        request.setAttribute("ScheduleSuccessAlertMessage", "Successfully Scheduled!");
                        request.getRequestDispatcher("scheduleClass.jsp").forward(request, response);
                    }else{
                        request.setAttribute("scheduleSuccess", false);
                        request.setAttribute("showScheduleError", true);
                        request.setAttribute("ScheduleErrorAlert", "DB Error!");
                        request.getRequestDispatcher("scheduleClass.jsp").forward(request, response);
                    }

                }else{

                    request.setAttribute("scheduleSuccess", false);
                    request.setAttribute("showScheduleError", true);
                    request.setAttribute("ScheduleErrorAlert", "The Class is already booked on to that time schedule.");
                    request.getRequestDispatcher("scheduleClass.jsp").forward(request, response);
                }
            }
        }




    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
