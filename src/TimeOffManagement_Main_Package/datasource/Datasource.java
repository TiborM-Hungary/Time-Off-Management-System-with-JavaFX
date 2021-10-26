package TimeOffManagement_Main_Package.datasource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class: Datasource
 * <p>
 * This class is used for SQL methods and handles communication with the database
 * <p>
 * This class contains:
 * - constants for the db path, table and column names,
 * because, if any of them changes, you need only to update the constants
 * - the connection to the DB and the open and close method using the connection
 * - the queries for retrieving the data and putting them into an ArrayList,
 * the ArrayList is made of the classes for the data,
 * the classes has the attributes for the data in the given table
 */

/**
 * This is class is a "Singleton" type class
 * <p>
 * https://www.geeksforgeeks.org/singleton-class-java/
 * <p>
 * In object-oriented programming, a singleton class is a class that can have only one object (an instance of the class)
 * at a time.
 * After first time, if we try to instantiate the Singleton class, the new variable also points to the
 * first instance created. So whatever modifications we do to any variable inside the class through any instance,
 * it affects the variable of the single instance created and is visible if we access that variable
 * through any variable of that class type defined.
 * To design a singleton class:
 * <p>
 * Make constructor as private.
 * Write a static method that has return type object of this singleton class.
 */

public class Datasource {

    //****************************************************************************************
    //****************************************************************************************
    //Singleton class setup
    //create a variable that can hold the instance of the class and create the instance when the class loaded
    //Lazy initialization
    private static Datasource instance;

    //private constructor, only this class can create an instance of it
    private Datasource() {
    }

    //the method that any other class can call to access the instance of the class
//    the other classes will access the instance and its method like:
//    Datasource.getInstance().methodName
    public static Datasource getDatasource_instance() {
        //if datasource_instance does not exist
        //create instance and call the Datasource open method, to connect to DB
        if (instance == null) {

            instance = new Datasource();
            instance.open();
        }

        return instance;
    }
    //****************************************************************************************
    //****************************************************************************************

    //Database file and Database file path
    public static final String DB_NAME = "TimeOffManagementSystem-SQLiteDatabase.db";
    /**
     * Import DB to the project prior to release
     */

//    DB path on HDD, needs JDBC instead of MariaDB:
//    public static final String CONNECTION_STRING = "jdbc:sqlite:D:\\Suli\\0 - Szakdolgozat\\" + DB_NAME;

    //DB path for server:

    String url = "jdbc:mariadb://127.0.0.1:3306/main";
    String user = "root";
    String password = "admin";

    //Tables and the table content constants

    //users table
    public static final String TABLE_USERS = "users";
    public static final String COLUMN_USERS_ID = "idUser";
    public static final String COLUMN_USERS_EMPLOYEE_NUMBER = "employeeNumber";
    public static final String COLUMN_USERS_EMPLOYEE_NAME = "employeeName";
    public static final String COLUMN_USERS_MANAGER_EMPLOYEE_NUMBER = "managerEmployeeNumber";
    public static final String COLUMN_USERS_MANAGER_NAME = "managerName";
    public static final String COLUMN_USERS_SECURITY_ROLE = "securityRole";
    public static final String COLUMN_USERS_USER_ACTIVE = "userActive";
    public static final String COLUMN_USERS_USER_CREATION_DATE = "userCreationDate";
    public static final String COLUMN_USERS_USER_DEACTIVATION_DATE = "userDeActivationDate";
    public static final String COLUMN_USERS_USER_LATEST_CHANGE_DATE = "userLatestChangeDate";
    public static final String COLUMN_USERS_LATEST_CHANGE_BY_USER = "userLatestChangeByUser";
    public static final String COLUMN_USERS_DAYS_AVAILABLE = "userDaysAvailable";
    public static final String COLUMN_USERS_USER_DAYS_TAKEN = "userDaysTaken";
    public static final String COLUMN_USERS_USER_DAYS_FOR_YEAR = "userDaysForYear";
    public static final String COLUMN_USERS_USER_USERNAME = "userName";
    public static final String COLUMN_USERS_USER_PASSWORD = "password";


    //timeoffs table
    public static final String TABLE_TIMEOFFS = "timeoffs";
    public static final String COLUMN_TIMEOFFS_ENTRY_NUMBER = "entryNumber";
    //Should I store employeeNumber,employeeName,managerEmployeeNumber and  managerName in this table as well?
    //Might be redundant...
    //EmployeeNumber is needed for foreign key
    public static final String COLUMN_TIMEOFFS_EMPLOYEE_NUMBER = "employeeNumber";
    public static final String COLUMN_TIMEOFFS_EMPLOYEE_NAME = "employeeName";
    public static final String COLUMN_TIMEOFFS_MANAGER_EMPLOYEE_NUMBER = "managerEmployeeNumber";
    public static final String COLUMN_TIMEOFFS_MANAGER_NAME = "managerName";
    //
    public static final String COLUMN_TIMEOFFS_START_OF_LEAVE = "startOfLeave";
    public static final String COLUMN_TIMEOFFS_END_OF_LEAVE = "endOfLeave";
    public static final String COLUMN_TIMEOFFS_LEAVE_STATUS = "leaveStatus";
    public static final String COLUMN_TIMEOFFS_DATE_ENTRY_CREATED = "dateEntryCreated";
    public static final String COLUMN_TIMEOFFS_DATE_ENTRY_LATEST_CHANGE = "dateEntryLatestChange";
    public static final String COLUMN_TIMEOFFS_DATE_ENTRY_LATEST_CHANGE_BY = "dateEntryLatestChangeBy";
    public static final String COLUMN_TIMEOFFS_DURATION_OF_LEAVE = "durationOfLeave";

    /**
     * Adding index numbers to table columns, because it can more efficient for querying, but
     * it has a trade-off, if column gets inserted to the table, it has to be revised.
     * You can use the index of the column to refer to it in the table,
     * In SQL indexing starts at 1.
     * This matters, because with column index, the tool knows exactly where to go,
     * with names, it has to locate the right column first, with a large DB it can take time.
     */

