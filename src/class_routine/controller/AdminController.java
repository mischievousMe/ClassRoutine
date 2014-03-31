package class_routine.controller;

import java.sql.*;
import java.util.ArrayList;

import javax.sql.DataSource;

import class_routine.model.Admin;
import class_routine.view.AdminSignUp;

public class AdminController {
	private static DataSource dataSource;
	private static Admin curAdmin;

	public static void insert(Admin newAdmin) throws ClassNotFoundException{
		
		
		String sql = "INSERT INTO ADMIN_ACC_REQUEST (USERID, PASSWORD) VALUES (?, ?)";
		Connection conn = null;
		try {
			Class.forName("org.sqlite.JDBC");
		    conn = DriverManager.getConnection("jdbc:sqlite:/Users/shalini/Desktop/java/test.db");
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, newAdmin.getUserID());
			ps.setString(2, newAdmin.getPassword());
			ps.executeUpdate();
			ps.close();
 
		} catch (SQLException e) {
			throw new RuntimeException(e);
 
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}
	
	public static ArrayList<String> fetchAdminRequests() throws ClassNotFoundException {
		String sql = "SELECT * FROM ADMIN_ACC_REQUEST";
		Connection conn = null;
		try {
			Class.forName("org.sqlite.JDBC");
		    conn = DriverManager.getConnection("jdbc:sqlite:/Users/shalini/Desktop/java/test.db");
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			ArrayList<String> users = new ArrayList<String>();
			while(rs != null && rs.next()) {
				String userID = rs.getString("USERID");
				users.add(userID);
				System.out.println(userID);
			}
			System.out.println(users.size());
			ps.close();
			return users;
 
		} catch (SQLException e) {
			throw new RuntimeException(e);
 
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}
 
	public static Admin findByUserId(String id) throws ClassNotFoundException{
 
		String sql = "SELECT * FROM ADMIN WHERE USERID = ?";
 
		Connection conn = null;
 
		try {
			Class.forName("org.sqlite.JDBC");
		    conn = DriverManager.getConnection("jdbc:sqlite:/Users/shalini/Desktop/java/test.db");
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			Admin curAdmin = new Admin("", "");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				curAdmin = new Admin(
					rs.getString("USERID"),
					rs.getString("PASSWORD")
				);
			}
			rs.close();
			ps.close();
			return curAdmin;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}
	}
	

}
