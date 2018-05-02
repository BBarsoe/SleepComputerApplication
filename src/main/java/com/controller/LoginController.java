package com.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sun.rmi.runtime.Log;

import javax.xml.transform.Result;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class LoginController {

	private static MainController mainController;
	private Result db_user_id;


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
//		this.user_name.setOnKeyPressed((event) -> { if(event.getCode() == KeyCode.ENTER) { handleLoginButton(); } });
//		this.user_pass.setOnKeyPressed((event) -> { if(event.getCode() == KeyCode.ENTER) { handleLoginButton(); } });
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
	    try{
	        String SQL = ("INSERT INTO healthcoordinator (user_pass) VALUES('"+register_password.getText()+"')");

	        mainController.con.createStatement().executeUpdate(SQL);
        } catch(SQLException e) {
	        e.printStackTrace();
            System.out.println("Fejl");
        }
       /* try{
	        String SQL = ("SELECT user_id FROM healthcoordinator WERE"+ user_pass+"="+register_password);
	        db_user_id= (Result) mainController.con.createStatement().executeQuery(SQL);

        } catch (SQLException e){
	        e.printStackTrace();
            System.out.println("Fejl 2");
        }*/
        System.out.println(register_name.getText());
        System.out.println(register_username.getText());
        System.out.println(register_password.getText());
        mainController.goToMainView();
    }
}
