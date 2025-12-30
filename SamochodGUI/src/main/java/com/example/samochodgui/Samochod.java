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
        this.setDaemon(true);
        this.start();
    }

    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    public void removeListener(Listener listener) {
        listeners.remove(listener);
    }

    private void notifyListeners() {
        List<Listener> copy = new ArrayList<>(listeners);
        for (Listener listener : copy) {
            listener.update();
        }
    }

    public void jedzDo(Pozycja nowaPozycja) {
        this.cel = nowaPozycja;
    }

    @Override
    public void run() {
        double deltat = 0.1;
        while (!isInterrupted()) {
            try {
                if (cel != null) {
                    double dx_cel = cel.x - pozycja.x;
                    double dy_cel = cel.y - pozycja.y;
                    double odleglosc = Math.sqrt(dx_cel * dx_cel + dy_cel * dy_cel);

                    double v = getPredkosc();
                    double krok = v * deltat;

                    if (odleglosc <= krok || odleglosc < 1.0) {
                        pozycja.x = cel.x;
                        pozycja.y = cel.y;
                        cel = null;
                    } else if (v > 0) {
                        pozycja.x += krok * (dx_cel / odleglosc);
                        pozycja.y += krok * (dy_cel / odleglosc);
                    }

                    notifyListeners();
                }
                Thread.sleep(20);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    public double getWaga() {
        return silnik.getWaga() + skrzynia.getWaga() + sprzeglo.getWaga();
    }

    public double getPredkosc() {
        if (silnik.getAktualneObroty() > 0 && !sprzeglo.isNacisniete() && skrzynia.getAktualnyBieg() > 0) {
            double stosunekObrotow = (double) silnik.getAktualneObroty() / silnik.getMaxObroty();
            double stosunekBiegow = (double) skrzynia.getAktualnyBieg() / skrzynia.getIloscBiegow();
            return stosunekObrotow * stosunekBiegow * 200.0;
        }
        return 0;
    }

    @Override
    public String toString() {
        return nazwa;
    }

    public String getNazwa() { return nazwa; }
    public Silnik getSilnik() { return silnik; }
    public Skrzyniabiegow getSkrzynia() { return skrzynia; }
    public Sprzeglo getSprzeglo() { return sprzeglo; }
    public Pozycja getPozycja() { return pozycja; }
}