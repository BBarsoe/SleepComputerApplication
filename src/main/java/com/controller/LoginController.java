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

	private MainController mainController;
	UserModel userModel = new UserModel();

    private static Stage primaryStage;
    private static AnchorPane loginView;
    private static AnchorPane registerView;

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

    /**
     *Med metoden initialize() sættes knappen loginButton til at være den markerede knap, således man kan
     * trykke enter når man vil logge ind.
     */
    public void initialize(){
        LoginButton.setDefaultButton(true);
	}

    /**
     * Med metoden goToLogin() sættes primaryStage til at være lig med variablen primarayStage i klassen mainController.
     * Dette gøres for at ændre primaryStage til nu at være LoginView.
     * Herefter instanserers loader fra klassen FXMLLoader.
     * Herefter benyttes setLocation til at fortælle, hvilket view vi gerne vil benytte.
     * variablen loginView sættes til at være lig med dette view, som er et AnchorPane.
     * Der instanseres nu en Scene, som skal være loginView, hvorefter dette view vises.
     * Hvis lokationen til viewet ikke kan findes smides der en IOException, hvor der bruges
     * printStackTrace, således man kan se hvad fejlen er og hvor i koden fejlen opstår
     */
    public void goToLogin(Stage primaryStage) {
        this.primaryStage = mainController.primaryStage;
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

    public void goToRegister(Stage primaryStage) {
        this.primaryStage = mainController.primaryStage;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainController.class.getResource("/RegisterView.fxml"));
            registerView = (AnchorPane) loader.load();
            Scene scene = new Scene(registerView);
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
    }

	public void handleLoginButton () throws SQLException {
        handleLogin();
	}

	public void handleGoToRegister (){
        goToRegister(primaryStage);
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
        if ((register_firstname.equals("")) || (register_pass.equals(""))) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Mangler at indtaste navn og/eller kodeord");
            alert.showAndWait();
        }else {
            userModel.setUser_pass(register_pass);
            userModel.setUser_firstname(register_firstname);
            userModel.updateModel();
            goToLogin(primaryStage);
        }
    }
}
