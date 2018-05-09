package com.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import java.io.IOException;


public class SleepController {

    @FXML
    private ChoiceBox countChoiceBox;

    public void onClickChoiceBox(ActionEvent actionEvent) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("PreviousSleepView.fxml"));
            Stage newStage = new Stage();
            newStage.setTitle("Change item's count");
            newStage.setScene(new Scene(root, 320, 240));
            newStage.show();
            countChoiceBox = new ChoiceBox();
            countChoiceBox.setItems(FXCollections.observableArrayList("Alle","Anne","Allan"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


