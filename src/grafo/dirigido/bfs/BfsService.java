package grafo.dirigido.bfs;

import grafo.dirigido.VertexState;
import grafo.dirigido.Vertice;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BfsService<T> {
    /*public BfsIterableCollection<T> collection;
    public BfsIterator<T> iterador;

    public BfsService(BfsIterableCollection<T> collection, BfsIterator<T> iterador) {
        this.collection = collection;
        this.iterador = iterador;
    }

    public void percorrerBFS(T source) {
        //Queue<Vertice<T>> q = new LinkedList<Vertice<T>>();
        // List<Vertice<T>> uAdjacentes = null;

        Vertice<T> v = this.iterador.getVertice(source);

        if (!this.iterador.exists(v))
            return;
        // Marcando todos os n�s como NAO-VISITADOS
        this.iterador.setAllVerticesUnvisited();

        v.setStatus(VertexState.Visited);
        this.iterador.queue.add(v);
        //this.iterador.showMarked();

        while (this.iterador.hasNext()) {
            Vertice<T> u = this.iterador.getNext();
            System.out.println(u);
        }
    }*/
}