    public static final int INDEX_USERS_ID = 1;
    public static final int INDEX_USERS_EMPLOYEE_NUMBER = 2;
    public static final int INDEX_USERS_EMPLOYEE_NAME = 3;
    public static final int INDEX_USERS_MANAGER_EMPLOYEE_NUMBER = 4;
    public static final int INDEX_USERS_MANAGER_NAME = 5;
    public static final int INDEX_USERS_SECURITY_ROLE = 6;
    public static final int INDEX_USERS_USER_ACTIVE = 7;
    public static final int INDEX_USERS_USER_CREATION_DATE = 8;
    public static final int INDEX_USERS_USER_DEACTIVATION_DATE = 9;
    public static final int INDEX_USERS_USER_LATEST_CHANGE_DATE = 10;
    public static final int INDEX_USERS_LATEST_CHANGE_BY_USER = 11;
    public static final int INDEX_USERS_DAYS_AVAILABLE = 12;
    public static final int INDEX_USERS_USER_DAYS_TAKEN = 13;
    public static final int INDEX_USERS_USER_DAYS_FOR_YEAR = 14;
    public static final int INDEX_USERS_USER_USERNAME = 15;
    public static final int INDEX_USERS_USER_PASSWORD = 16;

    public static final int INDEX_TIMEOFFS_ENTRY_NUMBER = 1;
    public static final int INDEX_TIMEOFFS_EMPLOYEE_NUMBER = 2;
    public static final int INDEX_TIMEOFFS_EMPLOYEE_NAME = 3;
    public static final int INDEX_TIMEOFFS_MANAGER_EMPLOYEE_NUMBER = 4;
    public static final int INDEX_TIMEOFFS_MANAGER_NAME = 5;
    //
    public static final int INDEX_TIMEOFFS_START_OF_LEAVE = 6;
    public static final int INDEX_TIMEOFFS_END_OF_LEAVE = 7;
    public static final int INDEX_TIMEOFFS_LEAVE_STATUS = 8;
    public static final int INDEX_TIMEOFFS_DATE_ENTRY_CREATED = 9;
    public static final int INDEX_TIMEOFFS_DATE_ENTRY_LATEST_CHANGE = 10;
    public static final int INDEX_TIMEOFFS_DATE_ENTRY_LATEST_CHANGE_BY = 11;
    public static final int INDEX_TIMEOFFS_DURATION_OF_LEAVE = 12;

    /**
     * The base API doesn't allow us to create custom queries, like for sorting,
     * so we need to create methods, that accept certain parameters to do the sorting.
     * For this constants needs to created.
     */
    public static final int ORDER_BY_NONE = 1;
    public static final int ORDER_BY_ASC = 2;
    public static final int ORDER_BY_DESC = 3;

    /**
     * Prepared Statements - Queries
     * Starting to use PreparedStatement to prevent SQL - Injection Attack
     * The only difference below in the Query, is the "?" at the end of the query
     * for prepared statement, we need to use this "?" at the end of the query
     */

    /**
     * Queries - Retrieve data
     * <p>
     * Multiple queries to retrieve data
     */

    //User verification
    public static final String QUERY_USER_VERIFICATION =
            "SELECT * FROM " + TABLE_USERS +
                    " WHERE " + COLUMN_USERS_USER_USERNAME + " = ?" + " AND " + COLUMN_USERS_USER_PASSWORD + " = ?";

    //For employee searches - only employee data - intended for admins for maintenance
    public static final String QUERY_USERS_ALL_USER =
            "SELECT * FROM " + TABLE_USERS;

    public static final String QUERY_USERS_SELECT_USER =
            "SELECT " + COLUMN_USERS_ID + ", " + COLUMN_USERS_EMPLOYEE_NAME + ", " + COLUMN_USERS_EMPLOYEE_NUMBER + ", "
                    + COLUMN_USERS_MANAGER_NAME + ", " + COLUMN_USERS_MANAGER_EMPLOYEE_NUMBER + ", " +
                    COLUMN_USERS_SECURITY_ROLE + ", " + COLUMN_USERS_USER_ACTIVE + ", " +
                    COLUMN_USERS_DAYS_AVAILABLE + ", " + COLUMN_USERS_USER_DAYS_TAKEN + ", " + COLUMN_USERS_USER_DAYS_FOR_YEAR + ", " +
                    COLUMN_USERS_USER_USERNAME + ", " + COLUMN_USERS_USER_PASSWORD +
                    " FROM " + TABLE_USERS;

    public static final String QUERY_USERS_SPECIFIC_USER_EMPLOYEE_NAME =
            "SELECT * FROM " + TABLE_USERS +
                    " WHERE " + COLUMN_USERS_EMPLOYEE_NAME + " = ?";

    public static final String QUERY_USERS_SPECIFIC_USER_EMPLOYEE_NUMBER =
            "SELECT * FROM " + TABLE_USERS +
                    " WHERE " + COLUMN_USERS_EMPLOYEE_NUMBER + " = ?";

    public static final String QUERY_USERS_SPECIFIC_USER_MANAGER_NAME =
            "SELECT * FROM " + TABLE_USERS +
                    " WHERE " + COLUMN_USERS_MANAGER_NAME + " = ?";

    //For leave searches - intended to display data for users,managers and admins for user leaves

    public static final String QUERY_TIMEOFFS_ALL_TIMESOFFS =
            "SELECT * FROM " + TABLE_TIMEOFFS;

    //  Between dates example
    //  https://www.w3schools.com/sql/sql_between.asp
    //  SELECT column_name(s)
    //  FROM table_name
    //  WHERE column_name BETWEEN value1 AND value2;

    //Takes 3 parameters -- startDateOfLeave, endDateOfLeave, employeeNumber
    public static final String QUERY_TIMEOFFS_LEAVES_BETWEEN_TWO_DATES =
            "SELECT * FROM " + TABLE_TIMEOFFS +
                    " WHERE " + TABLE_TIMEOFFS + "." + COLUMN_TIMEOFFS_START_OF_LEAVE + " BETWEEN ? AND ? AND " +
                    TABLE_TIMEOFFS + "." + COLUMN_TIMEOFFS_EMPLOYEE_NUMBER + " = ?";

    //Takes 1 parameter -- employeeNumber
    public static final String QUERY_TIMEOFFS_LEAVES_ALL =
            "SELECT * FROM " + TABLE_TIMEOFFS +
                    " INNER JOIN " + TABLE_USERS + " ON " +
                    TABLE_TIMEOFFS + "." + COLUMN_TIMEOFFS_EMPLOYEE_NUMBER + " = " + TABLE_USERS + "." + COLUMN_USERS_EMPLOYEE_NUMBER +
                    " WHERE " + TABLE_USERS + "." + COLUMN_USERS_EMPLOYEE_NUMBER + " = ?";

    //Takes 2 parameters -- leaveStatus, employeeNumber
    public static final String QUERY_TIMEOFFS_LEAVES_WITH_SELECTED_STATUS =
            "SELECT * FROM " + TABLE_TIMEOFFS +
                    " INNER JOIN " + TABLE_USERS + " ON " +
                    TABLE_TIMEOFFS + "." + COLUMN_TIMEOFFS_EMPLOYEE_NUMBER + " = " + TABLE_USERS + "." + COLUMN_USERS_EMPLOYEE_NUMBER +
                    " WHERE " + TABLE_TIMEOFFS + "." + COLUMN_TIMEOFFS_LEAVE_STATUS + " = ? " +
                    "AND " + TABLE_USERS + "." + COLUMN_USERS_EMPLOYEE_NUMBER + " = ?";

