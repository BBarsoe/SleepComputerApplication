import javafx.scene.control.Alert;

import javax.naming.SizeLimitExceededException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;


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

	static void loadPopSleepModel() {
		Statement st = null;
		ResultSet rs = null;
		//java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd ; HH:mm:ss");
		//java.util.Date dt = new java.util.Date();
		ArrayList<String> sleep_time_array = new ArrayList<>();
		ArrayList<String> sleep_awoke_arry = new ArrayList<>();
		try {

			String SQL = ("SELECT sleep_time, awoke_time FROM sleepdata");
			connect().createStatement().executeQuery(SQL);
			st = connect().createStatement();
			rs = st.executeQuery(SQL);
			while (rs.next()) {
				String sleep_time = rs.getString(1);
				String awoke_time = rs.getString(2);
				sleep_time_array.add(sleep_time);
				sleep_awoke_arry.add(awoke_time);
			}
			SleepModel.awoke_time = sleep_awoke_arry;
			SleepModel.sleep_time = sleep_time_array;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL ERROR");
		}
	}


	static void loadSleepModel(String student_id) {
		Statement st = null;
		ResultSet rs = null;
		//java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd ; HH:mm:ss");
		//java.util.Date dt = new java.util.Date();
		ArrayList<String> sleep_time_array = new ArrayList<>();
		ArrayList<String> sleep_awoke_arry = new ArrayList<>();
		try {

			String SQL = ("SELECT * FROM sleepdata WHERE student_id= '" + student_id + "'");
			connect().createStatement().executeQuery(SQL);
			st = connect().createStatement();
			rs = st.executeQuery(SQL);
			while (rs.next()) {
				student_id = rs.getString(1);
				String sleep_time = rs.getString(2);
				String awoke_time = rs.getString(3);
				sleep_time_array.add(sleep_time);
				sleep_awoke_arry.add(awoke_time);
				new SleepModel().setStudent_id(student_id);
			}
			SleepModel.awoke_time = sleep_awoke_arry;
			SleepModel.sleep_time = sleep_time_array;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL ERROR");
		}
	}

	static void loadMeetingModel() {
		Statement st = null;
		ResultSet rs = null;
		ArrayList<String> participatingStudent = new ArrayList<>();
		ArrayList<Date> meeting_time = new ArrayList<>();
		ArrayList<String> participatingCoordinator = new ArrayList<>();
		try {
			String SQL = ("SELECT * FROM meeting");
			connect().createStatement().executeQuery(SQL);
			st = connect().createStatement();
			rs = st.executeQuery(SQL);
			while (rs.next()) {
				String student_id = rs.getString(2);
				String participatingCoordinator_id = rs.getString(3);
				Date meetingTime = rs.getDate(1);
				participatingStudent.add(student_id);
				meeting_time.add(meetingTime);
				participatingCoordinator.add(participatingCoordinator_id);
			}
			MeetingModel.setParticipatingCoordinator(participatingCoordinator);
			MeetingModel.participatingStudent_id = participatingStudent;
			MeetingModel.setMeetingTime(meeting_time);


		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL ERROR");
		}
	}

	static void loadStudentListModel() {
		Statement st = null;
		ResultSet rs = null;
		ArrayList<String> studentListModels = new ArrayList<>();
		try {

			String SQL = ("SELECT student_id FROM student WHERE student_consent = 1");
			connect().createStatement().executeQuery(SQL);
			st = connect().createStatement();
			rs = st.executeQuery(SQL);
			while (rs.next()) {
				String student = rs.getString(1);
				studentListModels.add(student);

			}
			StudentListModel.studentList_id = studentListModels;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL ERROR");
		}

	}


	static void loadUserModel(String user_id) {
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
				//System.out.println(new UserModel().getUser_id());
				//System.out.println(new UserModel().getUser_pass());
				//System.out.println(new UserModel().getUser_firstname());
			} else {
				System.out.println("fejl");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL ERROR");
		}

	}

	static void updateModel(String user_pass, String user_firstname) {
		Statement st = null;
		ResultSet rs = null;
		String sql_pass = user_pass;
		try {
			String SQL = ("INSERT INTO healthcoordinator (user_pass,user_firstname) VALUES('" + user_pass + "','" + user_firstname + "')");
			connect().createStatement().executeUpdate(SQL);

			String SQL2 = ("SELECT user_id FROM healthcoordinator WHERE user_pass = '" + sql_pass + "' AND user_firstname = '" + user_firstname + "'");
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

	static void updateMeetingModel(String student_id, String user_id, LocalDate meetingDate) {
		Statement st = null;
		ResultSet rs = null;
		try {
			String SQL = ("UPDATE meeting SET meeting_time = '" + meetingDate + "', user_id = '" + user_id + "' WHERE student_id = '" + student_id + "'");
			connect().createStatement().executeUpdate(SQL);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL ERROR");
		}
	}
}
