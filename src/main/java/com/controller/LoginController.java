import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

import java.sql.ResultSet;
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
    private PasswordField register_password;
    @FXML
    private Button LoginButton = new Button();


    public void initialize(){
        LoginButton.setDefaultButton(true);
	}

	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}

	public void handleLoginButton () throws SQLException {
			validateID();
			new UserModel().setUser_id(user_name.getText());
			new UserModel().loadDatabase();
	}

	public void handleGoToRegister (){
	    mainController.goToRegisterView();


    }

	public void handleCancelButton() {
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
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Forkert brugernavn eller kode");
                alert.showAndWait();
            }
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Brugernavn eller kodeord var forkert");
        }

    }

	public void handleRegister () {
        String register_pass = register_password.getText();
        String register_firstname = register_name.getText();
        new UserModel().setUser_pass(register_pass);
        new UserModel().setUser_firstname(register_firstname);
        new UserModel().updateDatabase();

        mainController.goToLogin();
    }
}
