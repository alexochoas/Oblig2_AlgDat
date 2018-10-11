import java.util.Iterator;

public class Main {

    public static void main(String[] args) {

       /* Liste<Integer> liste = new DobbeltLenketListe<>();

        for(int i = 0; i < 1000000; i++){

            liste.leggInn(i);


        }

        long tic = System.currentTimeMillis();

        liste.nullstill();

        long toc = System.currentTimeMillis();

        long timetaken = toc - tic;

        System.out.println("Dette tok " + timetaken + "ms");
*/
        DobbeltLenketListe<String> liste =
                new DobbeltLenketListe<>(new String[]
                        {"Birger","Lars","Anders","Bodil","Kari","Per","Berit"});
        liste.fjernHvis(navn -> navn.charAt(0) == 'B'); // fjerner navn som starter med B
        System.out.println(liste + " " + liste.omvendtString());
        // Utskrift: [Lars, Anders, Kari, Per] [Per, Kari, Anders, Lars]
        // Utskrift:
        // Lars Anders Bodil Kari Per Berit
        // Lars Anders Bodil Kari Per Berit


        // Utskrift:
        // Lars Anders Bodil Kari Per Berit
        // Lars Anders Bodil Kari Per Berit

    }


}