    //Takes 1 parameter -- managerEmployeeNumber
    public static final String QUERY_TIMEOFFS_TO_BE_APPROVED =
            "SELECT * FROM " + TABLE_TIMEOFFS +
                    " WHERE " + TABLE_TIMEOFFS + "." + COLUMN_TIMEOFFS_LEAVE_STATUS + " = 'Pending approval' " +
                    "AND " + TABLE_TIMEOFFS + "." + COLUMN_TIMEOFFS_MANAGER_EMPLOYEE_NUMBER + " = ?";

    public static final String QUERY_TIMEOFFS_MANAGER_ALL_EMPLOYEE_TIMEOFFS =
            "SELECT * FROM " + TABLE_TIMEOFFS +
                    " WHERE " + TABLE_TIMEOFFS + "." + COLUMN_TIMEOFFS_MANAGER_EMPLOYEE_NUMBER + " = ?";


    /**
     * Queries - Insert new data
     */

    /**
     * 2 Methods for admins to insert full new records
     */

    public static final String QUERY_INSERT_USERS =
            "INSERT INTO " + TABLE_USERS +
                    "(" + COLUMN_USERS_EMPLOYEE_NUMBER + ", " + COLUMN_USERS_EMPLOYEE_NAME + ", " + COLUMN_USERS_MANAGER_EMPLOYEE_NUMBER + ", " +
                    COLUMN_USERS_MANAGER_NAME + ", " + COLUMN_USERS_SECURITY_ROLE + ", " + COLUMN_USERS_USER_ACTIVE + ", " +
                    COLUMN_USERS_USER_CREATION_DATE + ", " + COLUMN_USERS_USER_LATEST_CHANGE_DATE + ", " +
                    COLUMN_USERS_LATEST_CHANGE_BY_USER + ", " + COLUMN_USERS_DAYS_AVAILABLE + ", " +
                    COLUMN_USERS_USER_DAYS_TAKEN + ", " + COLUMN_USERS_USER_DAYS_FOR_YEAR + ", " +
                    COLUMN_USERS_USER_USERNAME + ", " + COLUMN_USERS_USER_PASSWORD +
                    ") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    public static final String QUERY_INSERT_TIME_OFFS =
            "INSERT INTO " + TABLE_TIMEOFFS +
                    "(" + COLUMN_TIMEOFFS_EMPLOYEE_NUMBER + ", " + COLUMN_TIMEOFFS_EMPLOYEE_NAME + ", " + COLUMN_TIMEOFFS_MANAGER_EMPLOYEE_NUMBER + ", " +
                    COLUMN_TIMEOFFS_MANAGER_NAME + ", " + COLUMN_TIMEOFFS_START_OF_LEAVE + ", " + COLUMN_TIMEOFFS_END_OF_LEAVE + ", " +
                    COLUMN_TIMEOFFS_LEAVE_STATUS + ", " + COLUMN_TIMEOFFS_DATE_ENTRY_CREATED + ", " + COLUMN_TIMEOFFS_DATE_ENTRY_LATEST_CHANGE +
                    ", " + COLUMN_TIMEOFFS_DATE_ENTRY_LATEST_CHANGE_BY + ", " + COLUMN_TIMEOFFS_DURATION_OF_LEAVE +
                    ") VALUES (?,?,?,?,?,?,?,?,?,?,?)";


    /**
     * Queries - Update data
     */

    /**
     * 2 queries for admins - to update full existing records
     */

    //Takes parameters to be update, along with the idUser from the table, which is a auto incremented value
    public static final String QUERY_UPDATE_USER =
            "UPDATE " + TABLE_USERS +
                    " SET " + COLUMN_USERS_EMPLOYEE_NUMBER + " = ?, " + COLUMN_USERS_EMPLOYEE_NAME + " = ?, " +
                    COLUMN_USERS_MANAGER_EMPLOYEE_NUMBER + " = ?, " + COLUMN_USERS_MANAGER_NAME + " = ?, " +
                    COLUMN_USERS_SECURITY_ROLE + " = ?, " + COLUMN_USERS_USER_ACTIVE + " = ?, " +
                    COLUMN_USERS_USER_LATEST_CHANGE_DATE + " = ?, " + COLUMN_USERS_LATEST_CHANGE_BY_USER + " = ?, " +
                    COLUMN_USERS_DAYS_AVAILABLE + " = ?, " + COLUMN_USERS_USER_DAYS_TAKEN + " = ?, " + COLUMN_USERS_USER_DAYS_FOR_YEAR + " = ?," +
                    COLUMN_USERS_USER_USERNAME + " = ?," + COLUMN_USERS_USER_PASSWORD + " = ?" +
                    " WHERE " + COLUMN_USERS_ID + " = ?";

    public static final String QUERY_DAYS_AVAILABLE_USER_BASED_ON_EMPLOYEE_NUMBER =
            "UPDATE " + TABLE_USERS +
                    " SET " + COLUMN_USERS_DAYS_AVAILABLE + " = ? " +
                    " WHERE " + COLUMN_USERS_EMPLOYEE_NUMBER + " = ?";

    public static final String QUERY_TOGGLE_USER =
            "UPDATE " + TABLE_USERS +
                    " SET " + COLUMN_USERS_USER_ACTIVE + " = ? " +
                    " WHERE " + COLUMN_USERS_ID + " = ?";

    public static final String QUERY_UPDATE_TIME_OFFS =
            "UPDATE " + TABLE_TIMEOFFS +
                    " SET " + COLUMN_TIMEOFFS_START_OF_LEAVE + " = ?, " + COLUMN_TIMEOFFS_END_OF_LEAVE + " = ?, " +
                    COLUMN_TIMEOFFS_LEAVE_STATUS + " = ?, " + COLUMN_TIMEOFFS_DATE_ENTRY_LATEST_CHANGE + " = ?, " +
                    COLUMN_TIMEOFFS_DATE_ENTRY_LATEST_CHANGE_BY + " = ?, " + COLUMN_TIMEOFFS_DURATION_OF_LEAVE + " = ? " +
                    " WHERE " + COLUMN_TIMEOFFS_ENTRY_NUMBER + " = ?";

