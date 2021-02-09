package StudentTimetablingSystem.controllers;

import StudentTimetablingSystem.model.Course;
import StudentTimetablingSystem.model.DAO;
import StudentTimetablingSystem.model.MessageClass;
import com.google.gson.Gson;

import javax.jms.Message;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static java.lang.Integer.parseInt;

@WebServlet(name = "CourseServlet", urlPatterns = {"/CourseServlet"})
public class CourseServlet extends HttpServlet {
    private final Gson gson = new Gson();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String device = request.getParameter("device");
        String action  = request.getParameter("action");



        if(device != null && device != "" && device.equals("Mobile")){
            if(action != null){
                if(action.equals("getAllAvailableCourses")){
                    DAO availableCoursesDAO = new DAO();
                    List<Course> availableCourses = availableCoursesDAO.getCourses();
                    String output = this.gson.toJson(availableCourses);
                    PrintWriter out = response.getWriter();
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    out.print(output);
                    out.flush();
                }
                else if (action.equals("registration")) {
                    System.out.println("Inside MOBILE Course Registration section .......");

                    String courseName = request.getParameter("courseName");
                    System.out.println("New Course : "+ courseName);

                    String courseField = request.getParameter("courseField");
                    System.out.println("New Course's course field: "+courseField);

                    String addedBy = request.getParameter("currentUserLoggedInAdmin");
                    System.out.println("Course Added By : " + addedBy);

                    if(courseName!= null && courseField != null && addedBy != null ){

                        DAO dao = new DAO();
                        boolean isAdded = dao.addNewCourse(courseName,courseField,addedBy);
                        System.out.println("Course Added? "+ isAdded);
                        if(isAdded == true){
                            MessageClass messageClass = new MessageClass(true,courseName+" course is added successfully!");
                            String output = this.gson.toJson(messageClass);
                            PrintWriter out = response.getWriter();
                            response.setContentType("application/json");
                            response.setCharacterEncoding("UTF-8");
                            out.print(output);
                            out.flush();
                        }else{
                            MessageClass messageClass = new MessageClass(false,courseName+" course could not be added!");
                            String output = this.gson.toJson(messageClass);
                            PrintWriter out = response.getWriter();
                            response.setContentType("application/json");
                            response.setCharacterEncoding("UTF-8");
                            out.print(output);
                            out.flush();

                        }
                    }else{
                        MessageClass messageClass = new MessageClass(false,"Required Parameters not Found!");
                        String output = this.gson.toJson(messageClass);
                        PrintWriter out = response.getWriter();
                        response.setContentType("application/json");
                        response.setCharacterEncoding("UTF-8");
                        out.print(output);
                        out.flush();
                    }

                }
                else if (action.equals("Delete")) {
                    String deletedBy = request.getParameter("loggedInUserID");
                    String courseID = request.getParameter("courseID");
                    System.out.println("Deleting Course ID : "+ courseID);
                    System.out.println("Deleted By : "+ deletedBy);

                    if(courseID != null && deletedBy != null ){
                        int courseIDInt = parseInt(courseID);
                        DAO dao = new DAO();
                        boolean isDeleted = dao.deleteSelectedCourse(courseIDInt, deletedBy);
                        System.out.println("Selected Course Deleted? "+ isDeleted);
                        if(isDeleted == true){
                            MessageClass messageClass = new MessageClass(true, "The course deleted successfully!");
                            String output = this.gson.toJson(messageClass);
                            PrintWriter out = response.getWriter();
                            response.setContentType("application/json");
                            response.setCharacterEncoding("UTF-8");
                            out.print(output);
                            out.flush();

                        }else{
                            MessageClass messageClass = new MessageClass(true, "The course could not be deleted!");
                            String output = this.gson.toJson(messageClass);
                            PrintWriter out = response.getWriter();
                            response.setContentType("application/json");
                            response.setCharacterEncoding("UTF-8");
                            out.print(output);
                            out.flush();

                        }
                    }else{
                        MessageClass messageClass = new MessageClass(true, "Required Parameters are not available!");
                        String output = this.gson.toJson(messageClass);
                        PrintWriter out = response.getWriter();
                        response.setContentType("application/json");
                        response.setCharacterEncoding("UTF-8");
                        out.print(output);
                        out.flush();
                    }
                }
                else if (action.equals("Edit")){
                    String editedBy = request.getParameter("AdminUserID");
                    String courseID = request.getParameter("selectedCourseID");
                    System.out.println("Edited Course's ID : "+ courseID);
                    System.out.println("Edited By : "+ editedBy);
                    String courseName = request.getParameter("courseName");
                    String courseField = request.getParameter("courseField");
                    if(courseID != null && editedBy != null && courseName != null && courseField != null ){
                        int courseIdInt = parseInt(courseID);
                        DAO dao = new DAO();
                        boolean isEditSuccess =dao.updateSelectedCourseDetails(courseIdInt,courseName,courseField,editedBy);
                        System.out.println("Is Edited? "+isEditSuccess);
                        if(isEditSuccess == true){
                            MessageClass messageClass = new MessageClass(true, "The course is edited successfully!");
                            String output = this.gson.toJson(messageClass);
                            PrintWriter out = response.getWriter();
                            response.setContentType("application/json");
                            response.setCharacterEncoding("UTF-8");
                            out.print(output);
                            out.flush();
                        }else{
                            MessageClass messageClass = new MessageClass(true, "The course could not be edited!");
                            String output = this.gson.toJson(messageClass);
                            PrintWriter out = response.getWriter();
                            response.setContentType("application/json");
                            response.setCharacterEncoding("UTF-8");
                            out.print(output);
                            out.flush();
                        }
                    }else{
                        MessageClass messageClass = new MessageClass(true, "Required Parameters are not retrieved!");
                        String output = this.gson.toJson(messageClass);
                        PrintWriter out = response.getWriter();
                        response.setContentType("application/json");
                        response.setCharacterEncoding("UTF-8");
                        out.print(output);
                        out.flush();
                    }
                }



            }
        }

