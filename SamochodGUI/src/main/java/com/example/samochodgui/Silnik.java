package com.example.samochodgui;

public class Silnik extends Komponent {
    private int maxObroty;
    private int obroty;

    public Silnik(String producent, String model, double cena, double waga, int maxObroty) {
        super(producent, model, cena, waga);
        this.maxObroty = maxObroty;
        this.obroty = 0;
    }

    public void uruchom() {
        this.obroty = 800;
    }

    public void zatrzymaj() {
        this.obroty = 0;
    }

    // Zmieniamy na boolean, aby poinformować kontroler, czy dodanie gazu było możliwe
    public boolean zwiekszObroty() {
        if (this.obroty == 0) {
            return false; // Silnik jest wyłączony - operacja nieudana
        }

        if (this.obroty + 500 <= maxObroty) {
            this.obroty += 500;
        } else {
            this.obroty = maxObroty;
        }
        return true; // Sukces
    }

    public void zmniejszObroty() {
        if (this.obroty > 800) {
            this.obroty -= 500;
            if (this.obroty < 800) this.obroty = 800;
        }
    }

    public int getAktualneObroty() {
        return this.obroty;
    }

    public int getMaxObroty() {
        return maxObroty;
    }
}