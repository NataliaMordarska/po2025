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

    // Asocjacja (referencja) do głównego kontrolera
    private HelloController mainController;

    // Metoda ustawiająca referencję (setMainController)
    public void setParentController(HelloController mainController) {
        this.mainController = mainController;
    }

    @FXML
    private void onConfirmButton() {
        try {
            String model = modelTextField.getText();
            String registration = registrationTextField.getText();

            // Walidacja czy pola nie są puste
            if (model.isEmpty() || registration.isEmpty()) {
                throw new Exception("Model i numer rejestracyjny nie mogą być puste!");
            }

            double weight;
            try {
                weight = Double.parseDouble(weightTextField.getText());
            } catch (NumberFormatException e) {
                // Wykorzystanie metody pokazBlad z głównego kontrolera
                if (mainController != null) {
                    mainController.pokazBlad("Niepoprawny format wagi! Wprowadź liczbę.");
                }
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

            // Zamknięcie okna po sukcesie
            Stage stage = (Stage) confirmButton.getScene().getWindow();
            stage.close();

        } catch (Exception e) {
            // Obsługa pozostałych wyjątków przez okno Alert
            if (mainController != null) {
                mainController.pokazBlad(e.getMessage());
            }
        }
    }

    @FXML
    private void onCancelButton() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}