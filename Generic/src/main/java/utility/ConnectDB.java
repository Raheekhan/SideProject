package utility;

import configuration.Config;
import org.testng.annotations.Test;

import java.sql.*;

public class ConnectDB implements Config {

    private Connection myConn = null;
    private Statement myStmt = null;
    private PreparedStatement myPstmt = null;
    private ResultSet myRs = null;

    private void getConnectionToDB() {
        try {
            myConn = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
            myStmt = myConn.createStatement();
            System.out.println("\n===============================");
            System.out.println("Database connection successful!");
            System.out.println("===============================\n");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Unable to get a connection");
        }
    }

    private void getConnectionToDBPreparedStmt() {
        try {
            myConn = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
            System.out.println("\n===============================");
            System.out.println("Database connection successful!");
            System.out.println("===============================\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test() {
        readData("Laptop");
    }

    public void readData() {
        try {
            getConnectionToDB();
            myRs = myStmt.executeQuery("SELECT * FROM searchitems");
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

    public void readData(String tableName) {
        try {
            getConnectionToDBPreparedStmt();
            myPstmt = myConn.prepareStatement("SELECT * FROM " + tableName);
            myPstmt.setString(1, tableName);
            myRs = myPstmt.executeQuery();
            display(myRs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void display(ResultSet rs) {
        try {
            while (rs.next()) {
                System.out.println(rs.getString("item"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void close(ResultSet rs, Statement stmt, Connection conn) {
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
