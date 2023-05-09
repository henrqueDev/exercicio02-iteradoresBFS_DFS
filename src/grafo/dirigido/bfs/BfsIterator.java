package grafo.dirigido.bfs;

import grafo.dirigido.Aresta;
import grafo.dirigido.abstrato.GrafoIterator;
import grafo.dirigido.VertexState;
import grafo.dirigido.Vertice;
import grafo.dirigido.abstrato.Iterator;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BfsIterator<T> extends GrafoIterator implements Iterator {

    /**
     * Implementacao do algoritmo BFS (Busca em Profundidade)
     * OBS: est� considerando um grafo DIRECIONADO.
     *
     * @param *source O vertice inicial
     */


    public Queue<Vertice<T>> queue;
    // public T source;
    public BfsIterator(){
        super();
    }

    public BfsIterator(List <Vertice<T>> vertices , List <Aresta<T>> arestas){
        super(vertices, arestas);
        this.queue = new LinkedList<Vertice<T>>();
    }

    public void BFSDistance( T source ){

        Vertice<T> v = getVertice(source);
        if( !exists( v ) )
            return;

        // Marcando todos os n�s como NAO-VISITADOS
        this.setAllVerticesUnvisited();

        Queue<Vertice<T>> q = new LinkedList<Vertice<T>>();

        v.setStatus(VertexState.Visited);
        v.setDistance(0);
        q.add(v);

        while ( !q.isEmpty()){
            Vertice<T> u = q.remove();

            for(Aresta arco: u.getAdj() ) {
                @SuppressWarnings("unchecked")
                Vertice<T> w = (Vertice<T>) arco.getDestino();

                //hasNext()
                if( w.getStatus() == VertexState.Unvisited ) {
                    w.setStatus(VertexState.Visited);
                    q.add(w);
                    w.setDistance(u.getDistance() + 1);
                }
            }

            u.setStatus(VertexState.Finished);
        }

        System.out.println("Vertice (s)= " + source + "\n");
        this.checkDistanceIsInfinity();


    }

    @Override
     public Vertice<T> getNext(){
        // Vertice<T> u = q.remove();
        List<Vertice<T>> uAdjacentes = null;
        Vertice<T> removed = this.queue.remove();
        System.out.print("\t you" + this.queue.toString() + "\n");
        uAdjacentes = this.incidentes(removed);
        for(Vertice<T> w : uAdjacentes){
            if (w.getStatus() == VertexState.Unvisited) {
                w.setStatus(VertexState.Visited);
                this.queue.add(w);
            }
            this.showMarked();
            System.out.print("\t aim" + this.queue.toString() + "\n");

        }
        this.posicaoAtual++;
        removed.setStatus(VertexState.Finished);
        return removed;
    }
    /*public void BFS(T source) {
        List<Vertice<T>> uAdjacentes = null;

        Vertice<T> v = this.getVertice(source);

        if (!this.exists(v))
            return;
        // Marcando todos os n�s como NAO-VISITADOS
        this.setAllVerticesUnvisited();


        v.setStatus(VertexState.Visited);
        this.queue.add(v);
        this.showMarked();

        while (this.hasNext()) {
            Vertice<T> u = this.getNext();
            System.out.print("\t" + this.queue.toString() + "\n");

            uAdjacentes = this.incidentes(u);

            for (Vertice<T> w : uAdjacentes) {

                if (w.getStatus() == VertexState.Unvisited) {
                    w.setStatus(VertexState.Visited);
                    this.queue.add(w);
                }
                this.showMarked();
                System.out.print("\t" + this.queue.toString() + "\n");
            }
            this.posicaoAtual++;
            u.setStatus(VertexState.Finished);
        }

    }*/

    @Override
    public void reset() {
        this.vertices.clear();
        this.arestas.clear();
        this.posicaoAtual = 0;
        this.queue = new LinkedList<Vertice<T>>();
    }

    @Override
    public boolean hasNext() {
        return !(this.queue.isEmpty());
    }


}
