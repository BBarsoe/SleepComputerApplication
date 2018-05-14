import javafx.scene.control.Alert;

import java.sql.*;
import java.util.ArrayList;


class DatabaseController {


	static String dbAddress = "jdbc:mysql://212.10.146.182:3306/test?autoReconnect=true&useSSL=false";
	static String dbUsername = "admin";
	static String dbPassword = "KellePrik134!";
	static UserModel userModel;

	static Connection connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
			System.out.println("Class not found");
			return null;
		}
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(dbAddress, dbUsername, dbPassword);
			return connection;
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
			return null;
		}
	}

	static void loadSleepModel(String student_id) throws SQLException {
		Statement st = null;
		ResultSet rs = null;
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date dt = new java.util.Date();
		String awoke_time = sdf.format(dt);
		String sleep_time = sdf.format(dt);
		try {

			String SQL = ("SELECT * FROM sleepdata WHERE student_id= '" + student_id + "'");
			connect().createStatement().executeQuery(SQL);
			st = connect().createStatement();
			rs = st.executeQuery(SQL);
			if (rs.next()) {
				student_id = rs.getString(1);
				sleep_time = rs.getString(2);
				awoke_time = rs.getString(3);
				new SleepModel().setStudent_id(student_id);
				new SleepModel().setSleep_time(sleep_time);
				new SleepModel().setAwoke_time(awoke_time);
			} else {
				System.out.println("fejl");
			}
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL ERROR");
		}
	}

	static void loadStudentListModel() throws SQLException {
		Statement st = null;
		ResultSet rs = null;
		ArrayList<String> studentList = new ArrayList<>();
		try {

			String SQL = ("SELECT student_id FROM student WHERE student_consent = 1");
			connect().createStatement().executeQuery(SQL);
			st = connect().createStatement();
			rs = st.executeQuery(SQL);
			while (rs.next()) {
				String studentlist= rs.getString(1);
				for (int i = 0; i < studentlist.length(); i++) {
					String student = rs.getString(1);
					studentList.add(student);
					System.out.println(studentlist);
				}
			}

		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL ERROR");
		}

	}


	static void loadUserModel(String user_id) throws SQLException {
		Statement st = null;
		ResultSet rs = null;
		String user_pass;
		String user_firstname;
		try {

			String SQL = ("SELECT * FROM healthcoordinator WHERE user_id= '" + user_id + "'");
			connect().createStatement().executeQuery(SQL);
			st = connect().createStatement();
			rs = st.executeQuery(SQL);
			if (rs.next()) {
				user_id = rs.getString(1);
				user_pass = rs.getString(2);
				user_firstname = rs.getString(3);
				new UserModel().setUser_id(user_id);
				new UserModel().setUser_pass(user_pass);
				new UserModel().setUser_firstname(user_firstname);
				System.out.println(new UserModel().getUser_id());
				System.out.println(new UserModel().getUser_pass());
				System.out.println(new UserModel().getUser_firstname());
			} else {
				System.out.println("fejl");
			}
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL ERROR");
		}

	}

	static void updateModel(String user_pass, String user_firstname) {
		Statement st = null;
		ResultSet rs = null;
		String sql_pass = user_pass;
		try {
			String SQL = ("INSERT INTO healthcoordinator (user_pass,user_firstname) VALUES('"+user_pass+"','"+user_firstname+"')");
			connect().createStatement().executeUpdate(SQL);

			String SQL2 = ("SELECT user_id FROM healthcoordinator WHERE user_pass = '"+sql_pass+"'");
			st = connect().createStatement();
			rs = st.executeQuery(SQL2);
			if (rs.next()) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setContentText("Dette er dit brugerID: " + rs.getString(1));
				alert.show();
				loadUserModel(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL ERROR");
		}

	}
}
