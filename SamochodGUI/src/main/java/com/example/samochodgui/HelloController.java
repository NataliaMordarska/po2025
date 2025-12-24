package com.example.samochodgui;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;

public class HelloController {
    @FXML private TextField modelTextField, nrRejTextField, wagaTextField, predkoscTextField;
    @FXML private TextField sNazwaTextField, sCenaTextField, sWagaTextField, sBiegTextField;
    @FXML private TextField silNazwaTextField, silCenaTextField, silWagaTextField, silObrotyTextField;
    @FXML private TextField sprNazwaTextField, sprCenaTextField, sprWagaTextField, sprStanTextField;
    @FXML private ImageView carImage;
    @FXML private ComboBox<String> carComboBox;

    private Samochod auto = new Samochod(
            new Silnik("Bosch", "V8", 5000.0, 200.0, 6000),
            new Skrzyniabiegow("ZF", "Manual", 2500.0, 60.0, 6),
            new Sprzeglo("Sachs", "Sport", 1000.0, 15.0),
            new Pozycja(50, 50)
    );

    @FXML
    public void initialize() {
        carComboBox.getItems().addAll("Audi A4", "BMW M3", "Tesla Model S");
        carComboBox.getSelectionModel().selectFirst();
        aktualizujInterfejs();
    }

    @FXML
    public void onCarSelected() {
        aktualizujInterfejs();
    }

    @FXML public void onUruchomClick() { auto.getSilnik().uruchom(); aktualizujInterfejs(); }
    @FXML public void onWylaczClick() { auto.getSilnik().zatrzymaj(); aktualizujInterfejs(); }

    @FXML public void onGazClick() {
        auto.getSilnik().zwiekszObroty();
        carImage.setLayoutX(carImage.getLayoutX() + 10);
        aktualizujInterfejs();
    }

    @FXML public void onUjmijGazClick() {
        auto.getSilnik().zmniejszObroty();
        carImage.setLayoutX(carImage.getLayoutX() - 10);
        aktualizujInterfejs();
    }

    @FXML public void onBiegGoraClick() { auto.getSkrzynia().zwiekszBieg(); aktualizujInterfejs(); }
    @FXML public void onBiegDolClick() { auto.getSkrzynia().zmniejszBieg(); aktualizujInterfejs(); }
    @FXML public void onSprzegloNacisnijClick() { auto.getSprzeglo().nacisnij(); aktualizujInterfejs(); }
    @FXML public void onSprzegloZwolnijClick() { auto.getSprzeglo().zwolnij(); aktualizujInterfejs(); }

    private void aktualizujInterfejs() {
        modelTextField.setText(auto.getSilnik().getModel());
        nrRejTextField.setText("KR 12345");
        wagaTextField.setText(auto.getWaga() + " kg");
        predkoscTextField.setText(auto.getPredkosc() + " km/h");

        sNazwaTextField.setText(auto.getSkrzynia().getModel());
        sCenaTextField.setText(auto.getSkrzynia().getCena() + " zł");
        sWagaTextField.setText(auto.getSkrzynia().getWaga() + " kg");
        sBiegTextField.setText(String.valueOf(auto.getSkrzynia().getAktualnyBieg()));

        silNazwaTextField.setText(auto.getSilnik().getModel());
        silCenaTextField.setText(auto.getSilnik().getCena() + " zł");
        silWagaTextField.setText(auto.getSilnik().getWaga() + " kg");
        silObrotyTextField.setText(String.valueOf(auto.getSilnik().getAktualneObroty()));

        sprNazwaTextField.setText(auto.getSprzeglo().getModel());
        sprCenaTextField.setText(auto.getSprzeglo().getCena() + " zł");
        sprWagaTextField.setText(auto.getSprzeglo().getWaga() + " kg");
        sprStanTextField.setText(auto.getSprzeglo().isNacisniete() ? "Wciśnięte" : "Zwolnione");
    }
}