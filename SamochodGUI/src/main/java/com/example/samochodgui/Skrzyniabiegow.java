package com.example.samochodgui;

public class Skrzyniabiegow extends Komponent {
    private int aktualnyBieg;
    private int iloscBiegow;

    public Skrzyniabiegow(String producent, String model, double cena, double waga, int iloscBiegow) {
        super(producent, model, cena, waga);
        this.iloscBiegow = iloscBiegow;
        this.aktualnyBieg = 0;
    }

    public void zwiekszBieg() {
        if (aktualnyBieg < iloscBiegow) {
            this.aktualnyBieg++;
        }
    }

    public void zmniejszBieg() {
        if (aktualnyBieg > 0) {
            this.aktualnyBieg--;
        }
    }

    public int getAktualnyBieg() {
        return this.aktualnyBieg;
    }

    public void zerujBieg() {
        this.aktualnyBieg = 0;
    }
}