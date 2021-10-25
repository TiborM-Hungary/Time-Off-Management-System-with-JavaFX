package TimeOffManagement_Main_Package.datasource;

/**
 * Class containing all the datafields,
 * that is returned for querying one record in the timeoffs table
 * for storing it for processing
 */

public class TimeOff {

    private int entryNumber;
    private int employeeNumber;
    private String employeeName;
    private int managerEmployeeNumber;
    private String managerName;
    private String startOfLeave;
    private String endOfLeave;
    private String leaveStatus;
    private String dateEntryCreated;
    private String dateEntryLatestChange;
    private String dateEntryLatestChangeBy;
    private int durationOfLeave;

    public int getEntryNumber() {
        return entryNumber;
    }

    public void setEntryNumber(int entryNumber) {
        this.entryNumber = entryNumber;
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

    public String getStartOfLeave() {
        return startOfLeave;
    }

    public void setStartOfLeave(String startOfLeave) {
        this.startOfLeave = startOfLeave;
    }

    public String getEndOfLeave() {
        return endOfLeave;
    }

    public void setEndOfLeave(String endOfLeave) {
        this.endOfLeave = endOfLeave;
    }

    public String getLeaveStatus() {
        return leaveStatus;
    }

    public void setLeaveStatus(String leaveStatus) {
        this.leaveStatus = leaveStatus;
    }

    public String getDateEntryCreated() {
        return dateEntryCreated;
    }

    public void setDateEntryCreated(String dateEntryCreated) {
        this.dateEntryCreated = dateEntryCreated;
    }

    public String getDateEntryLatestChange() {
        return dateEntryLatestChange;
    }

    public void setDateEntryLatestChange(String dateEntryLatestChange) {
        this.dateEntryLatestChange = dateEntryLatestChange;
    }

    public String getDateEntryLatestChangeBy() {
        return dateEntryLatestChangeBy;
    }

    public void setDateEntryLatestChangeBy(String dateEntryLatestChangeBy) {
        this.dateEntryLatestChangeBy = dateEntryLatestChangeBy;
    }

    public int getDurationOfLeave() {
        return durationOfLeave;
    }

    public void setDurationOfLeave(int durationOfLeave) {
        this.durationOfLeave = durationOfLeave;
    }
}
