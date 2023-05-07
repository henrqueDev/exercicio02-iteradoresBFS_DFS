package grafo.dirigido.bfs;

import grafo.dirigido.Aresta;
import grafo.dirigido.abstrato.GrafoIterator;
import grafo.dirigido.VertexState;
import grafo.dirigido.Vertice;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BfsIterator<T> extends GrafoIterator {

    /**
     * Implementacao do algoritmo BFS (Busca em Profundidade)
     * OBS: est� considerando um grafo DIRECIONADO.
     *
     * @param *source O vertice inicial
     */



    public BfsIterator(){
        super();
    }

    public BfsIterator(List <Vertice<T>> vertices , List <Aresta<T>> arestas){
        super(vertices, arestas);
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

    public void BFS(T source) {
        Queue<Vertice<T>> q = new LinkedList<Vertice<T>>();
        List<Vertice<T>> uAdjacentes = null;

        Vertice<T> v = getVertice(source);

        if (!exists(v))
            return;
        // Marcando todos os n�s como NAO-VISITADOS
        this.setAllVerticesUnvisited();


        v.setStatus(VertexState.Visited);
        q.add(v);
        showMarked();

        while (!q.isEmpty()) {
            Vertice<T> u = q.remove();
            System.out.print("\t" + q.toString() + "\n");

            uAdjacentes = incidentes(u);

            for (Vertice<T> w : uAdjacentes) {

                if (w.getStatus() == VertexState.Unvisited) {
                    w.setStatus(VertexState.Visited);
                    q.add(w);
                }
                showMarked();
                System.out.print("\t" + q.toString() + "\n");
            }

            u.setStatus(VertexState.Finished);
        }

    }
}
