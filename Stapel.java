import java.util.ArrayList;
import java.util.Collections;

public class Stapel {
   private ArrayList<Karte> karten = new ArrayList<>();
   // Builder: Erstelle ein leeres Stapel und initialisiere die Karten.
   public Stapel(){
        karten = new ArrayList<>();
        initialisieren (); 
   }
   // Methode zur Erstellung aller Karten
   private void initialisieren(){
    String[] farben = {"Herz","Kreuz","Karo","Pik"};
        for (String farbe : farben){
            for(int nummer = 1; nummer <= 13; nummer++){
                karten.add(new Karte(farbe, nummer));
            }
        }
   }
   // Methode zum Mischen der Karten
   public void mischen() {
        Collections.shuffle(karten);
   }
   // Verfahren zum Ziehen einer Karte vom Stapel
   public Karte abnehmen() {
        if(karten.isEmpty()){
            return null;  // Wenn keine Karten mehr vorhanden sind, wird null zurückgegeben.
        }
        return karten.remove(0);  // Die erste Karte entfernen und zurückgeben
   }
   // Methode zur Anzeige der Größe des Stapels
   public int getAnzahlKarten(){
        return karten.size();
   }
}