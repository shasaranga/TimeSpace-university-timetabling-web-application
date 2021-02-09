package StudentTimetablingSystem.controllers;

import StudentTimetablingSystem.model.DAO;
import StudentTimetablingSystem.model.Lecturer;
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

@WebServlet(name = "LecturerServlet", urlPatterns = {"/LecturerServlet"})
public class LecturerServlet extends HttpServlet {
    private final Gson gson = new Gson();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String action  = request.getParameter("action");
        String device = request.getParameter("device");
        if(device.equals("Mobile")){
            if(action.equals("getAvailableLecturer")){
                DAO lecturerDAO = new DAO();
                List<Lecturer> lecturerList = lecturerDAO.getAllLecturers();
                if(lecturerList != null){
                    String output = this.gson.toJson(lecturerList);
                    PrintWriter out = response.getWriter();
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    out.print(output);
                    out.flush();
                }else{

                    System.out.println("There is no lecturer to fetch!");

                }
            }
        }else{
            if(action != null){
                if(action.equals("registration")){
                    System.out.println("Inside Lecturer Registration section .......");
                    String lecturerID = request.getParameter("lecturerID");
                    String fName = request.getParameter("fName");
                    String lName = request.getParameter("lName");
                    String addresss = request.getParameter("address");
                    String email = request.getParameter("email");
                    String password = request.getParameter("pwd");
                    String mobileNumber = request.getParameter("mobileNumber");
                    String emMobileNumber = request.getParameter("emMobileNumber");
                    String currentUser = request.getParameter("currentUserLoggedInAdmin");
                    System.out.println("Logged In User: "+currentUser);
                    DAO dao  = new DAO();
                    if(lecturerID != null){
                        boolean isValidID = dao.verifyNewLecturerID(lecturerID);
                        System.out.println("Lecturer ID is new? "+isValidID);
                        if(isValidID ==true){
                            if( fName !=null && lName != null  && addresss !=null && email != null && password != null && mobileNumber !=null && emMobileNumber !=null && currentUser != null){
                                DAO dao1 = new DAO();
                                Boolean isSucessful = dao1.registerLecturer(lecturerID,fName,lName,addresss,email,password,mobileNumber,emMobileNumber,currentUser);
                                System.out.println("Register method return value : " + isSucessful);
                                if(isSucessful == true){
                                    request.setAttribute("showSuccessMessage", true);
                                    request.setAttribute("SuccessMsgBody", "The Lecturer with the following ID : " + lecturerID + " is successfully registered!");
                                    request.getRequestDispatcher("addLecturer.jsp").forward(request, response);

                                }else{
                                    request.setAttribute("showRegisterErrorMessage", true);
                                    request.setAttribute("RegisterErrorMsgBody", "The registration of Lecturer : "+ lecturerID + " was unsuccessful!");
                                    request.getRequestDispatcher("addLecturer.jsp").forward(request, response);

                                }

                            }

                        }else{
                            request.setAttribute("showInvalidIDError", true);
                            request.setAttribute("IDErrorMessage", "The Lecturer with the following ID : " + lecturerID + " is already existing");
                            request.getRequestDispatcher("addLecturer.jsp").forward(request, response);

                        }
                    }
                }
                else if(action.equals("Delete")){
                    System.out.println("Inside Lecturer Delete section .......");

                    String lecturerID = request.getParameter("UserID");
                    System.out.println("Deleting Lecturer ID : " + lecturerID);

                    String adminID = request.getParameter("loggedInUserID");
                    System.out.println("Deleted by : " +adminID);

                    if(lecturerID != null && adminID != null ){
                        DAO dao = new DAO();
                        boolean isDeletedSuccessfully = dao.deleteSelectedLecturer(lecturerID,adminID);
                        System.out.println("Lecture Deletion Successful ? " + isDeletedSuccessfully);
                        if(isDeletedSuccessfully ==true){
                            request.setAttribute("showSuccessMessage", true);
                            request.setAttribute("SuccessMsgBody", "The Lecturer with the following ID : " + lecturerID + " is successfully deleted!");
                            request.getRequestDispatcher("viewEditDeleteLecturer.jsp").forward(request, response);

                        }else{
                            request.setAttribute("showRegisterErrorMessage", true);
                            request.setAttribute("RegisterErrorMsgBody", "The Lecturer : "+ lecturerID + " details could not be deleted !");
                            request.getRequestDispatcher("viewEditDeleteLecturer.jsp").forward(request, response);
                        }
                    }else{
                        request.setAttribute("showRegisterErrorMessage", true);
                        request.setAttribute("RegisterErrorMsgBody", "Technical Error! Data could not be retrieved!");
                        request.getRequestDispatcher("viewEditDeleteLecturer.jsp").forward(request, response);
                    }

                }
                else if(action.equals("Edit")){
                    System.out.println("Inside Lecturer Edit section .......");

                    String selectedLecturerID = request.getParameter("selectedLecturerID");
                    System.out.println("Selected Lecturer : " + selectedLecturerID);

                    String editedBy = request.getParameter("AdminUserID");
                    System.out.println("Edited by Admin ID : " + editedBy);

                    String email = request.getParameter("email");
                    System.out.println("Edited Email : " + email);

                    String pwd = request.getParameter("pwd");
                    System.out.println("Edited Password : "+ pwd);

                    String address = request.getParameter("address");
                    System.out.println("Edited Address : " + address);

                    String mobile =  request.getParameter("mobileNumber");
                    System.out.println("Edited Mobile Number : " + mobile);

                    String emMobile = request.getParameter("emMobileNumber");
                    System.out.println("Edited Emergency Number : " + emMobile);

                    if(selectedLecturerID !=null && editedBy!=null && email != null && pwd != null && address != null && mobile != null && emMobile !=null){
                        DAO dao = new DAO();
                        boolean isUpdated = dao.updateLecturerDetails(selectedLecturerID,editedBy,email,pwd,address,mobile,emMobile);
                        System.out.println("Has Updated ? " + isUpdated);
                        if(isUpdated == true){
                            request.setAttribute("showSuccessMessage", true);
                            request.setAttribute("SuccessMsgBody", "The Lecturer with the following ID : " + selectedLecturerID + " is successfully updated!");
                            request.getRequestDispatcher("viewEditDeleteLecturer.jsp").forward(request, response);


                        }else{
                            request.setAttribute("showRegisterErrorMessage", true);
                            request.setAttribute("RegisterErrorMsgBody", "The Lecturer : "+ selectedLecturerID + " details could not be updated !");
                            request.getRequestDispatcher("viewEditDeleteLecturer.jsp").forward(request, response);

                        }
                    }else{

                        request.setAttribute("showRegisterErrorMessage", true);
                        request.setAttribute("RegisterErrorMsgBody", "Technical Error! Data could not be retrieved!");
                        request.getRequestDispatcher("viewEditDeleteLecturer.jsp").forward(request, response);

                    }

                }
            }
        }

    }
}
