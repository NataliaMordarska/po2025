package com.example.samochodgui;

public class Samochod {
    private Silnik silnik;
    private Skrzyniabiegow skrzynia;
    private Sprzeglo sprzeglo;
    private Pozycja pozycja;

    public Samochod(Silnik silnik, Skrzyniabiegow skrzynia, Sprzeglo sprzeglo, Pozycja pozycja) {
        this.silnik = silnik;
        this.skrzynia = skrzynia;
        this.sprzeglo = sprzeglo;
        this.pozycja = pozycja;
    }

    public double getWaga() {
        return silnik.getWaga() + skrzynia.getWaga() + sprzeglo.getWaga();
    }

    public double getPredkosc() {
        if (silnik.getAktualneObroty() > 0 && !sprzeglo.isNacisniete()) {
            return (silnik.getAktualneObroty() * skrzynia.getAktualnyBieg()) / 100.0;
        }
        return 0;
    }

    public Silnik getSilnik() { return silnik; }
    public Skrzyniabiegow getSkrzynia() { return skrzynia; }
    public Sprzeglo getSprzeglo() { return sprzeglo; }
}