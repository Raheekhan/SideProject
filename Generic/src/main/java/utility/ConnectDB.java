package utility;

import java.sql.*;

public class ConnectDB {

    private static Connection myConn = null;
    private static Statement myStmt = null;
    private static PreparedStatement myPstmt = null;
    private static ResultSet myRs = null;

    private static String DBURL = "jdbc:mysql://localhost:3306/demo";
    private static String USER = "student";
    private static String PASS = "student";

    private static void getConnectionToDB() {
        try {
            myConn = DriverManager.getConnection(DBURL, USER, PASS);
            myStmt = myConn.createStatement();
            System.out.println("\n===============================");
            System.out.println("Database connection successful!");
            System.out.println("===============================\n");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Unable to get a connection");
        }
    }

    public void readData(String tableName) {
        try {
            getConnectionToDB();
            myRs = myStmt.executeQuery("SELECT * FROM " + tableName);
            while (myRs.next()) {
                display(myRs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            close(myRs, myStmt, myConn);
        }
    }

    private static void display(ResultSet rs) {
        try {
            while (rs.next()) {
                System.out.println("Employee: " + rs.getString("id") + ", " + rs.getString("last_name") + ", " + rs.getString("first_name")
                        + ", " + rs.getString("email") + ", " + rs.getString("department") + ", " + rs.getString("salary"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void close(ResultSet rs, Statement stmt, Connection conn) {
        if((rs != null) && (stmt != null) && (conn != null)) {
            try {
                rs.close();
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
