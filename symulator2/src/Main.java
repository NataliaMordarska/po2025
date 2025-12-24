package Symulator;

public class Main {
    public static void main(String[] args) {
        Silnik s = new Silnik("Bosch", "V8", 6000);
        Skrzyniabiegow sk = new Skrzyniabiegow("ZF", "Manual", 6);
        Sprzeglo sp = new Sprzeglo("Sachs", "Sport");
        Pozycja p = new Pozycja(0, 0);

        Samochod auto = new Samochod(s, sk, sp, p);

        auto.wlacz();

        auto.getSkrzynia().zwiekszBieg();
        auto.getSkrzynia().zwiekszBieg();
        auto.getSkrzynia().zmniejszBieg();

        auto.getSprzeglo().wcisnij();

        auto.getPozycja().aktualizujPozycje(100.0, 50.0);

        System.out.println("Stan silnika: " + auto.getSilnik().getModel());
        System.out.println("Aktualny bieg: " + auto.getSkrzynia().getAktualnyBieg());
        System.out.println("Czy sprzeglo wcisniete: " + auto.getSprzeglo().isStanSprzegla());
        System.out.println("Aktualna pozycja: " + auto.getPozycja().getPozycja());
    }
}