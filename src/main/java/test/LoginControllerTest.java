import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.*;

/**
 * Tests the loginController class' UI.
 * Contains the required method start, inherited form the the ApplicationTest, along with the method for testing the class, and a method for tearing down the FXToolKit.
 */
public class LoginControllerTest extends ApplicationTest {

	public FXMLLoader fxmlLoader = new FXMLLoader();

	/**
	 * The main entry point for all JavaFX applications. The start method is called after the init method has returned, and after the system is ready for the application to begin running.
	 * @param stage		The primary stage for this application, onto which the application scene can be set.
	 * @throws Exception
	 */
	@Override
	public void start (Stage stage) throws Exception {
		Parent mainNode = fxmlLoader.load(LoginController.class.getResource("LoginView.fxml"));
		stage.setScene(new Scene(mainNode));
		stage.show();
		stage.toFront();
	}

	/**
	 * The final method the be called, after a test. Used to clear out the thread, and release the mouse/keyboard control from the JavaFX application
	 * @throws Exception
	 */
	@After
	public void tearDown () throws Exception {
		FxToolkit.hideStage();
		release(new KeyCode[]{});
		release(new MouseButton[]{});
	}

	/**
	 * Tests the UI of loginView.
	 * It creates two strings, one for username and one for the password.
	 * Writes the strings on screen, and checks whether the input is detected in the controller.
	 */
	@Test
	public void testUserInput () {
		String username = "1";
		String password = "123";
		clickOn("#user_name");
		write(username);
		press(KeyCode.TAB);
		write(password);
		assertEquals(username, lookup("#user_name").<TextField>query().getText());
		assertEquals(password, lookup("#user_pass").<TextField>query().getText());
	}
}