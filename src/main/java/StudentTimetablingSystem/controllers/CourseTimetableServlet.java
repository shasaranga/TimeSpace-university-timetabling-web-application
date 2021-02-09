package StudentTimetablingSystem.controllers;

import StudentTimetablingSystem.model.CourseTimetable;
import StudentTimetablingSystem.model.DAO;
import com.google.gson.Gson;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

import static java.lang.Integer.parseInt;

@WebServlet(name = "CourseTimetableServlet", urlPatterns = {"/CourseTimetableServlet"})
public class CourseTimetableServlet extends HttpServlet {
    private final Gson gson = new Gson();
    List<CourseTimetable> recordList;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println("Action : "+action);
        String viewType = request.getParameter("viewType");
        String device = request.getParameter("device");
        String userType = request.getParameter("userType");
        if(viewType != null && action !=null){

        }
        if(device != null && device != "" && device.equals("Mobile")){
            if(viewType.equals("commonStudentView")){
                String badgeID = request.getParameter("badge");
                String courseID = request.getParameter("courseName");
                if(courseID != null && badgeID != null){
                    int coursIDInt = parseInt(courseID);
                    int badgeIDint = parseInt(badgeID);
                    DAO daoStud = new DAO();
                    recordList = daoStud.getStudentTimeTable(coursIDInt,badgeIDint);
                    if(recordList != null){

                        String output = this.gson.toJson(recordList);
                        PrintWriter out = response.getWriter();
                        response.setContentType("application/json");
                        response.setCharacterEncoding("UTF-8");
                        out.print(output);
                        out.flush();
                    }else{
                        System.out.println("No timetable found for the following search criteria");
                    }
                }else{
                    System.out.println("Data not retrieved!");
                }
            }
            else if(viewType.equals("commonLecturerView")){

                String lecturerID = request.getParameter("lecturerID");
                System.out.println("Lecturer ID : "+ lecturerID);
                DAO daoLecturer = new DAO();
                List<CourseTimetable> timetableRecordList = daoLecturer.getLecturerTimeTable(lecturerID);
                if(timetableRecordList != null){
                    String output = this.gson.toJson(timetableRecordList);
                    PrintWriter out = response.getWriter();
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    out.print(output);
                    out.flush();
                }else{
                    System.out.println("No timetable found");
                }
            }
        }else{
            if(action.equals("createTimetable")){
                String time0 = request.getParameter("time-0");
                String mon0 =  request.getParameter("mon-0");
                String tue0 = request.getParameter("tue-0");
                String wed0 = request.getParameter("wed-0");
                String thur0 = request.getParameter("thur-0");
                String fri0 = request.getParameter("fri-0");

                String selectedCourseID = request.getParameter("courseName");
                String selectedBadgeID = request.getParameter("badge");
                String addedBy = request.getParameter("loggedTimetabler");

                boolean allRecordsVerified = false;

                if(selectedCourseID != null && selectedBadgeID != null && addedBy != null ){

                    int courseIDInt = parseInt(selectedCourseID);
                    int badgeIDInt = parseInt(selectedBadgeID);
                    DAO daoVerifier = new DAO();
                    boolean badgeAlreadyHaveATimtable = daoVerifier.verifyBadgeAlreadyHaveATimetable(courseIDInt,badgeIDInt);
                    if(badgeAlreadyHaveATimtable == true){
                        request.setAttribute("showErrorMessage", true);
                        request.setAttribute("ErrorMsgBody", "The selected Badge already have a timetable! Please delete the current one if need to create a new one...");
                        request.getRequestDispatcher("createCourseBadgeTimetable.jsp").forward(request, response);
                    }else{
                        //Verifying Records
                        for(int y=0; y < 8; y++){
                            String startTimeName = "startTime-"+y;
                            String duration =  "duration-"+y;
                            String monName = "mon-"+y;
                            String tueName = "tue-"+y;
                            String wedName = "wed-"+y;
                            String thurName = "thur-"+y;
                            String friName = "fri-"+y;

                            String startTime = request.getParameter(startTimeName);
                            String durationValue = request.getParameter(duration);
                            System.out.println("Start Time before if :  " + startTime);
                            System.out.println("Duration before if :  " + durationValue);

                            if(startTime !=null && durationValue !=null){
                                if(startTime.length()!=0 && durationValue.length()!=0){
                                    System.out.println("\n");
                                    System.out.println("Inside IF Start time "+startTime);
                                    System.out.println("Inside IF Duration "+durationValue);

                                    int durationINT = parseInt(durationValue);

                                    LocalTime startTimeLocalFormat = LocalTime.parse(startTime);
                                    System.out.println("Parsed Start Time Local: " + startTimeLocalFormat);

                                    LocalTime endTimeLocalFormat = startTimeLocalFormat.plusHours(durationINT);
                                    System.out.println("Parsed End Time Local : " + endTimeLocalFormat);
                                    Time startTimeLocal  = Time.valueOf(startTimeLocalFormat);
                                    System.out.println("Parsed Start Time : " + startTimeLocal);

                                    Time endTimeLocal  = Time.valueOf(endTimeLocalFormat);
                                    System.out.println("Parsed End Time : " + endTimeLocal);

                                    System.out.println("Duration : " +duration);
                                    String mondayModule = request.getParameter(monName);
                                    String tuesdayModule = request.getParameter(tueName);
                                    String wedModule = request.getParameter(wedName);
                                    String thurModule =  request.getParameter(thurName);
                                    String friModule = request.getParameter(friName);


                                    if(mondayModule != null) {
                                        DAO dao = new DAO();
                                        int monModuleID = parseInt(mondayModule);
                                        boolean isAreadyAvailable = dao.verifyNewTimetableRecord(courseIDInt,badgeIDInt,"Monday",durationINT,startTimeLocal,endTimeLocal, monModuleID);
                                        if(isAreadyAvailable == true){
                                            allRecordsVerified = false;
                                            System.out.println("Monday Record : "+ y + " already available");
                                            break;
                                        }else{
                                            allRecordsVerified = true;

                                        }
                                    }

                                    if(tuesdayModule != null) {
                                        DAO dao1 = new DAO();
                                        int tueModuleID = parseInt(tuesdayModule);
                                        boolean isAreadyAvailable = dao1.verifyNewTimetableRecord(courseIDInt,badgeIDInt,"Tuesday",durationINT,startTimeLocal,endTimeLocal, tueModuleID);
                                        if(isAreadyAvailable == true){
                                            allRecordsVerified = false;
                                            System.out.println("Tuesday Record : "+ y + " already available");
                                            break;
                                        }else{
                                            allRecordsVerified = true;

                                        }
                                    }

                                    if(wedModule != null) {
                                        DAO dao2 = new DAO();
                                        int wedModuleID = parseInt(wedModule);
                                        boolean isAreadyAvailable = dao2.verifyNewTimetableRecord(courseIDInt,badgeIDInt,"Wednesday",durationINT,startTimeLocal,endTimeLocal, wedModuleID);
                                        if(isAreadyAvailable == true){
                                            allRecordsVerified = false;
                                            System.out.println("Wednesday Record : "+ y + " already available");
                                            break;
                                        }else{
                                            allRecordsVerified = true;

                                        }
                                    }

                                    if(thurModule != null) {
                                        DAO dao3 = new DAO();
                                        int thurModuleID = parseInt(thurModule);
                                        boolean isAreadyAvailable = dao3.verifyNewTimetableRecord(courseIDInt,badgeIDInt,"Thursday",durationINT,startTimeLocal,endTimeLocal, thurModuleID);
                                        if(isAreadyAvailable == true){
                                            allRecordsVerified = false;
                                            System.out.println("Thursday Record : "+ y + " already available");
                                            break;
                                        }else{
                                            allRecordsVerified = true;

                                        }
                                    }

                                    if(friModule != null) {
                                        DAO dao4 = new DAO();
                                        int friModuleID = parseInt(friModule);
                                        boolean isAreadyAvailable = dao4.verifyNewTimetableRecord(courseIDInt,badgeIDInt,"Friday",durationINT,startTimeLocal,endTimeLocal, friModuleID);
                                        if(isAreadyAvailable == true){
                                            allRecordsVerified = false;
                                            System.out.println("Friday Record : "+ y + " already available");
                                            break;
                                        }else{
                                            allRecordsVerified = true;

                                        }
                                    }
                                }
                            }


                        }
                        System.out.println("All Records Verified ? "+allRecordsVerified);
                        if(allRecordsVerified == true){
                            //Inserting Records
                            for(int i=0; i < 8; i++){
                                String startTimeName = "startTime-"+i;
                                String duration =  "duration-"+i;
                                String monName = "mon-"+i;
                                String tueName = "tue-"+i;
                                String wedName = "wed-"+i;
                                String thurName = "thur-"+i;
                                String friName = "fri-"+i;

                                String startTime = request.getParameter(startTimeName);
                                String durationValue = request.getParameter(duration);
                                System.out.println("Start Time before if :  " + startTime);
                                System.out.println("Duration before if :  " + durationValue);

                                if(startTime !=null && durationValue !=null){
                                    if(startTime.length()!=0 && durationValue.length()!=0){
                                        System.out.println("\n");
                                        System.out.println("Inside IF Start time "+startTime);
                                        System.out.println("Inside IF Duration "+durationValue);

                                        int durationINT = parseInt(durationValue);

                                        LocalTime startTimeLocalFormat = LocalTime.parse(startTime);
                                        System.out.println("Parsed Start Time Local: " + startTimeLocalFormat);

                                        LocalTime endTimeLocalFormat = startTimeLocalFormat.plusHours(durationINT);
                                        System.out.println("Parsed End Time Local : " + endTimeLocalFormat);
                                        Time startTimeLocal  = Time.valueOf(startTimeLocalFormat);
                                        System.out.println("Parsed Start Time : " + startTimeLocal);

                                        Time endTimeLocal  = Time.valueOf(endTimeLocalFormat);
                                        System.out.println("Parsed End Time : " + endTimeLocal);

                                        System.out.println("Duration : " +duration);
                                        String mondayModule = request.getParameter(monName);
                                        String tuesdayModule = request.getParameter(tueName);
                                        String wedModule = request.getParameter(wedName);
                                        String thurModule =  request.getParameter(thurName);
                                        String friModule = request.getParameter(friName);

                                        if(mondayModule != null){
                                            DAO dao = new DAO();
                                            int monModuleID = parseInt(mondayModule);
                                            boolean isAdded = dao.insertTimetableRecord(courseIDInt,badgeIDInt,1,"Monday",durationINT,startTimeLocal,endTimeLocal,monModuleID,addedBy);
                                            System.out.println(isAdded);
                                            if(isAdded == true){
                                                request.setAttribute("showSuccessMessage", true);
                                                request.setAttribute("SuccessMsgBody", "The Timetable is created successfully!");

                                            } else{
                                                request.setAttribute("showErrorMessage", true);
                                                request.setAttribute("ErrorMsgBody", "The Timetable could not be created successfully!");

                                            }
                                        }
                                        if(tuesdayModule != null){
                                            DAO dao1 = new DAO();
                                            int tueModuleID = parseInt(tuesdayModule);
                                            boolean isAdded  = dao1.insertTimetableRecord(courseIDInt,badgeIDInt,2,"Tuesday",durationINT,startTimeLocal,endTimeLocal,tueModuleID,addedBy);
                                            System.out.println(isAdded);
                                            if(isAdded == true){
                                                request.setAttribute("showSuccessMessage", true);
                                                request.setAttribute("SuccessMsgBody", "The Timetable is created successfully!");

                                            } else{
                                                request.setAttribute("showErrorMessage", true);
                                                request.setAttribute("ErrorMsgBody", "The Timetable could not be created successfully!");

                                            }

                                        }
                                        if(wedModule != null){
                                            DAO dao2 = new DAO();
                                            int wedModuleID = parseInt(wedModule);
                                            boolean isAdded  = dao2.insertTimetableRecord(courseIDInt,badgeIDInt,3,"Wednesday",durationINT,startTimeLocal,endTimeLocal,wedModuleID,addedBy);
                                            System.out.println(isAdded);
                                            if(isAdded == true){
                                                request.setAttribute("showSuccessMessage", true);
                                                request.setAttribute("SuccessMsgBody", "The Timetable is created successfully!");

                                            } else{
                                                request.setAttribute("showErrorMessage", true);
                                                request.setAttribute("ErrorMsgBody", "The Timetable could not be created successfully!");

                                            }

                                        }
                                        if(thurModule != null){
                                            DAO dao3 = new DAO();
                                            int thurModuleID = parseInt(thurModule);
                                            boolean isAdded  = dao3.insertTimetableRecord(courseIDInt,badgeIDInt,4,"Thursday",durationINT,startTimeLocal,endTimeLocal,thurModuleID,addedBy);
                                            System.out.println(isAdded);
                                            if(isAdded == true){
                                                request.setAttribute("showSuccessMessage", true);
                                                request.setAttribute("SuccessMsgBody", "The Timetable is created successfully!");

                                            } else{
                                                request.setAttribute("showErrorMessage", true);
                                                request.setAttribute("ErrorMsgBody", "The Timetable could not be created successfully!");
                                            }
                                        }
                                        if(friModule != null){
                                            DAO dao4 = new DAO();
                                            int friModuleID = parseInt(friModule);
                                            boolean isAdded  = dao4.insertTimetableRecord(courseIDInt,badgeIDInt,5,"Friday",durationINT,startTimeLocal,endTimeLocal,friModuleID,addedBy);
                                            System.out.println(isAdded);
                                            if(isAdded == true){
                                                request.setAttribute("showSuccessMessage", true);
                                                request.setAttribute("SuccessMsgBody", "The Timetable is created successfully!");

                                            } else{
                                                request.setAttribute("showErrorMessage", true);
                                                request.setAttribute("ErrorMsgBody", "The Timetable could not be created successfully!");

                                            }
                                        }
                                    }
                                }

                            }
                            request.getRequestDispatcher("createCourseBadgeTimetable.jsp").forward(request, response);
                        }else{
                            request.setAttribute("showErrorMessage", true);
                            request.setAttribute("ErrorMsgBody", "There are some records with same time and duration on the same day");
                            request.getRequestDispatcher("createCourseBadgeTimetable.jsp").forward(request, response);
                        }
                    }
                }
            }
            else if(action.equals("search")){
                if(viewType!=null){
                    if(userType != null){
                        if(viewType.equals("timetablerLecturerView")){
                            if(userType.equals("lecturer")){
                                String lecturerID = request.getParameter("lecturerID");
                                System.out.println("Lecturer ID : "+ lecturerID);
                                if(lecturerID != null){
                                    DAO daoLecturer = new DAO();
                                    List<CourseTimetable> timetableRecordList = daoLecturer.getLecturerTimeTable(lecturerID);
                                    if(timetableRecordList != null){
                                        request.setAttribute("showTable", true);
                                        request.setAttribute("searchResult", timetableRecordList);
                                        request.getRequestDispatcher("searchLecturerTimeTable.jsp").forward(request, response);
                                    }else{
                                        request.setAttribute("showErrorMessage", true);
                                        request.setAttribute("ErrorMsgBody", "No timetable found for the following search criteria");
                                        request.getRequestDispatcher("searchLecturerTimeTable.jsp").forward(request, response);
                                    }
                                }else{
                                    request.setAttribute("showErrorMessage", true);
                                    request.setAttribute("ErrorMsgBody", "Lecturer ID could not be retrieved!");
                                    request.getRequestDispatcher("searchLecturerTimeTable.jsp").forward(request, response);
                                }
                            }

                        }
                        else if(viewType.equals("timetablerStudentView")){
                            if(userType.equals("student")){
                                String badgeID = request.getParameter("badge");
                                String courseID = request.getParameter("courseName");
                                if(courseID != null && badgeID != null){
                                    int coursIDInt = parseInt(courseID);
                                    int badgeIDint = parseInt(badgeID);
                                    DAO daoStud = new DAO();
                                    List<CourseTimetable> timetableRecordList = daoStud.getStudentTimeTable(coursIDInt,badgeIDint);
                                    if(timetableRecordList != null && timetableRecordList.size() !=0){
                                        request.setAttribute("showTable", true);
                                        request.setAttribute("searchResult", timetableRecordList);
                                        request.setAttribute("badgeIDVal", timetableRecordList.get(0).getBadgeID());
                                        request.setAttribute("courseIDVal", timetableRecordList.get(0).getCourseID());
                                        request.getRequestDispatcher("searchStudentTimeTable.jsp").forward(request, response);
                                    }else{
                                        request.setAttribute("showErrorMessage", true);
                                        request.setAttribute("ErrorMsgBody", "No timetable found for the following search criteria");
                                        request.getRequestDispatcher("searchStudentTimeTable.jsp").forward(request, response);
                                    }
                                }else{
                                    request.setAttribute("showErrorMessage", true);
                                    request.setAttribute("ErrorMsgBody", "Data not retrieved!");
                                    request.getRequestDispatcher("searchStudentTimeTable.jsp").forward(request, response);
                                }
                            }
                        }
                        else if(viewType.equals("commonLecturerView")){
                            if(userType.equals("lecturer")){
                                String lecturerID = request.getParameter("lecturerID");
                                System.out.println("Lecturer ID : "+ lecturerID);
                                if(lecturerID != null){
                                    DAO daoLecturer = new DAO();
                                    List<CourseTimetable> timetableRecordList = daoLecturer.getLecturerTimeTable(lecturerID);
                                    if(timetableRecordList != null){
                                        request.setAttribute("showTable", true);
                                        request.setAttribute("searchResult", timetableRecordList);
                                        request.getRequestDispatcher("searchLecturerTimeTableForLecturer.jsp").forward(request, response);
                                    }else{
                                        request.setAttribute("showErrorMessage", true);
                                        request.setAttribute("ErrorMsgBody", "No timetable found for the following search criteria");
                                        request.getRequestDispatcher("searchLecturerTimeTableForLecturer.jsp").forward(request, response);
                                    }
                                }else{
                                    request.setAttribute("showErrorMessage", true);
                                    request.setAttribute("ErrorMsgBody", "Lecturer ID could not be retrieved!");
                                    request.getRequestDispatcher("searchLecturerTimeTableForLecturer.jsp").forward(request, response);
                                }
                            }
                        }
                        else if(viewType.equals("commonStudentView")){
                            if(userType.equals("student")){
                                String badgeID = request.getParameter("badge");
                                String courseID = request.getParameter("courseName");
                                if(courseID != null && badgeID != null){
                                    int coursIDInt = parseInt(courseID);
                                    int badgeIDint = parseInt(badgeID);
                                    DAO daoStud = new DAO();
                                    List<CourseTimetable> timetableRecordList = daoStud.getStudentTimeTable(coursIDInt,badgeIDint);
                                    if(timetableRecordList != null){
                                        request.setAttribute("showTable", true);
                                        request.setAttribute("searchResult", timetableRecordList);
                                        request.getRequestDispatcher("searchStudentTimeTableForStudent.jsp").forward(request, response);
                                    }else{
                                        request.setAttribute("showErrorMessage", true);
                                        request.setAttribute("ErrorMsgBody", "No timetable found for the following search criteria");
                                        request.getRequestDispatcher("searchStudentTimeTableForStudent.jsp").forward(request, response);
                                    }
                                }else{
                                    request.setAttribute("showErrorMessage", true);
                                    request.setAttribute("ErrorMsgBody", "Data not retrieved!");
                                    request.getRequestDispatcher("searchStudentTimeTableForStudent.jsp").forward(request, response);
                                }
                            }
                        }
                        else if(viewType.equals("commonStudentViewForLecturer")){
                            if(userType.equals("student")){
                                String badgeID = request.getParameter("badge");
                                String courseID = request.getParameter("courseName");
                                if(courseID != null && badgeID != null){
                                    int coursIDInt = parseInt(courseID);
                                    int badgeIDint = parseInt(badgeID);
                                    DAO daoStud = new DAO();
                                    List<CourseTimetable> timetableRecordList = daoStud.getStudentTimeTable(coursIDInt,badgeIDint);
                                    if(timetableRecordList != null){
                                        request.setAttribute("showTable", true);
                                        request.setAttribute("searchResult", timetableRecordList);
                                        request.getRequestDispatcher("searchStudentTimeTableForLecturer.jsp").forward(request, response);
                                    }else{
                                        request.setAttribute("showErrorMessage", true);
                                        request.setAttribute("ErrorMsgBody", "No timetable found for the following search criteria");
                                        request.getRequestDispatcher("searchStudentTimeTableForLecturer.jsp").forward(request, response);
                                    }
                                }else{
                                    request.setAttribute("showErrorMessage", true);
                                    request.setAttribute("ErrorMsgBody", "Data not retrieved!");
                                    request.getRequestDispatcher("searchStudentTimeTableForLecturer.jsp").forward(request, response);
                                }
                            }
                        }
                        else if(viewType.equals("commonLecturerViewForStudent")){
                            if(userType.equals("lecturer")){
                                String lecturerID = request.getParameter("lecturerID");
                                System.out.println("Lecturer ID : "+ lecturerID);
                                if(lecturerID != null){
                                    DAO daoLecturer = new DAO();
                                    List<CourseTimetable> timetableRecordList = daoLecturer.getLecturerTimeTable(lecturerID);
                                    if(timetableRecordList != null){
                                        request.setAttribute("showTable", true);
                                        request.setAttribute("searchResult", timetableRecordList);
                                        request.getRequestDispatcher("searchLecturerTimeTableForStudent.jsp").forward(request, response);
                                    }else{
                                        request.setAttribute("showErrorMessage", true);
                                        request.setAttribute("ErrorMsgBody", "No timetable found for the following search criteria");
                                        request.getRequestDispatcher("searchLecturerTimeTableForStudent.jsp").forward(request, response);
                                    }
                                }else{
                                    request.setAttribute("showErrorMessage", true);
                                    request.setAttribute("ErrorMsgBody", "Lecturer ID could not be retrieved!");
                                    request.getRequestDispatcher("searchLecturerTimeTableForStudent.jsp").forward(request, response);
                                }
                            }

                        }
                        else if(viewType.equals("commonLecturerViewForAdmin")){
                            if(userType.equals("lecturer")){
                                String lecturerID = request.getParameter("lecturerID");
                                System.out.println("Lecturer ID : "+ lecturerID);
                                if(lecturerID != null){
                                    DAO daoLecturer = new DAO();
                                    List<CourseTimetable> timetableRecordList = daoLecturer.getLecturerTimeTable(lecturerID);
                                    if(timetableRecordList != null){
                                        request.setAttribute("showTable", true);
                                        request.setAttribute("searchResult", timetableRecordList);
                                        request.getRequestDispatcher("searchLecturerTimeTableForAdmin.jsp").forward(request, response);
                                    }else{
                                        request.setAttribute("showErrorMessage", true);
                                        request.setAttribute("ErrorMsgBody", "No timetable found for the following search criteria");
                                        request.getRequestDispatcher("searchLecturerTimeTableForAdmin.jsp").forward(request, response);
                                    }
                                }else{
                                    request.setAttribute("showErrorMessage", true);
                                    request.setAttribute("ErrorMsgBody", "Lecturer ID could not be retrieved!");
                                    request.getRequestDispatcher("searchLecturerTimeTableForAdmin.jsp").forward(request, response);
                                }


                            }

                        }
                        else if(viewType.equals("commonStudentViewForAdmin")){
                            if(userType.equals("student")){
                                String badgeID = request.getParameter("badge");
                                String courseID = request.getParameter("courseName");
                                if(courseID != null && badgeID != null){
                                    int coursIDInt = parseInt(courseID);
                                    int badgeIDint = parseInt(badgeID);
                                    DAO daoStud = new DAO();
                                    List<CourseTimetable> timetableRecordList = daoStud.getStudentTimeTable(coursIDInt,badgeIDint);
                                    if(timetableRecordList != null){
                                        request.setAttribute("showTable", true);
                                        request.setAttribute("searchResult", timetableRecordList);
                                        request.getRequestDispatcher("searchStudentTimeTableForAdmin.jsp").forward(request, response);
                                    }else{
                                        request.setAttribute("showErrorMessage", true);
                                        request.setAttribute("ErrorMsgBody", "No timetable found for the following search criteria");
                                        request.getRequestDispatcher("searchStudentTimeTableForAdmin.jsp").forward(request, response);
                                    }
                                }else{
                                    request.setAttribute("showErrorMessage", true);
                                    request.setAttribute("ErrorMsgBody", "Data not retrieved!");
                                    request.getRequestDispatcher("searchStudentTimeTableForAdmin.jsp").forward(request, response);
                                }


                            }
                        }

                    }

                }






            }
            else if(action.equals("DeleteBadgeTimetable")){
                String badgeID = request.getParameter("badgeIDRec");
                String deletedBy = request.getParameter("loggedInUserID");
                String courseID = request.getParameter("courseIDRec");
                if(badgeID != null && deletedBy != null && courseID != null){
                    int badgeIDint = parseInt(badgeID);
                    int courseIDInt = parseInt(courseID);
                    DAO dao = new DAO();
                    boolean isDeleteSuccess = dao.deleteBadgeTimetable(courseIDInt,badgeIDint,deletedBy);
                    System.out.println("Is Badge Time table deleted? : "+ isDeleteSuccess);
                    if(isDeleteSuccess == true){
                        request.setAttribute("showSuccessMessage", true);
                        request.setAttribute("SuccessMsgBody", "The selected Badge ID : "+ badgeIDint+ " timetable is deleted Successfully!");
                        request.getRequestDispatcher("searchStudentTimeTable.jsp").forward(request, response);
                    }else{
                        request.setAttribute("showErrorMessage", true);
                        request.setAttribute("ErrorMsgBody", "The selected Badge ID : "+ badgeIDint+ " timetable could not be deleted!");
                        request.getRequestDispatcher("searchStudentTimeTable.jsp").forward(request, response);
                    }

                }else{
                    request.setAttribute("showErrorMessage", true);
                    request.setAttribute("ErrorMsgBody", "Technical Error! Data could not be retirieved!");
                    request.getRequestDispatcher("searchStudentTimeTable.jsp").forward(request, response);
                }

            }
        }

    }
}
