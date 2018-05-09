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

	static void loadModel(String user_id) throws SQLException {
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
			userModel = new UserModel();
			userModel.setUser_id(user_id);
			userModel.setUser_pass(user_pass);
			userModel.setUser_firstname(user_firstname);
		} else {
			System.out.println("fejl");
		}
	}

	static void updateModel(String user_id, String user_pass) {
		try {
			String SQL = "SELECT * FROM healthcoordinator WHERE user_id LIKE " + user_id + " AND user_pass LIKE " + user_pass + ";";

			ResultSet rs = connect().createStatement().executeQuery(SQL);
			int numColumns = rs.getMetaData().getColumnCount();
			String[] resultString = new String[numColumns];
			while (rs.next()) {
				for (int i = 1; i <= numColumns; i++) {
					try {
						resultString[i - 1] = rs.getObject(i).toString();
					} catch (NullPointerException e) {
						resultString[i - 1] = "0";
					}
				}
			}
			//userModel = new UserModel(resultString[0], resultString[1], resultString[2]);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL ERROR");
		}

	}
}
