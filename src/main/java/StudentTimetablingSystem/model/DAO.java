package StudentTimetablingSystem.model;



import javax.servlet.http.Part;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;



public class DAO {
    private Connection connection;
    String sql =null;

    public DAO(){
        connection = Database.getConnection();
    }

    public Student loginStudent(String email, String pass){

        Student foundStudentUser = null;

        String emailInLowerCase = email.toLowerCase();
        String emailStartWith = emailInLowerCase.substring(0,2);
        System.out.println(emailStartWith);
        try{
            sql = "{call VALIDATE_USER_SP(?,?,?)}";
            CallableStatement statement = connection.prepareCall(sql);
            statement.setString(1,email);
            statement.setString(2,pass);
            statement.setString(3,"student");

            ResultSet result = statement.executeQuery();
            if(result != null){
                while(result.next()){
                    int id =  result.getInt("ID");
                    String studentID = result.getString("STUDENT_ID");
                    String fName = result.getString("FIRST_NAME");
                    String lName = result.getString("LAST_NAME");
                    int courseID =  result.getInt("COURSE_ID");
                    String address = result.getString("ADDRESS");
                    String emailAddress    = result.getString("EMAIL");
                    String password = result.getString("PASSWORD");
                    String mobileNum = result.getString("MOBILE_NUMBER");
                    String emergencyNum = result.getString("EMERGENCY_CONTACT_NUMBER");



                    int isActive = result.getInt("IS_ACTIVE");
                    Timestamp created = result.getTimestamp("CREATED");
                    String createdBy = result.getString("CREATED_BY");
                    Timestamp modified = result.getTimestamp("MODIFIED");
                    String modifiedBy = result.getString("MODIFIED_BY");

                    foundStudentUser = new Student(id, studentID, fName, lName,courseID,address,emailAddress, password, mobileNum,emergencyNum, isActive, created, createdBy, modified, modifiedBy);
                    System.out.println("Student ID: "+foundStudentUser.getId());
                    System.out.println("Student Name: "+foundStudentUser.getfName());

                    break;
                }
            }


        }catch(Exception e){
            System.out.println(e.getMessage());

        }finally {
            if (connection != null) {
                // closes the database connection
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }

        return foundStudentUser;
    }
    public Lecturer loginLecturer(String email, String pass){

        Lecturer foundLecturerUser = null;

        String emailInLowerCase = email.toLowerCase();
        String emailStartWith = emailInLowerCase.substring(0,2);
        System.out.println(emailStartWith);
        try{
            sql = "{call VALIDATE_USER_SP(?,?,?)}";
            CallableStatement statement = connection.prepareCall(sql);
            statement.setString(1,email);
            statement.setString(2,pass);
            statement.setString(3,"lecturer");

            ResultSet result = statement.executeQuery();
            if(result != null){
                while(result.next()){
                    int id =  result.getInt("ID");
                    String lecturerID = result.getString("LECTURER_ID");
                    String fName = result.getString("FIRST_NAME");
                    String lName = result.getString("LAST_NAME");
                    String address = result.getString("ADDRESS");
                    String emailAddress    = result.getString("EMAIL");
                    String password = result.getString("PASSWORD");
                    String mobileNum = result.getString("CONTACT_NUMBER");
                    String emergencyNum = result.getString("EMERGENCY_CONTACT_NUMBER");


                    int isActive = result.getInt("IS_ACTIVE");
                    Timestamp created = result.getTimestamp("CREATED");
                    String createdBy = result.getString("CREATED_BY");
                    Timestamp modified = result.getTimestamp("MODIFIED");
                    String modifiedBy = result.getString("MODIFIED_BY");

                    foundLecturerUser = new Lecturer(id, lecturerID, fName, lName,address,emailAddress, password, mobileNum,emergencyNum, isActive, created, createdBy, modified, modifiedBy);
                    System.out.println("Lecturer ID: "+foundLecturerUser.getId());
                    System.out.println("Lecturer Name: "+foundLecturerUser.getfName());
                    break;
                }
            }


        }catch(Exception e){
            System.out.println(e.getMessage());

        }finally {
            if (connection != null) {
                // closes the database connection
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }

        return foundLecturerUser;
    }
    public Admin loginAdmin(String email, String pass){

        Admin foundAdmin = null;

        String emailInLowerCase = email.toLowerCase();
        String emailStartWith = emailInLowerCase.substring(0,2);
        System.out.println(emailStartWith);
        try{
            sql = "{call VALIDATE_USER_SP(?,?,?)}";
            CallableStatement statement = connection.prepareCall(sql);
            statement.setString(1,email);
            statement.setString(2,pass);
            statement.setString(3,"admin");

            System.out.println(email);
            System.out.println(pass);

            ResultSet result = statement.executeQuery();
            if(result != null){
                while(result.next()){
                    int id =  result.getInt("ID");
                    String adminId = result.getString("ADMIN_ID");
                    String fName = result.getString("FIRST_NAME");
                    String lName = result.getString("LAST_NAME");
                    String address = result.getString("ADDRESS");
                    String emailAddress    = result.getString("EMAIL");
                    String password = result.getString("PASSWORD");
                    String mobileNum = result.getString("CONTACT_NUMBER");
                    String emergencyNum = result.getString("EMERGENCY_NUMBER");

                    int isActive = result.getInt("IS_ACTIVE");
                    Timestamp created = result.getTimestamp("CREATED");
                    String createdBy = result.getString("CREATED_BY");
                    Timestamp modified = result.getTimestamp("MODIFIED");
                    String modifiedBy = result.getString("MODIFIED_BY");

                    foundAdmin = new Admin(id, adminId, fName, lName,address,emailAddress, password, mobileNum,emergencyNum, isActive, created, createdBy, modified, modifiedBy);
                    System.out.println("Admin ID: "+foundAdmin.getId());
                    System.out.println("Admin Name: "+foundAdmin.getfName());
                    break;
                }
            }


        }catch(Exception e){
            System.out.println(e.getMessage());

        }finally {
            if (connection != null) {
                // closes the database connection
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }

        return foundAdmin;
    }
    public Timetabler loginTimetabler(String email, String pass){

        Timetabler foundTimetabler = null;

        String emailInLowerCase = email.toLowerCase();
        String emailStartWith = emailInLowerCase.substring(0,2);
        System.out.println(emailStartWith);
        try{
            sql = "{call VALIDATE_USER_SP(?,?,?)}";
            CallableStatement statement = connection.prepareCall(sql);
            statement.setString(1,email);
            statement.setString(2,pass);
            statement.setString(3,"timetabler");

            System.out.println(email);
            System.out.println(pass);

            ResultSet result = statement.executeQuery();
            if(result != null){
                while(result.next()){
                    int id =  result.getInt("ID");
                    String adminId = result.getString("TIMETABLER_ID");
                    String fName = result.getString("FIRST_NAME");
                    String lName = result.getString("LAST_NAME");
                    String address = result.getString("ADDRESS");
                    String emailAddress    = result.getString("EMAIL");
                    String password = result.getString("PASSWORD");
                    String mobileNum = result.getString("CONTACT_NUMBER");
                    String emergencyNum = result.getString("EMERGENCY_CONTACT_NUMBER");

                    int isActive = result.getInt("IS_ACTIVE");
                    Timestamp created = result.getTimestamp("CREATED");
                    String createdBy = result.getString("CREATED_BY");
                    Timestamp modified = result.getTimestamp("MODIFIED");
                    String modifiedBy = result.getString("MODIFIED_BY");

                    foundTimetabler = new Timetabler(id, adminId, fName, lName,address,emailAddress, password, mobileNum,emergencyNum, isActive, created, createdBy, modified, modifiedBy);
                    System.out.println("Timetabler ID: "+foundTimetabler.getId());
                    System.out.println("Timetabler Name: "+foundTimetabler.getfName());
                    break;
                }
            }


        }catch(Exception e){
            System.out.println(e.getMessage());

        }finally {
            if (connection != null) {
                // closes the database connection
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }

        return foundTimetabler;
    }
    public List<Course> getCourses(){
        sql = null;
        List<Course> listCourse = new ArrayList<>();

        try{
            sql = "Call GET_COURSE_DETAILS()";//"SELECT * FROM bangerco.brand;";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery(sql);
            if(result != null){
                System.out.println("Data found!!");
                while (result.next()) {
                    int id =  result.getInt("COURSE_ID");
                    String name = result.getString("COURSE_NAME");
                    String courseField = result.getString("COURSE_FIELD");
                    int isActive = result.getInt("IS_ACTIVE");
                    Timestamp created = result.getTimestamp("CREATED");
                    String createdBy = result.getString("CREATED_BY");
                    Timestamp modified = result.getTimestamp("MODIFIED");
                    String modifiedBy = result.getString("MODIFIED_BY");
                    Course course = new Course(id,name,courseField,isActive,created,createdBy,modified,modifiedBy);

                    listCourse.add(course);
                }
            }else{
                System.out.println("No Data found!!");
            }

        }catch(Exception e){
            e.printStackTrace();

        }
        return listCourse;

    }
    public Course getCourseDetailsById(int courseID){
        sql = null;
        Course courseInfo = null;

        try{
            sql = "{Call GET_COURSE_DETAILS_BY_ID(?)}";
            CallableStatement statement = connection.prepareCall(sql);
            statement.setInt(1,courseID);
            ResultSet result = statement.executeQuery();
            if(result != null){
                System.out.println("Data found!!");
                while (result.next()) {
                    int id =  result.getInt("COURSE_ID");
                    String name = result.getString("COURSE_NAME");
                    String courseField = result.getString("COURSE_FIELD");
                    int isActive = result.getInt("IS_ACTIVE");
                    Timestamp created = result.getTimestamp("CREATED");
                    String createdBy = result.getString("CREATED_BY");
                    Timestamp modified = result.getTimestamp("MODIFIED");
                    String modifiedBy = result.getString("MODIFIED_BY");
                    Course course = new Course(id,name,courseField,isActive,created,createdBy,modified,modifiedBy);

                    courseInfo = course;
                }
            }else{
                System.out.println("No Data found!!");
            }

        }catch(Exception e){
            e.printStackTrace();

        }
        return courseInfo;

    }

    public List<Module> getModulesBasedOnCourseID(int courseID){
        sql = null;
        List<Module> listModules = new ArrayList<>();

        try{
            sql = "Call GET_MODULES_BY_COURSE_ID(?)";//"SELECT * FROM bangerco.brand;";
            CallableStatement statement = connection.prepareCall(sql);
            statement.setInt(1,courseID);
            ResultSet result = statement.executeQuery();
            if(result != null){
                System.out.println("Data found!!");
                while (result.next()) {
                    int moduleID =  result.getInt("MODULE_ID");
                    String moduleName = result.getString("MODULE_NAME");
                    String moduleDescription = result.getString("MODULE_DESCRIPTION");
                    int courseIDVal = result.getInt("COURSE_ID");
                    String courseName = result.getString("COURSE_NAME");
                    String lecturerID = result.getString("LECTURER_ID");
                    int classID = result.getInt("CLASS_ID");
                    String classroomName = result.getString("CLASS_ROOM_NAME");
                    int isActive = result.getInt("IS_ACTIVE");
                    Timestamp created = result.getTimestamp("CREATED");
                    String createdBy = result.getString("CREATED_BY");
                    Timestamp modified = result.getTimestamp("MODIFIED");
                    String modifiedBy = result.getString("MODIFIED_BY");
                    Module module = new Module(moduleID,moduleName,moduleDescription,courseName,courseIDVal,lecturerID,classID, classroomName,isActive,created,createdBy,modified,modifiedBy);


                    listModules.add(module);
                }
            }else{
                System.out.println("No Data found!!");
            }

        }catch(Exception e){
            e.printStackTrace();

        }
        return listModules;

    }
    public List<Module> getModules(){
        sql = null;
        List<Module> listModules = new ArrayList<>();

        try{
            sql = "{Call GET_MODULES()}";//"SELECT * FROM bangerco.brand;";
            CallableStatement statement = connection.prepareCall(sql);

            ResultSet result = statement.executeQuery();
            if(result != null){
                System.out.println("Data found!!");
                while (result.next()) {
                    int moduleID =  result.getInt("MODULE_ID");
                    String moduleName = result.getString("MODULE_NAME");
                    String moduleDescription = result.getString("MODULE_DESCRIPTION");
                    int courseIDVal = result.getInt("COURSE_ID");
                    String courseName = result.getString("COURSE_NAME");
                    String lecturerID = result.getString("LECTURER_ID");
                    String lecturerFName = result.getString("LECTURER_FNAME");
                    String lecturerLName = result.getString("LECTURER_LNAME");
                    int classID = result.getInt("CLASS_ID");
                    String classroomName = result.getString("CLASS_ROOM_NAME");
                    int isActive = result.getInt("IS_ACTIVE");
                    Timestamp created = result.getTimestamp("CREATED");
                    String createdBy = result.getString("CREATED_BY");
                    Timestamp modified = result.getTimestamp("MODIFIED");
                    String modifiedBy = result.getString("MODIFIED_BY");

                    Module module = new Module(moduleID,moduleName,moduleDescription,courseName,courseIDVal,lecturerID,lecturerFName,lecturerLName,classID, classroomName,isActive,created,createdBy,modified,modifiedBy);
                    listModules.add(module);
                }
            }else{
                System.out.println("No Data found!!");
            }

        }catch(Exception e){
            e.printStackTrace();

        }
        return listModules;

    }
    public List<Badge> getBadges(int courseID){
        sql = null;
        List<Badge> listBadge = new ArrayList<>();

        try{
            sql = "call GET_BADGE_DETAILS_BY_COURSE_ID(?)";
             CallableStatement statement = connection.prepareCall(sql);
            statement.setInt(1,courseID);

            ResultSet result = statement.executeQuery();
            if(result != null){
                System.out.println("Data found!!");
                while (result.next()) {
                    int id =  result.getInt("ID");
                    String name = result.getString("BADGE_NAME");
                    int courseIDForeignKey =  result.getInt("COURSE_ID");
                    int badgeYear = result.getInt("BADGE_YEAR");
                    int isActive = result.getInt("IS_ACTIVE");
                    Timestamp created = result.getTimestamp("CREATED");
                    String createdBy = result.getString("CREATED_BY");
                    Timestamp modified = result.getTimestamp("MODIFIED");
                    String modifiedBy = result.getString("MODIFIED_BY");
                    Badge badge = new Badge(id,name,courseIDForeignKey,badgeYear,isActive,created,createdBy,modified,modifiedBy);

                    listBadge.add(badge);
                }
            }else{
                System.out.println("No Data found!!");
            }

        }catch(Exception e){
            e.printStackTrace();

        }
        return listBadge;

    }
    public List<Badge> getAllBadges(){
        sql = null;
        List<Badge> listBadge = new ArrayList<>();

        try{
            sql = "call GET_All_BADGES()";
            CallableStatement statement = connection.prepareCall(sql);

            ResultSet result = statement.executeQuery();
            if(result != null){
                System.out.println("Data found!!");
                while (result.next()) {
                    int id =  result.getInt("ID");
                    String name = result.getString("BADGE_NAME");
                    int courseIDForeignKey =  result.getInt("COURSE_ID");
                    String courseName = result.getString("COURSE_NAME");
                    int badgeYear = result.getInt("BADGE_YEAR");
                    int isActive = result.getInt("IS_ACTIVE");
                    Timestamp created = result.getTimestamp("CREATED");
                    String createdBy = result.getString("CREATED_BY");
                    Timestamp modified = result.getTimestamp("MODIFIED");
                    String modifiedBy = result.getString("MODIFIED_BY");
                    Badge badge = new Badge(id,name,courseIDForeignKey, courseName,badgeYear,isActive,created,createdBy,modified,modifiedBy);

                    listBadge.add(badge);
                }
            }else{
                System.out.println("No Data found!!");
            }

        }catch(Exception e){
            e.printStackTrace();

        }
        return listBadge;

    }
    public List<Classroom> getAvailableClassrooms(){
        sql = null;
        List<Classroom> listClassrooms = new ArrayList<>();
        try{
            sql = "Call GET_AVAILABLE_CLASS_ROOMS_DETAILS()";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery(sql);
            if(result != null){
                System.out.println("Data found!!");
                while (result.next()) {
                    int id =  result.getInt("ID");
                    String name = result.getString("CLASS_ROOM_NAME");
                    int floorLevel = result.getInt("FLOOR_LEVEL");
                    int isAllocatedForCourseModule = result.getInt("IS_ALLOCATED_FOR_COURSE_MODULE");
                    int isActive = result.getInt("IS_ACTIVE");
                    Timestamp created = result.getTimestamp("CREATED");
                    String createdBy = result.getString("CREATED_BY");
                    Timestamp modified = result.getTimestamp("MODIFIED");
                    String modifiedBy = result.getString("MODIFIED_BY");
                    Classroom classroom = new Classroom(id,name,floorLevel,isAllocatedForCourseModule,isActive,created,createdBy,modified,modifiedBy);

                    listClassrooms.add(classroom);
                }
            }else{
                System.out.println("No Data found!!");
            }

        }catch(Exception e){
            e.printStackTrace();

        }
        return listClassrooms;
    }
    public List<Classroom> getAvailableClassroomsForModules(){
        sql = null;
        List<Classroom> listClassrooms = new ArrayList<>();
        try{
            sql = "Call GET_CLASSES_ALLOCATED_FOR_COURSE_MODULES()";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery(sql);
            if(result != null){
                System.out.println("Data found!!");
                while (result.next()) {
                    int id =  result.getInt("ID");
                    String name = result.getString("CLASS_ROOM_NAME");
                    int floorLevel = result.getInt("FLOOR_LEVEL");
                    int isAllocatedForCourseModule = result.getInt("IS_ALLOCATED_FOR_COURSE_MODULE");
                    int isActive = result.getInt("IS_ACTIVE");
                    Timestamp created = result.getTimestamp("CREATED");
                    String createdBy = result.getString("CREATED_BY");
                    Timestamp modified = result.getTimestamp("MODIFIED");
                    String modifiedBy = result.getString("MODIFIED_BY");
                    Classroom classroom = new Classroom(id,name,floorLevel,isAllocatedForCourseModule,isActive,created,createdBy,modified,modifiedBy);

                    listClassrooms.add(classroom);
                }
            }else{
                System.out.println("No Data found!!");
            }

        }catch(Exception e){
            e.printStackTrace();

        }
        return listClassrooms;
    }
    public List<Classroom> getAllClassrooms(){
        sql = null;
        List<Classroom> listClassrooms = new ArrayList<>();
        try{
            sql = "Call GET_ALL_CLASSROOMS()";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery(sql);
            if(result != null){
                System.out.println("Data found!!");
                while (result.next()) {
                    int id =  result.getInt("ID");
                    String name = result.getString("CLASS_ROOM_NAME");
                    int floorLevel = result.getInt("FLOOR_LEVEL");
                    int isAllocatedForCourseModule = result.getInt("IS_ALLOCATED_FOR_COURSE_MODULE");
                    int isActive = result.getInt("IS_ACTIVE");
                    Timestamp created = result.getTimestamp("CREATED");
                    String createdBy = result.getString("CREATED_BY");
                    Timestamp modified = result.getTimestamp("MODIFIED");
                    String modifiedBy = result.getString("MODIFIED_BY");
                    Classroom classroom = new Classroom(id,name,floorLevel,isAllocatedForCourseModule,isActive,created,createdBy,modified,modifiedBy);

                    listClassrooms.add(classroom);
                }
            }else{
                System.out.println("No Data found!!");
            }

        }catch(Exception e){
            e.printStackTrace();

        }
        return listClassrooms;
    }

    public boolean verifySelectedClass(int classID, Timestamp startDate, Timestamp endDate){
        sql = null;
        boolean isValid= false;
        try{
            sql = "Call VERIFY_AVAILABLE_CLASS_ROOM_DETAILS(?,?,?,?)";
            CallableStatement statement = connection.prepareCall(sql);
            statement.setInt(1,classID);
            statement.setTimestamp(2,startDate);
            statement.setTimestamp(3,endDate);
            statement.registerOutParameter(4, Types.SMALLINT);
            statement.execute();
            int isAvailable = statement.getInt(4);
            if(isAvailable == 1){
                isValid = true;
                System.out.println("Selected Room is Available!");

            }else{
                isValid = false;
                System.out.println("Room not Available!");

            }

        }catch(Exception e){
            e.printStackTrace();

        }
        return isValid;
    }

    public boolean addBooking(int badgeID, int classID, int duration, Timestamp onDate, Timestamp endDate, String lecturerID, String lecturerUsername){

        boolean bookedSuccessfully = false;
        try{
            sql = "{call ADD_NEW_BOOKING(?,?,?,?,?,?,?,?)}";
            CallableStatement statement = connection.prepareCall(sql);
            statement.setInt(1, badgeID);
            statement.setInt(2, classID);
            statement.setInt(3, duration);
            statement.setString(4, lecturerID);
            statement.setString(5, lecturerUsername);
            statement.setTimestamp(6, onDate);
            statement.setTimestamp(7, endDate);
            statement.registerOutParameter(8, Types.SMALLINT);
            statement.execute();
            int hasAdded = statement.getInt(8);

            if(hasAdded == 1){
                System.out.println(hasAdded);
                bookedSuccessfully = true;
            }else{
                System.out.println(hasAdded);
                bookedSuccessfully = false;
            }


        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            if (connection != null) {
                // closes the database connection
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return bookedSuccessfully;
    }
    public void deleteParticularBooking(int bookingID){
        try{
            sql = "{call DELETE_CURRENT_LECTURER_SELECTED_BOOKING(?)}";
            CallableStatement statement = connection.prepareCall(sql);
            statement.setInt(1, bookingID);

            statement.execute();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            if (connection != null) {
                // closes the database connection
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    public boolean editParticularLecturerBooking(String lecturerID, int classID, int bookingID, int newDuration, Timestamp newStartDate, Timestamp newEndDate){
        boolean isSuccessful = false;
        try{
            sql = "{call EDIT_CURRENT_LECTURER_SELECTED_BOOKING(?,?,?,?,?,?,?)}";
            CallableStatement statement = connection.prepareCall(sql);
            statement.setInt(1, classID);
            statement.setInt(2, bookingID);
            statement.setString(3, lecturerID);
            statement.setInt(4, newDuration);
            statement.setTimestamp(5, newStartDate);
            statement.setTimestamp(6, newEndDate);
            statement.registerOutParameter(7, Types.SMALLINT );
            statement.execute();

            int hasEdited = statement.getInt(7);

            if(hasEdited == 1){
                System.out.println(hasEdited);
                isSuccessful = true;
            }else{
                System.out.println(hasEdited);
                isSuccessful = false;
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            if (connection != null) {
                // closes the database connection
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return isSuccessful;
    }
    public List<Booking> listBookings(String lecturerID){
        List<Booking> bookingList = new ArrayList<>();
        try{
            sql = "{call GET_CURRENT_LECTURER_BOOKINGS(?)}";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, lecturerID);

            ResultSet rs = statement.executeQuery();

            if(rs != null){
                while(rs.next()){
                    int bookingID = rs.getInt("BOOKING_ID");
                    int classroomID = rs.getInt("CLASS_ID");
                    String classRoomName = rs.getString("CLASS_ROOM_NAME");
                    String badgeName = rs.getString("BADGE_NAME");
                    String lecturerIDString = rs.getString("LECTURER_ID");
                    int duration = rs.getInt("DURATION");
                    Timestamp startDate = rs.getTimestamp("START_DATE");
                    Timestamp endDate = rs.getTimestamp("END_DATE");
                    int isActive = rs.getInt("IS_ACTIVE");
                    Timestamp created = rs.getTimestamp("CREATED");
                    String createdBy = rs.getString("CREATED_BY");
                    Timestamp modified = rs.getTimestamp("MODIFIED");
                    String modifiedBy = rs.getString("MODIFIED_BY");


                    String bookingInString = String.valueOf(bookingID);
                    Booking booking = new Booking(bookingID, classroomID, classRoomName, badgeName, lecturerIDString, duration, startDate, endDate, isActive, created, createdBy,modified, modifiedBy);

                    bookingList.add(booking);
                }

            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            if (connection != null) {
                // closes the database connection
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }

        return bookingList;
    }
    public List<Booking> getLecturerBookings(String lecturerID){
        List<Booking> bookingList = new ArrayList<>();
        try{
            sql = "{call GET_CURRENT_LECTURER_BOOKINGS(?)}";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, lecturerID);

            ResultSet rs = statement.executeQuery();

            if(rs != null){
                while(rs.next()){
                    int bookingID = rs.getInt("BOOKING_ID");
                    int classroomID = rs.getInt("CLASS_ID");
                    String classRoomName = rs.getString("CLASS_ROOM_NAME");
                    String badgeName = rs.getString("BADGE_NAME");
                    String lecturerIDString = rs.getString("LECTURER_ID");
                    int duration = rs.getInt("DURATION");
                    LocalDateTime startDate = rs.getTimestamp("START_DATE").toLocalDateTime();
                    LocalDateTime endDate = rs.getTimestamp("END_DATE").toLocalDateTime();
                    int isActive = rs.getInt("IS_ACTIVE");
                    LocalDateTime created = rs.getTimestamp("CREATED").toLocalDateTime();
                    String createdBy = rs.getString("CREATED_BY");
                    LocalDateTime modified = rs.getTimestamp("MODIFIED").toLocalDateTime();
                    String modifiedBy = rs.getString("MODIFIED_BY");



                    String bookingInString = String.valueOf(bookingID);
                    Booking booking = new Booking(bookingID, classroomID, classRoomName, badgeName, lecturerIDString, duration, startDate, endDate, isActive, created, createdBy,modified, modifiedBy);

                    bookingList.add(booking);
                }

            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            if (connection != null) {
                // closes the database connection
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }

        return bookingList;
    }
    public void checkForOldBookings(){
        try{
            sql = "{call GET_OLD_BOOKING_RECORDS_IDS()}";
            PreparedStatement statement1 = connection.prepareStatement(sql);
            ResultSet rs = statement1.executeQuery();
            List<Integer> bookingsIDList = new ArrayList<>();
            int i =0;
            while(rs.next()){
                bookingsIDList.add(i,rs.getInt("BOOKING_ID"));
                System.out.println("\n OLD BOOKING ID "+ i +" : " +bookingsIDList.get(i));
                i++;
            }
            if(!bookingsIDList.isEmpty()){
                for(int j=0; j < bookingsIDList.size(); j++){
                    sql = "{call CHECK_FOR_OLD_BOOKING_RECORDS(?)}";

                    CallableStatement statement = connection.prepareCall(sql);
                    statement.setInt(1,bookingsIDList.get(j));
                    statement.execute();
                    java.lang.Thread.sleep(1000);
                }

            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            if (connection != null) {
                // closes the database connection
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }

    }
    public boolean verifyNewStudentID(String studentID){
        boolean isNew = false;

        try{
            sql = "{call VERIFY_ENTERED_NEW_STUDENT_ID(?,?)}";
            CallableStatement statement = connection.prepareCall(sql);
            statement.setString(1, studentID);
            statement.registerOutParameter(2, Types.SMALLINT );
            statement.execute();

            int isAvailable = statement.getInt(2);

            if(isAvailable == 0){
                System.out.println(isAvailable);
                isNew = true;
            }else{
                System.out.println(isAvailable);
                isNew = false;
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            if (connection != null) {
                // closes the database connection
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }

        return isNew;
    }
    public boolean registerStudent(String studentID, String fName, String lName, int courseID, int badgeID, String address, String mail, String pwd, String mobileNumber, String emrgencyNumber, String recordCreatedBy){
        boolean isSuccess = false;
        try {

            // constructs SQL statement
            sql = "{call REGISTER_NEW_STUDENT(?,?,?,?,?,?,?,?,?,?,?,?)}";
            CallableStatement statement = connection.prepareCall(sql);
            statement.setString(1, studentID);
            statement.setString(2, fName);
            statement.setString(3, lName);
            statement.setInt(4, courseID);
            statement.setInt(5, badgeID);
            statement.setString(6, address);
            statement.setString(7, mail);
            statement.setString(8, pwd);
            statement.setString(9, mobileNumber);
            statement.setString(10, emrgencyNumber);
            statement.setString(11, recordCreatedBy);
            statement.registerOutParameter(12, Types.SMALLINT);
            statement.execute();

            int isAddedSucessfully=  statement.getInt(12);
            if(isAddedSucessfully == 1){
                isSuccess = true;
            }else{
                isSuccess = false;
            }


        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            if (connection != null) {
                // closes the database connection
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

        }
        return isSuccess;

    }
    public List<Student> getAllStudents(){
        List<Student> studentList = new ArrayList<>();

        try{
            sql = "{call GET_ALL_STUDENTS()}";
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet rs = statement.executeQuery();

            if(rs != null){
                while(rs.next()){
                    int id = rs.getInt("ID");
                    String studentID = rs.getString("STUDENT_ID");
                    String fName = rs.getString("FIRST_NAME");
                    String lName = rs.getString("LAST_NAME");
                    int courseID = rs.getInt("COURSE_ID");
                    String courseName = rs.getString("COURSE_NAME");
                    String address = rs.getString("ADDRESS");
                    String email = rs.getString("EMAIL");
                    String pwd = rs.getString("PASSWORD");
                    String mobileNum = rs.getString("MOBILE_NUMBER");
                    String emMobileNum = rs.getString("EMERGENCY_CONTACT_NUMBER");
                    int isActive = rs.getInt("IS_ACTIVE");
                    Timestamp created = rs.getTimestamp("CREATED");
                    String createdBy = rs.getString("CREATED_BY");
                    Timestamp modified = rs.getTimestamp("MODIFIED");
                    String modifiedBy = rs.getString("MODIFIED_BY");

                    Student student = new Student(id,studentID,fName,lName,courseID,courseName,address,email,pwd,mobileNum,emMobileNum, isActive, created, createdBy,modified, modifiedBy);

                    studentList.add(student);
                }

            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            if (connection != null) {
                // closes the database connection
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }


        return studentList;
    }
    public boolean updateStudentDetails(String studentID, String adminID, String email, String pass, String address, String mobile, String emMobile){
        boolean isUpdated = false;

        try {

            // constructs SQL statement
            sql = "{call UPDATE_STUDENT_DETAILS(?,?,?,?,?,?,?,?)}";
            CallableStatement statement = connection.prepareCall(sql);
            statement.setString(1, studentID);
            statement.setString(2, adminID);
            statement.setString(3, email);
            statement.setString(4, pass);
            statement.setString(5, address);
            statement.setString(6, mobile);
            statement.setString(7, emMobile);
            statement.registerOutParameter(8, Types.SMALLINT);
            statement.execute();

            int isUpdatedSuccessfully=  statement.getInt(8);
            if(isUpdatedSuccessfully == 1){
                isUpdated = true;
            }else{
                isUpdated = false;
            }


        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            if (connection != null) {
                // closes the database connection
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

        }

        return  isUpdated;
    }
    public boolean deleteSelectedStudent(String studentID, String deletedBy){
        boolean isDeleted = false;
        try {

            // constructs SQL statement
            sql = "{call DELETE_PARTICULAR_STUDENT(?,?,?)}";
            CallableStatement statement = connection.prepareCall(sql);
            statement.setString(1, studentID);
            statement.setString(2, deletedBy);
            statement.registerOutParameter(3, Types.SMALLINT);
            statement.execute();

            int isDeletedSuccessfully=  statement.getInt(3);
            if(isDeletedSuccessfully == 1){
                isDeleted = true;
            }else{
                isDeleted = false;
            }


        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            if (connection != null) {
                // closes the database connection
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

        }

        return isDeleted;
    }

    public boolean verifyNewLecturerID(String lecturerID){
        boolean isNew = false;

        try{
            sql = "{call VERIFY_ENTERED_NEW_LECTURER_ID(?,?)}";
            CallableStatement statement = connection.prepareCall(sql);
            statement.setString(1, lecturerID);
            statement.registerOutParameter(2, Types.SMALLINT );
            statement.execute();

            int isAvailable = statement.getInt(2);

            if(isAvailable == 0){
                System.out.println(isAvailable);
                isNew = true;
            }else{
                System.out.println(isAvailable);
                isNew = false;
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            if (connection != null) {
                // closes the database connection
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }

        return isNew;
    }
    public boolean registerLecturer(String lecturerID, String fName, String lName, String address, String mail, String pwd, String mobileNumber, String emrgencyNumber, String recordCreatedBy){
        boolean isSuccess = false;
        try {

            // constructs SQL statement
            sql = "{call REGISTER_NEW_LECTURER(?,?,?,?,?,?,?,?,?,?)}";
            CallableStatement statement = connection.prepareCall(sql);
            statement.setString(1, lecturerID);
            statement.setString(2, fName);
            statement.setString(3, lName);
            statement.setString(4, address);
            statement.setString(5, mail);
            statement.setString(6, pwd);
            statement.setString(7, mobileNumber);
            statement.setString(8, emrgencyNumber);
            statement.setString(9, recordCreatedBy);
            statement.registerOutParameter(10, Types.SMALLINT);
            statement.execute();

            int isAddedSucessfully=  statement.getInt(10);
            if(isAddedSucessfully == 1){
                isSuccess = true;
            }else{
                isSuccess = false;
            }


        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            if (connection != null) {
                // closes the database connection
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

        }
        return isSuccess;

    }
    public List<Lecturer> getAllLecturers(){
        List<Lecturer> lecturerList = new ArrayList<>();

        try{
            sql = "{call GET_ALL_LECTURERS()}";
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet rs = statement.executeQuery();

            if(rs != null){
                while(rs.next()){
                    int id = rs.getInt("ID");
                    String studentID = rs.getString("LECTURER_ID");
                    String fName = rs.getString("FIRST_NAME");
                    String lName = rs.getString("LAST_NAME");
                    String address = rs.getString("ADDRESS");
                    String email = rs.getString("EMAIL");
                    String pwd = rs.getString("PASSWORD");
                    String mobileNum = rs.getString("CONTACT_NUMBER");
                    String emMobileNum = rs.getString("EMERGENCY_CONTACT_NUMBER");
                    int isActive = rs.getInt("IS_ACTIVE");
                    Timestamp created = rs.getTimestamp("CREATED");
                    String createdBy = rs.getString("CREATED_BY");
                    Timestamp modified = rs.getTimestamp("MODIFIED");
                    String modifiedBy = rs.getString("MODIFIED_BY");

                    Lecturer lecturer = new Lecturer(id,studentID,fName,lName,address,email,pwd,mobileNum,emMobileNum, isActive, created, createdBy,modified, modifiedBy);

                    lecturerList.add(lecturer);
                }

            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            if (connection != null) {
                // closes the database connection
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }


        return lecturerList;
    }
    public boolean updateLecturerDetails(String lecturerID, String adminID, String email, String pass, String address, String mobile, String emMobile){
        boolean isUpdated = false;

        try {

            // constructs SQL statement
            sql = "{call UPDATE_LECTURER_DETAILS(?,?,?,?,?,?,?,?)}";
            CallableStatement statement = connection.prepareCall(sql);
            statement.setString(1, lecturerID);
            statement.setString(2, adminID);
            statement.setString(3, email);
            statement.setString(4, pass);
            statement.setString(5, address);
            statement.setString(6, mobile);
            statement.setString(7, emMobile);
            statement.registerOutParameter(8, Types.SMALLINT);
            statement.execute();

            int isUpdatedSuccessfully=  statement.getInt(8);
            if(isUpdatedSuccessfully == 1){
                isUpdated = true;
            }else{
                isUpdated = false;
            }


        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            if (connection != null) {
                // closes the database connection
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

        }

        return  isUpdated;
    }
    public boolean deleteSelectedLecturer(String lecturerID, String deletedBy){
        boolean isDeleted = false;
        try {

            // constructs SQL statement
            sql = "{call DELETE_PARTICULAR_LECTURER(?,?,?)}";
            CallableStatement statement = connection.prepareCall(sql);
            statement.setString(1, lecturerID);
            statement.setString(2, deletedBy);
            statement.registerOutParameter(3, Types.SMALLINT);
            statement.execute();

            int isDeletedSuccessfully=  statement.getInt(3);
            if(isDeletedSuccessfully == 1){
                isDeleted = true;
            }else{
                isDeleted = false;
            }


        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            if (connection != null) {
                // closes the database connection
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

        }

        return isDeleted;
    }

    public boolean verifyNewTimetablerID(String newTimetablerID){
        boolean isNew = false;

        try{
            sql = "{call VERIFY_ENTERED_NEW_TIMETABLER_ID(?,?)}";
            CallableStatement statement = connection.prepareCall(sql);
            statement.setString(1, newTimetablerID);
            statement.registerOutParameter(2, Types.SMALLINT );
            statement.execute();

            int isAvailable = statement.getInt(2);

            if(isAvailable == 0){
                System.out.println(isAvailable);
                isNew = true;
            }else{
                System.out.println(isAvailable);
                isNew = false;
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            if (connection != null) {
                // closes the database connection
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }

        return isNew;
    }
    public boolean registerTimetabler(String timetablerID, String fName, String lName, String address, String mail, String pwd, String mobileNumber, String emrgencyNumber, String recordCreatedBy){
        boolean isSuccess = false;
        try {

            // constructs SQL statement
            sql = "{call REGISTER_NEW_TIMETABLER(?,?,?,?,?,?,?,?,?,?)}";
            CallableStatement statement = connection.prepareCall(sql);
            statement.setString(1, timetablerID);
            statement.setString(2, fName);
            statement.setString(3, lName);
            statement.setString(4, address);
            statement.setString(5, mail);
            statement.setString(6, pwd);
            statement.setString(7, mobileNumber);
            statement.setString(8, emrgencyNumber);
            statement.setString(9, recordCreatedBy);
            statement.registerOutParameter(10, Types.SMALLINT);
            statement.execute();

            int isAddedSucessfully=  statement.getInt(10);
            if(isAddedSucessfully == 1){
                isSuccess = true;
            }else{
                isSuccess = false;
            }


        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            if (connection != null) {
                // closes the database connection
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

        }
        return isSuccess;

    }
    public List<Timetabler> getAllTimetablers(){
        List<Timetabler> timetablerList = new ArrayList<>();

        try{
            sql = "{call GET_ALL_TIMETABLERS()}";
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet rs = statement.executeQuery();

            if(rs != null){
                while(rs.next()){
                    int id = rs.getInt("ID");
                    String studentID = rs.getString("TIMETABLER_ID");
                    String fName = rs.getString("FIRST_NAME");
                    String lName = rs.getString("LAST_NAME");
                    String address = rs.getString("ADDRESS");
                    String email = rs.getString("EMAIL");
                    String pwd = rs.getString("PASSWORD");
                    String mobileNum = rs.getString("CONTACT_NUMBER");
                    String emMobileNum = rs.getString("EMERGENCY_CONTACT_NUMBER");
                    int isActive = rs.getInt("IS_ACTIVE");
                    Timestamp created = rs.getTimestamp("CREATED");
                    String createdBy = rs.getString("CREATED_BY");
                    Timestamp modified = rs.getTimestamp("MODIFIED");
                    String modifiedBy = rs.getString("MODIFIED_BY");

                    Timetabler timetabler = new Timetabler(id,studentID,fName,lName,address,email,pwd,mobileNum,emMobileNum, isActive, created, createdBy,modified, modifiedBy);

                    timetablerList.add(timetabler);
                }

            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            if (connection != null) {
                // closes the database connection
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }


        return timetablerList;
    }
    public boolean updateTimetablerDetails(String timetablerID, String adminID, String email, String pass, String address, String mobile, String emMobile){
        boolean isUpdated = false;

        try {

            // constructs SQL statement
            sql = "{call UPDATE_TIMETABLER_DETAILS(?,?,?,?,?,?,?,?)}";
            CallableStatement statement = connection.prepareCall(sql);
            statement.setString(1, timetablerID);
            statement.setString(2, adminID);
            statement.setString(3, email);
            statement.setString(4, pass);
            statement.setString(5, address);
            statement.setString(6, mobile);
            statement.setString(7, emMobile);
            statement.registerOutParameter(8, Types.SMALLINT);
            statement.execute();

            int isUpdatedSuccessfully=  statement.getInt(8);
            if(isUpdatedSuccessfully == 1){
                isUpdated = true;
            }else{
                isUpdated = false;
            }


        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            if (connection != null) {
                // closes the database connection
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

        }

        return  isUpdated;
    }
    public boolean deleteSelectedTimetabler(String timetablerID, String deletedBy){
        boolean isDeleted = false;
        try {

            // constructs SQL statement
            sql = "{call DELETE_PARTICULAR_TIMETABLER(?,?,?)}";
            CallableStatement statement = connection.prepareCall(sql);
            statement.setString(1, timetablerID);
            statement.setString(2, deletedBy);
            statement.registerOutParameter(3, Types.SMALLINT);
            statement.execute();

            int isDeletedSuccessfully=  statement.getInt(3);
            if(isDeletedSuccessfully == 1){
                isDeleted = true;
            }else{
                isDeleted = false;
            }


        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            if (connection != null) {
                // closes the database connection
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

        }

        return isDeleted;
    }

    public boolean addNewCourse(String courseName, String courseField, String addedBy){
        boolean isAdded = false;
        try {

            // constructs SQL statement
            sql = "{call REGISTER_NEW_COURSE_DETAILS(?,?,?,?)}";
            CallableStatement statement = connection.prepareCall(sql);
            statement.setString(1, courseName);
            statement.setString(2, courseField);
            statement.setString(3, addedBy);
            statement.registerOutParameter(4, Types.SMALLINT);
            statement.execute();

            int isAddedSucessfully=  statement.getInt(4);
            if(isAddedSucessfully == 1){
                isAdded = true;
            }else{
                isAdded = false;
            }


        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            if (connection != null) {
                // closes the database connection
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

        }


        return isAdded;
    }
    public boolean deleteSelectedCourse(int courseID, String deletedBy){
        boolean isDeleted = false;
        try {

            // constructs SQL statement
            sql = "{call DELETE_COURSE_BY_ID(?,?,?)}";
            CallableStatement statement = connection.prepareCall(sql);
            statement.setInt(1, courseID);
            statement.setString(2, deletedBy);
            statement.registerOutParameter(3, Types.SMALLINT);
            statement.execute();

            int isDeletedSuccessfully=  statement.getInt(3);
            if(isDeletedSuccessfully == 1){
                isDeleted = true;
            }else{
                isDeleted = false;
            }


        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            if (connection != null) {
                // closes the database connection
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }

        return isDeleted;
    }
    public boolean updateSelectedCourseDetails(int courseID, String courseName, String courseField, String editedBy){
        boolean isEditSuccess = false;

        try {

            // constructs SQL statement
            sql = "{call UPDATE_COURSE_DETAILS(?,?,?,?,?)}";
            CallableStatement statement = connection.prepareCall(sql);
            statement.setInt(1, courseID);
            statement.setString(2, courseName);
            statement.setString(3, courseField);
            statement.setString(4, editedBy);
            statement.registerOutParameter(5, Types.SMALLINT);
            statement.execute();

            int isEditedSuccessfully=  statement.getInt(5);
            if(isEditedSuccessfully == 1){
                isEditSuccess = true;
            }else{
                isEditSuccess = false;
            }


        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            if (connection != null) {
                // closes the database connection
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

        }

        return isEditSuccess;
    }
    
    public boolean addNewModule(int courseID, String moduleName, String moduleDescription, String lecturerID, int classsID,String addedBy){
        boolean isAdded = false;
        try {

            // constructs SQL statement
            sql = "{call REGISTER_NEW_MODULE_DETAILS(?,?,?,?,?,?,?)}";
            CallableStatement statement = connection.prepareCall(sql);
            statement.setInt(1, courseID);
            statement.setString(2, moduleName);
            statement.setString(3, moduleDescription);
            statement.setString(4, lecturerID);
            statement.setInt(5, classsID);
            statement.setString(6, addedBy);
            statement.registerOutParameter(7, Types.SMALLINT);
            statement.execute();

            int isAddedSucessfully=  statement.getInt(7);
            if(isAddedSucessfully == 1){
                isAdded = true;
            }else{
                isAdded = false;
            }


        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            if (connection != null) {
                // closes the database connection
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

        }


        return isAdded;
    }
    public boolean deleteSelectedModule(int courseID, int moduleID, String deletedBy){
        boolean isDeleted = false;
        try {

            // constructs SQL statement
            sql = "{call DELETE_MODULE_BY_ID(?,?,?,?)}";
            CallableStatement statement = connection.prepareCall(sql);
            statement.setInt(1, moduleID);
            statement.setInt(2, courseID);
            statement.setString(3, deletedBy);
            statement.registerOutParameter(4, Types.SMALLINT);
            statement.execute();

            int isDeletedSuccessfully=  statement.getInt(4);
            if(isDeletedSuccessfully == 1){
                isDeleted = true;
            }else{
                isDeleted = false;
            }


        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            if (connection != null) {
                // closes the database connection
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }

        return isDeleted;
    }
    public boolean updateSelectedModuleDetails(int moduleID, int courseID, String moduleNam, String moduleDescription,String lecturerID,int classID, String editedBy){
        boolean isEditSuccess = false;

        try {

            // constructs SQL statement
            sql = "{call UPDATE_MODULE_BY_ID(?,?,?,?,?,?,?,?)}";
            CallableStatement statement = connection.prepareCall(sql);
            statement.setInt(1, moduleID);
            statement.setInt(2, courseID);
            statement.setString(3, moduleNam);
            statement.setString(4, moduleDescription);
            statement.setString(5, lecturerID);
            statement.setInt(6, classID);
            statement.setString(7, editedBy);
            statement.registerOutParameter(8, Types.SMALLINT);
            statement.execute();

            int isEditedSuccessfully=  statement.getInt(8);
            if(isEditedSuccessfully == 1){
                isEditSuccess = true;
            }else{
                isEditSuccess = false;
            }


        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            if (connection != null) {
                // closes the database connection
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

        }

        return isEditSuccess;
    }
    
    public boolean addNewClass(String classroomName, int floorLevel, int isAllocated, String addedBy){
        boolean isAdded = false;
        try {

            // constructs SQL statement
            sql = "{call ADD_NEW_CLASS(?,?,?,?,?)}";
            CallableStatement statement = connection.prepareCall(sql);
            statement.setString(1, classroomName);
            statement.setInt(2, floorLevel);
            statement.setInt(3, isAllocated);
            statement.setString(4, addedBy);
            statement.registerOutParameter(5, Types.SMALLINT);
            statement.execute();

            int isAddedSucessfully=  statement.getInt(5);
            if(isAddedSucessfully == 1){
                isAdded = true;
            }else{
                isAdded = false;
            }


        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            if (connection != null) {
                // closes the database connection
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

        }


        return isAdded;
    }
    public boolean verifySelectedClassroomSafeToDelete(int classID){
        boolean isSafeToDelete = false;
        try {

            // constructs SQL statement
            sql = "{call VERIFY_SELECTED_CLASSROOM_IS_SAFE_TO_DELETE(?,?)}";
            CallableStatement statement = connection.prepareCall(sql);
            statement.setInt(1, classID);
            statement.registerOutParameter(2, Types.SMALLINT);
            statement.execute();

            int isSafe=  statement.getInt(2);
            if(isSafe == 1){
                isSafeToDelete = true;
            }else{
                isSafeToDelete = false;
            }


        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            if (connection != null) {
                // closes the database connection
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }

        return isSafeToDelete;
    }
    public boolean deleteSelectedClassroom(int classID, String deletedBy){
        boolean isDeleted = false;
        try {

            // constructs SQL statement
            sql = "{call DELETE_SELECTED_CLASSROOM(?,?,?)}";
            CallableStatement statement = connection.prepareCall(sql);
            statement.setInt(1, classID);
            statement.setString(2, deletedBy);
            statement.registerOutParameter(3, Types.SMALLINT);
            statement.execute();

            int isDeletedSuccessfully=  statement.getInt(3);
            if(isDeletedSuccessfully == 1){
                isDeleted = true;
            }else{
                isDeleted = false;
            }


        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            if (connection != null) {
                // closes the database connection
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }

        return isDeleted;
    }
    public boolean updateSelectedClassroom(int classID, String className, int floorLevel, int isAllocated, String editedBy){
        boolean isEditSuccess = false;

        try {

            // constructs SQL statement
            sql = "{call UPDATE_SELECTED_CLASSROOM(?,?,?,?,?,?)}";
            CallableStatement statement = connection.prepareCall(sql);
            statement.setInt(1, classID);
            statement.setString(2, className);
            statement.setInt(3, floorLevel);
            statement.setInt(4, isAllocated);
            statement.setString(5, editedBy);
            statement.registerOutParameter(6, Types.SMALLINT);
            statement.execute();

            int isEditedSuccessfully=  statement.getInt(6);
            if(isEditedSuccessfully == 1){
                isEditSuccess = true;
            }else{
                isEditSuccess = false;
            }


        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            if (connection != null) {
                // closes the database connection
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

        }

        return isEditSuccess;
    }

    public boolean verifyNewBadgeName(String newBadgeName){
        boolean isNew = false;

        try{
            sql = "{call VERIFY_ENTERED_NEW_BADGE_NAME(?,?)}";
            CallableStatement statement = connection.prepareCall(sql);
            statement.setString(1, newBadgeName);
            statement.registerOutParameter(2, Types.SMALLINT );
            statement.execute();

            int isAvailable = statement.getInt(2);

            if(isAvailable == 0){
                System.out.println(isAvailable);
                isNew = true;
            }else{
                System.out.println(isAvailable);
                isNew = false;
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            if (connection != null) {
                // closes the database connection
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }

        return isNew;
    }
    public boolean addNewBadge(int courseID, String badgeName, int badgeYear, String addedBy){
        boolean isAdded = false;
        try {

            // constructs SQL statement
            sql = "{call REGISTER_NEW_BADGE(?,?,?,?,?)}";
            CallableStatement statement = connection.prepareCall(sql);
            statement.setInt(1, courseID);
            statement.setString(2, badgeName);
            statement.setInt(3, badgeYear);
            statement.setString(4, addedBy);
            statement.registerOutParameter(5, Types.SMALLINT);
            statement.execute();

            int isAddedSucessfully=  statement.getInt(5);
            if(isAddedSucessfully == 1){
                isAdded = true;
            }else{
                isAdded = false;
            }


        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            if (connection != null) {
                // closes the database connection
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

        }


        return isAdded;
    }
    public boolean updateSelectedBadgeDetails(int badgeID,int courseID, String badgeName, int badgeYear, String editedBy){
        boolean isEditSuccess = false;

        try {

            // constructs SQL statement
            sql = "{call UPDATE_BADGE_DETAILS(?,?,?,?,?,?)}";
            CallableStatement statement = connection.prepareCall(sql);
            statement.setInt(1, badgeID);
            statement.setInt(2, courseID);
            statement.setString(3, badgeName);
            statement.setInt(4, badgeYear);
            statement.setString(5, editedBy);
            statement.registerOutParameter(6, Types.SMALLINT);
            statement.execute();

            int isEditedSuccessfully=  statement.getInt(6);
            if(isEditedSuccessfully == 1){
                isEditSuccess = true;
            }else{
                isEditSuccess = false;
            }


        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            if (connection != null) {
                // closes the database connection
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

        }

        return isEditSuccess;
    }
    public boolean deleteSelectedBadge(int badgeID, int courseID, String deletedBy){
        boolean isDeleted = false;
        try {

            // constructs SQL statement
            sql = "{call DELETE_BADGE_BY_ID(?,?,?,?)}";
            CallableStatement statement = connection.prepareCall(sql);
            statement.setInt(1, badgeID);
            statement.setInt(2, courseID);
            statement.setString(3, deletedBy);
            statement.registerOutParameter(4, Types.SMALLINT);
            statement.execute();

            int isDeletedSuccessfully=  statement.getInt(4);
            if(isDeletedSuccessfully == 1){
                isDeleted = true;
            }else{
                isDeleted = false;
            }


        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            if (connection != null) {
                // closes the database connection
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }

        return isDeleted;
    }

    public boolean verifyBadgeAlreadyHaveATimetable(int courseID, int badgeID){
        boolean isAvailable = false;
        try {

            // constructs SQL statement
            sql = "{call VERIFY_WHETHER_SELECTED_BADGE_ALREADY_HAS_A_TIMETABLE(?,?,?)}";
            CallableStatement statement = connection.prepareCall(sql);
            statement.setInt(1, courseID);
            statement.setInt(2, badgeID);
            statement.registerOutParameter(3, Types.SMALLINT);
            statement.execute();

            int isAlreadyAvaliable = statement.getInt(3);
            if(isAlreadyAvaliable == 1){
                // The selected module is already available to the same duration on the same day and time!
                isAvailable = true;
            }else{
                isAvailable = false;
            }

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            if (connection != null) {
                // closes the database connection
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }


        return isAvailable;

    }
    public boolean verifyNewTimetableRecord(int courseID, int badgeID,String weekDay, int duration, Time startTime, Time endTime, int moduleID){
        boolean isAvailable = false;
        try {

            // constructs SQL statement
            sql = "{call VERIFY_ENTERED_NEW_TIME_TABLE_RECORD(?,?,?,?,?,?,?,?)}";
            CallableStatement statement = connection.prepareCall(sql);
            statement.setInt(1, courseID);
            statement.setInt(2, badgeID);
            statement.setString(3, weekDay);
            statement.setInt(4, duration);
            statement.setTime(5, startTime);
            statement.setTime(6, endTime);
            statement.setInt(7, moduleID);
            statement.registerOutParameter(8, Types.SMALLINT);
            statement.execute();

            int isAlreadyAvaliable = statement.getInt(8);
            if(isAlreadyAvaliable == 1){
                // The selected module is already available to the same duration on the same day and time!
                isAvailable = true;
            }else{
                isAvailable = false;
            }

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            if (connection != null) {
                // closes the database connection
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }


        return isAvailable;

    }
    public boolean insertTimetableRecord(int courseID, int badgeID,int weekIDInt,String weekDay, int duration, Time startTime, Time endTime, int moduleID, String addedBy){
        boolean isAdded = false;
        try {

            // constructs SQL statement
            sql = "{call INSERT_TO_COURSE_TIMETABLE(?,?,?,?,?,?,?,?,?,?)}";
            CallableStatement statement = connection.prepareCall(sql);
            statement.setInt(1, courseID);
            statement.setInt(2, badgeID);
            statement.setInt(3, weekIDInt);
            statement.setString(4, weekDay);
            statement.setInt(5, duration);
            statement.setTime(6, startTime);
            statement.setTime(7, endTime);
            statement.setInt(8, moduleID);
            statement.setString(9, addedBy);
            statement.registerOutParameter(10, Types.SMALLINT);
            statement.execute();

            int isAddedSuccessfully=  statement.getInt(10);
            if(isAddedSuccessfully == 1){
                isAdded = true;
            }else{
                isAdded = false;
            }


        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            if (connection != null) {
                // closes the database connection
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }


        return isAdded;

    }
    public List<CourseTimetable> getStudentTimeTable(int courseID, int badgeID ){
        List<CourseTimetable> courseTimetableList = new ArrayList<>();

        try {

            // constructs SQL statement
            sql = "{call GET_STUDENT_COURSE_TIMETABLE(?,?)}";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, courseID);
            statement.setInt(2, badgeID);
            ResultSet result = statement.executeQuery();
            if(result != null){
                System.out.println("Data found!!");
                while (result.next()) {
                    int id =  result.getInt("ID");
                    int courseIDVal = result.getInt("COURSE_ID");
                    String courseName = result.getString("COURSE_NAME");
                    int badgeIDVal = result.getInt("BADGE_ID");
                    String badgeName = result.getString("BADGE_NAME");
                    int weekID = result.getInt("WEEK_DAY_ID");
                    String weekDay = result.getString("WEEK_DAY");
                    int duration = result.getInt("DURATION");
                    Time startTime = result.getTime("FROM_TIME");
                    Time endTime = result.getTime("TO_TIME");
                    int moduleID = result.getInt("MODULE_ID");
                    String moduleName = result.getString("MODULE_NAME");
                    String lecturerID = result.getString("LECTURER_ID");
                    String lecturerFName = result.getString("FIRST_NAME");
                    String lecturerLName =  result.getString("LAST_NAME");
                    String lecturerFullName = lecturerFName + " " + lecturerLName;
                    int isActive = result.getInt("IS_ACTIVE");
                    Timestamp created = result.getTimestamp("CREATED");
                    String createdBy = result.getString("CREATED_BY");
                    Timestamp modified = result.getTimestamp("MODIFIEED");
                    String modifiedBy = result.getString("MODIFIED_BY");

                    CourseTimetable timetableRecord = new CourseTimetable(id,courseIDVal,courseName,badgeIDVal,badgeName,weekID,weekDay,duration,startTime,endTime,moduleID,moduleName,lecturerID,lecturerFName,lecturerLName,lecturerFullName,isActive,created,createdBy,modified,modifiedBy);

                    courseTimetableList.add(timetableRecord);
                }
            }else{
                System.out.println("No Data found!!");
            }

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            if (connection != null) {
                // closes the database connection
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }

        return courseTimetableList;
    }
    public List<CourseTimetable> getLecturerTimeTable(String lecturerID){
        List<CourseTimetable> courseTimetableList = new ArrayList<>();

        try {

            // constructs SQL statement
            sql = "{call GET_LECTURER_COURSE_TIMETABLE(?)}";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, lecturerID);
            ResultSet result = statement.executeQuery();
            if(result != null){
                System.out.println("Data found!!");
                while (result.next()) {
                    int id =  result.getInt("ID");
                    int courseIDVal = result.getInt("COURSE_ID");
                    String courseName = result.getString("COURSE_NAME");
                    int badgeIDVal = result.getInt("BADGE_ID");
                    String badgeName = result.getString("BADGE_NAME");
                    int weekID = result.getInt("WEEK_DAY_ID");
                    String weekDay = result.getString("WEEK_DAY");
                    int duration = result.getInt("DURATION");
                    Time startTime = result.getTime("FROM_TIME");
                    Time endTime = result.getTime("TO_TIME");
                    int moduleID = result.getInt("MODULE_ID");
                    String moduleName = result.getString("MODULE_NAME");
                    String lecturerIDVal = result.getString("LECTURER_ID");
                    String lecturerFName = result.getString("FIRST_NAME");
                    String lecturerLName =  result.getString("LAST_NAME");
                    String lecturerFullName = lecturerFName + " " + lecturerLName;
                    int isActive = result.getInt("IS_ACTIVE");
                    Timestamp created = result.getTimestamp("CREATED");
                    String createdBy = result.getString("CREATED_BY");
                    Timestamp modified = result.getTimestamp("MODIFIEED");
                    String modifiedBy = result.getString("MODIFIED_BY");

                    CourseTimetable timetableRecord = new CourseTimetable(id,courseIDVal,courseName,badgeIDVal,badgeName,weekID,weekDay,duration,startTime,endTime,moduleID,moduleName,lecturerIDVal,lecturerFName,lecturerLName,lecturerFullName,isActive,created,createdBy,modified,modifiedBy);

                    courseTimetableList.add(timetableRecord);
                }
            }else{
                System.out.println("No Data found!!");
            }

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            if (connection != null) {
                // closes the database connection
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }

        return courseTimetableList;
    }
    public boolean deleteBadgeTimetable(int courseID, int badgeID, String deletedBy){
        boolean isDeleted = false;
        try {

            // constructs SQL statement
            sql = "{call DELETE_SELECTED_BADGE_TIMETABLE(?,?,?,?)}";
            CallableStatement statement = connection.prepareCall(sql);
            statement.setInt(1, courseID);
            statement.setInt(2, badgeID);
            statement.setString(3, deletedBy);
            statement.registerOutParameter(4, Types.SMALLINT);
            statement.execute();

            int isDeletedSuccessfully=  statement.getInt(4);
            if(isDeletedSuccessfully == 1){
                isDeleted = true;
            }else{
                isDeleted = false;
            }


        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            if (connection != null) {
                // closes the database connection
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }

        return isDeleted;
    }

    public boolean updateSelectedClassroomAsAllocated(int classID, int isAllocated, String editedBy){
        boolean isSuccess = false;

        try {

            // constructs SQL statement
            sql = "{call UPDATE_SELECTED_CLASSROOM_AS_ALLOCATED(?,?,?,?)}";
            CallableStatement statement = connection.prepareCall(sql);
            statement.setInt(1, classID);
            statement.setInt(2, isAllocated);
            statement.setString(3, editedBy);
            statement.registerOutParameter(4, Types.SMALLINT);
            statement.execute();

            int isUpdated=  statement.getInt(4);
            if(isUpdated == 1){
                isSuccess = true;
            }else{
                isSuccess = false;
            }


        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            if (connection != null) {
                // closes the database connection
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

        }

        return isSuccess;
    }
}
