package Symulator;

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
}