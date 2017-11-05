package utility;

import base.CommonAPI;
import configuration.Config;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ConnectDB extends CommonAPI implements Config {

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

    @Test(enabled = false)
    public void test() {
        readData();
    }

    public List<String> readData() {
        List<String> items = new LinkedList<>();
        try {
            getConnectionToDB();
            myRs = myStmt.executeQuery("SELECT * FROM searchitems");
            while (myRs.next()) {
                items.add(myRs.getString("item"));
            }
            for(String item : items) {
                System.out.println(item);
//                enterInput(element, item);
//                clearInput(element);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            close(myRs, myStmt, myConn);
        }
        return null;
    }

//    public String readData(String tableName) {
//        try {
//            getConnectionToDB();
//            myRs = myStmt.executeQuery("SELECT * FROM " + tableName);
//            while (myRs.next()) {
//                display(myRs);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        finally {
//            close(myRs, myStmt, myConn);
//        }
//        return tableName;
//    }

    public String readData(String tableName, String item) {
        try {
            getConnectionToDBPreparedStmt();
            myPstmt = myConn.prepareStatement("SELECT * FROM " + tableName + " where item = ?");
            myPstmt.setString(1, item);
            myRs = myPstmt.executeQuery();
            display(myRs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tableName;
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
