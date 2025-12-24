package com.example.samochodgui;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class HelloController {
    @FXML private TextField modelField, nrRejField, wagaField, predkoscField, obrotyField, biegField, sprzegloField;
    @FXML private ImageView carImage;

    private Samochod auto = new Samochod(
            new Silnik("Bosch", "V8", 6000),
            new Skrzyniabiegow("ZF", "Manual", 6),
            new Sprzeglo("Sachs", "Sport"),
            new Pozycja(50, 50)
    );

    @FXML
    protected void onUruchomClick() {
        auto.getSilnik().uruchom();
        aktualizujInterfejs();
    }

    @FXML
    protected void onWylaczClick() {
        auto.getSilnik().zatrzymaj();
        aktualizujInterfejs();
    }

    @FXML
    protected void onGazClick() {
        auto.getSilnik().zwiekszObroty();
        carImage.setLayoutX(carImage.getLayoutX() + 10);
        aktualizujInterfejs();
    }

    @FXML
    protected void onUjmijGazClick() {
        carImage.setLayoutX(carImage.getLayoutX() - 10);
        aktualizujInterfejs();
    }

    @FXML
    protected void onBiegGoraClick() {
        auto.getSkrzynia().zwiekszBieg();
        aktualizujInterfejs();
    }

    @FXML
    protected void onBiegDolClick() {
        auto.getSkrzynia().zmniejszBieg();
        aktualizujInterfejs();
    }

    @FXML
    protected void onSprzegloNacisnijClick() {
        auto.getSprzeglo().nacisnij();
        aktualizujInterfejs();
    }

    @FXML
    protected void onSprzegloZwolnijClick() {
        auto.getSprzeglo().zwolnij();
        aktualizujInterfejs();
    }

    private void aktualizujInterfejs() {
        modelField.setText(auto.getSilnik().getModel());
        obrotyField.setText(String.valueOf(auto.getSilnik().getAktualneObroty()));
        biegField.setText(String.valueOf(auto.getSkrzynia().getAktualnyBieg()));
        sprzegloField.setText(auto.getSprzeglo().isNacisniete() ? "Wciśnięte" : "Puszczone");
    }
}