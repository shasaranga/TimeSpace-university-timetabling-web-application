package StudentTimetablingSystem.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Booking {

    private int bookingID;
    private int classID;
    private String className;
    private int badgeID;
    private String badgeName;
    private int lecturerID;
    private String lecturerName;
    private int duration;
    private Timestamp startDate;
    private Timestamp endDate;
    private int isActive;
    private Timestamp created;
    private String createdBy;
    private Timestamp modified;
    private String modifiedBy;

    private LocalDateTime startDateLocal;
    private LocalDateTime endDateLocal;
    private LocalDateTime createdLocal;
    private LocalDateTime modifiedLocal;

    private String startDateLocalString;
    private String endDateLocalString;
    private String createdLocalString;
    private String modifiedLocalString;


    private String bookingIDString;

    public Booking(int bookingID, String bookingIDString, String className, String badgeName, String lecturerName, int duration, Timestamp startDate , Timestamp endDate, int isActive, Timestamp created, String createdBy, Timestamp modified, String modifiedBy) {
        this.bookingID = bookingID;
        this.bookingIDString = bookingIDString;
        this.className = className;
        this.badgeName = badgeName;
        this.lecturerName = lecturerName;
        this.duration = duration;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isActive = isActive;
        this.created = created;
        this.createdBy = createdBy;
        this.modified = modified;
        this.modifiedBy = modifiedBy;
    }
    public Booking(int bookingID, int classroomID, String className, String badgeName, String lecturerName, int duration, Timestamp startDate , Timestamp endDate, int isActive, Timestamp created, String createdBy, Timestamp modified, String modifiedBy) {
        this.bookingID = bookingID;
        this.classID = classroomID;
        this.bookingIDString = bookingIDString;
        this.className = className;
        this.badgeName = badgeName;
        this.lecturerName = lecturerName;
        this.duration = duration;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isActive = isActive;
        this.created = created;
        this.createdBy = createdBy;
        this.modified = modified;
        this.modifiedBy = modifiedBy;
    }

    public Booking(int bookingID, String className, String badgeName, String lecturerName, int duration, int isActive, Timestamp created, String createdBy, Timestamp modified, String modifiedBy) {
        this.bookingID = bookingID;
        this.className = className;
        this.badgeName = badgeName;
        this.lecturerName = lecturerName;
        this.duration = duration;
        this.isActive = isActive;
        this.created = created;
        this.createdBy = createdBy;
        this.modified = modified;
        this.modifiedBy = modifiedBy;
    }

    public Booking(int bookingID, int classroomID, String className, String badgeName, String lecturerName, int duration, LocalDateTime startDate , LocalDateTime endDate, int isActive, LocalDateTime created, String createdBy, LocalDateTime modified, String modifiedBy) {
        this.bookingID = bookingID;
        this.classID = classroomID;
        this.bookingIDString = bookingIDString;
        this.className = className;
        this.badgeName = badgeName;
        this.lecturerName = lecturerName;
        this.duration = duration;
        this.startDateLocalString = startDate.toString();
        this.startDateLocal = startDate;
        this.endDateLocalString = endDate.toString();
        this.endDateLocal = endDate;
        this.isActive = isActive;
        this.createdLocal = created;
        this.createdLocalString = created.toString();
        this.createdBy = createdBy;
        this.modifiedLocal = modified;
        this.modifiedLocalString = modified.toString();
        this.modifiedBy = modifiedBy;
    }
    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public String getBookingIDString() {
        return bookingIDString;
    }

    public void setBookingIDString(String bookingIDString) {
        this.bookingIDString = bookingIDString;
    }

    public int getClassID() {
        return classID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getBadgeID() {
        return badgeID;
    }

    public void setBadgeID(int badgeID) {
        this.badgeID = badgeID;
    }

    public String getBadgeName() {
        return badgeName;
    }

    public void setBadgeName(String badgeName) {
        this.badgeName = badgeName;
    }

    public int getLecturerID() {
        return lecturerID;
    }

    public void setLecturerID(int lecturerID) {
        this.lecturerID = lecturerID;
    }

    public String getLecturerName() {
        return lecturerName;
    }

    public void setLecturerName(String lecturerName) {
        this.lecturerName = lecturerName;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getModified() {
        return modified;
    }

    public void setModified(Timestamp modified) {
        this.modified = modified;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
}
