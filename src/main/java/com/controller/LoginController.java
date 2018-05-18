import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sun.rmi.runtime.Log;
import sun.tools.jar.Main;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class LoginController {

	private static MainController mainController;
	UserModel userModel = new UserModel();
    private static Stage primaryStage;
    private static AnchorPane loginView;

    @FXML
	private TextField user_name;
	@FXML
	private PasswordField user_pass;
    @FXML
    private TextField register_name;
    @FXML
    private PasswordField register_password;
    @FXML
    private Button LoginButton = new Button();


    public void initialize(){
        LoginButton.setDefaultButton(true);
	}
    public void goToLogin(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainController.class
                    .getResource("/LoginView.fxml"));
            loginView = (AnchorPane) loader.load();
            Scene scene = new Scene(loginView);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}
	public void handleLogin() {
        String login_username = user_name.getText();
        String login_pass = user_pass.getText();
        validateID(login_username, login_pass);
        userModel.setUser_id(user_name.getText());
        userModel.loadModel();
    }

	public void handleLoginButton () throws SQLException {
        handleLogin();
	}

	public void handleGoToRegister (){
	    mainController.goToRegister();
    }

	public void handleCancelButton() {
        goToLogin(primaryStage);
    }

	public void validateID(String login_userID, String login_password){
        userModel.validateID(login_userID,login_password);
    }

    public void handleRegister () {
        String register_pass = register_password.getText();
        String register_firstname = register_name.getText();
        userModel.setUser_pass(register_pass);
        userModel.setUser_firstname(register_firstname);
        userModel.updateModel();
        goToLogin(primaryStage);
    }
}
