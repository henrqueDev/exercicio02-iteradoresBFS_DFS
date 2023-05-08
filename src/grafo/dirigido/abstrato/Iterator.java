package grafo.dirigido.abstrato;

import grafo.dirigido.Vertice;

public interface Iterator<T> {
    boolean hasNext();
    Vertice<T> getNext();
    void reset();
}