        else {
            if(action != null) {
                if (action.equals("registration")) {
                    System.out.println("Inside Course Registration section .......");

                    String courseName = request.getParameter("courseName");
                    System.out.println("New Course : "+ courseName);

                    String courseField = request.getParameter("courseField");
                    System.out.println("New Course's course field: "+courseField);

                    String addedBy = request.getParameter("currentUserLoggedInAdmin");
                    System.out.println("Course Added By : " + addedBy);

                    if(courseName!= null && courseField != null && addedBy != null ){

                        DAO dao = new DAO();
                        boolean isAdded = dao.addNewCourse(courseName,courseField,addedBy);
                        System.out.println("Course Added? "+ isAdded);
                        if(isAdded == true){
                            request.setAttribute("showSuccessMessage", true);
                            request.setAttribute("SuccessMsgBody", "The Course with the following name : " + courseName + " is successfully added!");
                            request.getRequestDispatcher("addNewCourse.jsp").forward(request, response);
                        }else{
                            request.setAttribute("showRegisterErrorMessage", true);
                            request.setAttribute("RegisterErrorMsgBody", "The registration of New Course : " + courseName + " was unsuccessful!");
                            request.getRequestDispatcher("addNewCourse.jsp").forward(request, response);

                        }
                    }else{
                        request.setAttribute("showInvalidIDError", true);
                        request.setAttribute("IDErrorMessage", "Technical Error! Data could not be retrieved!");
                        request.getRequestDispatcher("addNewCourse.jsp").forward(request, response);
                    }

                }
                else if (action.equals("Delete")) {
                    String deletedBy = request.getParameter("loggedInUserID");
                    String courseID = request.getParameter("courseID");
                    System.out.println("Deleting Course ID : "+ courseID);
                    System.out.println("Deleted By : "+ deletedBy);

                    if(courseID != null && deletedBy != null ){
                        int courseIDInt = parseInt(courseID);
                        DAO dao = new DAO();
                        boolean isDeleted = dao.deleteSelectedCourse(courseIDInt, deletedBy);
                        System.out.println("Selected Course Deleted? "+ isDeleted);
                        if(isDeleted == true){
                            request.setAttribute("showSuccessMessage", true);
                            request.setAttribute("SuccessMsgBody", "The Course with the following ID : " + courseID + " is successfully deleted!");
                            request.getRequestDispatcher("viewEditDeleteCourses.jsp").forward(request, response);

                        }else{
                            request.setAttribute("showErrorMessage", true);
                            request.setAttribute("ErrorMsgBody", "The Course with the following ID: "+ courseID + " could not be deleted !");
                            request.getRequestDispatcher("viewEditDeleteCourses.jsp").forward(request, response);

                        }
                    }else{
                        request.setAttribute("showErrorMessage", true);
                        request.setAttribute("ErrorMsgBody", "Technical Error! Data could not be retrieved!");
                        request.getRequestDispatcher("viewEditDeleteCourses.jsp").forward(request, response);
                    }
                }
                else if (action.equals("Edit")){
                    String editedBy = request.getParameter("AdminUserID");
                    String courseID = request.getParameter("selectedCourseID");
                    System.out.println("Edited Course's ID : "+ courseID);
                    System.out.println("Edited By : "+ editedBy);
                    String courseName = request.getParameter("courseName");
                    String courseField = request.getParameter("courseField");
                    if(courseID != null && editedBy != null && courseName != null && courseField != null ){
                        int courseIdInt = parseInt(courseID);
                        DAO dao = new DAO();
                        boolean isEditSuccess =dao.updateSelectedCourseDetails(courseIdInt,courseName,courseField,editedBy);
                        System.out.println("Is Edited? "+isEditSuccess);
                        if(isEditSuccess == true){
                            request.setAttribute("showSuccessMessage", true);
                            request.setAttribute("SuccessMsgBody", "The Course with the following ID : " + courseID + " is successfully updated!");
                            request.getRequestDispatcher("viewEditDeleteCourses.jsp").forward(request, response);

                        }else{
                            request.setAttribute("showErrorMessage", true);
                            request.setAttribute("ErrorMsgBody", "The Course with the following ID: "+ courseID + " could not be updated !");
                            request.getRequestDispatcher("viewEditDeleteCourses.jsp").forward(request, response);

                        }
                    }else{
                        request.setAttribute("showErrorMessage", true);
                        request.setAttribute("ErrorMsgBody", "Technical Error! Data could not be retrieved!");
                        request.getRequestDispatcher("viewEditDeleteCourses.jsp").forward(request, response);
                    }
                }
            }
        }


    }
}
