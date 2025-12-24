package samochodgui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {

    @FXML private Button startButton;
    @FXML private Button stopButton;
    @FXML private Button increaseGearButton;
    @FXML private Button decreaseGearButton;
    @FXML private Button genericButton;
    @FXML private Button addCarButton;
    @FXML private Button removeCarButton;
    @FXML private Button addGasButton;
    @FXML private Button reduceGasButton;
    @FXML private Button pressClutchButton;
    @FXML private Button releaseClutchButton;

    @FXML private Label welcomeText;

    @FXML private TextField modelTextField;
    @FXML private TextField weightTextField;
    @FXML private TextField speedTextField;

    @FXML private TextField gearboxNameTextField;
    @FXML private TextField gearboxPriceTextField;
    @FXML private TextField gearboxWeightTextField;
    @FXML private TextField gearboxGearTextField;

    @FXML private TextField engineNameTextField;
    @FXML private TextField enginePriceTextField;
    @FXML private TextField engineWeightTextField;
    @FXML private TextField engineRPMTextField;

    @FXML private TextField clutchNameTextField;
    @FXML private TextField clutchPriceTextField;
    @FXML private TextField clutchWeightTextField;
    @FXML private TextField clutchStateTextField;

    @FXML
    public void initialize() {
        modelTextField.setEditable(false);
        weightTextField.setEditable(false);
        speedTextField.setEditable(false);

        gearboxNameTextField.setEditable(false);
        gearboxPriceTextField.setEditable(false);
        gearboxWeightTextField.setEditable(false);
        gearboxGearTextField.setEditable(false);

        engineNameTextField.setEditable(false);
        enginePriceTextField.setEditable(false);
        engineWeightTextField.setEditable(false);
        engineRPMTextField.setEditable(false);

        clutchNameTextField.setEditable(false);
        clutchPriceTextField.setEditable(false);
        clutchWeightTextField.setEditable(false);
        clutchStateTextField.setEditable(false);
    }

    private int get(TextField t) {
        try { return Integer.parseInt(t.getText()); }
        catch (Exception e) { return 0; }
    }

    @FXML
    private void onStartButton() {
        welcomeText.setText("Samochód uruchomiony");
        speedTextField.setText("50");
    }

    @FXML
    private void onStopButton() {
        welcomeText.setText("Samochód zatrzymany");
        speedTextField.setText("0");
    }

    @FXML
    private void onIncreaseGearButton() {
        int g = get(gearboxGearTextField);
        if (g < 6) g++;
        gearboxGearTextField.setText(String.valueOf(g));
    }

    @FXML
    private void onDecreaseGearButton() {
        int g = get(gearboxGearTextField);
        if (g > 0) g--;
        gearboxGearTextField.setText(String.valueOf(g));
    }

    @FXML
    private void onGenericButton() {
        welcomeText.setText("Kliknięto genericButton");
    }

    @FXML
    private void onAddCarButton() {
        welcomeText.setText("Dodano samochód");
    }

    @FXML
    private void onRemoveCarButton() {
        welcomeText.setText("Usunięto samochód");
    }

    @FXML
    private void onAddGasButton() {
        int rpm = get(engineRPMTextField) + 100;
        int speed = get(speedTextField) + 10;
        engineRPMTextField.setText(String.valueOf(rpm));
        speedTextField.setText(String.valueOf(speed));
    }

    @FXML
    private void onReduceGasButton() {
        int rpm = Math.max(0, get(engineRPMTextField) - 100);
        int speed = Math.max(0, get(speedTextField) - 10);
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
