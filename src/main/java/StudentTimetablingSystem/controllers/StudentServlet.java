package StudentTimetablingSystem.controllers;

import StudentTimetablingSystem.model.DAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.Integer.parseInt;

@WebServlet(name = "StudentServlet",  urlPatterns = {"/StudentServlet"})
public class StudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        String action  = request.getParameter("action");
        if(action != null) {
            if (action.equals("registration")) {
                System.out.println("Inside Registration section .......");
                String studentID = request.getParameter("studentID");
                String fName = request.getParameter("fName");
                String lName = request.getParameter("lName");
                String selectedCourseIDString = request.getParameter("courseName");
                String selectedBadgeIDString = request.getParameter("badge");
                String addresss = request.getParameter("address");
                String email = request.getParameter("email");
                String password = request.getParameter("pwd");
                String mobileNumber = request.getParameter("mobileNumber");
                String emMobileNumber = request.getParameter("emMobileNumber");
                String currentUser = request.getParameter("currentUserLoggedInAdmin");
                System.out.println("Logged In User: " + currentUser);
                DAO dao = new DAO();
                if (studentID != null) {
                    boolean isValidID = dao.verifyNewStudentID(studentID);
                    System.out.println("Student ID is new? " + isValidID);
                    if (isValidID == true) {
                        if (fName != null && lName != null && selectedCourseIDString != null && addresss != null && email != null && password != null && mobileNumber != null && emMobileNumber != null && currentUser != null) {
                            int courseID = parseInt(selectedCourseIDString);
                            int badgeID =parseInt(selectedBadgeIDString);
                            DAO dao1 = new DAO();
                            boolean isSucessful = dao1.registerStudent(studentID, fName, lName, courseID,badgeID, addresss, email, password, mobileNumber, emMobileNumber, currentUser);
                            System.out.println("Register method return value : " + isSucessful);
                            if (isSucessful == true) {
                                request.setAttribute("showSuccessMessage", true);
                                request.setAttribute("SuccessMsgBody", "The Student with the following ID : " + studentID + " is successfully registered!");
                                request.getRequestDispatcher("addStudent.jsp").forward(request, response);

                            } else {
                                request.setAttribute("showRegisterErrorMessage", true);
                                request.setAttribute("RegisterErrorMsgBody", "The registration of Student : " + studentID + " was unsuccessful!");
                                request.getRequestDispatcher("addStudent.jsp").forward(request, response);

                            }

                        }

                    } else {
                        request.setAttribute("showInvalidIDError", true);
                        request.setAttribute("IDErrorMessage", "The Student with the following ID : " + studentID + " is already existing");
                        request.getRequestDispatcher("addStudent.jsp").forward(request, response);

                    }
                }
            } else if (action.equals("Delete")) {
                System.out.println("Inside Student Delete section .......");

                String studentID = request.getParameter("UserID");
                System.out.println("Deleting Lecturer ID : " + studentID);

                String adminID = request.getParameter("loggedInUserID");
                System.out.println("Deleted by : " +adminID);

                if(studentID != null && adminID != null){
                    DAO dao = new DAO();
                    boolean isDeletedSuccessfully = dao.deleteSelectedStudent(studentID,adminID);
                    System.out.println("Student Deletion Successful ? " + isDeletedSuccessfully);
                    if(isDeletedSuccessfully ==true){
                        request.setAttribute("showSuccessMessage", true);
                        request.setAttribute("SuccessMsgBody", "The Student with the following ID : " + studentID + " is successfully deleted!");
                        request.getRequestDispatcher("viewEditDeleteStudent.jsp").forward(request, response);

                    }else{
                        request.setAttribute("showRegisterErrorMessage", true);
                        request.setAttribute("RegisterErrorMsgBody", "The Student : "+ studentID + " details could not be deleted !");
                        request.getRequestDispatcher("viewEditDeleteStudent.jsp").forward(request, response);
                    }
                }else{
                    request.setAttribute("showRegisterErrorMessage", true);
                    request.setAttribute("RegisterErrorMsgBody", "Technical Error! Data could not be retrieved!");
                    request.getRequestDispatcher("viewEditDeleteStudent.jsp").forward(request, response);
                }

            } else if (action.equals("Edit")) {
                System.out.println("Inside Student Edit section .......");

                String selectedStudentID = request.getParameter("selectedStudentID");
                System.out.println("Selected Student : " + selectedStudentID);

                String editedBy = request.getParameter("AdminUserID");
                System.out.println("Edited by Admin ID : " + editedBy);

                String email = request.getParameter("email");
                System.out.println("Edited Email : " + email);

                String pwd = request.getParameter("pwd");
                System.out.println("Edited Password : " + pwd);

                String address = request.getParameter("address");
                System.out.println("Edited Address : " + address);

                String mobile = request.getParameter("mobileNumber");
                System.out.println("Edited Mobile Number : " + mobile);

                String emMobile = request.getParameter("emMobileNumber");
                System.out.println("Edited Emergency Number : " + emMobile);

                if (selectedStudentID != null && editedBy != null && email != null && pwd != null && address != null && mobile != null && emMobile != null) {
                    DAO dao = new DAO();
                    boolean isUpdated = dao.updateStudentDetails(selectedStudentID,editedBy,email,pwd,address,mobile,emMobile);
                    System.out.println("Has Updated ? " + isUpdated);
                    if(isUpdated == true){
                        request.setAttribute("showSuccessMessage", true);
                        request.setAttribute("SuccessMsgBody", "The Student with the following ID : " + selectedStudentID + " is successfully updated!");
                        request.getRequestDispatcher("viewEditDeleteStudent.jsp").forward(request, response);


                    }else{
                        request.setAttribute("showRegisterErrorMessage", true);
                        request.setAttribute("RegisterErrorMsgBody", "The Student : "+ selectedStudentID + " details could not be updated !");
                        request.getRequestDispatcher("viewEditDeleteStudent.jsp").forward(request, response);

                    }
                }else{

                    request.setAttribute("showRegisterErrorMessage", true);
                    request.setAttribute("RegisterErrorMsgBody", "Technical Error! Data could not be retrieved!");
                    request.getRequestDispatcher("viewEditDeleteStudent.jsp").forward(request, response);

                }
            }


        }

    }
}