    public static final String QUERY_UPDATE_DAYS_AVAILABLE_USER_Based_On_UserID =
            "UPDATE " + TABLE_USERS +
                    " SET " + COLUMN_USERS_DAYS_AVAILABLE + " = ? " +
                    " WHERE " + COLUMN_USERS_ID + " = ?";

    /**
     * 2 queries for admins or managers to approve or decline time-offs
     */

    public static final String QUERY_TIMEOFFS_APPROVE =
            "UPDATE " + TABLE_TIMEOFFS +
                    " SET " + COLUMN_TIMEOFFS_LEAVE_STATUS + " = 'Approved', " + COLUMN_TIMEOFFS_DATE_ENTRY_LATEST_CHANGE + " = ?, " +
                    COLUMN_TIMEOFFS_DATE_ENTRY_LATEST_CHANGE_BY + " = ? " +
                    " WHERE " + COLUMN_TIMEOFFS_ENTRY_NUMBER + " = ?";


    public static final String QUERY_TIMEOFFS_DENY =
            "UPDATE " + TABLE_TIMEOFFS +
                    " SET " + COLUMN_TIMEOFFS_LEAVE_STATUS + " = 'Denied', " + COLUMN_TIMEOFFS_DATE_ENTRY_LATEST_CHANGE + " = ?, " +
                    COLUMN_TIMEOFFS_DATE_ENTRY_LATEST_CHANGE_BY + " = ? " +
                    " WHERE " + COLUMN_TIMEOFFS_ENTRY_NUMBER + " = ?";

    /**
     * Queries - Drop data
     */

    public static final String DROP_TIME_OFF =
            "DELETE FROM " + TABLE_TIMEOFFS + " WHERE " + TABLE_TIMEOFFS + "." + COLUMN_TIMEOFFS_ENTRY_NUMBER + "= ?";

    public static final String DROP_USER =
            "DELETE FROM " + TABLE_USERS + " WHERE " + TABLE_USERS + "." + COLUMN_USERS_ID + "= ?";

    /**
     * Prepared statement - Prepared statement objects
     */

    private PreparedStatement queryAllUsers;
    private PreparedStatement querySpecificUserEmployeeName;
    private PreparedStatement querySpecificUserEmployeeNumber;
    private PreparedStatement querySpecificUserManagerName;
    private PreparedStatement queryLeavesBetweenTwoDates;
    private PreparedStatement queryLeavesAll;
    private PreparedStatement queryLeaveWithSelectedStatus;
    private PreparedStatement queryToBeApproved;
    private PreparedStatement insertUser;
    private PreparedStatement insertTimeOff;
    private PreparedStatement updateUser;
    private PreparedStatement updateTimeOff;
    private PreparedStatement timeOffApprove;
    private PreparedStatement timeOffDeny;
    private PreparedStatement toggleUser;
    private PreparedStatement queryUserVerification;
    private PreparedStatement querySelectedUser;
    private PreparedStatement deleteTimeOff;
    private PreparedStatement deleteUser;
    private PreparedStatement updateUserDaysAvailableBasedOnUserID;
    private PreparedStatement queryManagerAllEmployeeTimeOffs;
    private PreparedStatement updateUserDaysAvailableBasedOnEmployeeNumber;

    /**
     * Create a Connection Class object, you will use this to connect to the DB
     */

    private Connection conn;

    /**
     * 2 functions - open and close
     * to make the connection to the DB and close the connection once we are done
     *
     * @return
     */

    public boolean open() {
        try {

//            Conn for HDD access - needs JDBC
//            conn = DriverManager.getConnection(CONNECTION_STRING);

            Class.forName("org.mariadb.jdbc.Driver");

            conn = DriverManager.getConnection(url, user, password);

            /**
             * Prepared Statements - Connections
             *
             * Setting up the prepared statement
             * Using the conn object to send the queries through as a prepared statement
             * Prepared statement is better, because it is handled as a literal
             * The instruction are handled as simple text and NOT sql commands
             */

            queryAllUsers = conn.prepareStatement(QUERY_USERS_ALL_USER);
            querySpecificUserEmployeeName = conn.prepareStatement(QUERY_USERS_SPECIFIC_USER_EMPLOYEE_NAME);
            querySpecificUserEmployeeNumber = conn.prepareStatement(QUERY_USERS_SPECIFIC_USER_EMPLOYEE_NUMBER);
            querySpecificUserManagerName = conn.prepareStatement(QUERY_USERS_SPECIFIC_USER_MANAGER_NAME);
            queryLeavesAll = conn.prepareStatement(QUERY_TIMEOFFS_LEAVES_ALL);
            queryLeavesBetweenTwoDates = conn.prepareStatement(QUERY_TIMEOFFS_LEAVES_BETWEEN_TWO_DATES);
            queryLeaveWithSelectedStatus = conn.prepareStatement(QUERY_TIMEOFFS_LEAVES_WITH_SELECTED_STATUS);
            queryToBeApproved = conn.prepareStatement(QUERY_TIMEOFFS_TO_BE_APPROVED);
            insertUser = conn.prepareStatement(QUERY_INSERT_USERS);
            insertTimeOff = conn.prepareStatement(QUERY_INSERT_TIME_OFFS);
            updateUser = conn.prepareStatement(QUERY_UPDATE_USER);
            updateTimeOff = conn.prepareStatement(QUERY_UPDATE_TIME_OFFS);
            timeOffApprove = conn.prepareStatement(QUERY_TIMEOFFS_APPROVE);
            timeOffDeny = conn.prepareStatement(QUERY_TIMEOFFS_DENY);
            toggleUser = conn.prepareStatement(QUERY_TOGGLE_USER);
            queryUserVerification = conn.prepareStatement(QUERY_USER_VERIFICATION);
            querySelectedUser = conn.prepareStatement(QUERY_USERS_SELECT_USER);
            deleteTimeOff = conn.prepareStatement(DROP_TIME_OFF);
            deleteUser = conn.prepareStatement(DROP_USER);
            updateUserDaysAvailableBasedOnUserID = conn.prepareStatement(QUERY_UPDATE_DAYS_AVAILABLE_USER_Based_On_UserID);
            queryManagerAllEmployeeTimeOffs = conn.prepareStatement(QUERY_TIMEOFFS_MANAGER_ALL_EMPLOYEE_TIMEOFFS);
            updateUserDaysAvailableBasedOnEmployeeNumber = conn.prepareStatement(QUERY_DAYS_AVAILABLE_USER_BASED_ON_EMPLOYEE_NUMBER);

            return true;

//        } catch (SQLException e) {
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Couldn't connect to database: " + e.getMessage());
            return false;
        }
    }

