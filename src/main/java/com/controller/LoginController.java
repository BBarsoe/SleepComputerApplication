package com.controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

	private MainController mainController;

	@FXML
	private TextField user_name;
	@FXML
	private PasswordField user_pass;

	private void Logincontroller () {

	}

	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}

	public void handleLoginButton () {
		if (user_name.getText().equalsIgnoreCase("admin") && user_pass.getText().equalsIgnoreCase("admin")) {
			/*
			TODO: Add real login authentication
			 */
			System.out.println("LOGIN SUCCESS");
		}
		mainController.goToMainView();
	}
}
