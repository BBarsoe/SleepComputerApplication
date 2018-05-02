package com.controller;

import com.model.UserModel;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainController extends Application {

	public static Connection con;
	private Stage primaryStage;
	private AnchorPane loginView;
	private BorderPane mainView;
	private AnchorPane registerView;
	private UserModel userModel;

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
			mainView = (BorderPane) loader.load();
			Scene scene = new Scene(mainView);
			primaryStage.setScene(scene);


			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void updateModels(String user_name, String user_pass) {
		try {
			String SQL = "SELECT * FROM healthcoordinator WHERE user_id LIKE " + user_name + " AND user_pass LIKE " + user_pass + ";";

			ResultSet rs = con.createStatement().executeQuery(SQL);
			int numColumns = rs.getMetaData().getColumnCount();
			String[] resultString = new String[numColumns];
			while (rs.next()) {
				for ( int i = 1 ; i <= numColumns ; i++ ) {
					try {
						resultString[i - 1] = rs.getObject(i).toString();
					} catch (NullPointerException e) {
						resultString[i - 1] = "0";
					}
				}
			}
			this.userModel = new UserModel(resultString[0],resultString[1],resultString[2],resultString[3]);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL ERROR");
		}


	}
}
