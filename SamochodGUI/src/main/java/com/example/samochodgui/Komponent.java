package com.example.samochodgui;

public class Komponent {
    private String producent;
    private String model;
    private double cena;
    private double waga;

    public Komponent(String producent, String model, double cena, double waga) {
        this.producent = producent;
        this.model = model;
        this.cena = cena;
        this.waga = waga;
    }

    public String getProducent() {
        return producent;
    }

    public String getModel() {
        return model;
    }

    public double getCena() {
        return cena;
    }

    public double getWaga() {
        return waga;
    }
}