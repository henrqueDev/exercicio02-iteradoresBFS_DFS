package grafo.dirigido.bfs;

import grafo.dirigido.abstrato.Grafo;
import grafo.dirigido.VertexState;
import grafo.dirigido.Vertice;
import grafo.dirigido.abstrato.Iterator;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BfsIterator<T> implements Iterator {

    /**
     * Implementacao do algoritmo BFS (Busca em Profundidade)
     * OBS: est� considerando um grafo DIRECIONADO.
     *
     * @param *source O vertice inicial
     */


    private Queue<Vertice<T>> queue;
    // public T source;
    private Grafo<T> grafo;

    private Vertice<T> source;


    public BfsIterator(Grafo<T> grafo, Vertice<T> source) {
        this.grafo = grafo;
        this.source = source;
        this.queue = new LinkedList<Vertice<T>>();
        this.grafo.setAllVerticesUnvisited();
        this.queue.add(this.source);
    }


    @Override
     public Vertice<T> getNext(){
        List<Vertice<T>> uAdjacentes = null;
        Vertice<T> removed = this.queue.remove();
        removed.setStatus(VertexState.Visited);
        uAdjacentes = this.grafo.incidentes(removed);
        for(Vertice<T> w : uAdjacentes){
            if (w.getStatus() == VertexState.Unvisited) {
                w.setStatus(VertexState.Visited);
                this.queue.add(w);
            }

        }
        removed.setStatus(VertexState.Finished);
        return removed;
    }

    @Override
    public void reset() {
        this.queue = new LinkedList<Vertice<T>>();
        this.grafo.setAllVerticesUnvisited();
    }

    @Override
    public boolean hasNext() {
        boolean isNotEmpty = !(this.queue.isEmpty());
        if(!isNotEmpty){
            this.reset();
        }
        return isNotEmpty;
    }


}
