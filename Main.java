import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Erstellen eines Stapels und mischen
        Stapel stapel = new Stapel();
        stapel.mischen();
        System.out.println("\nGemischtes Stapel.");

        try (// Spieler erstellen
        Scanner scanner = new Scanner(System.in)) {
            System.out.println("Name des Spielers 1: ");
            String name1 = scanner.nextLine();
            System.out.println("Spielername 2 (leer, wenn du gegen die CPU spielen willst): ");
            String name2 = scanner.nextLine();
            String name3 = "CPU";
            if(name2 == ""){
                name2="CPU 1";
                name3="CPU 2";
            }
            Spieler spieler1 = new Spieler(name1);
            Spieler spieler2 = new Spieler(name2);
            Spieler spieler3 = new Spieler(name3);

            // Karten austeilen
            for (int i = 0; i < 5; i++) {
                spieler1.karteHinzufugen(stapel.abnehmen());
                
                spieler2.karteHinzufugen(stapel.abnehmen());
                spieler3.karteHinzufugen(stapel.abnehmen());
            }

            // Ablagestapel starten
            ArrayList<Karte> ablageStapel = new ArrayList<>();
            ablageStapel.add(stapel.abnehmen());

            // Spiel starten
            SpielManager spielManager = new SpielManager();
            spielManager.spielStarten(stapel, ablageStapel, spieler1, spieler2, spieler3);
        }
    }
}
