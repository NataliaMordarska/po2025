package com.example.samochodgui;

import javafx.application.Platform;
import javafx.animation.AnimationTimer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloController {
    @FXML private TextField modelTextField, nrRejTextField, wagaTextField, predkoscTextField;
    @FXML private TextField sNazwaTextField, sCenaTextField, sWagaTextField, sBiegTextField;
    @FXML private TextField silNazwaTextField, silCenaTextField, silWagaTextField, silObrotyTextField;
    @FXML private TextField sprNazwaTextField, sprCenaTextField, sprWagaTextField, sprStanTextField;
    @FXML private ImageView carImage;
    @FXML private Pane mapa;

    @FXML private ComboBox<Samochod> samochodComboBox;
    private ObservableList<Samochod> samochody = FXCollections.observableArrayList();
    private Samochod samochod;

    @FXML
    public void initialize() {
        try {
            Image carImg = new Image(getClass().getResource("/com/example/samochodgui/car.png").toExternalForm());
            carImage.setImage(carImg);
        } catch (Exception e) {
            System.out.println("Blad obrazka: " + e.getMessage());
        }

        samochodComboBox.setItems(samochody);
        samochodComboBox.setOnAction(event -> {
            samochod = samochodComboBox.getSelectionModel().getSelectedItem();
            refresh();
        });

        mapa.setOnMouseClicked(event -> {
            if (samochod != null) {
                double x = event.getX();
                double y = event.getY();
                Pozycja nowaPozycja = new Pozycja(x, y);
                samochod.jedzDo(nowaPozycja);
            }
        });

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                refresh();
            }
        }.start();

        // Startowe auto dodane nową metodą
        Samochod startowy = new Samochod(
                new Silnik("Bosch", "V8", 5000.0, 200.0, 6000),
                new Skrzyniabiegow("ZF", "Manual", 2500.0, 60.0, 6),
                new Sprzeglo("Sachs", "Sport", 1000.0, 15.0),
                new Pozycja(0, 0),
                "Auto Startowe"
        );
        dodajSamochod(startowy);
    }

    /**
     * Metoda zgodna z poleceniem z obrazka
     */
    public void dodajSamochod(Samochod nowySamochod) {
        samochody.add(nowySamochod);
        samochodComboBox.getSelectionModel().select(nowySamochod);
        this.samochod = nowySamochod;
        carImage.setVisible(true);
        refresh();
    }

    @FXML
    public void onUsunSamochod(ActionEvent actionEvent) {
        if (samochod != null) {
            samochody.remove(samochod);
            if (samochody.isEmpty()) {
                samochod = null;
                wyczyscPola(); // Rozwiązuje problem z image_14d669.png
                carImage.setVisible(false);
            } else {
                samochodComboBox.getSelectionModel().selectFirst();
                samochod = samochodComboBox.getSelectionModel().getSelectedItem();
            }
        }
    }

    /**
     * Metoda czyszcząca pola, gdy nie ma żadnego samochodu
     */
    private void wyczyscPola() {
        TextField[] wszystkiePola = {
                modelTextField, nrRejTextField, wagaTextField, predkoscTextField,
                sNazwaTextField, sCenaTextField, sWagaTextField, sBiegTextField,
                silNazwaTextField, silCenaTextField, silWagaTextField, silObrotyTextField,
                sprNazwaTextField, sprCenaTextField, sprWagaTextField, sprStanTextField
        };
        for (TextField f : wszystkiePola) {
            if (f != null) f.clear();
        }
    }

    @FXML
    public void onDodajAutoClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DodajSamochod.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        DodajSamochodController controller = loader.getController();

        // Ustawienie asocjacji do głównego kontrolera
        controller.setParentController(this);

        stage.setTitle("Dodaj nowy samochód");
        stage.show();
    }

    @FXML public void onUruchomClick() { if (samochod != null) samochod.getSilnik().uruchom(); }
    @FXML public void onWylaczClick() { if (samochod != null) samochod.getSilnik().zatrzymaj(); }
    @FXML public void onGazClick() { if (samochod != null) samochod.getSilnik().zwiekszObroty(); }
    @FXML public void onUjmijGazClick() { if (samochod != null) samochod.getSilnik().zmniejszObroty(); }
    @FXML public void onBiegGoraClick() { if (samochod != null) samochod.getSkrzynia().zwiekszBieg(); }
    @FXML public void onBiegDolClick() { if (samochod != null) samochod.getSkrzynia().zmniejszBieg(); }
    @FXML public void onSprzegloNacisnijClick() { if (samochod != null) samochod.getSprzeglo().nacisnij(); }
    @FXML public void onSprzegloZwolnijClick() { if (samochod != null) samochod.getSprzeglo().zwolnij(); }

    private void refresh() {
        if (samochod == null) return;

        wagaTextField.setText(String.valueOf(samochod.getWaga()));
        predkoscTextField.setText(String.format("%.2f", samochod.getPredkosc()));
        modelTextField.setText(samochod.getSilnik().getModel());
        sNazwaTextField.setText(samochod.getSkrzynia().getModel());
        sBiegTextField.setText(String.valueOf(samochod.getSkrzynia().getAktualnyBieg()));
        silObrotyTextField.setText(String.valueOf(samochod.getSilnik().getAktualneObroty()));
        sprStanTextField.setText(samochod.getSprzeglo().isNacisniete() ? "Wciśnięte" : "Zwolnione");

        Platform.runLater(() -> {
            carImage.setTranslateX(samochod.getPozycja().getX());
            carImage.setTranslateY(samochod.getPozycja().getY());
        });
    }
}