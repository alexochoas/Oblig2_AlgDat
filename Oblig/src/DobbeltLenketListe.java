import java.util.Iterator;
import java.util.Objects;

public class DobbeltLenketListe<T> implements Liste<T> {

    private int antall = 0;
    private int endringer = 0;
    private Node<T> hode;
    private Node<T> hale;




    public DobbeltLenketListe(T[] a){

        if(a == null){
            throw new NullPointerException("Tabellen a er null!");
        }

        if(a.length == 0){
            return;
        }


        if(a.length == 1 && a[0] != null){
            antall = 1;
            Node<T> firstNode = new Node<>(a[0],null,null);
            hode = firstNode;
            hale = firstNode;

            return;
        }




        //Finner første ikke null verdien i tabellen
        int hi = 0;
        for(int i = 0; i < a.length; i++){
            if(a[i] != null){
                hale = new Node<>(a[i], null, null);
                hode = hale;
                antall++;
                hi = i+1;
                break;
            }
        }






        for(int i = hi; i < a.length; i++){

            if(a[i] != null){
                hale.neste = new Node<>(a[i], null, hale);
                hale = hale.neste;
                antall++;

            }

        }



    }

    public DobbeltLenketListe(T[] a, int fra, int til){

        if(a == null){
            throw new NullPointerException("Tabellen a er null!");
        }

        if(a.length == 0){
            return;
        }


        if(a.length == 1 && a[0] != null){
            antall = 1;
            Node<T> firstNode = new Node<>(a[0],null,null);
            hode = firstNode;
            hale = firstNode;

            return;
        }




        //Finner første ikke null verdien i tabellen
        int hi = 0;
        for(int i = 0; i < a.length; i++){
            if(a[i] != null){
                hale = new Node<>(a[i], null, null);
                hode = hale;
                antall++;
                hi = i+1;
                break;
            }
        }






        for(int i = hi; i < a.length; i++){

            if(a[i] != null){
                hale.neste = new Node<>(a[i], null, hale);
                hale = hale.neste;
                antall++;

            }

        }



    }

    public DobbeltLenketListe(){

    }

    @Override
    public boolean leggInn(T verdi) {
        Objects.requireNonNull(verdi, "veriden er null!");

        //Sjekker om listen er tom.
        if(hode == null){
            hale = hode = new Node<>(verdi, null,null);
            endringer ++;
            antall++;
        }else{

            hale.neste = new Node<>(verdi, null, hale);
            hale = hale.neste;
            endringer++;
            antall++;


        }



        return true;
    }

    @Override
    public void LeggInn(int i, T verdi) {

    }

    @Override
    public boolean inneholder(T verdi) {
        return false;
    }

    @Override
    public T hent(int i) {
        return finnNode(i).verdi;
    }

    @Override
    public int indeksTil(T verdi) {
        return 0;
    }

    @Override
    public T oppdater(int i, T verdi) {

        T gml = hent(i);

        finnNode(i).verdi = verdi;

        endringer++;

        return gml;



    }

    @Override
    public boolean fjern(T verdi) {
        return false;
    }


    @Override
    public T fjern(int indeks) {
        return null;
    }

    @Override
    public int antall() {
        return antall;
    }

    @Override
    public boolean tom() {
        return antall == 0;
    }

    @Override
    public void nullstill() {

    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }


    public String omvendtString() {

        if(hode == null){
            return "[]";
        }

        String s = "[";
        StringBuilder stringBuilder = new StringBuilder(s);

        Node<T> currNode = hale;


        while(currNode.forrige != null){
            stringBuilder.append(currNode.verdi + ", ");
            currNode = currNode.forrige;

        }
        // Legger til siste noden
        stringBuilder.append(currNode.verdi + "]");




        return stringBuilder.toString();
    }

    @Override
    public String toString() {

        if (hode == null) {
            return "[]";
        }

        String s = "[";
        StringBuilder stringBuilder = new StringBuilder(s);

        Node<T> currNode = hode;


        while (currNode.neste != null) {
            stringBuilder.append(currNode.verdi + ", ");
            currNode = currNode.neste;

        }
        // Legger til siste noden
        stringBuilder.append(currNode.verdi + "]");


        return stringBuilder.toString();

    }

    private Node<T> finnNode(int indeks){

        indeksKontroll(indeks, false);

        int teller;
        Node<T> currNode;

        if(indeks < antall/2){
            currNode = hode;
            teller = 0;
            while (teller < indeks){
                currNode = currNode.neste;
            }

            return currNode;
        } else{

            currNode = hale;
            teller = antall;
            while (teller > indeks){
                currNode = currNode.forrige;
            }

            return currNode;

        }


    }

    public static void fratilKontroll(int antall, int fra, int til)
    {
        if (fra < 0)                                  // fra er negativ
            throw new IndexOutOfBoundsException
                    ("fra(" + fra + ") er negativ!");

        if (til > antall)                          // til er utenfor tabellen
            throw new IndexOutOfBoundsException
                    ("til(" + til + ") > antall(" + antall + ")");

        if (fra > til)                                // fra er større enn til
            throw new IllegalArgumentException
                    ("fra(" + fra + ") > til(" + til + ") - illegalt intervall!");
    }

    public Liste<T> subListe(Liste liste, int fra, int til){

        fratilKontroll(antall, fra, til);



        return null;




    }



    public static final class Node<T>{

        private T verdi;
        private Node<T> neste;
        private Node<T> forrige;
        public Node(T verdi, Node<T> neste, Node<T> forrige){

            this.verdi = verdi;
            this.neste = neste;
            this.forrige = forrige;

        }


    }
}
