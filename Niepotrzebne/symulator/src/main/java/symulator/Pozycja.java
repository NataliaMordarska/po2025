package symulator;

public class Pozycja {
    private double x;
    private double y;

    public void aktualizujPozycję(double deltaX, double deltaY) {
        this.x += deltaX;
        this.y += deltaY;
    }

    // Metoda do wyświetlania pozycji
    public String getPozycja() {
        return "Pozycja: (" + x + ", " + y + ")";
    }
}




