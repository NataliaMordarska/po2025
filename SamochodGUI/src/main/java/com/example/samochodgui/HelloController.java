package com.example.samochodgui;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloController implements Listener {
    @FXML private TextField modelTextField, nrRejTextField, wagaTextField, predkoscTextField;
    @FXML private TextField sNazwaTextField, sCenaTextField, sWagaTextField, sBiegTextField;
    @FXML private TextField silNazwaTextField, silCenaTextField, silWagaTextField, silObrotyTextField;
    @FXML private TextField sprNazwaTextField, sprCenaTextField, sprWagaTextField, sprStanTextField;
    @FXML private ImageView carImage;
    @FXML private Pane mapa;
    @FXML private ComboBox<Samochod> samochodComboBox;
    @FXML private Button usunButton;

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

        samochodComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            samochod = newVal;
            if (samochod != null) {
                carImage.setVisible(true);
                refresh();
            } else {
                wyczyscPola();
                carImage.setVisible(false);
            }
            if (usunButton != null) {
                usunButton.setDisable(newVal == null);
            }
        });

        mapa.setOnMouseClicked(event -> {
            if (samochod != null) {
                samochod.jedzDo(new Pozycja(event.getX(), event.getY()));
            }
        });

        dodajSamochod(new Samochod(
                new Silnik("Bosch", "V8", 5000.0, 200.0, 6000),
                new Skrzyniabiegow("ZF", "Manual", 2500.0, 60.0, 6),
                new Sprzeglo("Sachs", "Sport", 1000.0, 15.0),
                new Pozycja(0, 0),
                "Auto Startowe [WA12345]"
        ));
    }

    @Override
    public void update() {
        refresh();
    }

    public void pokazBlad(String wiadomosc) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Błąd");
        alert.setHeaderText(null);
        alert.setContentText(wiadomosc);
        alert.showAndWait();
    }

    public void dodajSamochod(Samochod nowySamochod) {
        samochody.add(nowySamochod);
        nowySamochod.addListener(this);
        samochodComboBox.getSelectionModel().select(nowySamochod);
        this.samochod = nowySamochod;
        carImage.setVisible(true);
        refresh();
    }

    @FXML
    public void onUsunSamochod(ActionEvent actionEvent) {
        if (samochod != null) {
            samochod.removeListener(this);
            samochody.remove(samochod);
            if (samochody.isEmpty()) {
                samochod = null;
                wyczyscPola();
                carImage.setVisible(false);
            } else {
                samochodComboBox.getSelectionModel().selectFirst();
            }
        }
    }

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
        controller.setParentController(this);
        stage.setTitle("Dodaj nowy samochód");
        stage.show();
    }

    @FXML public void onUruchomClick() {
        if (samochod != null) {
            samochod.getSilnik().uruchom();
            refresh();
        }
    }

    @FXML public void onWylaczClick() {
        if (samochod != null) {
            samochod.getSilnik().zatrzymaj();
            refresh();
        }
    }

    @FXML
    public void onGazClick() {
        if (samochod != null) {
            if (!samochod.getSilnik().zwiekszObroty()) {
                pokazBlad("Nie można dodać gazu! Najpierw uruchom silnik.");
            }
            refresh();
        }
    }

    @FXML public void onUjmijGazClick() {
        if (samochod != null) {
            samochod.getSilnik().zmniejszObroty();
            refresh();
        }
    }

    @FXML
    public void onBiegGoraClick() {
        if (samochod != null) {
            boolean wcisniete = samochod.getSprzeglo().isNacisniete();
            String wynik = samochod.getSkrzynia().zwiekszBieg(wcisniete);
            handleSkrzyniaResult(wynik);
            refresh();
        }
    }

    @FXML
    public void onBiegDolClick() {
        if (samochod != null) {
            boolean wcisniete = samochod.getSprzeglo().isNacisniete();
            String wynik = samochod.getSkrzynia().zmniejszBieg(wcisniete);
            handleSkrzyniaResult(wynik);
            refresh();
        }
    }

    private void handleSkrzyniaResult(String wynik) {
        if (wynik.equals("BRAK_SPRZEGLA")) {
            pokazBlad("Zgrzyt! Musisz wcisnąć sprzęgło, aby zmienić bieg.");
        } else if (wynik.equals("MAX_BIEG")) {
            pokazBlad("Osiągnięto już najwyższy bieg.");
        } else if (wynik.equals("MIN_BIEG")) {
            pokazBlad("Osiągnięto już najniższy bieg (luz).");
        }
    }

    @FXML
    public void onSprzegloNacisnijClick() {
        if (samochod != null) {
            samochod.getSprzeglo().nacisnij();
            refresh();
        }
    }

    @FXML
    public void onSprzegloZwolnijClick() {
        if (samochod != null) {
            samochod.getSprzeglo().zwolnij();
            refresh();
        }
    }

    private void refresh() {
        if (samochod == null) return;

        Platform.runLater(() -> {
            wagaTextField.setText(String.format("%.1f", samochod.getWaga()));
            predkoscTextField.setText(String.format("%.2f", samochod.getPredkosc()));
            modelTextField.setText(samochod.getSilnik().getModel());

            // Rozwiązanie problemu nr rejestracyjnego - wyciągamy tekst z nawiasów [ ]
            String pelnaNazwa = samochod.getNazwa();
            if (pelnaNazwa.contains("[") && pelnaNazwa.contains("]")) {
                String nr = pelnaNazwa.substring(pelnaNazwa.indexOf("[") + 1, pelnaNazwa.indexOf("]"));
                nrRejTextField.setText(nr);
            } else {
                nrRejTextField.setText("Brak");
            }

            sNazwaTextField.setText(samochod.getSkrzynia().getModel());
            sBiegTextField.setText(String.valueOf(samochod.getSkrzynia().getAktualnyBieg()));
            silObrotyTextField.setText(String.valueOf(samochod.getSilnik().getAktualneObroty()));
            sprStanTextField.setText(samochod.getSprzeglo().isNacisniete() ? "Wciśnięte" : "Zwolnione");

            carImage.setTranslateX(samochod.getPozycja().getX());
            carImage.setTranslateY(samochod.getPozycja().getY());
        });
    }
}