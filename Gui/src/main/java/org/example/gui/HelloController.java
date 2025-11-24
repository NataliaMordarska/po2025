package org.example.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Button startButton;

    @FXML
    private Button stopButton;

    @FXML
    private Button increaseGearButton;

    @FXML
    private Button decreaseGearButton;

    @FXML
    private Label welcomeText;

    @FXML
    private void onStartButton() {
        System.out.println("Samochód uruchomiony!");
        welcomeText.setText("Samochód uruchomiony!");
    }

    @FXML
    private void onStopButton() {
        System.out.println("Samochód zatrzymany!");
        welcomeText.setText("Samochód zatrzymany!");
    }

    @FXML
    private void onIncreaseGearButton() {
        System.out.println("Zwiększono bieg!");
        welcomeText.setText("Zwiększono bieg!");
    }

    @FXML
    private void onDecreaseGearButton() {
        System.out.println("Zmniejszono bieg!");
        welcomeText.setText("Zmniejszono bieg!");
    }
}
