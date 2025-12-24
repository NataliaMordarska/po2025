package symulator;

public class Sprzeglo extends Komponent {
    private boolean stanSprzęgła;

    // Konstruktor
    public Sprzeglo(String producent, String model) {
        super(producent, model); // Wywołanie konstruktora klasy nadrzędnej Komponent
        this.stanSprzęgła = false; // Domyślny stan sprzęgła (nieaktywne)
    }

    public void wciśnij() {
        stanSprzęgła = true;
    }

    public void zwolnij() {
        stanSprzęgła = false;
    }
}
