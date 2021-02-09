package StudentTimetablingSystem.model;

import java.sql.Timestamp;

public class Customer {
    private int id;
    private String fName;
    private String lName;
    private String drivingLicenceNum;
    private String drivingLicenceImage;
    private String otherFormOfIdentityImage;
    private String address;
    private String mobileNumber;
    private String email;
    private String password;
    private int isVerified;
    private int isActive;
    private Timestamp created;
    private String createdBy;
    private Timestamp modified;
    private String modifiedBy;

    private byte[] licenceImg;
    private byte[] otherImg;


    public Customer(String fName, String lName, String drivingLicenceNum, String drivingLicenceImage, String otherFormOfIdentityImage, String address, String mobileNumber, String email, String password) {
        this.fName = fName;
        this.lName = lName;
        this.drivingLicenceNum = drivingLicenceNum;
        this.drivingLicenceImage = drivingLicenceImage;
        this.otherFormOfIdentityImage = otherFormOfIdentityImage;
        this.address = address;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.password = password;
    }

    public Customer(int id, String fName, String lName, String drivingLicenceNum, String drivingLicenceImage, String otherFormOfIdentityImage, String address, String mobileNumber, String email, String password, int isVerified, int isActive, Timestamp created, String createdBy, Timestamp modified, String modifiedBy) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.drivingLicenceNum = drivingLicenceNum;
        this.drivingLicenceImage = drivingLicenceImage;
        this.otherFormOfIdentityImage = otherFormOfIdentityImage;
        this.address = address;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.password = password;
        this.isVerified = isVerified;
        this.isActive = isActive;
        this.created = created;
        this.createdBy = createdBy;
        this.modified = modified;
        this.modifiedBy = modifiedBy;
    }

    public Customer(int id, String fName, String lName, String drivingLicenceNum, byte[] licenceImg, byte[] otherImg, String address, String mobileNumber, String email, String password, int isVerified, int isActive, Timestamp created, String createdBy, Timestamp modified, String modifiedBy) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.drivingLicenceNum = drivingLicenceNum;
        this.address = address;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.password = password;
        this.isVerified = isVerified;
        this.isActive = isActive;
        this.created = created;
        this.createdBy = createdBy;
        this.modified = modified;
        this.modifiedBy = modifiedBy;
        this.licenceImg = licenceImg;
        this.otherImg = otherImg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getDrivingLicenceNum() {
        return drivingLicenceNum;
    }

    public void setDrivingLicenceNum(String drivingLicenceNum) {
        this.drivingLicenceNum = drivingLicenceNum;
    }

    public String getDrivingLicenceImage() {
        return drivingLicenceImage;
    }

    public void setDrivingLicenceImage(String drivingLicenceImage) {
        this.drivingLicenceImage = drivingLicenceImage;
    }

    public String getOtherFormOfIdentityImage() {
        return otherFormOfIdentityImage;
    }

    public void setOtherFormOfIdentityImage(String otherFormOfIdentityImage) {
        this.otherFormOfIdentityImage = otherFormOfIdentityImage;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
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

    public int getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(int isVerified) {
        this.isVerified = isVerified;
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
