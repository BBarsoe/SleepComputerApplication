import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import sun.applet.Main;

import java.security.Key;

import static org.junit.Assert.*;

public class LoginControllerTest extends ApplicationTest {

	public MainController mainController;
	public FXMLLoader fxmlLoader = new FXMLLoader();

	@Override
	public void start (Stage stage) throws Exception {
		Parent mainNode = fxmlLoader.load(LoginController.class.getResource("LoginView.fxml"));
		stage.setScene(new Scene(mainNode));
		stage.show();
		stage.toFront();
	}

	@Before
	public void setUp () throws Exception {

	}

	@After
	public void tearDown () throws Exception {
		FxToolkit.hideStage();
		release(new KeyCode[]{});
		release(new MouseButton[]{});
	}

	@Test
	public void testEnglishInput () {
		write("1");
		press(KeyCode.TAB);
		write("123");
		press(KeyCode.ENTER);
	}

}