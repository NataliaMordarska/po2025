package com.example.samochodgui;

public class Sprzeglo extends Komponent {
    private boolean nacisniete;

    public Sprzeglo(String producent, String model, double cena, double waga) {
        super(producent, model, cena, waga);
        this.nacisniete = false;
    }

    public void nacisnij() {
        this.nacisniete = true;
    }

    public void zwolnij() {
        this.nacisniete = false;
    }

    public boolean isNacisniete() {
        return nacisniete;
    }
}