package Symulator;

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

    public void wlacz() {
        silnik.uruchom();
    }

    public void wylacz() {
        silnik.zatrzymaj();
        skrzynia.zerujBieg();
    }

    public Silnik getSilnik() {
        return silnik;
    }

    public Skrzyniabiegow getSkrzynia() {
        return skrzynia;
    }

    public Sprzeglo getSprzeglo() {
        return sprzeglo;
    }

    public Pozycja getPozycja() {
        return pozycja;
    }
}