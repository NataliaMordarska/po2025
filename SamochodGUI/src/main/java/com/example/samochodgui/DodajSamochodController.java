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

    private HelloController mainController;

    public void setParentController(HelloController mainController) {
        this.mainController = mainController;
    }

    @FXML
    private void onConfirmButton() {
        try {
            String model = modelTextField.getText();
            String registration = registrationTextField.getText();

            if (model.isEmpty() || registration.isEmpty()) {
                mainController.pokazBlad("Model i numer rejestracyjny nie mogą być puste!");
                return;
            }

            double weight;
            int maxObroty;
            try {
                weight = Double.parseDouble(weightTextField.getText());
                maxObroty = Integer.parseInt(speedTextField.getText());
            } catch (NumberFormatException e) {
                mainController.pokazBlad("Waga i Obroty muszą być liczbami!");
                return;
            }

            if (weight <= 0) {
                mainController.pokazBlad("Waga musi być większa od 0!");
                return;
            }

            if (maxObroty < 2000 || maxObroty > 10000) {
                mainController.pokazBlad("Obroty muszą mieścić się w zakresie 2000 - 10000!");
                return;
            }

            Silnik silnik = new Silnik("Producent", "Silnik " + model, 2000.0, weight, maxObroty);
            Skrzyniabiegow skrzynia = new Skrzyniabiegow("Producent", "Skrzynia", 1500.0, 15, 6);
            Sprzeglo sprzeglo = new Sprzeglo("Producent", "Sprzeglo", 500.0, 15);

            Samochod nowySamochod = new Samochod(
                    silnik,
                    skrzynia,
                    sprzeglo,
                    new Pozycja(0, 0),
                    model + " [" + registration + "]"
            );

            if (mainController != null) {
                mainController.dodajSamochod(nowySamochod);
            }

            Stage stage = (Stage) confirmButton.getScene().getWindow();//zamykanie okna
            stage.close();

        } catch (Exception e) {//blad jakby np pamieci bylo zbyt malo
            if (mainController != null) {
                mainController.pokazBlad("Błąd krytyczny: " + e.getMessage());
            }
        }
    }

    @FXML
    private void onCancelButton() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}