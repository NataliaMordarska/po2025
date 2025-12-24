package com.example.samochodgui;

public class Sprzeglo extends Komponent {
    private boolean nacisniete;

    public Sprzeglo(String producent, String model) {
        super(producent, model);
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