package com.controller;

import com.model.UserModel;
import com.mysql.cj.xdevapi.SqlStatement;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
		this.user_name.setOnKeyPressed((event) -> { if(event.getCode() == KeyCode.ENTER) { handleLoginButton(); } });
		this.user_pass.setOnKeyPressed((event) -> { if(event.getCode() == KeyCode.ENTER) { handleLoginButton(); } });
	}

	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}

	public void handleLoginButton () {
			validateID();
		mainController.updateModels(user_name.getText(),user_pass.getText());
	}

	public void handleGoToRegister (){
	    mainController.goToRegisterView();

	}

	public void handleCancelButton(){
	    mainController.goToLogin();
    }

	public void validateID(){
        Statement st = null;
        ResultSet rs = null;
        String sql_login_username;
        String sql_login_pass;
        String login_username = user_name.getText();
        String login_pass = user_pass.getText();
        try {
            String SQL = ("SELECT * FROM healthcoordinator WHERE user_id = '" + user_name.getText() + "' AND user_pass = '" + user_pass.getText() + "'");
            mainController.con.createStatement().executeQuery(SQL);
            st = mainController.con.createStatement();
            rs = st.executeQuery(SQL);
            if (rs.next()) {
                sql_login_username = rs.getString(1);
                sql_login_pass = rs.getString(2);
                if (sql_login_username.equalsIgnoreCase(login_username) && sql_login_pass.equalsIgnoreCase(login_pass)) {

                    System.out.println("Login success");
                    mainController.goToMainView();
                } else {
                    System.out.println("fejl");
                }
            } else {
                System.out.println("Forkert brugernavn eller kode");
            }
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Brugernavn eller kodeord var forkert");
        }

    }

	public void handleRegister (){
        Statement st = null;
        ResultSet rs = null;
        String sql_pass = register_password.getText();
	    try{
	        String SQL = ("INSERT INTO healthcoordinator (user_pass) VALUES('"+register_password.getText()+"')");

	        mainController.con.createStatement().executeUpdate(SQL);

            String SQL2 = ("SELECT user_id FROM healthcoordinator WHERE user_pass = '"+sql_pass+"'");
            st = mainController.con.createStatement();
            rs= st.executeQuery(SQL2);
            if (rs.next()) {
                System.out.print("Dette er dit id= ");
                System.out.println(rs.getString(1));
            }

        } catch(SQLException e) {
	        e.printStackTrace();
            System.out.println("Fejl");
        }
        System.out.println(register_name.getText());
        System.out.println(register_username.getText());
        System.out.println(register_password.getText());
        mainController.goToMainView();
    }
}
