package TimeOffManagement_Main_Package.datasource;

/**
 * Class containing all the datafields,
 * that is returned for querying one record in the users table
 * for storing it for processing
 */

public class User {

    private int idUser;
    private int employeeNumber;
    private String employeeName;
    private int managerEmployeeNumber;
    private String managerName;
    private String securityRole;
    private String userActive;
    private String userCreationDate;
    private String userDeActivationDate;
    private String userLatestChangeDate;
    private String userLatestChangeByUser;
    private int userDaysAvailable;
    private int userDaysTaken;
    private int userDaysForYear;
    private String userName;
    private String password;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public int getManagerEmployeeNumber() {
        return managerEmployeeNumber;
    }

    public void setManagerEmployeeNumber(int managerEmployeeNumber) {
        this.managerEmployeeNumber = managerEmployeeNumber;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getSecurityRole() {
        return securityRole;
    }

    public void setSecurityRole(String securityRole) {
        this.securityRole = securityRole;
    }

    public String getUserCreationDate() {
        return userCreationDate;
    }

    public void setUserCreationDate(String userCreationDate) {
        this.userCreationDate = userCreationDate;
    }

    public String getUserDeActivationDate() {
        return userDeActivationDate;
    }

    public void setUserDeActivationDate(String userDeActivationDate) {
        this.userDeActivationDate = userDeActivationDate;
    }

    public String getUserLatestChangeDate() {
        return userLatestChangeDate;
    }

    public void setUserLatestChangeDate(String userLatestChangeDate) {
        this.userLatestChangeDate = userLatestChangeDate;
    }

    public String getUserLatestChangeByUser() {
        return userLatestChangeByUser;
    }

    public void setUserLatestChangeByUser(String userLatestChangeByUser) {
        this.userLatestChangeByUser = userLatestChangeByUser;
    }

    public String getUserActive() {
        return userActive;
    }

    public void setUserActive(String userActive) {
        this.userActive = userActive;
    }

    public int getUserDaysAvailable() {
        return userDaysAvailable;
    }

    public void setUserDaysAvailable(int userDaysAvailable) {
        this.userDaysAvailable = userDaysAvailable;
    }

    public int getUserDaysTaken() {
        return userDaysTaken;
    }

    public void setUserDaysTaken(int userDaysTaken) {
        this.userDaysTaken = userDaysTaken;
    }

    public int getUserDaysForYear() {
        return userDaysForYear;
    }

    public void setUserDaysForYear(int userDaysForYear) {
        this.userDaysForYear = userDaysForYear;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "idUser=" + idUser +
                ", employeeNumber=" + employeeNumber +
                ", employeeName='" + employeeName + '\'' +
                ", managerEmployeeNumber=" + managerEmployeeNumber +
                ", managerName='" + managerName + '\'' +
                ", securityRole='" + securityRole + '\'' +
                ", userActive='" + userActive + '\'' +
                ", userCreationDate='" + userCreationDate + '\'' +
                ", userDeActivationDate='" + userDeActivationDate + '\'' +
                ", userLatestChangeDate='" + userLatestChangeDate + '\'' +
                ", userLatestChangeByUser='" + userLatestChangeByUser + '\'' +
                ", userDaysAvailable=" + userDaysAvailable +
                ", userDaysTaken=" + userDaysTaken +
                ", userDaysForYear=" + userDaysForYear +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