    public void close() {
        try {
            //Closing resources, ORDER OF CLOSING MATTERS!
            /**
             * Prepared Statements - Closing
             */

            if (queryAllUsers != null) {
                queryAllUsers.close();
            }

            if (querySpecificUserEmployeeName != null) {
                querySpecificUserEmployeeName.close();
            }

            if (querySpecificUserEmployeeNumber != null) {
                querySpecificUserEmployeeNumber.close();
            }
            if (querySpecificUserManagerName != null) {
                querySpecificUserManagerName.close();
            }

            if (queryLeavesAll != null) {
                queryLeavesAll.close();
            }

            if (queryLeavesBetweenTwoDates != null) {
                queryLeavesBetweenTwoDates.close();
            }
            if (queryLeaveWithSelectedStatus != null) {
                queryLeaveWithSelectedStatus.close();
            }

            if (queryToBeApproved != null) {
                queryToBeApproved.close();
            }

            if (insertUser != null) {
                insertUser.close();
            }
            if (insertTimeOff != null) {
                insertTimeOff.close();
            }

            if (updateUser != null) {
                updateUser.close();
            }

            if (updateTimeOff != null) {
                updateTimeOff.close();
            }

            if (timeOffApprove != null) {
                timeOffApprove.close();
            }

            if (timeOffDeny != null) {
                timeOffDeny.close();
            }

            if (queryUserVerification != null) {
                queryUserVerification.close();
            }

            if (querySelectedUser != null) {
                querySelectedUser.close();
            }

            if (deleteTimeOff != null) {
                deleteTimeOff.close();
            }

            if (deleteUser != null) {
                deleteUser.close();
            }

            if (updateUserDaysAvailableBasedOnUserID != null) {
                updateUserDaysAvailableBasedOnUserID.close();
            }

            if (queryManagerAllEmployeeTimeOffs != null) {
                queryManagerAllEmployeeTimeOffs.close();
            }

            /**
             * Connection - Closing
             */
            //conn is our connection object
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Couldn't close connection: " + e.getMessage());
        }
    }

    /**
     * Methods with Prepared statements
     */

    /**
     * User verification
     */

    public User userVerification(String userName, String password) {
        try {
            queryUserVerification.setString(1, userName);
            queryUserVerification.setString(2, password);
            ResultSet results = queryUserVerification.executeQuery();
            while (results.next()) {
                User user = new User();
                user.setIdUser(results.getInt(1));
                user.setEmployeeNumber(results.getInt(2));
                user.setEmployeeName(results.getString(3));
                user.setManagerEmployeeNumber(results.getInt(4));
                user.setManagerName(results.getString(5));
                user.setSecurityRole(results.getString(6));
                user.setUserActive(results.getString(7));
                user.setUserCreationDate(results.getString(8));
                user.setUserDeActivationDate(results.getString(9));
                user.setUserLatestChangeDate(results.getString(10));
                user.setUserLatestChangeByUser(results.getString(11));
                user.setUserDaysAvailable(results.getInt(12));
                user.setUserDaysTaken(results.getInt(13));
                user.setUserDaysForYear(results.getInt(14));
                user.setUserName(results.getString(15));
                user.setPassword(results.getString(16));
                return user;
            }
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
        }
        return null;
    }


    /**
     * User data retrieval methods
     *
     * @return
     */

