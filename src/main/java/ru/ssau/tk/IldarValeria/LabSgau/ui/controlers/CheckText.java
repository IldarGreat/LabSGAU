package ru.ssau.tk.IldarValeria.LabSgau.ui.controlers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.ssau.tk.IldarValeria.LabSgau.ui.Errors.Errors;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class CheckText {

    @FXML
    private Button input;

    @FXML
    private TextField textField;

    @FXML
    void check(ActionEvent event) {

    }

    @FXML
    void click(ActionEvent event) throws IOException {
        try {
            int points = Integer.parseInt(textField.getText());
            if (points <= 0) {
                new Errors();
            } else {
                Stage stage = new Stage();
                InputStream iconStream = getClass().getResourceAsStream("/icons/howCreate.png");
                Image image = new Image(iconStream);
                stage.getIcons().add(image);
                stage.setTitle("Табулированная функция через массивы");
                stage.initModality(Modality.APPLICATION_MODAL);
                // stage.initOwner(new Main().getPrimaryStage());
                stage.setResizable(false);
                stage.show();
                FXMLLoader loader = new FXMLLoader();
                URL xmlUrl = getClass().getResource("/CreateObject/MassCreate.fxml");
                loader.setLocation(xmlUrl);
                Parent root = loader.load();
                stage.setScene(new Scene(root));
            }
        } catch (NumberFormatException e) {
            new Errors();
        }

    }
}
