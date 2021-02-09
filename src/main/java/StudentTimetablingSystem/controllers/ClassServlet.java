package StudentTimetablingSystem.controllers;

import StudentTimetablingSystem.model.Classroom;
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

@WebServlet(name = "ClassServlet", urlPatterns = {"/ClassServlet"})
public class ClassServlet extends HttpServlet {
    private final Gson gson = new Gson();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String action  = request.getParameter("action");
        String device = request.getParameter("device");

        if(device.equals("Mobile")){
            if(action !=null){
                if(action.equals("getAvailableClassrooms")){
                    DAO dao = new DAO();
                    List<Classroom> listClassroom = dao.getAvailableClassrooms();
                    if(listClassroom != null){
                        String output = this.gson.toJson(listClassroom);
                        PrintWriter out = response.getWriter();
                        response.setContentType("application/json");
                        response.setCharacterEncoding("UTF-8");
                        out.print(output);
                        out.flush();
                    }else{
                        System.out.println("The classrooms could not be fetched!");
                    }
                }
                else if(action.equals("getAllClassrooms")){
                    DAO dao = new DAO();
                    List<Classroom> listClassroom = dao.getAllClassrooms();
                    if(listClassroom != null){
                        String output = this.gson.toJson(listClassroom);
                        PrintWriter out = response.getWriter();
                        response.setContentType("application/json");
                        response.setCharacterEncoding("UTF-8");
                        out.print(output);
                        out.flush();
                    }else{
                        System.out.println("The classrooms could not be fetched!");
                    }
                }
                else if(action.equals("getAvailableClassroomsForModules")){
                    DAO dao = new DAO();
                    List<Classroom> classroomsForModulesList = dao.getAvailableClassroomsForModules();
                    String output = this.gson.toJson(classroomsForModulesList);
                    PrintWriter out = response.getWriter();
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    out.print(output);
                    out.flush();

                }
                else if (action.equals("registration")) {
                    System.out.println("Inside Mobile Class Adding section .......");
                    String className = request.getParameter("className");
                    String floorLevel = request.getParameter("floorLevel");
                    String isAllocatedForModule = request.getParameter("isAllocatedForModule");
                    String addedBy = request.getParameter("currentUserLoggedInAdmin");

                    System.out.println("New Class Name"+ className);
                    System.out.println("New Class floor level"+ floorLevel);
                    System.out.println("New Class allocated for module or not "+ isAllocatedForModule);
                    System.out.println("Added by : " + addedBy);
                    boolean isAllocated;
                    int allocationIsThere;
                    if(isAllocatedForModule.equals("true")){
                        isAllocated = true;
                        allocationIsThere = 1;
                    }else{
                        isAllocated= false;
                        allocationIsThere =0;
                    }
                    System.out.println("Is Allocated for a module : " +isAllocated);
                    if(className != null && floorLevel != null && isAllocatedForModule != null){
                        int floorInt = parseInt(floorLevel);
                        DAO dao = new DAO();
                        boolean isAdded = dao.addNewClass(className,floorInt,allocationIsThere,addedBy);
                        if(isAdded == true){
                            MessageClass messageClass = new MessageClass(true,  "New classroom is added successfully!");
                            String output = this.gson.toJson(messageClass);
                            PrintWriter out = response.getWriter();
                            response.setContentType("application/json");
                            response.setCharacterEncoding("UTF-8");
                            out.print(output);
                            out.flush();
                        }else{
                            MessageClass messageClass = new MessageClass(false,  "New classroom could not be added!");
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
                else if (action.equals("Delete")){
                    System.out.println("Inside MOBILE Class Deleting section .......");
                    String classroomID = request.getParameter("classroomID");
                    String deletedBy = request.getParameter("loggedInUserID");
                    if(classroomID != null && deletedBy !=null){
                        int classID = parseInt(classroomID);
                        DAO dao = new DAO();
                        boolean isSafe = dao.verifySelectedClassroomSafeToDelete(classID);
                        System.out.println("Is Safe to delete selected classroom? " + isSafe);
                        if(isSafe ==true){
                            DAO dao2 = new DAO();
                            boolean isDeleted = dao2.deleteSelectedClassroom(classID,deletedBy);
                            System.out.println("Is Selected Class Deleted? "+ isDeleted);
                            if(isDeleted == true){
                                MessageClass messageClass = new MessageClass(true,  "Classroom is deleted successfully!");
                                String output = this.gson.toJson(messageClass);
                                PrintWriter out = response.getWriter();
                                response.setContentType("application/json");
                                response.setCharacterEncoding("UTF-8");
                                out.print(output);
                                out.flush();
                            }else{
                                MessageClass messageClass = new MessageClass(false,  "Classroom could not be deleted!");
                                String output = this.gson.toJson(messageClass);
                                PrintWriter out = response.getWriter();
                                response.setContentType("application/json");
                                response.setCharacterEncoding("UTF-8");
                                out.print(output);
                                out.flush();
                            }
                        }else{
                            MessageClass messageClass = new MessageClass(false,  "The class is allocated for a module! Please update the module class!");
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
                else if(action.equals("Edit")){
                    System.out.println("Inside MOBILE Class EDITING section .......");
                    String classroomID = request.getParameter("selectedClassID");
                    String classroomName = request.getParameter("className");
                        String isAllocatedString = request.getParameter("selectedClassIDIsAllocated");
                    String floorLevel = request.getParameter("floorLevel");
                        String addedBy = request.getParameter("AdminUserID");
                    if(classroomID != null && classroomName != null && floorLevel != null && addedBy !=null && isAllocatedString != null){
                        int classID = parseInt(classroomID);
                        int floorLevelInt = parseInt(floorLevel);
                        int isAllocatedInt = parseInt(isAllocatedString);

                        DAO dao = new DAO();
                        boolean isSuccess = dao.updateSelectedClassroom(classID,classroomName,floorLevelInt,isAllocatedInt,addedBy);
                        if(isSuccess == true){
                            MessageClass messageClass = new MessageClass(true,  "Classroom is edited successfully!");
                            String output = this.gson.toJson(messageClass);
                            PrintWriter out = response.getWriter();
                            response.setContentType("application/json");
                            response.setCharacterEncoding("UTF-8");
                            out.print(output);
                            out.flush();
                        }else{
                            MessageClass messageClass = new MessageClass(false,  "Classroom could not be edited!");
                            String output = this.gson.toJson(messageClass);
                            PrintWriter out = response.getWriter();
                            response.setContentType("application/json");
                            response.setCharacterEncoding("UTF-8");
                            out.print(output);
                            out.flush();
                        }

                    }else{
                        MessageClass messageClass = new MessageClass(false,  "Required Data not found!");
                        String output = this.gson.toJson(messageClass);
                        PrintWriter out = response.getWriter();
                        response.setContentType("application/json");
                        response.setCharacterEncoding("UTF-8");
                        out.print(output);
                        out.flush();
                    }
                }
            }
        }else{
            if(action != null) {
                if (action.equals("registration")) {
                    System.out.println("Inside Class Adding section .......");
                    String className = request.getParameter("className");
                    String floorLevel = request.getParameter("floorLevel");
                    String isAllocatedForModule = request.getParameter("isAllocatedForModule");
                    String addedBy = request.getParameter("currentUserLoggedInAdmin");

                    System.out.println("New Class Name"+ className);
                    System.out.println("New Class floor level"+ floorLevel);
                    System.out.println("New Class allocated for module or not "+ isAllocatedForModule);
                    System.out.println("Added by : " + addedBy);
                    boolean isAllocated;
                    int allocationIsThere;
                    if(isAllocatedForModule.equals("on")){
                        isAllocated = true;
                        allocationIsThere = 1;
                    }else{
                        isAllocated= false;
                        allocationIsThere =0;
                    }
                    System.out.println("Is Allocated for a module : " +isAllocated);
                    if(className != null && floorLevel != null && isAllocatedForModule != null){
                        int floorInt = parseInt(floorLevel);
                        DAO dao = new DAO();
                        boolean isAdded = dao.addNewClass(className,floorInt,allocationIsThere,addedBy);
                        if(isAdded == true){
                            request.setAttribute("showSuccessMessage", true);
                            request.setAttribute("SuccessMsgBody", "The Classroom with the following name : " + className + " is successfully added!");
                            request.getRequestDispatcher("addClass.jsp").forward(request, response);
                        }else{
                            request.setAttribute("showRegisterErrorMessage", true);
                            request.setAttribute("RegisterErrorMsgBody", "The registration of New Classroom : " + className + " was unsuccessful!");
                            request.getRequestDispatcher("addClass.jsp").forward(request, response);

                        }
                    }else{
                        request.setAttribute("showInvalidIDError", true);
                        request.setAttribute("IDErrorMessage", "Technical Error! Data could not be retrieved!");
                        request.getRequestDispatcher("addClass.jsp").forward(request, response);
                    }
                }
                else if (action.equals("Delete")){
                    System.out.println("Inside Class Deleting section .......");
                    String classroomID = request.getParameter("classroomID");
                    String deletedBy = request.getParameter("loggedInUserID");
                    if(classroomID != null && deletedBy !=null){
                        int classID = parseInt(classroomID);
                        DAO dao = new DAO();
                        boolean isSafe = dao.verifySelectedClassroomSafeToDelete(classID);
                        System.out.println("Is Safe to delete selected classroom? " + isSafe);
                        if(isSafe ==true){
                            DAO dao2 = new DAO();
                            boolean isDeleted = dao2.deleteSelectedClassroom(classID,deletedBy);
                            System.out.println("Is Selected Class Deleted? "+ isDeleted);
                            if(isDeleted == true){
                                request.setAttribute("showSuccessMessage", true);
                                request.setAttribute("SuccessMsgBody", "The selected classroom : " + classID + " is deleted successfully!");
                                request.getRequestDispatcher("viewEditDeleteClassrooms.jsp").forward(request, response);
                            }else{
                                request.setAttribute("showErrorMessage", true);
                                request.setAttribute("ErrorMsgBody", "The selected classroom : " + classID + " could not be deleted!");
                                request.getRequestDispatcher("viewEditDeleteClassrooms.jsp").forward(request, response);
                            }
                        }else{
                            request.setAttribute("showErrorMessage", true);
                            request.setAttribute("ErrorMsgBody", "The selected classroom is allocated for a module! Please assign a class to the module before deleting the particular classroom!");
                            request.getRequestDispatcher("viewEditDeleteClassrooms.jsp").forward(request, response);
                        }
                    }else{
                        request.setAttribute("showErrorMessage", true);
                        request.setAttribute("ErrorMsgBody", "Technical Error! Data could not be retrieved!");
                        request.getRequestDispatcher("viewEditDeleteClassrooms.jsp").forward(request, response);
                    }
                }
                else if(action.equals("Edit")){
                    System.out.println("Inside Class Editing section .......");
                    String classroomID = request.getParameter("selectedClassID");
                    String classroomName = request.getParameter("className");
                    String isAllocatedString = request.getParameter("selectedClassIDIsAllocated");
                    String floorLevel = request.getParameter("floorLevel");
                    String addedBy = request.getParameter("AdminUserID");
                    if(classroomID != null && classroomName != null && floorLevel != null && addedBy !=null && isAllocatedString != null){
                        int classID = parseInt(classroomID);
                        int floorLevelInt = parseInt(floorLevel);
                        int isAllocatedInt = parseInt(isAllocatedString);
                        DAO dao = new DAO();
                        boolean isSuccess = dao.updateSelectedClassroom(classID,classroomName,floorLevelInt,isAllocatedInt,addedBy);
                        if(isSuccess == true){
                            request.setAttribute("showSuccessMessage", true);
                            request.setAttribute("SuccessMsgBody", "The selected classroom : " + classID + " is updated successfully!");
                            request.getRequestDispatcher("viewEditDeleteClassrooms.jsp").forward(request, response);
                        }else{
                            request.setAttribute("showErrorMessage", true);
                            request.setAttribute("ErrorMsgBody", "The selected classroom : " + classID + " could not be updated!");
                            request.getRequestDispatcher("viewEditDeleteClassrooms.jsp").forward(request, response);
                        }
                    }else{
                        request.setAttribute("showErrorMessage", true);
                        request.setAttribute("ErrorMsgBody", "Technical Error! Data could not be retrieved!");
                        request.getRequestDispatcher("viewEditDeleteClassrooms.jsp").forward(request, response);
                    }
                }
            }
        }
    }
}
