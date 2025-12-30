package com.example.samochodgui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DodajSamochodController {
    @FXML private TextField modelTextField;
    @FXML private TextField registrationTextField;
    @FXML private TextField weightTextField;
    @FXML private TextField speedTextField;
    @FXML private Button confirmButton;
    @FXML private Button cancelButton;

    // Asocjacja do głównego kontrolera zgodnie z instrukcją
    private HelloController mainController;

    // Metoda ustawiająca referencję do głównego kontrolera
    public void setParentController(HelloController mainController) {
        this.mainController = mainController;
    }

    @FXML
    private void onConfirmButton() {
        String model = modelTextField.getText();
        String registration = registrationTextField.getText();
        double weight;

        try {
            weight = Double.parseDouble(weightTextField.getText());
        } catch (NumberFormatException e) {
            System.out.println("Niepoprawne dane wagi.");
            return;
        }

        // Tworzenie obiektu Samochod z początkową pozycją (0, 0)
        Samochod nowySamochod = new Samochod(
                new Silnik("Bosch", model, 2000.0, weight * 0.2, 6000),
                new Skrzyniabiegow("ZF", "Manual", 1500.0, weight * 0.1, 6),
                new Sprzeglo("Sachs", "Standard", 500.0, 5.0),
                new Pozycja(0, 0),
                model + " (" + registration + ")"
        );

        // Wywołanie publicznej metody dodajSamochod w głównym kontrolerze
        if (mainController != null) {
            mainController.dodajSamochod(nowySamochod);
        }

        Stage stage = (Stage) confirmButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void onCancelButton() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}