    public List<User> allUsers() {
        try {
            //Running the query as usual, only through the prepared statement instead of conn object
            ResultSet results = queryAllUsers.executeQuery();

            //Creating an arraylist to hold the user objects returned by each record stored in the ResultSet from the executed query
            List<User> users = new ArrayList<>();

            //Looping through the ResultSet and adding each returned record to the ArrayList created to hold them
            while (results.next()) {
                //Creating a user object to hold the data for each record from the query
                User user = new User();
                //Setting the attributes of the user object with the data from the fields from each row from the query
                //using column index number - if the order of the columns is changed, order needs to be updated!
                user.setIdUser(results.getInt(1));
                user.setEmployeeNumber(results.getInt(2));
                user.setEmployeeName(results.getString(3));
                user.setManagerEmployeeNumber(results.getInt(4));
                user.setManagerName(results.getString(5));
                user.setSecurityRole(results.getString(6));
                user.setUserActive(results.getString(7));
                user.setUserCreationDate(results.getString(8));
                user.setUserDeActivationDate(results.getString(9));
                user.setUserLatestChangeDate(results.getString(10));
                user.setUserLatestChangeByUser(results.getString(11));
                user.setUserDaysAvailable(results.getInt(12));
                user.setUserDaysTaken(results.getInt(13));
                user.setUserDaysForYear(results.getInt(14));
                users.add(user);
            }
            //Returning the list of objects/records, which we have created from the query data
            return users;

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public List<User> specificUserEmployeeName(String employeeName) {
        try {
            //Telling the prepared statement, that the first expected variable "?" is the title input for the query
            // from the method
            querySpecificUserEmployeeName.setString(1, employeeName);

            ResultSet results = querySpecificUserEmployeeName.executeQuery();
            List<User> users = new ArrayList<>();
            while (results.next()) {
                User user = new User();
                user.setIdUser(results.getInt(1));
                user.setEmployeeNumber(results.getInt(2));
                user.setEmployeeName(results.getString(3));
                user.setManagerEmployeeNumber(results.getInt(4));
                user.setManagerName(results.getString(5));
                user.setSecurityRole(results.getString(6));
                user.setUserActive(results.getString(7));
                user.setUserCreationDate(results.getString(8));
                user.setUserDeActivationDate(results.getString(9));
                user.setUserLatestChangeDate(results.getString(10));
                user.setUserLatestChangeByUser(results.getString(11));
                user.setUserDaysAvailable(results.getInt(12));
                user.setUserDaysTaken(results.getInt(13));
                user.setUserDaysForYear(results.getInt(14));
                users.add(user);
            }
            return users;

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public User specificUserEmployeeNumber(int employeeNumber) {
        try {
            querySpecificUserEmployeeNumber.setInt(1, employeeNumber);
            ResultSet results = querySpecificUserEmployeeNumber.executeQuery();
            List<User> users = new ArrayList<>();
            User user = new User();
            while (results.next()) {
                user.setIdUser(results.getInt(1));
                user.setEmployeeNumber(results.getInt(2));
                user.setEmployeeName(results.getString(3));
                user.setManagerEmployeeNumber(results.getInt(4));
                user.setManagerName(results.getString(5));
                user.setSecurityRole(results.getString(6));
                user.setUserActive(results.getString(7));
                user.setUserCreationDate(results.getString(8));
                user.setUserDeActivationDate(results.getString(9));
                user.setUserLatestChangeDate(results.getString(10));
                user.setUserLatestChangeByUser(results.getString(11));
                user.setUserDaysAvailable(results.getInt(12));
                user.setUserDaysTaken(results.getInt(13));
                user.setUserDaysForYear(results.getInt(14));
                users.add(user);
            }
            return user;
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public List<User> specificUserManagerName(String managerName) {
        try {
            querySpecificUserEmployeeName.setString(1, managerName);
            ResultSet results = querySpecificUserEmployeeName.executeQuery();
            List<User> users = new ArrayList<>();
            while (results.next()) {
                User user = new User();
                user.setIdUser(results.getInt(1));
                user.setEmployeeNumber(results.getInt(2));
                user.setEmployeeName(results.getString(3));
                user.setManagerEmployeeNumber(results.getInt(4));
                user.setManagerName(results.getString(5));
                user.setSecurityRole(results.getString(6));
                user.setUserActive(results.getString(7));
                user.setUserCreationDate(results.getString(8));
                user.setUserDeActivationDate(results.getString(9));
                user.setUserLatestChangeDate(results.getString(10));
                user.setUserLatestChangeByUser(results.getString(11));
                user.setUserDaysAvailable(results.getInt(12));
                user.setUserDaysTaken(results.getInt(13));
                user.setUserDaysForYear(results.getInt(14));
                users.add(user);
            }
            return users;

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    /**
     * Time off data retrieval methods
     */

    public List<TimeOff> leavesAll(int employeeNumber) {
        try {
            queryLeavesAll.setInt(1, employeeNumber);
            ResultSet results = queryLeavesAll.executeQuery();
            List<TimeOff> timeOffs = new ArrayList<>();
            while (results.next()) {
                TimeOff timeoff = new TimeOff();
                timeoff.setEntryNumber(results.getInt(1));
                timeoff.setEmployeeNumber(results.getInt(2));
                timeoff.setEmployeeName(results.getString(3));
                timeoff.setManagerEmployeeNumber(results.getInt(4));
                timeoff.setManagerName(results.getString(5));
                timeoff.setStartOfLeave(results.getString(6));
                timeoff.setEndOfLeave(results.getString(7));
                timeoff.setLeaveStatus(results.getString(8));
                timeoff.setDateEntryCreated(results.getString(9));
                timeoff.setDateEntryLatestChange(results.getString(10));
                timeoff.setDateEntryLatestChangeBy(results.getString(11));
                timeoff.setDurationOfLeave(results.getInt(12));
                timeOffs.add(timeoff);
            }
            return timeOffs;

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public List<TimeOff> leavesBetweenTwoDates(String startDate, String endDate, int employeeNumber) {
        try {
            queryLeavesBetweenTwoDates.setString(1, startDate);
            queryLeavesBetweenTwoDates.setString(2, endDate);
            queryLeavesBetweenTwoDates.setInt(3, employeeNumber);
            ResultSet results = queryLeavesBetweenTwoDates.executeQuery();
            List<TimeOff> timeOffs = new ArrayList<>();
            while (results.next()) {
                TimeOff timeoff = new TimeOff();
                timeoff.setEntryNumber(results.getInt(1));
                timeoff.setEmployeeNumber(results.getInt(2));
                timeoff.setEmployeeName(results.getString(3));
                timeoff.setManagerEmployeeNumber(results.getInt(4));
                timeoff.setManagerName(results.getString(5));
                timeoff.setStartOfLeave(results.getString(6));
                timeoff.setEndOfLeave(results.getString(7));
                timeoff.setLeaveStatus(results.getString(8));
                timeoff.setDateEntryCreated(results.getString(9));
                timeoff.setDateEntryLatestChange(results.getString(10));
                timeoff.setDateEntryLatestChangeBy(results.getString(11));
                timeoff.setDurationOfLeave(results.getInt(12));

                timeOffs.add(timeoff);
            }
            return timeOffs;

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public List<TimeOff> leaveWithSelectedStatus(String approvalStatus, int employeeNumber) {
        try {
            queryLeaveWithSelectedStatus.setString(1, approvalStatus);
            queryLeaveWithSelectedStatus.setInt(2, employeeNumber);
            ResultSet results = queryLeaveWithSelectedStatus.executeQuery();
            List<TimeOff> timeOffs = new ArrayList<>();
            while (results.next()) {
                TimeOff timeoff = new TimeOff();
                timeoff.setEntryNumber(results.getInt(1));
                timeoff.setEmployeeNumber(results.getInt(2));
                timeoff.setEmployeeName(results.getString(3));
                timeoff.setManagerEmployeeNumber(results.getInt(4));
                timeoff.setManagerName(results.getString(5));
                timeoff.setStartOfLeave(results.getString(6));
                timeoff.setEndOfLeave(results.getString(7));
                timeoff.setLeaveStatus(results.getString(8));
                timeoff.setDateEntryCreated(results.getString(9));
                timeoff.setDateEntryLatestChange(results.getString(10));
                timeoff.setDateEntryLatestChangeBy(results.getString(11));
                timeoff.setDurationOfLeave(results.getInt(12));
                timeOffs.add(timeoff);
            }
            return timeOffs;

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public List<TimeOff> toBeApproved(int managerEmployeeNumber) {
        try {
            queryToBeApproved.setInt(1, managerEmployeeNumber);
            ResultSet results = queryToBeApproved.executeQuery();
            List<TimeOff> timeOffs = new ArrayList<>();
            while (results.next()) {
                TimeOff timeoff = new TimeOff();
                timeoff.setEntryNumber(results.getInt(1));
                timeoff.setEmployeeNumber(results.getInt(2));
                timeoff.setEmployeeName(results.getString(3));
                timeoff.setManagerEmployeeNumber(results.getInt(4));
                timeoff.setManagerName(results.getString(5));
                timeoff.setStartOfLeave(results.getString(6));
                timeoff.setEndOfLeave(results.getString(7));
                timeoff.setLeaveStatus(results.getString(8));
                timeoff.setDateEntryCreated(results.getString(9));
                timeoff.setDateEntryLatestChange(results.getString(10));
                timeoff.setDateEntryLatestChangeBy(results.getString(11));
                timeoff.setDurationOfLeave(results.getInt(12));
                timeOffs.add(timeoff);
            }
            return timeOffs;

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public List<TimeOff> allEmployeeTimeOffs(int managerEmployeeNumber) {
        try {
            queryManagerAllEmployeeTimeOffs.setInt(1, managerEmployeeNumber);
            ResultSet results = queryManagerAllEmployeeTimeOffs.executeQuery();
            List<TimeOff> timeOffs = new ArrayList<>();
            while (results.next()) {
                TimeOff timeoff = new TimeOff();
                timeoff.setEntryNumber(results.getInt(1));
                timeoff.setEmployeeNumber(results.getInt(2));
                timeoff.setEmployeeName(results.getString(3));
                timeoff.setManagerEmployeeNumber(results.getInt(4));
                timeoff.setManagerName(results.getString(5));
                timeoff.setStartOfLeave(results.getString(6));
                timeoff.setEndOfLeave(results.getString(7));
                timeoff.setLeaveStatus(results.getString(8));
                timeoff.setDateEntryCreated(results.getString(9));
                timeoff.setDateEntryLatestChange(results.getString(10));
                timeoff.setDateEntryLatestChangeBy(results.getString(11));
                timeoff.setDurationOfLeave(results.getInt(12));
                timeOffs.add(timeoff);
            }
            return timeOffs;

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }


    public List<User> selectUserFromAllUsers() {
        try {
            ResultSet results = querySelectedUser.executeQuery();
            List<User> users = new ArrayList<>();
            while (results.next()) {
                User user = new User();
                user.setIdUser(results.getInt(1));
                user.setEmployeeName(results.getString(2));
                user.setEmployeeNumber(results.getInt(3));
                user.setManagerName(results.getString(4));
                user.setManagerEmployeeNumber(results.getInt(5));
                user.setSecurityRole(results.getString(6));
                user.setUserActive(results.getString(7));
                user.setUserDaysAvailable(results.getInt(8));
                user.setUserDaysTaken(results.getInt(9));
                user.setUserDaysForYear(results.getInt(10));
                user.setUserName(results.getString(11));
                user.setPassword(results.getString(12));
                users.add(user);
            }
            return users;

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    /**
     * Insert into table methods
     */

    public int insertUser(int employeeNumber, String employeeName, int managerEmployeeNumber, String managerName,
                          String securityRole, String userActive, String userCreationDate,
                          String userLatestChangeDate, String userLatestChangeByUser, int userDaysAvailable,
                          int userDaysTaken, int userDaysForYear, String userName, String password) throws SQLException {

        //Check, whether user already exist or not based on employeeNumber
        //I am not checking employee name, because 2 person can have the same one, but not the employee number
        querySpecificUserEmployeeNumber.setInt(1, employeeNumber);
        ResultSet results = querySpecificUserEmployeeNumber.executeQuery();
        if (results.next()) {
            //If we have a match, we return with idUser
            return results.getInt(1);
            //If the user does not exist, then we proceed with adding it to the table
        } else {
            // Insert the user
            insertUser.setInt(1, employeeNumber);
            insertUser.setString(2, employeeName);
            insertUser.setInt(3, managerEmployeeNumber);
            insertUser.setString(4, managerName);
            insertUser.setString(5, securityRole);
            insertUser.setString(6, userActive);
            insertUser.setString(7, userCreationDate);
            insertUser.setString(8, userLatestChangeDate);
            insertUser.setString(9, userLatestChangeByUser);
            insertUser.setInt(10, userDaysAvailable);
            insertUser.setInt(11, userDaysTaken);
            insertUser.setInt(12, userDaysForYear);
            insertUser.setString(13, userName);
            insertUser.setString(14, password);

            // Get the number of rows changed in the table
            int affectedRows = insertUser.executeUpdate();

            // If the affected number of rows is not 1, then something went wrong
            if (affectedRows != 1) {
                throw new SQLException("Couldn't insert album!");
            }

            // Checking the idUser as well, which is an autoincrement field
            ResultSet generatedKeys = insertUser.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("Couldn't get id for the new user!");
            }
        }
    }

    public void insertTimeOff(int employeeNumber, String employeeName, int managerEmployeeNumber, String managerName,
                              String startOfLeave, String endOfLeave, String leaveStatus, String dateEntryCreated,
                              String dateEntryLatestChange, String dateEntryLatestChangeBy, long durationOfLeave)
            throws SQLException {

        //THIS WORKS! DO NOT MESS WITH IT!

        //Need logic to check, if timeoff already exists
        queryLeavesBetweenTwoDates.setString(1, startOfLeave);
        queryLeavesBetweenTwoDates.setString(2, endOfLeave);
        queryLeavesBetweenTwoDates.setInt(3, employeeNumber);

        ResultSet results_check = queryLeavesBetweenTwoDates.executeQuery();

        if (results_check.next()) {
            //If we have a match, we return with idUser
            return;
            //If the time off does not exist, then we proceed with adding it to the table
        } else {

            // Insert the timeoff
            //Set insert query parameters
            insertTimeOff.setInt(1, employeeNumber);
            insertTimeOff.setString(2, employeeName);
            insertTimeOff.setInt(3, managerEmployeeNumber);
            insertTimeOff.setString(4, managerName);
            insertTimeOff.setString(5, startOfLeave);
            insertTimeOff.setString(6, endOfLeave);
            insertTimeOff.setString(7, leaveStatus);
            insertTimeOff.setString(8, dateEntryCreated);
            insertTimeOff.setString(9, dateEntryLatestChange);
            insertTimeOff.setString(10, dateEntryLatestChangeBy);
            insertTimeOff.setLong(11, durationOfLeave);

            // Get the number of rows changed in the table
            int affectedRows = insertTimeOff.executeUpdate();

            // If the affected number of rows is not 1, then something went wrong
            if (affectedRows != 1) {
                throw new SQLException("Couldn't insert time off!");
            }
        }
    }

    /**
     * Update table methods
     */

    public int updateUser(int employeeNumber, String employeeName, int managerEmployeeNumber, String managerName,
                          String securityRole, String userActive,
                          String userLatestChangeDate, String userLatestChangeByUser,
                          int userDaysAvailable, int userDaysTaken, int userDaysForYear,
                          String userName, String password, int userID) throws SQLException {

        //Check, whether user already exist or not based on employeeNumber
        //I am not checking the employee name, because 2 person can have the same name, but not the same employee number
        querySpecificUserEmployeeNumber.setInt(1, employeeNumber);
        ResultSet results = querySpecificUserEmployeeNumber.executeQuery();

        if (results != null) {
            //If the user exist, then we proceed with updating it in the table
            updateUser.setInt(1, employeeNumber);
            updateUser.setString(2, employeeName);
            updateUser.setInt(3, managerEmployeeNumber);
            updateUser.setString(4, managerName);
            updateUser.setString(5, securityRole);
            updateUser.setString(6, userActive);
            updateUser.setString(7, userLatestChangeDate);
            updateUser.setString(8, userLatestChangeByUser);
            updateUser.setInt(9, userDaysAvailable);
            updateUser.setInt(10, userDaysTaken);
            updateUser.setInt(11, userDaysForYear);
            updateUser.setString(12, userName);
            updateUser.setString(13, password);
            updateUser.setInt(14, userID);

            // Get the number of rows changed in the table
            int affectedRows = updateUser.executeUpdate();
            System.out.println(affectedRows);
            if (affectedRows == 1) {
                return affectedRows;
            }
            // If the affected number of rows is not 1, then something went wrong
            if (affectedRows != 1) {
                throw new SQLException("Update user was NOT successful!");
            }

        } else {
            throw new SQLException("User already exists!");
        }
        return 0;
    }

    public int updateUserDaysAvailableBasedOnUserID(int empID, int employeeDaysAvailable) throws SQLException {

        updateUserDaysAvailableBasedOnUserID.setInt(1, employeeDaysAvailable);
        updateUserDaysAvailableBasedOnUserID.setInt(2, empID);

        // Get the number of rows changed in the table
        int affectedRows = updateUserDaysAvailableBasedOnUserID.executeUpdate();
        if (affectedRows == 1) {
            return affectedRows;
        }
        // If the affected number of rows is not 1, then something went wrong
        if (affectedRows != 1) {
            throw new SQLException("Update user was NOT successful!");
        } else {
            throw new SQLException("Update user was NOT successful!");
        }

    }

    public int updateUserDaysAvailableBasedOnEmployeeNumber(int employeeNumber, int employeeDaysAvailable) throws SQLException {

        updateUserDaysAvailableBasedOnEmployeeNumber.setInt(1, employeeDaysAvailable);
        updateUserDaysAvailableBasedOnEmployeeNumber.setInt(2, employeeNumber);

        // Get the number of rows changed in the table
        int affectedRows = updateUserDaysAvailableBasedOnEmployeeNumber.executeUpdate();
        if (affectedRows == 1) {
            return affectedRows;

//        // If the affected number of rows is not 1, then something went wrong
        } else {
            throw new SQLException("Update user was NOT successful!");
        }
    }

    //toggleUser - activate or de-activate
    public void updateUser(int employeeNumber, String userActive) throws SQLException {

        //Check, whether user already exist or not based on employeeNumber
        //I am not checking employee name, because 2 person can have the same one, but not the employee number
        toggleUser.setInt(1, employeeNumber);
        ResultSet results = toggleUser.executeQuery();

        if (results != null) {
            //If the user exist, then we proceed with updating it in the table
            toggleUser.setInt(1, employeeNumber);
            toggleUser.setString(2, userActive);

            // Get the number of rows changed in the table
            int affectedRows = toggleUser.executeUpdate();

            // If the affected number of rows is not 1, then something went wrong
            if (affectedRows != 1) {
                throw new SQLException("Update user was NOT successful!");
            }

        } else {
            throw new SQLException("User already exists!");
        }
    }


    /**
     * 01: get all the variables into the controller function
     * 02: here you have to have all the data for the query
     */
    public boolean updateTimeOff(int entryNumber, int employeeNumber, String startOfLeave, String endOfLeave,
                                 String leaveStatus, String dateEntryLatestChange, String dateEntryLatestChangeBy,
                                 long durationOfLeave)
            throws SQLException {

        /**
         * Need logic to check, if time off already exists
         * No need to check for that it exists, because the query can ONLY run if we provide the time-off entry number,
         * which is unique to the selected item from the list
         */

        queryLeavesBetweenTwoDates.setString(1, startOfLeave);
        queryLeavesBetweenTwoDates.setString(2, endOfLeave);
        queryLeavesBetweenTwoDates.setInt(3, employeeNumber);
        ResultSet results_check = queryLeavesBetweenTwoDates.executeQuery();

        if (!results_check.next()) {
            //If we have a match, we return with idUser
            System.out.println("Time off wasn't found!");
            return false;
            //If the time off does not exist, then we proceed with adding it to the table
        } else {
            // Update time off
            updateTimeOff.setString(1, startOfLeave);
            updateTimeOff.setString(2, endOfLeave);
            updateTimeOff.setString(3, leaveStatus);
            updateTimeOff.setString(4, dateEntryLatestChange);
            updateTimeOff.setString(5, dateEntryLatestChangeBy);
            updateTimeOff.setLong(6, durationOfLeave);
            updateTimeOff.setInt(7, entryNumber);

            // Get the number of rows changed in the table
            int affectedRows = updateTimeOff.executeUpdate();

            // If the affected number of rows is not 1, then something went wrong
            if (affectedRows == 1) {
                return true;
            } else {
                throw new SQLException("Couldn't insert time off into DB!");
            }
        }
    }

    public boolean approveTimeOff(int entryNumber, String dateEntryLatestChange, String dateEntryLatestChangeBy)
            throws SQLException {

        timeOffApprove.setString(1, dateEntryLatestChange);
        timeOffApprove.setString(2, dateEntryLatestChangeBy);
        timeOffApprove.setInt(3, entryNumber);

        // Get the number of rows changed in the table
        int affectedRows = timeOffApprove.executeUpdate();

        // If the affected number of rows is not 1, then something went wrong
        if (affectedRows == 1) {
            return true;
        } else {
            throw new SQLException("Couldn't approve time off!");
        }
        //It shouldn't reach this point ever, but need to add return statement
    }

    public boolean declineTimeOff(int entryNumber, String dateEntryLatestChange, String dateEntryLatestChangeBy)
            throws SQLException {

        timeOffDeny.setString(1, dateEntryLatestChange);
        timeOffDeny.setString(2, dateEntryLatestChangeBy);
        timeOffDeny.setInt(3, entryNumber);

        // Get the number of rows changed in the table
        int affectedRows = timeOffDeny.executeUpdate();

        // If the affected number of rows is not 1, then something went wrong
        if (affectedRows == 1) {
            return true;
        } else {
            throw new SQLException("Couldn't decline time off!");
        }
        //It shouldn't reach this point ever, but need to add return statement
    }

    public boolean deleteTimeOff(int entryNumber) throws SQLException {
        deleteTimeOff.setInt(1, entryNumber);

        // Get the number of rows changed in the table
        int affectedRows = deleteTimeOff.executeUpdate();

        // If the affected number of rows is not 1, then something went wrong
        if (affectedRows == 1) {
            return true;
        } else {
            throw new SQLException("Couldn't delete time off off!");
        }
    }

    public boolean deleteUser(int idUser) throws SQLException {
        deleteUser.setInt(1, idUser);

        // Get the number of rows changed in the table
        int affectedRows = deleteUser.executeUpdate();

        // If the affected number of rows is not 1, then something went wrong
        if (affectedRows == 1) {
            return true;
        } else {
            throw new SQLException("Couldn't delete user off!");
        }
    }
}
