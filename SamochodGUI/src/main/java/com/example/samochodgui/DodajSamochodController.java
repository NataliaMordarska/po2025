package com.example.samochodgui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class DodajSamochodController {
    @FXML private TextField modelTextField;
    @FXML private TextField registrationTextField;
    @FXML private TextField weightTextField;
    @FXML private ComboBox<Silnik> enginePatternComboBox;
    @FXML private Button confirmButton;
    @FXML private Button cancelButton;

    private HelloController mainController;

    @FXML
    public void initialize() {
        // Definicja 3 wzorców silników
        enginePatternComboBox.getItems().addAll(
                new Silnik("EcoCorp", "1.0 EcoBoost", 5000.0, 100.0, 5000),
                new Silnik("StandardLine", "2.0 Dynamic", 12000.0, 180.0, 6500),
                new Silnik("PowerMax", "4.4 V8 BiTurbo", 35000.0, 300.0, 9000)
        );

        enginePatternComboBox.setConverter(new StringConverter<Silnik>() {
            @Override
            public String toString(Silnik s) {
                return s == null ? "" : s.getModel() + " (Max: " + s.getMaxObroty() + " obr/min)";
            }
            @Override
            public Silnik fromString(String string) {
                return null;
            }
        });

        enginePatternComboBox.getSelectionModel().selectFirst();
    }

    public void setParentController(HelloController mainController) {
        this.mainController = mainController;
    }

    @FXML
    private void onConfirmButton() {
        try {
            String model = modelTextField.getText();
            String registration = registrationTextField.getText();
            Silnik wzorzec = enginePatternComboBox.getValue();

            if (model.isEmpty() || registration.isEmpty() || wzorzec == null) {
                mainController.pokazBlad("Wszystkie pola muszą być wypełnione!");
                return;
            }

            double wagaKaroserii;
            try {
                wagaKaroserii = Double.parseDouble(weightTextField.getText());
            } catch (NumberFormatException e) {
                mainController.pokazBlad("Waga musi być liczbą!");
                return;
            }

            Silnik nowySilnik = new Silnik(
                    wzorzec.getProducent(),
                    wzorzec.getModel(),
                    wzorzec.getCena(),
                    wzorzec.getWaga(),
                    wzorzec.getMaxObroty()
            );

            Skrzyniabiegow skrzynia = new Skrzyniabiegow();
            Sprzeglo sprzeglo = new Sprzeglo();

            Samochod nowySamochod = new Samochod(
                    nowySilnik,
                    skrzynia,
                    sprzeglo,
                    new Pozycja(0, 0),
                    model + " [" + registration + "]"
            );

            if (mainController != null) {
                mainController.dodajSamochod(nowySamochod);
            }

            Stage stage = (Stage) confirmButton.getScene().getWindow();
            stage.close();

        } catch (Exception e) {
            if (mainController != null) {
                mainController.pokazBlad("Błąd: " + e.getMessage());
            }
        }
    }

    @FXML
    private void onCancelButton() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}