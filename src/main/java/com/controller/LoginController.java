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
	private static UserModel userModel;


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
    @FXML
	private Button LoginButton = new Button();


	public LoginController () {
    }
	public void initialize(){
		LoginButton.setDefaultButton(true);
	}

	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}

	public void handleLoginButton () throws SQLException {
			validateID();
		DatabaseController.loadModel(user_name.getText());
	}

	public void handleGoToRegister (){
	    mainController.goToRegisterView();


    }

	public void handleCancelButton() throws SQLException {
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

	public void handleRegister (){
        Statement st = null;
        ResultSet rs = null;
        String sql_pass = register_password.getText();
	    try{
	        String SQL = ("INSERT INTO healthcoordinator (user_pass,user_firstname) VALUES('"+register_password.getText()+"','"+register_name.getText()+"')");

	        mainController.con.createStatement().executeUpdate(SQL);

            String SQL2 = ("SELECT user_id FROM healthcoordinator WHERE user_pass = '"+sql_pass+"'");
            st = mainController.con.createStatement();
            rs= st.executeQuery(SQL2);
            if (rs.next()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Dette er dit brugerID: " + rs.getString(1));
                alert.show();
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
