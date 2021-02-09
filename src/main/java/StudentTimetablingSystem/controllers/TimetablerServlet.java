package StudentTimetablingSystem.controllers;

import StudentTimetablingSystem.model.DAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "TimetablerServlet",  urlPatterns = {"/TimetablerServlet"})
public class TimetablerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String action  = request.getParameter("action");
        if(action != null){
            if(action.equals("registration")){
                System.out.println("Inside Timetabler Registration section .......");
                String timetablerID = request.getParameter("timetablerID").toUpperCase();
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
                if(timetablerID != null && email != null){
                    String containsAcronym = timetablerID.toUpperCase().substring(0, 2);
                    String mailAcronym = email.toUpperCase().substring(0, 2);
                    if(containsAcronym.equals("TI") && mailAcronym.equals("TI")){
                        boolean isValidID = dao.verifyNewTimetablerID(timetablerID);
                        System.out.println("Timetabler ID is new? "+isValidID);
                        if(isValidID ==true){
                            if( fName !=null && lName != null  && addresss !=null && email != null && password != null && mobileNumber !=null && emMobileNumber !=null && currentUser != null){
                                DAO dao1 = new DAO();
                                Boolean isSucessful = dao1.registerTimetabler(timetablerID,fName,lName,addresss,email,password,mobileNumber,emMobileNumber,currentUser);
                                System.out.println("Register method return value : " + isSucessful);
                                if(isSucessful == true){
                                    request.setAttribute("showSuccessMessage", true);
                                    request.setAttribute("SuccessMsgBody", "The Lecturer with the following ID : " + timetablerID + " is successfully registered!");
                                    request.getRequestDispatcher("addTimetabler.jsp").forward(request, response);

                                }else{
                                    request.setAttribute("showRegisterErrorMessage", true);
                                    request.setAttribute("RegisterErrorMsgBody", "The registration of Timetabler : "+ timetablerID + " was unsuccessful!");
                                    request.getRequestDispatcher("addTimetabler.jsp").forward(request, response);

                                }

                            }

                        }else{
                            request.setAttribute("showInvalidIDError", true);
                            request.setAttribute("IDErrorMessage", "The Timetabler with the following ID : " + timetablerID + " is already existing");
                            request.getRequestDispatcher("addTimetabler.jsp").forward(request, response);

                        }
                    }else{
                        request.setAttribute("showInvalidIDError", true);
                        request.setAttribute("IDErrorMessage", "The Timetabler ID or Email Address entered is in incorrect format! Please check the provided examples...");
                        request.getRequestDispatcher("addTimetabler.jsp").forward(request, response);
                    }

                }
            }else if(action.equals("Delete")){
                System.out.println("Inside Timtabler Delete section .......");

                String timetablerID = request.getParameter("UserID");
                System.out.println("Deleting Timtabler ID : " + timetablerID);

                String adminID = request.getParameter("loggedInUserID");
                System.out.println("Deleted by : " +adminID);

                if(timetablerID != null && adminID != null ){
                    DAO dao = new DAO();
                    boolean isDeletedSuccessfully = dao.deleteSelectedTimetabler(timetablerID,adminID);
                    System.out.println("Timtabler Deletion Successful ? " + isDeletedSuccessfully);
                    if(isDeletedSuccessfully ==true){
                        request.setAttribute("showSuccessMessage", true);
                        request.setAttribute("SuccessMsgBody", "The Timetabler with the following ID : " + timetablerID + " is successfully deleted!");
                        request.getRequestDispatcher("viewEditDeleteTimetabler.jsp").forward(request, response);

                    }else{
                        request.setAttribute("showRegisterErrorMessage", true);
                        request.setAttribute("RegisterErrorMsgBody", "The Timetabler : "+ timetablerID + " details could not be deleted !");
                        request.getRequestDispatcher("viewEditDeleteTimetabler.jsp").forward(request, response);
                    }
                }else{
                    request.setAttribute("showRegisterErrorMessage", true);
                    request.setAttribute("RegisterErrorMsgBody", "Technical Error! Data could not be retrieved!");
                    request.getRequestDispatcher("viewEditDeleteTimetabler.jsp").forward(request, response);
                }

            } else if(action.equals("Edit")){
                System.out.println("Inside Lecturer Edit section .......");

                String selectedTimetablerID = request.getParameter("selectedTimetablerID");
                System.out.println("Selected Timetabler : " + selectedTimetablerID);

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

                if(selectedTimetablerID !=null && editedBy!=null && email != null && pwd != null && address != null && mobile != null && emMobile !=null){
                    DAO dao = new DAO();
                    boolean isUpdated = dao.updateTimetablerDetails(selectedTimetablerID,editedBy,email,pwd,address,mobile,emMobile);
                    System.out.println("Has Updated ? " + isUpdated);
                    if(isUpdated == true){
                        request.setAttribute("showSuccessMessage", true);
                        request.setAttribute("SuccessMsgBody", "The Timetabler with the following ID : " + selectedTimetablerID + " is successfully updated!");
                        request.getRequestDispatcher("viewEditDeleteTimetabler.jsp").forward(request, response);


                    }else{
                        request.setAttribute("showRegisterErrorMessage", true);
                        request.setAttribute("RegisterErrorMsgBody", "The Timetabler : "+ selectedTimetablerID + " details could not be updated !");
                        request.getRequestDispatcher("viewEditDeleteTimetabler.jsp").forward(request, response);

                    }
                }else{

                    request.setAttribute("showRegisterErrorMessage", true);
                    request.setAttribute("RegisterErrorMsgBody", "Technical Error! Data could not be retrieved!");
                    request.getRequestDispatcher("viewEditDeleteTimetabler.jsp").forward(request, response);

                }

            }
        }
    }
}
