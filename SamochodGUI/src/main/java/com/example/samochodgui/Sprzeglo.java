package com.example.samochodgui;

public class Sprzeglo extends Komponent {
    private boolean wcisniete;

    // Konstruktor domyślny
    public Sprzeglo() {
        super("Sachs", "Sport", 1000.0, 15.0);
        this.wcisniete = false;
    }

    public Sprzeglo(String producent, String model, double cena, double waga) {
        super(producent, model, cena, waga);
        this.wcisniete = false;
    }

    public void nacisnij() { this.wcisniete = true; }
    public void zwolnij() { this.wcisniete = false; }
    public boolean isNacisniete() { return wcisniete; }
}