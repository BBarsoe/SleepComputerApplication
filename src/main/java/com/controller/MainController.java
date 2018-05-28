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

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Sleep Computer Application");
		this.primaryStage.setResizable(false);
		loginController.goToLogin(primaryStage);
		initDatabaseConnection();
	}

	private void initDatabaseConnection() {
		con = DatabaseController.connect();
	}


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

	public void goToSleepHabits() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainController.class.getResource("/SleepHabitsView.fxml"));
			previousSleepView = (AnchorPane) loader.load();
			Scene scene = new Scene(sleepHabitsView);
			primaryStage.setScene(scene);

			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

    public void Logout(){
		new UserModel().setUser_id(null);
		new UserModel().setUser_firstname(null);
		new UserModel().setUser_pass(null);
		loginController.goToLogin(primaryStage);
	}

}

