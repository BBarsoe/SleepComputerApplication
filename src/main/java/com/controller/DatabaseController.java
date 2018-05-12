import javafx.scene.control.Alert;

import java.sql.*;

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

	static void loadUserModel(String user_id) throws SQLException {
		Statement st = null;
		ResultSet rs = null;
		String user_pass;
		String user_firstname;

		String SQL = ("SELECT * FROM healthcoordinator WHERE user_id= '"+user_id+"'");
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
