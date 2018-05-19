import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import java.lang.reflect.Method;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class SleepControllerTest extends ApplicationTest {

    public FXMLLoader fxmlLoader = new FXMLLoader();

    @Override
    public void start (Stage stage) throws Exception {
        Parent mainNode = fxmlLoader.load(SleepController.class.getResource("PreviousSleepView.fxml"));
        stage.setScene(new Scene(mainNode));
        stage.show();
        stage.toFront();
    }
    @Before
    public void setUp () throws Exception {


    }

    @After
    public void tearDown () throws Exception {

    }


    @Test
    public void testLineChart(){
        MouseButton seIndData = null;
        press(seIndData);
    }
}
