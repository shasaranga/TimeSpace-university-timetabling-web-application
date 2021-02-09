package StudentTimetablingSystem.controllers;

import StudentTimetablingSystem.model.Booking;
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
import java.util.List;

import static java.lang.Integer.parseInt;

@WebServlet(name = "ControlPanelVerifyUser", urlPatterns = {"/ControlPanelVerifyUser"})
public class ControlPanelVerifyUser extends HttpServlet {
    private final Gson gson = new Gson();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userID = request.getParameter("UserID");
        String device = request.getParameter("device");
        String action = request.getParameter("action");
        if(device.equals("Mobile")) {
            if (action.equals("getLecturerRelatedBookings")) {
                DAO checkDAO2 = new DAO();
                checkDAO2.checkForOldBookings();
                String lecturerID = request.getParameter("lecturerID");
                DAO doa1 = new DAO();
                List<Booking> bookingList = doa1.getLecturerBookings(lecturerID);

                String output = this.gson.toJson(bookingList);
                PrintWriter out = response.getWriter();
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                out.print(output);
                out.flush();
            }
            else if (action.equals("deleteParticularBooking")) {
                String bookingID = request.getParameter("bookingID");
                if (bookingID != null) {
                    DAO deleteDAO = new DAO();
                    int bookingIDInt = parseInt(bookingID);
                    System.out.println("Inside delete");
                    deleteDAO.deleteParticularBooking(bookingIDInt);
                }
            }
            else if (action.equals("editParticularBooking")) {
                Boolean isEditSucessful = false;
                System.out.println("\n");
                String editedClassIDString = request.getParameter("SelectedClassID");
                System.out.println("Class ID String format: " + editedClassIDString);
                String bookingIDValString = request.getParameter("bookingIDVal");
                System.out.println("Booking ID String format: " + bookingIDValString);
                String duration = request.getParameter("classDuration");
                System.out.println("Duration String format: " + duration);
                String onDate = request.getParameter("startDate");
                System.out.println("Start Date String Format : " + onDate);
                if (!userID.isEmpty() && !editedClassIDString.isEmpty() && !bookingIDValString.isEmpty() && !duration.isEmpty() && !onDate.isEmpty()) {
                    int editedClassID = parseInt(editedClassIDString);
                    int bookingIDVal = parseInt(bookingIDValString);
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


                    DAO editDAO = new DAO();
                    isEditSucessful = editDAO.editParticularLecturerBooking(userID, editedClassID, bookingIDVal, durationIDInt, onDateTimeStamp, endDateTimeStamp);
                    System.out.println("Has Edited Successful? " + isEditSucessful);
                    if(isEditSucessful == true){
                        MessageClass successMessage = new MessageClass(true,"Edited Successfully!");
                        String output = this.gson.toJson(successMessage);
                        PrintWriter out = response.getWriter();
                        response.setContentType("application/json");
                        response.setCharacterEncoding("UTF-8");
                        out.print(output);
                        out.flush();
                    }else{
                        MessageClass errorMessage = new MessageClass(false,"The system could not edit the booking!");
                        String output = this.gson.toJson(errorMessage);
                        PrintWriter out = response.getWriter();
                        response.setContentType("application/json");
                        response.setCharacterEncoding("UTF-8");
                        out.print(output);
                        out.flush();
                    }
                }
                else{
                    System.out.println("Needed Parameters are not available!");
                    MessageClass errorMessage = new MessageClass(false,"Needed Parameters are missing!");
                    String output = this.gson.toJson(errorMessage);
                    PrintWriter out = response.getWriter();
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    out.print(output);
                    out.flush();
                }
            }
        }
        else{
            if (userID != null) {
                String userIdentity = userID.toLowerCase().substring(0, 2);

                System.out.println("Action = " + action);
                DAO dao = new DAO();
                try {
                    if (userIdentity.equals("le")) {
                        System.out.println("Inside le");
                        if (action.equals("Delete")) {
                            String bookingIDString = request.getParameter("bookingID");
                            int bookingID = parseInt(bookingIDString);
                            System.out.println("Inside delete");
                            dao.deleteParticularBooking(bookingID);
                            request.getRequestDispatcher("changeBooking.jsp").forward(request, response);

                        }

                        else if (action.equals("Edit")) {
                            Boolean isEditSucessful = false;
                            System.out.println("Inside edit userID : " + userID);
                            String editedClassIDString = request.getParameter("SelectedClassID");
                            System.out.println("Class ID String format: "+ editedClassIDString);
                            String bookingIDValString = request.getParameter("bookingIDVal");
                            System.out.println("Booking ID String format: "+ bookingIDValString);
                            String duration = request.getParameter("classDuration");
                            System.out.println("Duration String format: "+duration);
                            String onDate = request.getParameter("startDate");
                            System.out.println("Start Date String Format : " + onDate);
                            if(!editedClassIDString.isEmpty() && !bookingIDValString.isEmpty() && !duration.isEmpty() && !onDate.isEmpty()){
                                int editedClassID = parseInt(editedClassIDString);
                                int bookingIDVal = parseInt(bookingIDValString);
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
                                Timestamp onDateTimeStamp = Timestamp.valueOf(formattedOnDate);
                                System.out.println("Converted New OnDate Timestamp "+ onDateTimeStamp);
                                LocalDateTime localDateTime =onDateTimeStamp.toLocalDateTime();
                                LocalDateTime endDateLocal = localDateTime.plusHours(durationIDInt);
                                System.out.println("New End Date Local Format : " + endDateLocal);

                                dateSection = endDateLocal.toString().split("T")[0];
                                timeSection = endDateLocal.toString().split("T")[1];
                                String seconds = ":00";
                                timeSection = timeSection + seconds;
                                formattedOnDate = dateSection + " "+timeSection;
                                System.out.println(formattedOnDate);

                                Timestamp endDateTimeStamp = Timestamp.valueOf(formattedOnDate);
                                System.out.println("Converted New EndDate Timestamp "+ endDateTimeStamp);

                                isEditSucessful = dao.editParticularLecturerBooking(userID,editedClassID,bookingIDVal,durationIDInt,onDateTimeStamp,endDateTimeStamp);
                                System.out.println("Has Edited Successful? "+isEditSucessful);

                                if(isEditSucessful ==true){

                                    request.setAttribute("errorShow", false);
                                    request.setAttribute("errorMessage", null);
                                    request.getRequestDispatcher("changeBooking.jsp").forward(request, response);

                                }else{
                                    request.setAttribute("errorShow", true);
                                    request.setAttribute("errorMessage", "Edit Error : The selected timeslot is already taken!");
                                    request.getRequestDispatcher("changeBooking.jsp").forward(request, response);
                                }

                            }

                        }
                    } else {
                        System.out.println("Outside le section");
                    }


                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            else {

                System.out.println("Not retrieved UserID");
            }
        }


    }
}
