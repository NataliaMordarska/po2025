package com.example.samochodgui;

public class Skrzyniabiegow extends Komponent {
    private int aktualnyBieg;
    private int iloscBiegow;

    public Skrzyniabiegow(String producent, String model, int iloscBiegow) {
        super(producent, model);
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