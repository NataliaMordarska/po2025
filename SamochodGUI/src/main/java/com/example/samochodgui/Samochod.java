package com.example.samochodgui;

import java.util.ArrayList;
import java.util.List;

public class Samochod extends Thread {
    private Silnik silnik;
    private Skrzyniabiegow skrzynia;
    private Sprzeglo sprzeglo;
    private Pozycja pozycja;
    private Pozycja cel;
    private String nazwa;
    private List<Listener> listeners = new ArrayList<>();

    public Samochod(Silnik silnik, Skrzyniabiegow skrzynia, Sprzeglo sprzeglo, Pozycja pozycja, String nazwa) {
        this.silnik = silnik;
        this.skrzynia = skrzynia;
        this.sprzeglo = sprzeglo;
        this.pozycja = pozycja;
        this.nazwa = nazwa;
        this.start();
    }

    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    public void removeListener(Listener listener) {
        listeners.remove(listener);
    }

    private void notifyListeners() {
        for (Listener listener : listeners) {
            listener.update();
        }
    }

    public void jedzDo(Pozycja nowaPozycja) {
        this.cel = nowaPozycja;
    }

    @Override
    public void run() {
        double deltat = 0.1;
        while (true) {
            try {
                if (cel != null) {
                    double odleglosc = Math.sqrt(Math.pow(cel.x - pozycja.x, 2) + Math.pow(cel.y - pozycja.y, 2));

                    if (odleglosc > 1) {
                        double dx = getPredkosc() * deltat * (cel.x - pozycja.x) / odleglosc;
                        double dy = getPredkosc() * deltat * (cel.y - pozycja.y) / odleglosc;

                        pozycja.x += dx;
                        pozycja.y += dy;

                        notifyListeners();
                    } else {
                        cel = null;
                        notifyListeners();
                    }
                }
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString() {
        return nazwa;
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

    public String getNazwa() { return nazwa; }
    public Silnik getSilnik() { return silnik; }
    public Skrzyniabiegow getSkrzynia() { return skrzynia; }
    public Sprzeglo getSprzeglo() { return sprzeglo; }
    public Pozycja getPozycja() { return pozycja; }
}