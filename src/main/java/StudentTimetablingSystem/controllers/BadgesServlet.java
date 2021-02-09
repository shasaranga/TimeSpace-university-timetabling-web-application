package StudentTimetablingSystem.controllers;

import StudentTimetablingSystem.model.Badge;
import StudentTimetablingSystem.model.Course;
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
import java.util.List;

import static java.lang.Integer.parseInt;

@WebServlet(name = "BadgesServlet", urlPatterns = {"/BadgesServlet"})
public class BadgesServlet extends HttpServlet {
    private final Gson gson = new Gson();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try(PrintWriter out = response.getWriter()){
            int courseID = parseInt(request.getParameter("valajax"));
            if(courseID != 0) {
                System.out.println("Got Course ID : "+ courseID );
                DAO dao = new DAO();
                List<Badge> badgeList = dao.getBadges(courseID);
                if(badgeList.size() != 0){
                    System.out.println("Got badges");

                    response.getWriter().write(" <option value='' disabled selected hidden>Select a Badge</option>");
                    for(int i =0; i < badgeList.size(); i++){
                        response.getWriter().write("<option value ="+badgeList.get(i).getId()+">"+badgeList.get(i).getBadgeName()+"</option>");
                        System.out.println(badgeList.get(i).getBadgeName());
                    }
                    request.setAttribute("startGettingClasses", true);

                }
            }

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String action  = request.getParameter("action");
        String device = request.getParameter("device");
        if(action != null) {
            if(device != null && device != "" && device.equals("Mobile")){
                if(action.equals("getAllAvailableBadges")){

                        DAO availableCoursesDAO = new DAO();
                        List<Badge> availableBadges = availableCoursesDAO.getAllBadges();
                        String output = this.gson.toJson(availableBadges);
                        PrintWriter out = response.getWriter();
                        response.setContentType("application/json");
                        response.setCharacterEncoding("UTF-8");
                        out.print(output);
                        out.flush();


                }
                else if(action.equals("getBadgeByCourseID")){
                    String courseID = request.getParameter("selectedCourseID");
                    int courseIDInt = parseInt(courseID);
                    DAO newoursesDAO2 = new DAO();
                    List<Badge> availableBadges = newoursesDAO2.getBadges(courseIDInt);
                    String output = this.gson.toJson(availableBadges);
                    PrintWriter out = response.getWriter();
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    out.print(output);
                    out.flush();
                }
                else if (action.equals("registration")) {
                    System.out.println("Inside MOBILE Module Registration section .......");

                    String courseID = request.getParameter("courseName");
                    System.out.println("New Course's ID : " + courseID);

                    String badgeName = request.getParameter("badgeName");
                    System.out.println("New Badge's Name: " + badgeName);

                    String badgeYear = request.getParameter("badgeYear");
                    System.out.println("New Badge's Year: " + badgeYear);

                    String addedBy = request.getParameter("currentUserLoggedInAdmin");
                    System.out.println("Badge Added By : " + addedBy);

                    if (courseID != null && badgeName != null && badgeYear != null &&addedBy != null) {
                        DAO dao = new DAO();
                        boolean isNameValid = dao.verifyNewBadgeName(badgeName);
                        if(isNameValid == true){
                            int courseIDInt = parseInt(courseID);
                            int badgeYearInt = parseInt(badgeYear);
                            DAO dao1 = new DAO();
                            boolean isAdded = dao1.addNewBadge(courseIDInt,badgeName,badgeYearInt,addedBy);
                            System.out.println("Badge Added? " + isAdded);
                            if (isAdded == true) {
                                MessageClass messageClass = new MessageClass(true,  " Badge is added successfully!");
                                String output = this.gson.toJson(messageClass);
                                PrintWriter out = response.getWriter();
                                response.setContentType("application/json");
                                response.setCharacterEncoding("UTF-8");
                                out.print(output);
                                out.flush();
                            } else {
                                MessageClass messageClass = new MessageClass(false,  "Badge could not be added!");
                                String output = this.gson.toJson(messageClass);
                                PrintWriter out = response.getWriter();
                                response.setContentType("application/json");
                                response.setCharacterEncoding("UTF-8");
                                out.print(output);
                                out.flush();

                            }
                        }else{
                            MessageClass messageClass = new MessageClass(false,  "Entered New Badge Name is already existing!");
                            String output = this.gson.toJson(messageClass);
                            PrintWriter out = response.getWriter();
                            response.setContentType("application/json");
                            response.setCharacterEncoding("UTF-8");
                            out.print(output);
                            out.flush();
                        }

                    } else {
                        MessageClass messageClass = new MessageClass(false,  "Required Parameters not Found!");
                        String output = this.gson.toJson(messageClass);
                        PrintWriter out = response.getWriter();
                        response.setContentType("application/json");
                        response.setCharacterEncoding("UTF-8");
                        out.print(output);
                        out.flush();
                    }

                }
                else if(action.equals("Edit")){
                        String badgeID = request.getParameter("selectedBadgeID");
                    String badgeName = request.getParameter("badgeName");
                        String badgeYear = request.getParameter("badgeYear");
                    String courseID =  request.getParameter("selectedCourseID");
                    String editedBy = request.getParameter("AdminUserID");

                    System.out.println(badgeID);
                    System.out.println(badgeName);
                    System.out.println(badgeYear);
                    System.out.println(courseID);
                    System.out.println(editedBy);

                    if(badgeID != null && courseID !=null && badgeName != null && badgeYear != null && editedBy != null){
                        int badgeIDInt = parseInt(badgeID);
                        int courseIdInt = parseInt(courseID);
                        int badgeYearInt = parseInt(badgeYear);
                        DAO dao = new DAO();
                        boolean isSuccess = dao.updateSelectedBadgeDetails(badgeIDInt,courseIdInt,badgeName,badgeYearInt,editedBy);
                        if(isSuccess == true){
                            MessageClass messageClass = new MessageClass(true,  " Badge is edited successfully!");
                            String output = this.gson.toJson(messageClass);
                            PrintWriter out = response.getWriter();
                            response.setContentType("application/json");
                            response.setCharacterEncoding("UTF-8");
                            out.print(output);
                            out.flush();
                        }else{
                            MessageClass messageClass = new MessageClass(false,  " Badge could not be edited!");
                            String output = this.gson.toJson(messageClass);
                            PrintWriter out = response.getWriter();
                            response.setContentType("application/json");
                            response.setCharacterEncoding("UTF-8");
                            out.print(output);
                            out.flush();

                        }
                    }else{
                        MessageClass messageClass = new MessageClass(false,  "Required Parameters not found!");
                        String output = this.gson.toJson(messageClass);
                        PrintWriter out = response.getWriter();
                        response.setContentType("application/json");
                        response.setCharacterEncoding("UTF-8");
                        out.print(output);
                        out.flush();
                    }
                }
                else if(action.equals("Delete")){
                        String badgeID = request.getParameter("badgeID");
                    String courseID = request.getParameter("courseID");
                    String deletedBy = request.getParameter("loggedInUserID");
                    System.out.println("Deleting badge : " + badgeID);
                    System.out.println("Deleting a badge of course : " + courseID);
                    System.out.println("Deleted By : " + deletedBy);

                    if(badgeID != null && courseID!=null && deletedBy != null){
                        int badgeIDInt = parseInt(badgeID);
                        int courseIDInt = parseInt(courseID);
                        DAO dao = new DAO();
                        boolean isDeleted = dao.deleteSelectedBadge(badgeIDInt,courseIDInt,deletedBy);
                        if(isDeleted == true){
                            MessageClass messageClass = new MessageClass(true,  " Badge is deleted successfully!");
                            String output = this.gson.toJson(messageClass);
                            PrintWriter out = response.getWriter();
                            response.setContentType("application/json");
                            response.setCharacterEncoding("UTF-8");
                            out.print(output);
                            out.flush();

                        }else{
                            MessageClass messageClass = new MessageClass(true,  " Badge could not be deleted!");
                            String output = this.gson.toJson(messageClass);
                            PrintWriter out = response.getWriter();
                            response.setContentType("application/json");
                            response.setCharacterEncoding("UTF-8");
                            out.print(output);
                            out.flush();
                        }
                    }else{
                        MessageClass messageClass = new MessageClass(true,  "Required data is not found!");
                        String output = this.gson.toJson(messageClass);
                        PrintWriter out = response.getWriter();
                        response.setContentType("application/json");
                        response.setCharacterEncoding("UTF-8");
                        out.print(output);
                        out.flush();
                    }
                }
            }else{
                if (action.equals("registration")) {
                    System.out.println("Inside Module Registration section .......");

                    String courseID = request.getParameter("courseName");
                    System.out.println("New Course's ID : " + courseID);

                    String badgeName = request.getParameter("badgeName");
                    System.out.println("New Badge's Name: " + badgeName);

                    String badgeYear = request.getParameter("badgeYear");
                    System.out.println("New Badge's Year: " + badgeYear);

                    String addedBy = request.getParameter("currentUserLoggedInAdmin");
                    System.out.println("Badge Added By : " + addedBy);

                    if (courseID != null && badgeName != null && badgeYear != null &&addedBy != null) {
                        DAO dao = new DAO();
                        boolean isNameValid = dao.verifyNewBadgeName(badgeName);
                        if(isNameValid == true){
                            int courseIDInt = parseInt(courseID);
                            int badgeYearInt = parseInt(badgeYear);
                            DAO dao1 = new DAO();
                            boolean isAdded = dao1.addNewBadge(courseIDInt,badgeName,badgeYearInt,addedBy);
                            System.out.println("Badge Added? " + isAdded);
                            if (isAdded == true) {
                                request.setAttribute("showSuccessMessage", true);
                                request.setAttribute("SuccessMsgBody", "The Badge with the following name : " + badgeName + " is successfully added!");
                                request.getRequestDispatcher("addNewBadge.jsp").forward(request, response);
                            } else {
                                request.setAttribute("showRegisterErrorMessage", true);
                                request.setAttribute("RegisterErrorMsgBody", "The saving of New Badge : " + badgeName + " was unsuccessful!");
                                request.getRequestDispatcher("addNewBadge.jsp").forward(request, response);

                            }
                        }else{
                            request.setAttribute("showRegisterErrorMessage", true);
                            request.setAttribute("RegisterErrorMsgBody", "The Badge Name : " + badgeName + " is already available!");
                            request.getRequestDispatcher("addNewBadge.jsp").forward(request, response);
                        }

                    } else {
                        request.setAttribute("showInvalidIDError", true);
                        request.setAttribute("IDErrorMessage", "Technical Error! Data could not be retrieved!");
                        request.getRequestDispatcher("addNewBadge.jsp").forward(request, response);
                    }

                }
                else if(action.equals("Edit")){
                    String badgeID = request.getParameter("selectedBadgeID");
                    String badgeName = request.getParameter("badgeName");
                    String badgeYear = request.getParameter("badgeYear");
                    String courseID =  request.getParameter("selectedCourseID");
                    String editedBy = request.getParameter("AdminUserID");

                    if(badgeID != null && courseID !=null && badgeName != null && badgeYear != null && editedBy != null){
                        int badgeIDInt = parseInt(badgeID);
                        int courseIdInt = parseInt(courseID);
                        int badgeYearInt = parseInt(badgeYear);
                        DAO dao = new DAO();
                        boolean isSuccess = dao.updateSelectedBadgeDetails(badgeIDInt,courseIdInt,badgeName,badgeYearInt,editedBy);
                        if(isSuccess == true){
                            request.setAttribute("showSuccessMessage", true);
                            request.setAttribute("SuccessMsgBody", "The Badge with the following ID : " + badgeID + " is successfully updated!");
                            request.getRequestDispatcher("viewEditDeleteBadges.jsp").forward(request, response);

                        }else{
                            request.setAttribute("showErrorMessage", true);
                            request.setAttribute("ErrorMsgBody", "The Badge with the following ID: "+ badgeID + " could not be updated !");
                            request.getRequestDispatcher("viewEditDeleteBadges.jsp").forward(request, response);

                        }
                    }else{
                        request.setAttribute("showErrorMessage", true);
                        request.setAttribute("ErrorMsgBody", "Technical Error! Data could not be retrieved!");
                        request.getRequestDispatcher("viewEditDeleteBadges.jsp").forward(request, response);
                    }
                }
                else if(action.equals("Delete")){
                    String badgeID = request.getParameter("badgeID");
                    String courseID = request.getParameter("courseID");
                    String deletedBy = request.getParameter("loggedInUserID");
                    System.out.println("Deleting badge : " + badgeID);
                    System.out.println("Deleting a badge of course : " + courseID);
                    System.out.println("Deleted By : " + deletedBy);

                    if(badgeID != null && courseID!=null && deletedBy != null){
                        int badgeIDInt = parseInt(badgeID);
                        int courseIDInt = parseInt(courseID);
                        DAO dao = new DAO();
                        boolean isDeleted = dao.deleteSelectedBadge(badgeIDInt,courseIDInt,deletedBy);
                        if(isDeleted == true){
                            request.setAttribute("showSuccessMessage", true);
                            request.setAttribute("SuccessMsgBody", "The Badge with the following ID : " + badgeID + " is successfully deleted!");
                            request.getRequestDispatcher("viewEditDeleteBadges.jsp").forward(request, response);

                        }else{
                            request.setAttribute("showErrorMessage", true);
                            request.setAttribute("ErrorMsgBody", "The Badge : "+ badgeID + " could not be deleted !");
                            request.getRequestDispatcher("viewEditDeleteBadges.jsp").forward(request, response);
                        }
                    }else{
                        request.setAttribute("showErrorMessage", true);
                        request.setAttribute("ErrorMsgBody", "Technical Error! Data could not be retrieved!");
                        request.getRequestDispatcher("viewEditDeleteBadges.jsp").forward(request, response);
                    }
                }
            }

        }
    }
}
