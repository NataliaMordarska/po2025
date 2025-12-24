package com.example.samochodgui;

public class Silnik extends Komponent {
    private int maxObroty;
    private int obroty;

    public Silnik(String producent, String model, int maxObroty) {
        super(producent, model);
        this.maxObroty = maxObroty;
        this.obroty = 0;
    }

    public void uruchom() {
        this.obroty = 800;
    }

    public void zatrzymaj() {
        this.obroty = 0;
    }

    public void zwiekszObroty() {
        if (this.obroty > 0) { // Silnik musi pracować, żeby dodać gazu
            if (this.obroty + 500 <= maxObroty) {
                this.obroty += 500;
            } else {
                this.obroty = maxObroty;
            }
        }
    }

    public int getAktualneObroty() {
        return this.obroty;
    }

    public int getMaxObroty() {
        return this.maxObroty;
    }
}