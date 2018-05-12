import com.sun.corba.se.spi.servicecontext.UEInfoServiceContext;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javax.xml.crypto.Data;
import java.awt.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainController extends Application {

	public static Connection con;
	private static Stage primaryStage;
	private AnchorPane loginView;
	private GridPane mainView;
	private AnchorPane registerView;
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


		goToLogin();
		initDatabaseConnection();
	}

	private void initDatabaseConnection() {
		con = DatabaseController.connect();
	}

	public void goToLogin() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainController.class
					.getResource("/LoginView.fxml"));
			loginView = (AnchorPane) loader.load();
			Scene scene = new Scene(loginView);
			primaryStage.setScene(scene);
			LoginController controller = loader.getController();
			controller.setMainController(this);

			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void goToRegisterView() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainController.class.getResource("/RegisterView.fxml"));
			registerView = (AnchorPane) loader.load();
			Scene scene = new Scene(registerView);
			primaryStage.setScene(scene);
			LoginController controller = loader.getController();
			controller.setMainController(this);

			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void goToMainView() {
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

	public void goToArrangeMeetingView(){
		try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainController.class
					.getResource("/MeetingView.fxml"));
            arrangeMeetingView = (AnchorPane) loader.load();
            Scene scene = new Scene(arrangeMeetingView);
            primaryStage.setScene(scene);

			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void goToPreviousSleepView() {
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

	public void goToSleepHabitsView() {
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
		goToLogin();
	}

}

