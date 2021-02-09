package StudentTimetablingSystem.model;

import java.sql.Timestamp;

public class Course {
    private int id;
    private String courseName;
    private String courseField;
    private int isActive;
    private Timestamp created;
    private String createdBy;
    private Timestamp modified;
    private String modifiedBy;

    public Course(int id, String courseName, String courseField, int isActive, Timestamp created, String createdBy, Timestamp modified, String modifiedBy) {
        this.id = id;
        this.courseName = courseName;
        this.courseField = courseField;
        this.isActive = isActive;
        this.created = created;
        this.createdBy = createdBy;
        this.modified = modified;
        this.modifiedBy = modifiedBy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseField() {
        return courseField;
    }

    public void setCourseField(String courseField) {
        this.courseField = courseField;
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
