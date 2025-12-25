package com.example.samochodgui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloController {
    @FXML private TextField modelTextField, nrRejTextField, wagaTextField, predkoscTextField;
    @FXML private TextField sNazwaTextField, sCenaTextField, sWagaTextField, sBiegTextField;
    @FXML private TextField silNazwaTextField, silCenaTextField, silWagaTextField, silObrotyTextField;
    @FXML private TextField sprNazwaTextField, sprCenaTextField, sprWagaTextField, sprStanTextField;
    @FXML private ImageView carImage;
    @FXML private ComboBox<String> carComboBox;

    private static Samochod auto = new Samochod(
            new Silnik("Bosch", "V8", 5000.0, 200.0, 6000),
            new Skrzyniabiegow("ZF", "Manual", 2500.0, 60.0, 6),
            new Sprzeglo("Sachs", "Sport", 1000.0, 15.0),
            new Pozycja(0, 0)
    );

    @FXML
    public void initialize() {
        try {
            Image carImg = new Image(getClass().getResource("/com/example/samochodgui/car.png").toExternalForm());
            carImage.setImage(carImg);
            carImage.setFitWidth(30);
            carImage.setFitHeight(20);
        } catch (Exception e) {
            System.out.println("Blad obrazka: " + e.getMessage());
        }

        carComboBox.getItems().addAll("Audi A4", "BMW M3", "Tesla Model S");
        carComboBox.getSelectionModel().selectFirst();
        refresh();
    }

    public void addCarToList(String model, String registration, double weight, int speed) {
        System.out.println("Dodano samochód: " + model + ", rej: " + registration + ", waga: " + weight + ", speed: " + speed);
        carComboBox.getItems().add(model + " (" + registration + ")");
        carComboBox.getSelectionModel().selectLast();
    }

    @FXML
    public void onDodajAutoClick() {
        try {
            openAddCarWindow();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openAddCarWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DodajSamochod.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));

        DodajSamochodController controller = loader.getController();
        controller.setParentController(this);

        stage.setTitle("Dodaj nowy samochód");
        stage.show();
    }

    @FXML public void onUruchomClick() { auto.getSilnik().uruchom(); refresh(); }
    @FXML public void onWylaczClick() { auto.getSilnik().zatrzymaj(); refresh(); }
    @FXML public void onGazClick() {
        auto.getSilnik().zwiekszObroty();
        carImage.setLayoutX(carImage.getLayoutX() + 10);
        refresh();
    }
    @FXML public void onUjmijGazClick() {
        auto.getSilnik().zmniejszObroty();
        carImage.setLayoutX(carImage.getLayoutX() - 10);
        refresh();
    }
    @FXML public void onBiegGoraClick() { auto.getSkrzynia().zwiekszBieg(); refresh(); }
    @FXML public void onBiegDolClick() { auto.getSkrzynia().zmniejszBieg(); refresh(); }
    @FXML public void onSprzegloNacisnijClick() { auto.getSprzeglo().nacisnij(); refresh(); }
    @FXML public void onSprzegloZwolnijClick() { auto.getSprzeglo().zwolnij(); refresh(); }
    @FXML public void onCarSelected() { refresh(); }

    private void refresh() {
        wagaTextField.setText(String.valueOf(auto.getWaga()));
        nrRejTextField.setText("KR 12345");
        predkoscTextField.setText(String.valueOf(auto.getPredkosc()));
        modelTextField.setText(auto.getSilnik().getModel());
        sNazwaTextField.setText(auto.getSkrzynia().getModel());
        sBiegTextField.setText(String.valueOf(auto.getSkrzynia().getAktualnyBieg()));
        silObrotyTextField.setText(String.valueOf(auto.getSilnik().getAktualneObroty()));
        sprStanTextField.setText(auto.getSprzeglo().isNacisniete() ? "Wciśnięte" : "Zwolnione");
    }
}