import java.util.ArrayList;
import java.util.Scanner;

public class SpielManager {
    public void spielStarten(Stapel stapel, ArrayList<Karte> ablageStapel, Spieler... spieler) {
        boolean spielLäuft = true;
        int aktuellerSpieler = 0;
                    
        while (spielLäuft) {
            if (zugAusfuhren(spieler[aktuellerSpieler], ablageStapel, stapel)) {
                System.out.println("\nDas Spiel ist vorbei! Der Gewinner ist: " + spieler[aktuellerSpieler].getName() + ".");
                spielLäuft = false;
            } else {
                    aktuellerSpieler = (aktuellerSpieler + 1) % spieler.length;  // Nächster Spieler
            }
        }        
    }

    public static boolean zugAusfuhren(Spieler spieler, ArrayList<Karte> ablageStapel, Stapel stapel) {
        System.out.println("\n" + spieler.getName() + " ist dran");
        Karte obersteKarte = ablageStapel.get(ablageStapel.size() - 1);
        System.out.println("Oberste Karte des Ablagestapels: " + obersteKarte);
    
        if (!spieler.getName().contains("CPU")) {
            // Logik für menschliche Spieler
            Scanner scanner = new Scanner(System.in);
            boolean gültigeKarte = false;

            while (!gültigeKarte) {
                spieler.manoZeigen();
                System.out.println("0: Eine Karte ziehen");
                System.out.print("Wähle eine Karte zum Spielen (Nummer): ");
                int wahl = scanner.nextInt();

                if (wahl == 0) {
                    Karte neueKarte = stapel.abnehmen();
                    if (neueKarte == null) {
                        System.out.println("Es sind keine Karten mehr im Stapel.");
                        return false;
                    } else {
                        System.out.println("Du hast " + neueKarte + " gezogen.");
                        spieler.karteHinzufugen(neueKarte);

                        System.out.println("Möchtest du diese Karte spielen? (1: Ja, 2: Nein)");
                        if (scanner.nextInt() == 1) {
                            if (neueKarte.getColor().equals(obersteKarte.getColor()) || neueKarte.getNumber() == obersteKarte.getNumber()) {
                                ablageStapel.add(spieler.karteSpielen(spieler.getMano().indexOf(neueKarte)));
                                System.out.println("Du hast gespielt: " + neueKarte);
                                gültigeKarte = true;
                            } else {
                                System.out.println("Diese Karte ist nicht gültig. Dein Zug ist beendet.");
                                return false;
                            }
                        } else {
                            System.out.println("Du hast entschieden, den Zug zu beenden.");
                            return false;
                        }
                    }
                } else {
                    Karte gewählteKarte = spieler.getMano().get(wahl - 1);
                    if (gewählteKarte.getColor().equals(obersteKarte.getColor()) || gewählteKarte.getNumber() == obersteKarte.getNumber()) {
                        ablageStapel.add(spieler.karteSpielen(wahl - 1));
                        System.out.println("Du hast gespielt: " + gewählteKarte);
                        gültigeKarte = true;
                    } else {
                        System.out.println("Ungültige Karte! Wähle eine andere.");
                    }
                }
            }
        } else {
            // Logik für CPU-Spieler (automatisches Ziehen & Spielen)
            Karte gewählteKarte = null;
            int indexKarte = -1;

            // Suche nach einer spielbaren Karte
            for (int i = 0; i < spieler.getMano().size(); i++) {
                Karte karte = spieler.getMano().get(i);
                if (karte.getColor().equals(obersteKarte.getColor()) || karte.getNumber() == obersteKarte.getNumber()) {
                    gewählteKarte = karte;
                    indexKarte = i;
                    break;
                }
            }

            if (gewählteKarte != null) {
                // Eine gültige Karte ausspielen
                ablageStapel.add(spieler.karteSpielen(indexKarte));
                System.out.println(spieler.getName() + " hat gespielt: " + gewählteKarte);
            } else {
                // Wenn die CPU keine spielbare Karte hat, ziehe eine vom Stapel.
                Karte neueKarte = stapel.abnehmen();
                if (neueKarte == null) {
                    System.out.println(spieler.getName() + " hat keine gültige Karte und es sind keine weiteren Karten im Stapel vorhanden.");
                } else {
                    System.out.println(spieler.getName() + " hat keine gültige Karte. Ziehen: " + neueKarte);
                    spieler.karteHinzufugen(neueKarte);

                    // Wenn die gezogene Karte spielbar ist, spielt die CPU sie sofort aus.
                    if (neueKarte.getColor().equals(obersteKarte.getColor()) || neueKarte.getNumber() == obersteKarte.getNumber()) {
                        ablageStapel.add(spieler.karteSpielen(spieler.getMano().indexOf(neueKarte)));
                        System.out.println(spieler.getName() + " hat die gezogene Karte gespielt: " + neueKarte);
                    }
                }
            }
        }
        return spieler.getMano().isEmpty();
    }
}