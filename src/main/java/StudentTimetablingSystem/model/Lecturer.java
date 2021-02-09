package StudentTimetablingSystem.model;

import java.sql.Timestamp;

public class Lecturer {
    private int id;
    private String lecturerID;
    private String fName;
    private String lName;
    private String address;
    private String email;
    private String password;
    private String contactNumber;
    private String emergencyNum;
    private String profileImgString;
    private int isActive;
    private Timestamp created;
    private String createdBy;
    private Timestamp modified;
    private String modifiedBy;

    private String userType;

    public Lecturer(int id, String lecturerID, String fName, String lName, String address, String email, String password, String contactNumber, String emergencyNum,int isActive, Timestamp created, String createdBy, Timestamp modified, String modifiedBy) {
        this.id = id;
        this.lecturerID = lecturerID;
        this.fName = fName;
        this.lName = lName;
        this.address = address;
        this.email = email;
        this.password = password;
        this.contactNumber = contactNumber;
        this.emergencyNum = emergencyNum;
        this.isActive = isActive;
        this.created = created;
        this.createdBy = createdBy;
        this.modified = modified;
        this.modifiedBy = modifiedBy;

        this.userType = "Lecturer";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLecturerID() {
        return lecturerID;
    }

    public void setLecturerID(String lecturerID) {
        this.lecturerID = lecturerID;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmergencyNum() {
        return emergencyNum;
    }

    public void setEmergencyNum(String emergencyNum) {
        this.emergencyNum = emergencyNum;
    }

    public String getProfileImgString() {
        return profileImgString;
    }

    public void setProfileImgString(String profileImgString) {
        this.profileImgString = profileImgString;
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
