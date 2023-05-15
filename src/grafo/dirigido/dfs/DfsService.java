package grafo.dirigido.dfs;


import grafo.dirigido.Aresta;
import grafo.dirigido.VertexState;
import grafo.dirigido.Vertice;

import java.util.List;

public class DfsService<T> {
    /*public DfsIterableCollection<T> collection;
    public DfsIterator<T> dfsIterator;

    public DfsService(DfsIterableCollection<T> collection, DfsIterator<T> dfsIterator) {
        this.collection = collection;
        this.dfsIterator = dfsIterator;
    }

    public void DFS(T source){
        Vertice<T> u = null;


        if ((u = this.dfsIterator.getVertice(source)) == null) {
            System.err.println("vertice nao encontrado em runDFS()");
            return;
        }

        this.dfsIterator.setAllVerticesUnvisited();

        runDFS( u );
    }


    private void runDFS(Vertice<T> u) {
        Vertice<T> w = null;
        List<Aresta<T>> uAdjacentes = null;
        if(!this.dfsIterator.hasNext())
            return;

        // u.setStatus(VertexState.Visited);
        //System.out.print("\t" + u.toString() + "\n");

        uAdjacentes = u.getAdj();
        this.dfsIterator.pilha.push(u);
        if(this.dfsIterator.hasNext()){
           this.dfsIterator.getNext();
        }
        /*for(Aresta<T> arco: uAdjacentes){
            this.dfsIterator.pilha.push(arco.getDestino());
            w = this.dfsIterator.getNext();

            if( w.getStatus() == VertexState.Unvisited )
                runDFS(w);
        }
    }
    */
}
