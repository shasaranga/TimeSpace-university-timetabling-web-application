package StudentTimetablingSystem.controllers;

import StudentTimetablingSystem.model.*;
import StudentTimetablingSystem.model.Module;
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

    @WebServlet(name = "ModuleServlet", urlPatterns = {"/ModuleServlet"})
public class ModuleServlet extends HttpServlet {
    private final Gson gson = new Gson();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try(PrintWriter out = response.getWriter()){
            int courseID = parseInt(request.getParameter("valajax"));
            if(courseID != 0) {
                System.out.println("Got Course ID : "+ courseID );
                DAO dao = new DAO();
                List<Module> moduleList = dao.getModulesBasedOnCourseID(courseID);
                if(moduleList.size() != 0){
                    System.out.println("Got modules");

                    response.getWriter().write(" <option value='' disabled selected hidden>Select a Module</option>");
                    for(int i =0; i < moduleList.size(); i++){
                        response.getWriter().write("<option value ="+moduleList.get(i).getModuleID()+">"+moduleList.get(i).getModuleName()+"</option>");
                        System.out.println(moduleList.get(i).getModuleName());
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
        System.out.println(action);
        System.out.println(device);
        if(device != null && device != ""&& device.equals("Mobile")){
            if (action.equals("registration")) {
                System.out.println("Inside MOBILE Course Registration section .......");
                String courseId = request.getParameter("courseName");
                String moduleName = request.getParameter("moduleName");
                String moduleDescription = request.getParameter("courseDescription");
                String lecturerID = request.getParameter("lecturerName");
                String classID =  request.getParameter("classroom");
                String addedBy = request.getParameter("currentUserLoggedInAdmin");

                System.out.println("Selected course : " + courseId);
                System.out.println("New Module Name : " + moduleName);
                System.out.println("New Module Description : "+ moduleDescription);
                System.out.println("New Module Lecturer : " + lecturerID);
                System.out.println("Allocated Class : " + classID);
                System.out.println("Added by : " + addedBy);

                if(courseId != null && moduleName != null && moduleDescription != null && lecturerID != null && classID != null){
                    int courseIDInt = parseInt(courseId);
                    int classIDInt  = parseInt(classID);
                    DAO dao = new DAO();
                    boolean isAdded = dao.addNewModule(courseIDInt,moduleName,moduleDescription,lecturerID,classIDInt,addedBy);
                    if(isAdded == true){
                        MessageClass messageClass = new MessageClass(true, moduleName + " module is added successfully!");
                        String output = this.gson.toJson(messageClass);
                        PrintWriter out = response.getWriter();
                        response.setContentType("application/json");
                        response.setCharacterEncoding("UTF-8");
                        out.print(output);
                        out.flush();
                    }else{
                        MessageClass messageClass = new MessageClass(false, moduleName + " module could not be added!");
                        String output = this.gson.toJson(messageClass);
                        PrintWriter out = response.getWriter();
                        response.setContentType("application/json");
                        response.setCharacterEncoding("UTF-8");
                        out.print(output);
                        out.flush();

                    }
                }else{
                    MessageClass messageClass = new MessageClass(false, "Required Parameters are not available!");
                    String output = this.gson.toJson(messageClass);
                    PrintWriter out = response.getWriter();
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    out.print(output);
                    out.flush();
                }
            }
            else if(action.equals("getAvailableModules")){
                DAO dao = new DAO();
                List<Module> moduleList = dao.getModules();
                String output = this.gson.toJson(moduleList);
                PrintWriter out = response.getWriter();
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                out.print(output);
                out.flush();

            }
            else if(action.equals("Delete")){
                    String deletedBy = request.getParameter("loggedInUserID");
                String moduleID = request.getParameter("moduleID");
                String courseID = request.getParameter("courseID");
                System.out.println("Deleting Module ID : "+ moduleID);
                System.out.println("Deleting a module of the course :" + courseID);
                System.out.println("Deleted By : "+ deletedBy);

                if(courseID != null && deletedBy != null ){
                    int courseIDInt = parseInt(courseID);
                    int moduleIDInt = parseInt(moduleID);
                    DAO dao = new DAO();
                    boolean isDeleted = dao.deleteSelectedModule(courseIDInt,moduleIDInt, deletedBy);
                    System.out.println("Selected Module Deleted? "+ isDeleted);
                    if(isDeleted == true){
                        MessageClass messageClass = new MessageClass(true,  " Module is deleted successfully!");
                        String output = this.gson.toJson(messageClass);
                        PrintWriter out = response.getWriter();
                        response.setContentType("application/json");
                        response.setCharacterEncoding("UTF-8");
                        out.print(output);
                        out.flush();

                    }else{
                        MessageClass messageClass = new MessageClass(true, "Module could not be deleted!");
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
            else if(action.equals("Edit")){
                String editedBy = request.getParameter("AdminUserID");
                String moduleID = request.getParameter("selectedModuleID");
                String courseID = request.getParameter("selectedCourseID");
                String moduleName = request.getParameter("moduleName");
                String moduleDesc = request.getParameter("moduleDesc");
                String lecturerID = request.getParameter("lecturerName");
                String classroomID = request.getParameter("classRoom");
                System.out.println("Edited Module's Course ID : "+ courseID);
                System.out.println("Edited Module's ID : "+ moduleID);
                System.out.println("Edited Module's Name : "+ moduleName);
                System.out.println("Edited Module Description : "+ moduleDesc);
                System.out.println("Edited Module's lecturerID : "+ lecturerID);
                System.out.println("Edited Module's Classroom : "+ classroomID);
                System.out.println("Edited By : "+ editedBy);

                if(courseID != null  && moduleID != null  && moduleName != null && moduleDesc != null && lecturerID != null && classroomID != null && editedBy != null ){
                    int moduleIdInt = parseInt(moduleID);
                    int courseIdInt = parseInt(courseID);
                    int classroomIdInt = parseInt(classroomID);
                    DAO dao = new DAO();
                    boolean isEditSuccess =dao.updateSelectedModuleDetails(moduleIdInt,courseIdInt,moduleName,moduleDesc,lecturerID,classroomIdInt,editedBy);
                    System.out.println("Is Edited? "+isEditSuccess);
                    if(isEditSuccess == true){
                        MessageClass messageClass = new MessageClass(true,  "Module is edited successfully!");
                        String output = this.gson.toJson(messageClass);
                        PrintWriter out = response.getWriter();
                        response.setContentType("application/json");
                        response.setCharacterEncoding("UTF-8");
                        out.print(output);
                        out.flush();


                    }else{
                        MessageClass messageClass = new MessageClass(false,  "Module could not be edited!");
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
        }else{
            if(action != null) {
                if (action.equals("registration")) {
                    System.out.println("Inside Course Registration section .......");
                    String courseId = request.getParameter("courseName");
                    String moduleName = request.getParameter("moduleName");
                    String moduleDescription = request.getParameter("courseDescription");
                    String lecturerID = request.getParameter("lecturerName");
                    String classID =  request.getParameter("classroom");
                    String addedBy = request.getParameter("currentUserLoggedInAdmin");

                    System.out.println("Selected course : " + courseId);
                    System.out.println("New Module Name : " + moduleName);
                    System.out.println("New Module Description : "+ moduleDescription);
                    System.out.println("New Module Lecturer : " + lecturerID);
                    System.out.println("Allocated Class : " + classID);
                    System.out.println("Added by : " + addedBy);

                    if(courseId != null && moduleName != null && moduleDescription != null && lecturerID != null && classID != null){
                        int courseIDInt = parseInt(courseId);
                        int classIDInt  = parseInt(classID);
                        DAO dao = new DAO();
                        boolean isAdded = dao.addNewModule(courseIDInt,moduleName,moduleDescription,lecturerID,classIDInt,addedBy);
                        if(isAdded == true){
                            request.setAttribute("showSuccessMessage", true);
                            request.setAttribute("SuccessMsgBody", "The Module with the following name : " + moduleName + " is successfully added!");
                            request.getRequestDispatcher("addNewModule.jsp").forward(request, response);
                        }else{
                            request.setAttribute("showRegisterErrorMessage", true);
                            request.setAttribute("RegisterErrorMsgBody", "The registration of New Module : " + moduleName + " was unsuccessful!");
                            request.getRequestDispatcher("addNewModule.jsp").forward(request, response);

                        }
                    }else{
                        request.setAttribute("showInvalidIDError", true);
                        request.setAttribute("IDErrorMessage", "Technical Error! Data could not be retrieved!");
                        request.getRequestDispatcher("addNewModule.jsp").forward(request, response);
                    }
                }
                else if(action.equals("Delete")){
                    String deletedBy = request.getParameter("loggedInUserID");
                    String moduleID = request.getParameter("moduleID");
                    String courseID = request.getParameter("courseID");
                    System.out.println("Deleting Module ID : "+ moduleID);
                    System.out.println("Deleting a module of the course :" + courseID);
                    System.out.println("Deleted By : "+ deletedBy);

                    if(courseID != null && deletedBy != null ){
                        int courseIDInt = parseInt(courseID);
                        int moduleIDInt = parseInt(moduleID);
                        DAO dao = new DAO();
                        boolean isDeleted = dao.deleteSelectedModule(courseIDInt,moduleIDInt, deletedBy);
                        System.out.println("Selected Module Deleted? "+ isDeleted);
                        if(isDeleted == true){
                            request.setAttribute("showSuccessMessage", true);
                            request.setAttribute("SuccessMsgBody", "The Module with the following ID : " + moduleID + " is successfully deleted!");
                            request.getRequestDispatcher("viewEditDeleteModules.jsp").forward(request, response);

                        }else{
                            request.setAttribute("showErrorMessage", true);
                            request.setAttribute("ErrorMsgBody", "The Module with the following ID: "+ moduleID + " could not be deleted !");
                            request.getRequestDispatcher("viewEditDeleteModules.jsp").forward(request, response);

                        }
                    }else{
                        request.setAttribute("showErrorMessage", true);
                        request.setAttribute("ErrorMsgBody", "Technical Error! Data could not be retrieved!");
                        request.getRequestDispatcher("viewEditDeleteModules.jsp").forward(request, response);
                    }
                }
                else if(action.equals("Edit")){
                    String editedBy = request.getParameter("AdminUserID");
                    String moduleID = request.getParameter("selectedModuleID");
                    String courseID = request.getParameter("selectedCourseID");
                    String moduleName = request.getParameter("moduleName");
                    String moduleDesc = request.getParameter("moduleDesc");
                    String lecturerID = request.getParameter("lecturerName");
                    String classroomID = request.getParameter("classRoom");
                    System.out.println("Edited Module's Course ID : "+ courseID);
                    System.out.println("Edited Module's ID : "+ moduleID);
                    System.out.println("Edited Module's Name : "+ moduleName);
                    System.out.println("Edited Module Description : "+ moduleDesc);
                    System.out.println("Edited Module's lecturerID : "+ lecturerID);
                    System.out.println("Edited Module's Classroom : "+ classroomID);
                    System.out.println("Edited By : "+ editedBy);

                    if(courseID != null  && moduleID != null  && moduleName != null && moduleDesc != null && lecturerID != null && classroomID != null && editedBy != null ){
                        int moduleIdInt = parseInt(moduleID);
                        int courseIdInt = parseInt(courseID);
                        int classroomIdInt = parseInt(classroomID);
                        DAO dao = new DAO();
                        boolean isEditSuccess =dao.updateSelectedModuleDetails(moduleIdInt,courseIdInt,moduleName,moduleDesc,lecturerID,classroomIdInt,editedBy);
                        System.out.println("Is Edited? "+isEditSuccess);
                        if(isEditSuccess == true){
                            request.setAttribute("showSuccessMessage", true);
                            request.setAttribute("SuccessMsgBody", "The Module with the following ID : " + moduleID + " is successfully updated!");
                            request.getRequestDispatcher("viewEditDeleteModules.jsp").forward(request, response);

                        }else{
                            request.setAttribute("showErrorMessage", true);
                            request.setAttribute("ErrorMsgBody", "The Module with the following ID: "+ moduleID + " could not be updated !");
                            request.getRequestDispatcher("viewEditDeleteModules.jsp").forward(request, response);

                        }
                    }else{
                        request.setAttribute("showErrorMessage", true);
                        request.setAttribute("ErrorMsgBody", "Technical Error! Data could not be retrieved!");
                        request.getRequestDispatcher("viewEditDeleteModules.jsp").forward(request, response);
                    }
                }

            }
        }


    }
}
