module org.example.gui {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.example.gui.samochodgui to javafx.fxml;

    exports org.example.gui.samochod;
    exports org.example.gui.samochodgui;
}
