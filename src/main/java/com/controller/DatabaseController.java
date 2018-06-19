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
	static UserModel userModel = new UserModel();
	static MeetingModel meetingModel = new MeetingModel();
	static SleepModel sleepModel = new SleepModel();
	static StudentListModel studentListModel = new StudentListModel();

	/**
	 * Har til formål at oprette en forbindelse til en database ved brug af JDBC driveren.
	 * Først tjekke at driveren er inkluderet i projektbiblioteket, derefter anvendes JDBC driverens getConnection-
	 * metode til at forsøge at oprette forbindelse til database. Hvis det løkkes returneres forbindelsen.
	 * @return En database forbindelse.
	 */
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

	/**
	 * I metoden loadPopSleepModel() defineres variablerne st og rs, som er af typerne Statement og ReulstSet, disse sættes
	 * til at være lig null. Herefter defineres to ArrayLists sleep_time_array og sleep_awoke_array, som skal indeholde strings.
	 * Der laves et SQL kald, derfor laves der en try/catch, således eventuelle fejl kan fanges. I try defineres en variable SQL af typen String, som sættes til at et SQL statement
	 * hvor variablerne sleep_time og awoke_time vælges fra tabellen sleepdata i databasen, hvor de sorteres efter dato, hvor den ældste komemr først.
	 * Herefter benyttes createStatement, som pakker variablen SQL således den kan læses af databasen. executeQuery sender sql statmentent og retunere
	 * et ResultSet fra databasen. Dette ResultSet gemmes i varaiblen rs. Herefter benyttes en while løkke, hvor rs.next() læser hver række der er
	 * blevet retuneret fra  databasen. Her defineres to variabler af typen String, hvor der i sleep_time gemmes det der står på søjle 1
	 * og i awoke_time det der står i søjle to. Herefter gemmes disse værdier i hver deres arrayList.
	 * Efter alle rækkerne er blevet gemt i arrayListen, kaldes setAwoke_time og setSleep_time fra klassen SleepModel.
	 * i sleepModel.setAwoke_time gemmes arrayListen sleep_awoke_array og i sleepModel.setSleep_time gemmes arrayListen
	 * sleep_time_array.
	 * Hvis der sker en fejl når SQL statmentet sendes til databasen, fanges fejlen ved en SQLException, som printer en fejl.
	 */

	public static void loadPopSleepModel() {
		Statement st = null;
		ResultSet rs = null;
		ArrayList<String> sleep_time_array = new ArrayList<>();
		ArrayList<String> sleep_awoke_array = new ArrayList<>();
		try {

			String SQL = ("SELECT sleep_time, awoke_time FROM sleepdata ORDER BY sleep_time");
			connect().createStatement().executeQuery(SQL);
			st = connect().createStatement();
			rs = st.executeQuery(SQL);
			while (rs.next()) {
				String sleep_time = rs.getString(1);
				String awoke_time = rs.getString(2);
				sleep_time_array.add(sleep_time);
				sleep_awoke_array.add(awoke_time);
			}
			sleepModel.setAwoke_time(sleep_awoke_array);
			sleepModel.setSleep_time(sleep_time_array);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL ERROR");
		}
	}

	/**
	 * I metoden loadSleepModel() defineres variablerne st og rs, som er af typerne Statement og ResulstSet, disse sættes
	 * til at være lig null. Herefter defineres to ArrayLists sleep_time_array og sleep_awoke_array, som skal indeholde strings.
	 * Der laves et SQL kald, derfor laves der en try/catch, således eventuelle fejl kan fanges. I try defineres en variable SQL af typen String, som sættes til at et SQL statement
	 * hvor alle variablerne vælges fra tabellen sleepdata i databasen, hvor student_id er lig med det student_id, som er blevet givet som input parameter til metoden, de sorteres efter dato,
	 * hvor den ældste komemr først.
	 * Herefter benyttes createStatement, som pakker variablen SQL således den kan læses af databasen. executeQuery sender sql statmentent og retunere
	 * et ResultSet fra databasen. Dette ResultSet gemmes i varaiblen rs. Herefter benyttes en while løkke, hvor rs.next() læser hver række der er
	 * blevet retuneret fra  databasen. Her defineres tre variabler, hvor der i student_id gemmes det der står i søjle 1 og sleep_time gemmes det der står på søjle 2
	 * og i awoke_time det der står i søjle 3. Herefter gemmes disse værdier i hver deres arrayList borset fra student_id der gemmes i SleepModel med metoden setStudent_id.
	 * Efter alle rækkerne er blevet gemt i arrayListen, kaldes setAwoke_time og setSleep_time fra klassen SleepModel.
	 * i sleepModel.setAwoke_time gemmes arrayListen sleep_awoke_array og i sleepModel.setSleep_time gemmes arrayListen
	 * sleep_time_array.
	 * Hvis der sker en fejl når SQL statmentet sendes til databasen, fanges fejlen ved en SQLException, som printer en fejl.
	 */
	public static void loadSleepModel(String student_id) {
		Statement st = null;
		ResultSet rs = null;
		ArrayList<String> sleep_time_array = new ArrayList<>();
		ArrayList<String> sleep_awoke_array = new ArrayList<>();
		try {

			String SQL = ("SELECT * FROM sleepdata WHERE student_id= '" + student_id + "' ORDER BY sleep_time ");
			connect().createStatement().executeQuery(SQL);
			st = connect().createStatement();
			rs = st.executeQuery(SQL);
			while (rs.next()) {
				student_id = rs.getString(1);
				String sleep_time = rs.getString(2);
				String awoke_time = rs.getString(3);
				sleep_time_array.add(sleep_time);
				sleep_awoke_array.add(awoke_time);
				new SleepModel().setStudent_id(student_id);
			}
			sleepModel.setAwoke_time(sleep_awoke_array);
			sleepModel.setSleep_time(sleep_time_array);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL ERROR");
		}
	}

	/**
	 * I metoden loadMeetingModel() defineres variablerne st og rs, som er af typerne Statement og ReulstSet, disse sættes
	 * til at være lig null. Herefter defineres fire ArrayLists: participatingStudent, meeting_time, meeting_location og participatingCoordinatorm hvor alle undtagen meeting_time skal indeholde strings og meeting_time
	 * skal indeholde data af typen Date.
	 * Der laves et SQL kald, derfor laves der en try/catch, således eventuelle fejl kan fanges. I try defineres en variable SQL af typen String, som sættes til at et SQL statement
	 * hvor alle variablerne vælges fra tabellen meeting i databasen.
	 * Herefter benyttes createStatement, som pakker variablen SQL således den kan læses af databasen. executeQuery sender sql statmentent og retunere
	 * et ResultSet fra databasen. Dette ResultSet gemmes i varaiblen rs. Herefter benyttes en while løkke, hvor rs.next() læser hver række der er
	 * blevet retuneret fra  databasen. Her defineres fire variabler, hvor der i student_id gemmes det der står i søjle 2 og participatingCoordinator_id gemmes det der står på søjle 3
	 * og i meetingTime gemmes det der står i søjle 1 og i meetingLocation gemmes det der står i søjle 4.
	 * Herefter gemmes disse værdier i hver deres arrayList.
	 * Efter alle rækkerne er blevet gemt i arrayListenerne, kaldes setParticipatingCoordinator, setMeetingTime, setparticipatingStudent_id og setMeetingLocation fra klassen MeetingModel.
	 * Dette gør at alt data gemmes i klassen MeetingModel.
	 * Hvis der sker en fejl når SQL statmentet sendes til databasen, fanges fejlen ved en SQLException, som printer en fejl.
	 */
	public static void loadMeetingModel() {
		Statement st = null;
		ResultSet rs = null;
		ArrayList<String> participatingStudent = new ArrayList<>();
		ArrayList<Date> meeting_time = new ArrayList<>();
		ArrayList<String> meeting_location = new ArrayList<>();
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
				String meetingLocation = rs.getString(4);
				meeting_location.add(meetingLocation);
				participatingStudent.add(student_id);
				meeting_time.add(meetingTime);
				participatingCoordinator.add(participatingCoordinator_id);
			}
			meetingModel.setParticipatingCoordinator(participatingCoordinator);
			meetingModel.setMeetingTime(meeting_time);
			meetingModel.setparticipatingStudent_id(participatingStudent);
			meetingModel.setMeetingLocation(meeting_location);


		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL ERROR");
		}
	}

	/**
	 * I metoden loadStudentListModel() defineres variablerne st og rs, som er af typerne Statement og ReulstSet, disse sættes
	 * til at være lig null. Herefter defineres ArrayListen studentListModel, som skal indeholde strings.
	 * Der laves et SQL kald, derfor laves der en try/catch, således eventuelle fejl kan fanges. I try defineres en variable SQL af typen String, som sættes til at et SQL statement
	 * hvor variablen student_id vælges fra tabellen student i databasen, hvor student_consent = 1.
	 * Herefter benyttes createStatement, som pakker variablen SQL således den kan læses af databasen. executeQuery sender sql statmentent og retunere
	 * et ResultSet fra databasen. Dette ResultSet gemmes i varaiblen rs. Herefter benyttes en while løkke, hvor rs.next() læser hver række der er
	 * blevet retuneret fra  databasen. Her defineres variablen student af typen String, hvor der gemmes det der står i søjle 1.
	 * og i awoke_time det der står i søjle to. Herefter gemmes disse værdier i hver deres arrayList.
	 * Efter alle rækkerne er blevet gemt i arrayListen, kaldes setStudentList_id fra klassen StudentListModel.
	 * i setStudentList_id gemmes arrayListen StudentListModels.
	 * Hvis der sker en fejl når SQL statmentet sendes til databasen, fanges fejlen ved en SQLException, som printer en fejl.
	 */
	public static void loadStudentListModel() {
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
			studentListModel.setStudentList_id(studentListModels);
			//StudentListModel.studentList_id = studentListModels;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL ERROR");
		}

	}

	/**
	 * I metoden loadUserModel() defineres variablerne st og rs, som er af typerne Statement og ReulstSet, disse sættes
	 * til at være lig null. Herefter defineres variablerne user_pass og user_firstname, som er af datatypen String.
	 * Der laves et SQL kald, derfor laves der en try/catch, således eventuelle fejl kan fanges. I try defineres en variable SQL af typen String, som sættes til at et SQL statement
	 * hvor alle variabler vælges fra tabellen healthcoordinator i databasen, hvor user_id er lig med det user_id der er givet som input til metoden.
	 * Herefter benyttes createStatement, som pakker variablen SQL således den kan læses af databasen. executeQuery sender sql statmentent og retunere
	 * et ResultSet fra databasen. Dette ResultSet gemmes i varaiblen rs. Herefter benyttes en if løkke, hvor rs.next() læser den række der bliver retuneret
	 * fra  databasen. Her defineres variablerne user_id, user_pass og user_firstname af typen String, hvor der gemmes det der står i søjle 1 på user_id
	 * og det der står i søjle 2 gemmes i user_pass og det der står i søjle 3 gemmes i user_firstname.
	 * Efter rækken er blevet gemt i de tre variabler, kaldes setUser_id, setUser_pass og setUser_firstname fra klassen UserModel.
	 * Disse metoder gemmer de retunerede værdier fra databasen i variablerne user_id, user_pass og user_firstname i klassen UserModel.
	 * Hvis der ikke retuneres noget fra databsen printes der en String ud der skriver "fejl".
	 * Hvis der sker en fejl når SQL statmentet sendes til databasen, fanges fejlen ved en SQLException, som printer en fejl.
	 */
	public static void loadUserModel(String user_id) {
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
				userModel.setUser_id(user_id);
				userModel.setUser_pass(user_pass);
				userModel.setUser_firstname(user_firstname);
			} else {
				System.out.println("fejl");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL ERROR");
		}

	}

	/**
	 * I metoden updateModel() defineres variablerne st og rs, som er af typerne Statement og ReulstSet, disse sættes
	 * til at være lig null. Herefter defineres variablen sql_pass, som er af datatypen String.
	 * Der laves et SQL kald, derfor laves der en try/catch, således eventuelle fejl kan fanges. I try defineres en variable SQL af typen String, som sættes til at et SQL statement
	 * hvor varibalerne user_pass og user_firstname indsættes i tabellen healthcoordinator i databasen, med værdierne user_pass og user_firstname, som er input parameterne til metoden.
	 * Herefter benyttes createStatement, som pakker variablen SQL således den kan læses af databasen. executeUpdate sender sql statmentent til databasen, her renuteres der ikke noget.
	 * Der laves en SQL2, hvor user_id vælges fra healthcoordinator, hvor user_pass er lig med det user_pass der er skrevet ind. Det samme gælder for user_firstname.
	 * Der bruges ORDER BY user_id DESC, da hvis en person opretter sig med samme navn og password som en anden, vil personen få tildelt det rigtige user_id.
	 * Dette ResultSet der reunteres gemmes i varaiblen rs. Herefter benyttes en if løkke, hvor rs.next() læser den række der bliver retuneret
	 * fra  databasen. Her instanseres en Alert af typen INFORMATION, med teksten "Dette er dit brugerID: ID" ved ID bruges rs.getString(1) som retunere det der står på søjle 1.
	 * Der bruges alert.show, så ledes information boksen vises for brugeren.
	 * Hvis der sker en fejl når SQL statmentet sendes til databasen, fanges fejlen ved en SQLException, som printer en fejl.
	 */
	public static void updateModel(String user_pass, String user_firstname) {
		Statement st = null;
		ResultSet rs = null;
		String sql_pass = user_pass;
		try {
			String SQL = ("INSERT INTO healthcoordinator (user_pass,user_firstname) VALUES('" + user_pass + "','" + user_firstname + "')");
			connect().createStatement().executeUpdate(SQL);

			String SQL2 = ("SELECT user_id FROM healthcoordinator WHERE user_pass = '" + sql_pass + "' AND user_firstname = '" + user_firstname + "'ORDER BY user_id DESC");
			st = connect().createStatement();
			rs = st.executeQuery(SQL2);
			if (rs.next()) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setContentText("Dette er dit brugerID: " + rs.getString(1));
				alert.show();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL ERROR");
		}

	}

	/**
	 *I metoden updateMeetingModel() defineres variablerne st og rs, som er af typerne Statement og ReulstSet, disse sættes
	 *til at være lig null. Der laves et SQL kald, derfor laves der en try/catch, således eventuelle fejl kan fanges. I try defineres en variable SQL af typen String, som sættes til at et SQL statement
	 *hvor der benyttes UPDATE af tabellen meeting i databasen, hvor meeting_time sættes til at være lig med det valgte møde tidspunkt, user_id sættes til at være lig med sundhedskoordintorens id for den student
	 * der er blevet valgt.
	 * Hvis der sker en fejl når SQL statmentet sendes til databasen, fanges fejlen ved en SQLException, som printer en fejl.
	 */
	public static void updateMeetingModel(String student_id, String user_id, LocalDate meetingDate) {
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
