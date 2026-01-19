package com.example.samochodgui;

public class Skrzyniabiegow extends Komponent {
    private int aktualnyBieg;
    private int iloscBiegow;

    // Konstruktor domyślny
    public Skrzyniabiegow() {
        super("ZF", "Manual", 2500.0, 60.0);
        this.iloscBiegow = 6;
        this.aktualnyBieg = 0;
    }

    public Skrzyniabiegow(String producent, String model, double cena, double waga, int iloscBiegow) {
        super(producent, model, cena, waga);
        this.iloscBiegow = iloscBiegow;
        this.aktualnyBieg = 0;
    }

    public String zwiekszBieg(boolean sprzegloWcisniete) {
        if (!sprzegloWcisniete) return "BRAK_SPRZEGLA";
        if (aktualnyBieg < iloscBiegow) {
            aktualnyBieg++;
            return "OK";
        }
        return "MAX_BIEG";
    }

    public String zmniejszBieg(boolean sprzegloWcisniete) {
        if (!sprzegloWcisniete) return "BRAK_SPRZEGLA";
        if (aktualnyBieg > 0) {
            aktualnyBieg--;
            return "OK";
        }
        return "MIN_BIEG";
    }

    public int getAktualnyBieg() { return aktualnyBieg; }
    public int getIloscBiegow() { return iloscBiegow; }
}
