package grafo.dirigido.abstrato;

public interface Iterator {
    boolean hasNext();
    Object getNext();
    void reset();
}
