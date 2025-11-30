package org.example.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
    private Button genericButton;

    @FXML
    private Button addCarButton;

    @FXML
    private Button removeCarButton;

    @FXML
    private Button addGasButton;

    @FXML
    private Button reduceGasButton;

    @FXML
    private Button pressClutchButton;

    @FXML
    private Button releaseClutchButton;

    @FXML
    private Label welcomeText;

    @FXML
    private TextField modelTextField;

    @FXML
    private TextField weightTextField;

    @FXML
    private TextField speedTextField;

    @FXML
    private TextField gearboxNameTextField;

    @FXML
    private TextField gearboxPriceTextField;

    @FXML
    private TextField gearboxWeightTextField;

    @FXML
    private TextField gearboxGearTextField;

    @FXML
    private TextField engineNameTextField;

    @FXML
    private TextField enginePriceTextField;

    @FXML
    private TextField engineWeightTextField;

    @FXML
    private TextField engineRPMTextField;

    @FXML
    private TextField clutchNameTextField;

    @FXML
    private TextField clutchPriceTextField;

    @FXML
    private TextField clutchWeightTextField;

    @FXML
    private TextField clutchStateTextField;

    @FXML
    private void onStartButton() {
        System.out.println("Samochód uruchomiony!");
        welcomeText.setText("Samochód uruchomiony!");
        speedTextField.setText("50");
    }

    @FXML
    private void onStopButton() {
        System.out.println("Samochód zatrzymany!");
        welcomeText.setText("Samochód zatrzymany!");
        speedTextField.setText("0");
    }

    @FXML
    private void onIncreaseGearButton() {
        int gear = 0;
        if (!gearboxGearTextField.getText().isEmpty()) {
            gear = Integer.parseInt(gearboxGearTextField.getText());
        }
        if (gear < 6) gear++;
        gearboxGearTextField.setText(String.valueOf(gear));
    }

    @FXML
    private void onDecreaseGearButton() {
        int gear = 0;
        if (!gearboxGearTextField.getText().isEmpty()) {
            gear = Integer.parseInt(gearboxGearTextField.getText());
        }
        if (gear > 0) gear--;
        gearboxGearTextField.setText(String.valueOf(gear));
    }

    @FXML
    private void onGenericButton() {
        System.out.println("Kliknięto przycisk genericButton");
        welcomeText.setText("Kliknięto przycisk genericButton");
    }

    @FXML
    private void onAddCarButton() {
        System.out.println("Dodano nowy samochód!");
        welcomeText.setText("Dodano nowy samochód!");
    }

    @FXML
    private void onRemoveCarButton() {
        System.out.println("Usunięto samochód!");
        welcomeText.setText("Usunięto samochód!");
    }

    @FXML
    private void onAddGasButton() {
        int rpm = 0;
        int speed = 0;
        if (!engineRPMTextField.getText().isEmpty()) rpm = Integer.parseInt(engineRPMTextField.getText());
        if (!speedTextField.getText().isEmpty()) speed = Integer.parseInt(speedTextField.getText());
        rpm += 100;
        speed += 10;
        engineRPMTextField.setText(String.valueOf(rpm));
        speedTextField.setText(String.valueOf(speed));
    }

    @FXML
    private void onReduceGasButton() {
        int rpm = 0;
        int speed = 0;
        if (!engineRPMTextField.getText().isEmpty()) rpm = Integer.parseInt(engineRPMTextField.getText());
        if (!speedTextField.getText().isEmpty()) speed = Integer.parseInt(speedTextField.getText());
        rpm -= 100;
        if (rpm < 0) rpm = 0;
        speed -= 10;
        if (speed < 0) speed = 0;
        engineRPMTextField.setText(String.valueOf(rpm));
        speedTextField.setText(String.valueOf(speed));
    }

    @FXML
    private void onPressClutchButton() {
        clutchStateTextField.setText("Wciśnięte");
        speedTextField.setText("0");
    }

    @FXML
    private void onReleaseClutchButton() {
        clutchStateTextField.setText("Zwolnione");
    }
}
