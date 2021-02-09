package StudentTimetablingSystem.controllers;

import StudentTimetablingSystem.model.*;
import com.google.gson.Gson;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "UserLoginServlet", urlPatterns = {"/UserLoginServlet"})
public class UserLoginServlet extends HttpServlet {
    private final Gson gson = new Gson();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        String email = null;
        email = request.getParameter("username");
        String password = null;
        password = request.getParameter("pass");
        String device = request.getParameter("device");

        String userIdentity = email.toLowerCase().substring(0, 2);

        System.out.println();
        DAO dao = new DAO();
        Boolean isValid = false;

        if(device == null || device =="" || !device.equals("Mobile")){

            System.out.println("NOT A MOBILE REQUEST !");
            if (userIdentity.equals("cb")) {
                Student foundStudent = dao.loginStudent(email, password);
                if (foundStudent != null) {
                    HttpSession session = request.getSession(true);
                    session.setAttribute("currentSessionUser", foundStudent);
                    RequestDispatcher rd = request.getRequestDispatcher("searchStudentTimeTableForStudent.jsp");
                    rd.forward(request, response);

                    isValid = true;
                } else {
                    isValid = false;
                }


            }
            else if (userIdentity.equals("le")) {
                Lecturer foundLecturer = dao.loginLecturer(email, password);
                if (foundLecturer != null) {
                    HttpSession session = request.getSession(true);
                    session.setAttribute("currentSessionUser", foundLecturer);
                    RequestDispatcher rd = request.getRequestDispatcher("searchLecturerTimeTableForLecturer.jsp");
                    rd.forward(request, response);
                    isValid = true;

                } else {
                    isValid = false;
                }

            }
            else if(userIdentity.equals("ti")) {
                Timetabler foundTimetabler= dao.loginTimetabler(email, password);
                if (foundTimetabler != null) {
                    HttpSession session = request.getSession(true);
                    session.setAttribute("currentSessionUser", foundTimetabler);
                    RequestDispatcher rd = request.getRequestDispatcher("createCourseBadgeTimetable.jsp");
                    rd.forward(request, response);
                    isValid = true;
                } else {
                    isValid = false;
                }

            }
            else {

                Admin foundAdmin = dao.loginAdmin(email, password);
                if (foundAdmin != null) {
                    HttpSession session = request.getSession(true);
                    session.setAttribute("currentSessionUser", foundAdmin);
                    RequestDispatcher rd = request.getRequestDispatcher("searchStudentTimeTableForAdmin.jsp");
                    rd.forward(request, response);
                    isValid = true;
                }
            }
            System.out.println(isValid);
            if (isValid == false) {
                System.out.println(isValid);
                request.setAttribute("showLoginError", true);
//            request.setAttribute("pass", password);
                request.getRequestDispatcher("index.jsp").forward(request, response);

            }

            DAO checkDAO1 = new DAO();
            checkDAO1.checkForOldBookings();
        }
        else{
            if(device.equals("Mobile")){
                System.out.println("MOBILE REQUEST !");
                if (userIdentity.equals("cb")) {
                    Student foundStudent = dao.loginStudent(email, password);
                    if (foundStudent != null) {

                        DAO courseInfoDao = new DAO();
                        Course followingCurrentCourse = courseInfoDao.getCourseDetailsById(foundStudent.getCourseID());

                        foundStudent.setFollowingCourse(followingCurrentCourse);

                        String output = this.gson.toJson(foundStudent);
                        PrintWriter out = response.getWriter();
                        response.setContentType("application/json");
                        response.setCharacterEncoding("UTF-8");
                        out.print(output);
                        //.print(output2);
                        out.flush();

                        isValid = true;
                    } else {
                        isValid = false;
                    }


                }
                else if (userIdentity.equals("le")) {
                    Lecturer foundLecturer = dao.loginLecturer(email, password);
                    if (foundLecturer != null) {

                        String output = this.gson.toJson(foundLecturer);
                        PrintWriter out = response.getWriter();
                        response.setContentType("application/json");
                        response.setCharacterEncoding("UTF-8");
                        out.print(output);
                        out.flush();
                    }

                }
                else if(userIdentity.equals("ti") || userIdentity.equals("TI")) {
                    Timetabler foundTimetabler= dao.loginTimetabler(email, password);
                    if (foundTimetabler != null) {
                        String output = this.gson.toJson(foundTimetabler);
                        PrintWriter out = response.getWriter();
                        response.setContentType("application/json");
                        response.setCharacterEncoding("UTF-8");
                        out.print(output);
                        out.flush();

                        isValid = true;
                    } else {
                        isValid = false;
                    }

                }
                else {

                    Admin foundAdmin = dao.loginAdmin(email, password);
                    if (foundAdmin != null) {
                        String output = this.gson.toJson(foundAdmin);
                        PrintWriter out = response.getWriter();
                        response.setContentType("application/json");
                        response.setCharacterEncoding("UTF-8");
                        out.print(output);
                        out.flush();
                        isValid = true;

                    }
                }
            }
            DAO checkDAO2 = new DAO();
            checkDAO2.checkForOldBookings();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
