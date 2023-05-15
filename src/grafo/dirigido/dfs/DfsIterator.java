package grafo.dirigido.dfs;

import grafo.dirigido.Aresta;
import grafo.dirigido.VertexState;
import grafo.dirigido.Vertice;
import grafo.dirigido.abstrato.Grafo;
import grafo.dirigido.abstrato.Iterator;

import java.util.List;
import java.util.Stack;

public class DfsIterator<T> implements Iterator {

    private Grafo<T> grafo;
    private Stack<Vertice<T>> pilha;

    private Vertice<T> source;


    public DfsIterator(){
        super();
        this.pilha = new Stack<>();
    }

    public DfsIterator(Grafo<T> grafo, Vertice<T> source){
        this.grafo = grafo;
        this.source = source;
        this.pilha = new Stack<>();
        this.grafo.setAllVerticesUnvisited();
        this.pilha.push(source);
    }


    @Override
    public boolean hasNext() {
        boolean isNotEmpty = !(this.pilha.isEmpty());
        if(!isNotEmpty){
            this.reset();
        }
        return isNotEmpty;
    }

    @Override
    public Vertice<T> getNext() {
        Vertice<T> w = null;
        List<Aresta<T>> uAdjacentes = null;

        Vertice<T> u = this.pilha.pop();
        u.setStatus(VertexState.Visited);

        uAdjacentes = u.getAdj();

        for(Aresta<T> arco: uAdjacentes){

            w = arco.getDestino();
            if( w.getStatus() == VertexState.Unvisited && !this.pilha.contains(w) ) {
                this.pilha.push(w);
            }
        }


        return u;
    }

    @Override
    public void reset() {
        this.pilha.clear();
        this.grafo.setAllVerticesUnvisited();
    }
}
