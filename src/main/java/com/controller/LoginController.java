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
		if ((user_name.getText().equalsIgnoreCase("admin")) && (user_pass.getText().equalsIgnoreCase("admin"))) {
			/*
			TODO: Add real login authentication
			 */
			System.out.println("LOGIN SUCCESS");
			mainController.goToMainView();
		} else {
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
