import javax.swing.text.html.HTMLDocument;
import java.util.Iterator;

public interface Liste<T> extends Beholder<T> {

    public boolean leggInn(T verdi);
    public void leggInn(int i, T verdi);
    public boolean inneholder(T verdi);
    public T hent(int i);
    public int indeksTil(T verdi);
    public T oppdater(int i, T verdi);
    public boolean fjern(T verdi);
    public T fjern(int indeks);
    public int antall();
    public boolean tom();
    public void nullstill();
    public Iterator<T> iterator();

    public default String melding(int indeks){
        return "indeks: " + indeks + ", Antall: " + antall();
    }

    public default void indeksKontroll(int indeks, boolean leggInn){
        if(indeks < 0 ? true : (leggInn ? indeks > antall() : indeks >= antall())){
            throw new IndexOutOfBoundsException(melding(indeks));
        }
    }
}
