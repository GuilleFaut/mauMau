public class Karte{
    private String farbe;   // Farbe
    private int nummer;     // Karte

    // Builder: Wird aufgerufen, wenn wir ein neues Diagramm erstellen.
    public Karte(String farbe, int nummer) {
        this.farbe = farbe;
        this.nummer = nummer;
    }
    // Methoden zur Ermittlung der Kartenwerte
    public String getColor() {
        return farbe;
    }
    public int getNumber() {
        return nummer;
    }
    // Methode zur Anzeige der Karte als Text
    @Override
    public String toString() {
        return farbe + " " + nummer;
    }
}