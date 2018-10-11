import java.util.*;

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

        hode = null;
        hale = null;

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
    public void leggInn(int i, T verdi) {

        Objects.requireNonNull(verdi, "Null-verdier er ikke lov!");

        indeksKontroll(i, true);
        if(tom()){

            hale = hode = new Node(verdi, null,null);
            antall++;
            endringer++;
            return;

        }


        if(i == indeksTil(hode.verdi)){

            hode.forrige = new Node<>(verdi, hode, null);
            hode = hode.forrige;

            endringer++;
            antall++;
        }
        else if(i == antall) {

            hale.neste  = new Node<>(verdi, null, hale);

            hale = hale.neste;

            antall ++;
            endringer++;

        }
            else {

            Node<T> p = finnNode(i-1);
            Node<T> q = finnNode(i);
            Node<T> newNode;
            newNode = new Node<>(verdi, q, p);
            p.neste = newNode;
            q.forrige = newNode;

            endringer++;
            antall++;
        }






    }

    @Override
    public boolean inneholder(T verdi) {

        return indeksTil(verdi) != -1;
    }

    @Override
    public T hent(int i) {
        return finnNode(i).verdi;
    }

    @Override
    public int indeksTil(T verdi) {

        if(verdi == null) {
            return -1;
        }


        Node<T> currNode = hode;
        int currNodeIndex = 0;
        while(currNode != null){

            if(currNode.verdi.equals(verdi)){
                return currNodeIndex;
            }

            currNode = currNode.neste;
            currNodeIndex++;

        }



        return -1;
    }

    @Override
    public T oppdater(int i, T verdi) {

        Objects.requireNonNull(verdi);
        T gml = hent(i);

        finnNode(i).verdi = verdi;

        endringer++;

        return gml;



    }

    @Override
    public boolean fjern(T verdi) {


        if(tom()) {
            return false;
        }

        if(antall == 1 && hode.verdi.equals(verdi)){

           hode = null;
           hale = null;

           antall--;
           endringer++;
           return true;

        }
        // Dersom man fjerner første verdi

        if(verdi.equals(hode.verdi)){


            hode = hode.neste;
            hode.forrige = null;

            antall--;
            endringer++;
            return true;


        }

        //Leter etter noden

        Node<T> currNode = hode.neste;

        while(currNode != null){

            if(currNode.verdi.equals(verdi)){

                //Dersom det er den siste noden som skal fjernes
                if(currNode == hale){

                    hale = hale.forrige;
                    hale.neste = null;

                    antall--;
                    endringer++;
                    return true;

                }

                //Ellers
                Node<T> v = currNode.forrige;
                Node<T> h = currNode.neste;

                v.neste = h;
                h.forrige = v;

                currNode.neste = null;
                currNode.forrige = null;

                antall--;
                endringer++;
                return true;

            }

            currNode = currNode.neste;


        }

        return false;

    }


    @Override
    public T fjern(int indeks) {

        indeksKontroll(indeks, false);


        //Itelfelle der Listen blir tom etter fjerning

        if(antall == 1 && indeks == 0){

            T gml = hode.verdi;

            hode = null;
            hale = null;

            antall--;

            endringer++;
            return gml;

        }


        //Om vi skal fjerne første node

        if(indeks == 0){

            T gml = hode.verdi;

            hode = hode.neste;
            hode.forrige = null;

            antall--;
            endringer++;
            return gml;
        }

        //Om vi skal fjerne den siste

        if(indeks == antall-1){

            T gml = hale.verdi;
            hale = hale.forrige;
            hale.neste = null;

            antall--;
            endringer++;
            return gml;

        }


        int teller;


        Node<T> currNode;

        //Tester om vi skal søke fra bakerst eller forrerst i lista



        currNode = hode.neste;

        for(teller = 1; currNode != null; teller++) {

            if (teller == indeks) {
                T gml = currNode.verdi;

                Node<T> v = currNode.forrige;
                Node<T> h = currNode.neste;

                v.neste = h;
                h.forrige = v;

                currNode.neste = null;
                currNode.forrige = null;
                currNode = null;


                antall--;
                endringer++;
                return gml;

        }

            currNode = currNode.neste;

        }

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


    /*public void nullstill1() {



        if(antall == 0){
            return;
        }

        while(hode != hale) {

            hode = hode.neste;
            hode.forrige = null;
            antall--;

        }

        hode = null;
        hale = null;
        antall--;







    }*/

    @Override
    public void nullstill() {



        while(antall > 0){

            fjern(0);

        }


    }

    @Override
    public Iterator<T> iterator() {
        return new DobbeltLenketListeIterator();
    }

    public Iterator<T> iterator(int indeks){

        return new DobbeltLenketListeIterator(indeks);
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


        StringBuilder stringBuilder = new StringBuilder("[");

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
                teller++;
            }

            return currNode;
        } else{

            currNode = hale;
            teller = antall-1;
            while (teller > indeks){
                currNode = currNode.forrige;
                teller--;
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

    public Liste<T> subliste(int fra, int til){


        fratilKontroll(antall, fra, til);
        Liste<T> sub = new DobbeltLenketListe<>();

        if(fra == til){
            return sub;
        }




        for(int i = fra; i < til; i++){

            sub.leggInn(finnNode(i).verdi);

        }

        /*while (currNode != lastNode.neste){
            sub.leggInn(currNode.verdi);
            currNode = currNode.neste;
        }*/



        return sub;


    }


    private class DobbeltLenketListeIterator implements Iterator<T>
    {
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;
        private DobbeltLenketListeIterator()
        {
            denne = hode; // denne starter på den første i listen
            fjernOK = false; // blir sann når next() kalles
            iteratorendringer = endringer; // teller endringer
        }

        private DobbeltLenketListeIterator(int indeks)
        {
            denne = finnNode(indeks);
            fjernOK = false;
            iteratorendringer = endringer;
        }
        @Override
        public boolean hasNext()
        {
            return denne != null; // denne koden skal ikke endres!
        }
        @Override
        public T next()
        {

            if(iteratorendringer != endringer){
                throw new ConcurrentModificationException("ikke ok");
            }

            if(!hasNext()){
                throw new NoSuchElementException("Det finnes ingen neste");
            }

            fjernOK = true;

            T returVerdi = denne.verdi;

            denne = denne.neste;

            return returVerdi;
        }
     /*   @Override
        public void remove()
        {

            if(!fjernOK){
                throw new IllegalStateException("remove kan ikke kalles nå");
            }

            if(endringer != iteratorendringer){
                throw new ConcurrentModificationException("endringer er ulik iteratorendringer.");

            }


            if(hode.neste == denne){

                 hode = hode.neste;

                 hode.forrige = null;

                 //Dette var den eneste noden.
                 if(denne == null){
                     hale = null;
                 } else{

                     Node<T> f = hode;

                 }

                 antall--;
                 endringer++;
                 iteratorendringer++;

            } else if(denne == hale){

                    denne = hale = hale.forrige;

                    denne.neste = null;
                    hode.neste = null;

                    antall--;
                    endringer++;
                    iteratorendringer++;
                } else{

                Node<T> v = denne.forrige;
                Node<T> h = denne.neste;

                v.neste = h;
                h.forrige = v;


                antall--;
                endringer++;
                iteratorendringer++;




            }*/


        @Override
        public void remove() {


            if(!fjernOK){
                throw new IllegalStateException("remove kan ikke kalles nå");
            }

            if(endringer != iteratorendringer){
                throw new ConcurrentModificationException("endringer er ulik iteratorendringer.");

            }

            fjernOK = false;



            if(antall == 1){
                hode = null;
                hale = null;


            }



            //hjelpeNode

            else if(hode.neste == denne){

                hode = hode.neste;
                hode.forrige = null;





                //Dersom denne var eneste noden i listen

                if(denne == null){
                    hale = null;
                }


            }else if (denne == null) {
                    hale = hale.forrige;
                    hale.neste = null;




                }
            else{
                //Leter etter forgjenger f til denne

                Node<T> f = hode;
                while (f.neste.neste != denne) {

                    f = f.neste;
                }

                //q skal fjernes
                f.neste = denne;
                denne.forrige = f;


              /*  q.verdi = null;
                q.neste = null;
                q.forrige = null;
*/


            }
                antall--;
                endringer++;
                iteratorendringer++;


                }

    }

    public static <T> void sorter (Liste<T> liste, Comparator<? super T> c){


        if(liste.tom()){
            return;
        }

        if(liste.antall() == 1){
            return;
        }
        int bytter = -1;

        int teller = 0;

        int sluttIndeks = liste.antall()-1;

        T v = liste.hent(teller);
        T h = liste.hent(teller +1);


        while(bytter != 0){

            bytter = 0;
            for(teller = 0; teller < sluttIndeks; teller++) {


                v = liste.hent(teller);
                h = liste.hent(teller + 1);
                if (c.compare(v, h) > 0) {

                    liste.oppdater(teller, h);
                    liste.oppdater(teller + 1, v);

                    bytter++;
                }



            }
            sluttIndeks--;

        }


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
