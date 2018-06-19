import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;

public class MainController extends Application {

	public static Connection con;
	LoginController loginController = new LoginController();
	public static Stage primaryStage;
	private static GridPane mainView;
	private AnchorPane arrangeMeetingView;
	private AnchorPane previousSleepView;
	private AnchorPane sleepHabitsView;


	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Gennem metoden start sættes vores primaryStage, med titlen 'Sleep Computer Application'.
	 * Her efter gøres der således vores primaryStage ikke kan justeres, men er den samme størrelse.
	 * Med metoden goToLogin(), som kaldes fra Logincontrolleren, sættes loginView til at være primaryStage.
	 * Med metoden initDatabaseConnection() skabes der forbindelse til databasen.
	 */
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Sleep Computer Application");
		this.primaryStage.setResizable(false);
		loginController.goToLogin(primaryStage);
		initDatabaseConnection();
	}

	/**
	 * variablen con er af typen java sql connection, hvor denne variable sættes til at være lig med
	 * metoden connect(), der kaldes fra klassen DatabaseController.
	 * Dette gøres for at kunne oprette forbindelse til databasen.
	 */
	private void initDatabaseConnection() {
		con = DatabaseController.connect();
	}

	/**
	 * Med metoden goToMain() instanserers loader fra klassen FXMLLoader.
	 * Herefter benyttes setLocation til at fortælle, hvilket view vi gerne vil benytte.
	 * variablen mainView sættes til at være lig med dette view, som er et GridPane.
	 * Der instanseres nu en Scene, som skal være mainView, hvorefter vises dette view.
	 * Hvis lokationen til viewet ikke kan findes smides der en IOException, hvor der bruges
	 * printStackTrace, således man kan se hvad fejlen er og hvor i koden fejlen opstår.
	 */
	public void goToMain() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainController.class
					.getResource("/MainView.fxml"));
			mainView = (GridPane) loader.load();
			Scene scene = new Scene(mainView);
			primaryStage.setScene(scene);

			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Med metoden goToArrangeMeeting() instanserers loader fra klassen FXMLLoader.
	 * Herefter benyttes setLocation til at fortælle, hvilket view vi gerne vil benytte.
	 * variablen arrangeMeetingView sættes til at være lig med dette view, som er et AnchorPane.
	 * Der instanseres nu en Scene, som skal være arrangeMeetingView, hvorefter dette view.
	 * Hvis lokationen til viewet ikke kan findes smides der en IOException, hvor der bruges
	 * printStackTrace, således man kan se hvad fejlen er og hvor i koden fejlen opstår
	 */
	public void goToArrangeMeeting(){
		try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainController.class
					.getResource("/ArrangeMeetingView.fxml"));
            arrangeMeetingView = (AnchorPane) loader.load();
            Scene scene = new Scene(arrangeMeetingView);
            primaryStage.setScene(scene);

			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Med metoden goToPreviousSleep() instanserers loader fra klassen FXMLLoader.
	 * Herefter benyttes setLocation til at fortælle, hvilket view vi gerne vil benytte.
	 * variablen previousSleepView sættes til at være lig med dette view, som er et AnchorPane.
	 * Der instanseres nu en Scene, som skal være previousSleepView, hvorefter dette view.
	 * Hvis lokationen til viewet ikke kan findes smides der en IOException, hvor der bruges
	 * printStackTrace, således man kan se hvad fejlen er og hvor i koden fejlen opstår
	 */
	public void goToPreviousSleep() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainController.class.getResource("/PreviousSleepView.fxml"));
			previousSleepView = (AnchorPane) loader.load();
			Scene scene = new Scene(previousSleepView);
			primaryStage.setScene(scene);

			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Med metoden goToSleepHabits() instanserers loader fra klassen FXMLLoader.
	 * Herefter benyttes setLocation til at fortælle, hvilket view vi gerne vil benytte.
	 * variablen sleepHabitsView sættes til at være lig med dette view, som er et AnchorPane.
	 * Der instanseres nu en Scene, som skal være sleepHabitsView, hvorefter dette view.
	 * Hvis lokationen til viewet ikke kan findes smides der en IOException, hvor der bruges
	 * printStackTrace, således man kan se hvad fejlen er og hvor i koden fejlen opstår
	 */
	public void goToSleepHabits() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainController.class.getResource("/SleepHabitsView.fxml"));
			sleepHabitsView = (AnchorPane) loader.load();
			Scene scene = new Scene(sleepHabitsView);
			primaryStage.setScene(scene);

			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Med metoden Logout() sættes user_id, User_firstname og user_pass til at være null i klassen UserModel.
	 * Herefter klades metoden goToLogin fra klassen LoginController, hvor loginView sættes til at være primaryStage.
	 */
    public void Logout(){
		new UserModel().setUser_id(null);
		new UserModel().setUser_firstname(null);
		new UserModel().setUser_pass(null);
		loginController.goToLogin(primaryStage);
	}

}

