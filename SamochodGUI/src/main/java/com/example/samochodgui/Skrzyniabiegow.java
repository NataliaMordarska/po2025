package com.example.samochodgui;

public class Skrzyniabiegow extends Komponent {
    private int aktualnyBieg;
    private int iloscBiegow;

    public Skrzyniabiegow(String producent, String model, double cena, double waga, int iloscBiegow) {
        super(producent, model, cena, waga);
        this.iloscBiegow = iloscBiegow;
        this.aktualnyBieg = 0;
    }

    // Dodajemy parametr informujący o stanie sprzęgła
    public String zwiekszBieg(boolean sprzegloWcisniete) {
        if (!sprzegloWcisniete) {
            return "BRAK_SPRZEGLA";
        }
        if (aktualnyBieg < iloscBiegow) {
            this.aktualnyBieg++;
            return "OK";
        }
        return "MAX_BIEG";
    }

    public String zmniejszBieg(boolean sprzegloWcisniete) {
        if (!sprzegloWcisniete) {
            return "BRAK_SPRZEGLA";
        }
        if (aktualnyBieg > 0) {
            this.aktualnyBieg--;
            return "OK";
        }
        return "MIN_BIEG";
    }

    public int getAktualnyBieg() {
        return this.aktualnyBieg;
    }

    public int getIloscBiegow() {
        return this.iloscBiegow;
    }

    public void zerujBieg() {
        this.aktualnyBieg = 0;
    }
}