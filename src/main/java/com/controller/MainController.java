package com.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController extends Application {

	private Stage primaryStage;
	private AnchorPane loginView;
	private BorderPane mainView;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Sleep Computer Application");
		this.primaryStage.setResizable(false);

		goToLogin();
	}

	private void goToLogin () {
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

	public void goToMainView () {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainController.class
					.getResource("/MainView.fxml"));
			mainView = (BorderPane) loader.load();
			Scene scene = new Scene(mainView);
			primaryStage.setScene(scene);

			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
