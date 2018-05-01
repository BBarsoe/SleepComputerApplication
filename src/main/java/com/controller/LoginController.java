package com.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginController {

	private static MainController mainController;



	@FXML
	private TextField user_name;
	@FXML
	private PasswordField user_pass;
	@FXML
	private TextField register_name;
	@FXML
	private TextField register_username;
	@FXML
	private PasswordField register_password;



	private void Logincontroller () {

	}

	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}

	public void handleLoginButton () {

		try {
			String SQL = "SELECT * FROM healthcoordinator WHERE user_id LIKE "+ user_name.getText() + " AND user_pass LIKE "+ user_pass.getText() + ";";

			mainController.con.createStatement().executeQuery(SQL);
			System.out.println("Login success");
			mainController.goToMainView();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Brugernavn eller kodeord var forkert");
		}
	}

	public void handleRegisterButton (){
		mainController.goToRegisterView();
	}

	public void handleOpretButton (){
	    mainController.goToMainView();
    }

}
