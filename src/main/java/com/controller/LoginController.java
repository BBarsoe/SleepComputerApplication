package com.controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

import java.sql.SQLException;


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

	public LoginController () {

	}

	public void initialize(){
		this.user_name.setOnKeyPressed((event) -> { if(event.getCode() == KeyCode.ENTER) { handleLoginButton(); } });
		this.user_pass.setOnKeyPressed((event) -> { if(event.getCode() == KeyCode.ENTER) { handleLoginButton(); } });
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

		mainController.updateModels(user_name.getText(),user_pass.getText());
	}

	public void handleRegisterButton (){
		mainController.goToRegisterView();
	}

	public void handleOpretButton (){
	    mainController.goToMainView();
    }
}
