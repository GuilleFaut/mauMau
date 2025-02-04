import java.util.ArrayList;

public class Spieler {
    private String name;
    private ArrayList<Karte> hand;

    // Erstelle einen Spieler mit einem Namen und ohne Karten
    public Spieler(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
    }
    // Methode zur Ermittlung des Namens des Spielers
    public String getName() {
        return name;
    }

    public ArrayList<Karte> getMano() {
        return hand;
    }
    // Methode zum Hinzufügen einer Karte
    public void karteHinzufugen(Karte karte) {
        if (karte != null) {
            hand.add(karte);
        }
    }
    // Methode zum Ausspielen einer Karte (Entfernen einer Karte von Spieler)
    public Karte karteSpielen(int index) {
        if (index >= 0 && index < hand.size()) {
            return hand.remove(index);
        }
        return null;
    }
    // Methode zum Auslegen der Karten
    public void manoZeigen() {
        System.out.println("\n"+name + " hat folgende Karten:");
        for (int i = 0; i < hand.size(); i++) {
            System.out.println((i + 1) + ": " + hand.get(i));
        }
    }
    // Methode zur Auswahl spielbarer Karten nach der letzten Karte im Ablagestapel
    public ArrayList<Karte> spielbarerKarten(Karte letzteKarte) {
        ArrayList<Karte> spielbarer = new ArrayList<>();
        for (Karte karte : hand) {
            if (letzteKarte == null || karte.getColor().equals(letzteKarte.getColor()) || karte.getNumber() == letzteKarte.getNumber()) {
                spielbarer.add(karte);
            }
        }
        return spielbarer;
    }
    // Methode zur Überprüfung, wie viele Karten der Spieler hat
    public int getkartenAnzahl(){
        return hand.size();
    }
}