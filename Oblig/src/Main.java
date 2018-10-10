

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

        String[] navn = {"Lars","Anders","Bodil","Kari","Per","Berit"};
        Liste<String> liste = new DobbeltLenketListe<>(navn);
        liste.forEach(s -> System.out.print(s + " "));
        System.out.println();
        for (String s : liste) System.out.print(s + " ");
        // Utskrift:
        // Lars Anders Bodil Kari Per Berit
        // Lars Anders Bodil Kari Per Berit

    }


}
